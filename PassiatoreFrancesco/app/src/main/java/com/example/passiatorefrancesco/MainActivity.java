package com.example.passiatorefrancesco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public ListView lvResoconto;
    ArrayList<Elemento> elenco = new ArrayList<>();
    CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lvResoconto = findViewById(R.id.listViewResoconto);

        Log.d("DEBUG", "SimpleListView create: listView=" + lvResoconto);

        customAdapter = new CustomAdapter(this, R.layout.list_element, elenco);

        lvResoconto.setAdapter(customAdapter);

    }

    public void lanciaActivity(View v) {
        Intent i = new Intent(this, ActivitySecondaria.class);

        i.putExtra("Descrizione", "");
        i.putExtra("Quantita", "");

        startActivityForResult(i, 99);

    }

    public void aggiornaElenco()
    {
        lvResoconto.setAdapter(customAdapter);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 99 && resultCode == RESULT_OK) {
            String d = data.getStringExtra("Descrizione");
            int q = data.getIntExtra("Quantita", 1);

            CustomAdapter customAdapter = new CustomAdapter(this, R.layout.list_element, elenco);

            lvResoconto.setAdapter(customAdapter);

            for(int i=0; i<elenco.size(); i++)
            {
                if(elenco.get(i).getDescrizione().equals(d) && q != 0)
                {
                    elenco.get(i).setQuantita(q);
                    aggiornaElenco();
                    return;
                }
                else if (elenco.get(i).getDescrizione().equals(d) && q == 0)
                {
                    elenco.remove(i);
                    aggiornaElenco();
                    return;
                }
            }

            Elemento e = new Elemento(d, q);
            elenco.add(e);
            aggiornaElenco();

        }
    }

}