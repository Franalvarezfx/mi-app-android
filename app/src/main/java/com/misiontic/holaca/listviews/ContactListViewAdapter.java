package com.misiontic.holaca.listviews;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.misiontic.holaca.R;
import com.misiontic.holaca.model.Persona;

import java.util.ArrayList;


public class ContactListViewAdapter extends ArrayAdapter<Persona> { // Cambiado

    ArrayList<Persona> list; // Cambiado
    Context context;

    public ContactListViewAdapter(Context context, ArrayList<Persona> items) { // Cambiado
        super(context, R.layout.contact_list_row, items);
        this.context = context;
        list = items;
    }

    public View getView (int position, @Nullable View convertView, @NonNull ViewGroup parent){

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.contact_list_row, null);
        }

        TextView tvContactName = convertView.findViewById(R.id.tvContactName);
        TextView tvPhone = convertView.findViewById(R.id.tvContactPhone);

        String fullName = list.get(position).getNombres() + " " + list.get(position).getApellidos();
        tvContactName.setText(fullName);

        tvPhone.setText(list.get(position).getTelefono());

        return  convertView;
    }

}
