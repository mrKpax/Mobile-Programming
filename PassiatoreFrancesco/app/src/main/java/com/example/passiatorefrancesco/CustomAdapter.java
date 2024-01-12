package com.example.passiatorefrancesco;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CustomAdapter extends ArrayAdapter<Elemento>
{
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resourceId, List<Elemento> objects)
    {
        super(context, resourceId, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        if (v == null)
        {
            v = inflater.inflate(R.layout.list_element, null);
        }

        Elemento e = getItem(position);

        TextView tvDes;
        TextView tvQt;

        tvDes = v.findViewById(R.id.tvDescrizione);
        tvQt = v.findViewById(R.id.tvQuantita);

        tvDes.setText(e.getDescrizione());
        tvQt.setText(String.valueOf(e.getQuantita()));

        return v;
    }

}
