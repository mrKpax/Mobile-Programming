package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    public ListView simpleListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Simple List View
        String [] array = {"Pasquale","Maria","Michele","Antonella", "Vincenzo",
                "Teresa", "Roberto", "Rossella", "Antonio", "Luca", "Liliana", "Stefania",
                "Francesca", "Andrea", "Marco", "Elisa", "Anna", "Lorenzo"};

        simpleListView = findViewById(R.id.simpleListView);

        Log.d("DEBUG", "SimpleListView create: listView=" + simpleListView);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, R.layout.list_element, R.id.textViewList, array);

        simpleListView.setAdapter(arrayAdapter);

        Log.d("DEBUG", "Done!");

        simpleListView.setOnItemClickListener(new OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String str = simpleListView.getItemAtPosition(position).toString();

                Toast.makeText(getApplicationContext(),
                        "Click su posizione n." +position+": " +str, Toast.LENGTH_LONG).show();
            }
        });
    }


}