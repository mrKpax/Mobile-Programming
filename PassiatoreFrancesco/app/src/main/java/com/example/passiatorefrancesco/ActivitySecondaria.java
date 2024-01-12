package com.example.passiatorefrancesco;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class ActivitySecondaria extends AppCompatActivity {

    EditText etDescrizione;
    EditText etQuantita;
    String descrizione;
    int quantita;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);

        etDescrizione = findViewById(R.id.etDescrizione);
        etQuantita = findViewById(R.id.etQuantita);

        Intent intent = getIntent();

        descrizione = intent.getStringExtra("Descrizione");
        quantita = intent.getIntExtra("Quantita", 0);

    }

    public void inserisciClicked(View v) {

        Intent i = new Intent();

        descrizione = String.valueOf(etDescrizione.getText());
        quantita = Integer.parseInt(etQuantita.getText().toString());

        i.putExtra("Descrizione", descrizione);
        i.putExtra("Quantita", quantita);

        setResult(RESULT_OK, i);
        finish();

    }

}
