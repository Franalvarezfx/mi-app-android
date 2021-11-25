package com.misiontic.holaca;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.misiontic.holaca.db.MySQLiteHelper;
import com.misiontic.holaca.listviews.ContactListViewAdapter;
import com.misiontic.holaca.model.Persona;

import java.util.ArrayList;

public class ScrollActivity extends AppCompatActivity {

    private TextView tvContactName;

    // lv
    private ArrayList<Persona> contactList; // Cambiado
    private static ListView listView;
    private static ContactListViewAdapter adapter;
    //


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll);

        tvContactName = findViewById(R.id.tvContactName);

        //lv
        contactList = new ArrayList<>();
        listView = findViewById(R.id.contactList);
        //

        showContacts();
    }

    public void showContacts(){
        int id;
        String nombre;
        String apellidos;
        String direccion;
        String telefono;
        String fecha_nacimiento;

        MySQLiteHelper conexion_bd = new MySQLiteHelper(this);
        String sentence = "SELECT * FROM personas";
        // String[] params = new String[]{"Camilo"};
        Cursor resultados = conexion_bd.getData(sentence, null);

        try {

            resultados.moveToFirst();
            do {
                id = resultados.getInt(0);
                nombre = resultados.getString(1);
                apellidos = resultados.getString(2);
                direccion = resultados.getString(3);
                telefono = resultados.getString(4);
                fecha_nacimiento = resultados.getString(5);

                Persona nuevoContacto = new Persona(nombre, apellidos, direccion, telefono, fecha_nacimiento, null);
                contactList.add(nuevoContacto);

            } while (resultados.moveToNext());

            // tv
            adapter = new ContactListViewAdapter(this, contactList);
            listView.setAdapter(adapter);
            //

        } catch (Exception e) {
            Toast.makeText(this, "Error al realizar la consulta", Toast.LENGTH_LONG).show();
        }
        finally {
            resultados.close();
        }
    }

}