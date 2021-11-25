package com.misiontic.holaca;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;

import com.misiontic.holaca.db.MySQLiteHelper;

public class ContactUpdateActivity extends AppCompatActivity {

    private EditText etName;
    private EditText etLastname;
    private EditText etAddress;
    private EditText etPhone;
    private EditText etBirthday;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_update);

        int personId = getIntent().getIntExtra("person", 0);

        etName = findViewById(R.id.etUpdatePersonName);
        etLastname = findViewById(R.id.etUpdateLastname);
        etAddress = findViewById(R.id.etUpdateAddress);
        etPhone =findViewById(R.id.etUpdateTelephone);
        etBirthday = findViewById(R.id.etUpdateBirthday);

        cargarContacto(personId);

    }

    public void cargarContacto(int personId) {
        MySQLiteHelper conexion_bd = new MySQLiteHelper(this);
        String sentence = "SELECT * FROM personas WHERE _id = ?";
        String[] params = new String[]{String.valueOf(personId)};
        Cursor resultados = conexion_bd.getData(sentence, params);

        resultados.moveToFirst();
        etName.setText(resultados.getString(1));
        etLastname.setText(resultados.getString(2));
        etAddress.setText(resultados.getString(3));
        etPhone.setText(resultados.getString(4));
        etBirthday.setText(resultados.getString(5));
    }

}