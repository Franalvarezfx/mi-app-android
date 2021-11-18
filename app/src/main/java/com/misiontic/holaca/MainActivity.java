package com.misiontic.holaca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText etName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void goToCalculator(View view) {
        Intent calculator = new Intent(this, CalculatorActivity.class);
        // Intent calculator = new Intent(this, ScrollActivity.class);

        etName = (EditText) findViewById(R.id.etPersonName);
        String nombre = etName.getText().toString();

        calculator.putExtra("user", nombre);

        startActivity(calculator);
    }

    public void goToContacts(View view) {
        Intent intentContacts = new Intent(this, ScrollActivity.class);
        startActivity(intentContacts);
    }

}