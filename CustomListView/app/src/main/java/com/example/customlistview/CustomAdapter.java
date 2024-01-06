package com.example.customlistview;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class CustomAdapter extends ArrayAdapter<Contatto>
{
    private LayoutInflater inflater;

    public CustomAdapter(Context context, int resourceId, List<Contatto> objects)
    {
        super(context, resourceId, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent)
    {
        if (v == null)
        {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(R.layout.list_element, null);
        }

        Contatto c = getItem(position);

        Log.d("DEBUG","contact c="+c);

        TextView nameTextView;
        TextView telTextView;
        ImageView fotoImageView;

        nameTextView = v.findViewById(R.id.elem_lista_nome);

        Log.d("DEBUG","nameTextView="+nameTextView);

        telTextView = v.findViewById(R.id.elem_lista_telefono);
        fotoImageView = v.findViewById(R.id.elem_lista_foto);

        fotoImageView.setImageDrawable(c.getPicture());
        nameTextView.setText(c.getName());
        telTextView.setText(c.getTel());

        return v;
    }
}
