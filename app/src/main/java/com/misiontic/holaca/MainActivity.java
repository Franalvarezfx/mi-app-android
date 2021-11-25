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

    public void goToWelcome(View view) {

        Intent welcomeIntent = new Intent(this, WelcomeActivity.class);

        etName = (EditText) findViewById(R.id.etPersonName);
        String nombre = etName.getText().toString();

        welcomeIntent.putExtra("user", nombre);

        //SP
        SharedPreferences.Editor editor = settings.edit();
        editor.putInt("id",1);
        editor.putString("usuario", nombre);
        editor.commit();
        //

        startActivity(welcomeIntent);
    }

}