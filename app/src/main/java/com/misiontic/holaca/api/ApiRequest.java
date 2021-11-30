package com.misiontic.holaca.api;

import android.content.Context;
import android.os.AsyncTask;
import android.util.JsonReader;
import android.widget.Toast;


import com.misiontic.holaca.model.Producto;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class ApiRequest {

    public ArrayList<Producto> consultarProductos(Context context) {
        ArrayList<Producto> productoList = new ArrayList<Producto>();
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {

                URL pizzaEndpoint = null;
                try {
                    // Create URL
                    pizzaEndpoint = new URL("https://tienda-ca-api.herokuapp.com/api/pizzeria/productos");

                    //Create connection
                    HttpsURLConnection conexion = (HttpsURLConnection) pizzaEndpoint.openConnection();

                    // Headers
                    conexion.setRequestProperty("Dato", "Hola");

                    if (conexion.getResponseCode() == 200) {
                        InputStream responseBody = conexion.getInputStream();
                        InputStreamReader responseBodyReader = new InputStreamReader(responseBody, StandardCharsets.UTF_8);

                        JsonReader reader = new JsonReader(responseBodyReader);

                        String id = "";
                        String codigo = "";
                        String nombreProducto = "";
                        String desc_corta = "";
                        String desc_larga = "";
                        Double precio = 0.0;


                        reader.beginArray();
                        while (reader.hasNext()) {
                            reader.beginObject();
                            while (reader.hasNext()) {
                                String key = reader.nextName();
                                if (key.equals("nombre")) {
                                    nombreProducto = reader.nextString();
                                } else if (key.equals("codigo")) {
                                    codigo = reader.nextString();
                                } else if (key.equals("desc_corta")) {
                                    desc_corta = reader.nextString();
                                } else if (key.equals("desc_larga")) {
                                    desc_larga = reader.nextString();
                                } else if (key.equals("precio")) {
                                    precio = reader.nextDouble();
                                } else if (key.equals("_id")) {
                                    id = reader.nextString();
                                } else {
                                    reader.skipValue();
                                }
                            }
                            reader.endObject();
                            productoList.add(new Producto(id, codigo, nombreProducto, desc_corta, desc_larga,precio));
                        }
                        reader.endArray();

                    } else {
                        Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });
        return productoList;
    }


}
