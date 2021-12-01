package com.misiontic.holaca;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.misiontic.holaca.db.MySQLiteHelper;
import com.misiontic.holaca.model.Usuario;

public class MainActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etPassword;

    SharedPreferences settings; //SP

    private boolean success = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences("id", Context.MODE_PRIVATE); //SP

        // pruebaLectura(); // FB
    }

    public void goToWelcome(View view) {

        Intent welcomeIntent = new Intent(this, WelcomeActivity.class);

        etName = (EditText) findViewById(R.id.etPersonName);
        String nombre = etName.getText().toString();

        // Nuevo
        etPassword = findViewById(R.id.etPassword);
        String contrasena = etPassword.getText().toString();

        welcomeIntent.putExtra("user", nombre);

        // SP
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("id", 1);
        editor.putString("usuario", nombre);
        editor.commit();
        //

        checkUser(nombre, contrasena, welcomeIntent);
    }

    public void goToNewUser(View view) {
        Intent newUserIntent = new Intent(this, NewUserActivity.class);
        startActivity(newUserIntent);
    }

    public void checkUser(String nombre, String contrasena, Intent intentMain) { //FB

        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuarios");
        myRef.child("u_" + nombre).addValueEventListener(new ValueEventListener(){

            @Override
            public void onDataChange(DataSnapshot snapshot) {
                Usuario value = snapshot.getValue(Usuario.class);
                if(value != null) {
                    String saved_password = value.getContrasena();
                    if (saved_password.equals(contrasena)) {
                        startActivity(intentMain);
                    } else {
                        Toast.makeText(MainActivity.this, "Usuario y/o contraseña no válidos", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Usuario y/o contraseña no válido", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                Toast.makeText(MainActivity.this, "Failed to read value." + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });
    }




    public void pruebaLectura() { //FB
        // Read from the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("usuarios");

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Usuario value = dataSnapshot.getValue(Usuario.class);
                Toast.makeText(MainActivity.this, "Value is: " + value.getNombre(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Toast.makeText(MainActivity.this, "Failed to read value." + error.toException(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}