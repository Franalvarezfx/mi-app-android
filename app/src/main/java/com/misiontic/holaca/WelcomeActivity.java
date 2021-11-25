package com.misiontic.holaca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        String nombre = getIntent().getStringExtra("user");

        Toast.makeText(this, "Hola " + nombre, Toast.LENGTH_SHORT).show();
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
        } else if (id == R.id.item_calculadora) {
            goToCalculator();
        } else if (id == R.id.item_salir) {
            goToLogin();
        }
        return super.onOptionsItemSelected(item);
    }


    public void goToCalculator() {
        Intent calculator = new Intent(this, CalculatorActivity.class);
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

    public void goToLogin() {
        Intent login = new Intent(this, MainActivity.class);
        startActivity(login);
    }

}