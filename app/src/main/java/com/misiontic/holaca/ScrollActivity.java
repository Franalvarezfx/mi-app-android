package com.misiontic.holaca;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.Toast;

import com.misiontic.holaca.db.MySQLiteHelper;

public class ScrollActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        showContacts();
    }

    public void showContacts(){
        int id;
        String nombre;
        String apellidos;

        MySQLiteHelper conexion_bd = new MySQLiteHelper(this);
        String sentence = "SELECT * FROM personas";
        // String[] params = new String[]{"Camilo"};
        Cursor resultados = conexion_bd.getData(sentence, null);
        String cadena = "";
        try {

            resultados.moveToFirst();
            do {
                id = resultados.getInt(0);
                nombre = resultados.getString(1);
                apellidos = resultados.getString(2);
                cadena = cadena.concat(id + " - " + nombre + " " + apellidos + "\n");
            } while (resultados.moveToNext());

            Toast.makeText(this, cadena, Toast.LENGTH_LONG).show();
        } catch (Exception e) {
            Toast.makeText(this, "Error al realizar la consulta", Toast.LENGTH_LONG).show();
        }
        finally {
            resultados.close();
        }
    }

}