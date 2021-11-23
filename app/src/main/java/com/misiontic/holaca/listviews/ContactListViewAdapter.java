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

import java.util.ArrayList;


public class ContactListViewAdapter extends ArrayAdapter<String> {

    ArrayList<String> list;
    Context context;

    public ContactListViewAdapter(Context context, ArrayList<String> items) {
        super(context, R.layout.contact_list_row, items);
        this.context = context;
        list = items;
    }

    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.contact_list_row, null);

            TextView tvContactName = convertView.findViewById(R.id.tvContactName);
            ImageView ivContact = convertView.findViewById(R.id.ivContact);

            tvContactName.setText(list.get(position));
        }
        return convertView;
    }

}
