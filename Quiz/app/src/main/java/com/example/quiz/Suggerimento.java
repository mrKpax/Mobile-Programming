package com.example.quiz;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Suggerimento extends AppCompatActivity
{
    private boolean risposta;
    private String domanda;
    TextView tvDomanda;
    TextView tvRisposta;
    private boolean risposta_vista = false;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.suggerimento);

        tvDomanda = findViewById(R.id.tvDomanda);
        tvRisposta = findViewById(R.id.tvRisposta);

        Intent i = getIntent();
        risposta = i.getBooleanExtra("RISPOSTA", false);
        domanda = i.getStringExtra("DOMANDA");

        tvDomanda.setText("Vuoi visualizzare la risposta di: " + domanda + "?");
    }

    private void aggiorna()
    {
        Intent i = new Intent();
        i.putExtra("SUGGERIMENTO_VISTO", risposta_vista);
        setResult(RESULT_OK, i);
    }

    public void siClicked (View v)
    {
        tvRisposta.setText("La risposta è " + risposta);
        risposta_vista = true;
        aggiorna();
    }

    public void noClicked (View v)
    {
        onBackPressed();

    }

}
