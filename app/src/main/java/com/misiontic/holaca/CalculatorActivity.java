package com.misiontic.holaca;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorActivity extends AppCompatActivity {

    private EditText etNumberOne;
    private EditText etNumberTwo;
    private TextView tvResult;

    SharedPreferences settings; //SP

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        settings = getSharedPreferences("id", Context.MODE_PRIVATE); //SP
    }

    public void calcularPromedio(View view) {
        etNumberOne = (EditText) findViewById(R.id.etNumberOne);
        etNumberTwo = (EditText) findViewById(R.id.etNumberTwo);
        double numberOne = Double.parseDouble(etNumberOne.getText().toString());
        double numberTwo = Double.parseDouble(etNumberTwo.getText().toString());

        double average = (numberOne + numberTwo) / 2;

        tvResult = (TextView)findViewById(R.id.tvResult);

        Resources res = getResources();
        String text = String.format(res.getString(R.string.avg_equals), String.valueOf(average));
        tvResult.setText(text);

        //SP
        int id = settings.getInt("id",0);
        String usuario = settings.getString("usuario", "");
        Toast.makeText(this, "Promedio calculado para usuario: " + id + "-" + usuario, Toast.LENGTH_SHORT).show();
        //
    }

}