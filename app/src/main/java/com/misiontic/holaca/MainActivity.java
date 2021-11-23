package com.misiontic.holaca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.misiontic.holaca.db.MySQLiteHelper;

public class MainActivity extends AppCompatActivity {

    private EditText etName;

    SharedPreferences settings; //SP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        settings = getSharedPreferences("id", Context.MODE_PRIVATE); //SP
    }

    // Método para generar el menú
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    // Método para asignar funciones al menú
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.item_contactos) {
            goToContacts();
        } else if (id == R.id.item_pedidos) {
            goToOrders();
        } else if (id == R.id.item_formulario) {
            goToPersonForm();
        }
        return super.onOptionsItemSelected(item);
    }


    public void goToCalculator(View view) {

        //BD
        /*
        MySQLiteHelper conexion_bd = new MySQLiteHelper(this);
        conexion_bd.insertData("INSERT INTO people (firstname, lastname, email) VALUES('Maria', 'Avila', 'majo@correo.com')");
        */
        //

        Intent calculator = new Intent(this, CalculatorActivity.class);
        // Intent calculator = new Intent(this, ScrollActivity.class);

        etName = (EditText) findViewById(R.id.etPersonName);
        String nombre = etName.getText().toString();

        calculator.putExtra("user", nombre);

        //SP
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("id",1);
        editor.putString("usuario", nombre);
        editor.commit();
        //

        startActivity(calculator);
    }

    public void goToContacts() {
        Intent intentContacts = new Intent(this, ScrollActivity.class);
        startActivity(intentContacts);
    }

    public void goToOrders() {
        Intent intentOrder = new Intent(this, OrderActivity.class);
        startActivity(intentOrder);
    }

    public void goToPersonForm() {
        Intent intentForm = new Intent(this, PersonFormActivity.class);
        startActivity(intentForm);
    }


}