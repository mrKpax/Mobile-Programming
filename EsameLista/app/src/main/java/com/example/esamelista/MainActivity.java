package com.example.esamelista;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView1, listView2;
    Switch selettore;
    EditText nome;
    ArrayList<String> array1, array2;
    ArrayAdapter<String> arrayAdapter1, arrayAdapter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        array1 = new ArrayList<>();
        array1.add("Francesco");
        array1.add("Michele");
        array1.add("Pasquale");
        array2 = new ArrayList<>();
        array2.add("Sara");
        array2.add("Anna");
        array2.add("Giulia");

        selettore = findViewById(R.id.selettore);
        nome = findViewById(R.id.nome);

        listView1 = findViewById(R.id.lista1);
        listView2 = findViewById(R.id.lista2);

        arrayAdapter1 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, array1);
        arrayAdapter2 = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1, array2);

        listView1.setAdapter(arrayAdapter1);
        listView2.setAdapter(arrayAdapter2);

        listView1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = listView1.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(), "Click su posizione n." + position + ": " + str, Toast.LENGTH_SHORT).show();
                array1.remove(position);
                if (selettore.isChecked()) {
                    array2.add(str);
                    arrayAdapter2.notifyDataSetChanged();
                }
                arrayAdapter1.notifyDataSetChanged();
            }
        });

        listView2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String str = listView2.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(), "Click su posizione n." + position + ": " + str, Toast.LENGTH_SHORT).show();
                array2.remove(position);
                if (selettore.isChecked()) {
                    array1.add(str);
                    arrayAdapter1.notifyDataSetChanged();
                }
                arrayAdapter2.notifyDataSetChanged();
            }
        });
    }

    public void inserisciNome(View v)
    {
        String str = nome.getText().toString();
        if (str.length() > 0)
        {
            array1.add(str);
            arrayAdapter1.notifyDataSetChanged();
        }
    }
}