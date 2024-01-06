package com.example.quiz;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
{
    private TextView tvNumeroDomanda;
    private TextView tvDomanda;
    private TextView tvRisposteTotali;
    private TextView tvRisposteCorretteValide;
    private TextView tvRisposteCorretteNonValide;
    private int quesito_corrente = 0;
    private int NUM_QUESITI = 6;
    private int risposteTotali = 0;
    private int risposteCorretteValide = 0 ;
    private int risposteCorretteNonValide = 0;

    private Quesito[] arrayQuesiti = new Quesito[] {
            new Quesito("Il risultato di 1+1 è 2", true),
            new Quesito("Il risultato di 1+1 è 3", false),
            new Quesito("Il risultato di 2+2 è 4", true),
            new Quesito("Il risultato di 2+2 è 5", false),
            new Quesito("Il risultato di 3+3 è 6", true),
            new Quesito("Il risultato di 3+3 è 7", false)
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvDomanda = findViewById(R.id.tvDomanda);
        tvNumeroDomanda = findViewById(R.id.tvNumeroQuesito);
        tvRisposteTotali = findViewById(R.id.tvRisposteTotali);
        tvRisposteCorretteValide = findViewById(R.id.tvRisposteCorretteValide);
        tvRisposteCorretteNonValide = findViewById(R.id.tvRisposteCorretteNonValide);

        tvNumeroDomanda.setText("Domanda numero 1");
        tvDomanda.setText(arrayQuesiti[0].getTesto());
    }

    public void aggiorna()
    {
        tvDomanda.setText(arrayQuesiti[quesito_corrente].getTesto());
        tvNumeroDomanda.setText("Domanda numero " + (quesito_corrente+1));
        tvRisposteTotali.setText("Risposte totali: " + risposteTotali);
        tvRisposteCorretteValide.setText("Risposte corrette valide: " + risposteCorretteValide);
        tvRisposteCorretteNonValide.setText("Risposte corrette non valide: " + risposteCorretteNonValide);
    }

    public void resetClicked(View v)
    {
        risposteTotali = 0;
        risposteCorretteValide = 0 ;
        risposteCorretteNonValide = 0;

        quesito_corrente = 0;

        for (int i = 0; i < arrayQuesiti.length; i++)
        {
            arrayQuesiti[i].setRisposta_counted(false);
            arrayQuesiti[i].setSugg_visto(false);
        }

        aggiorna();
    }

    public void precedenteClicked(View v)
    {
        quesito_corrente--;
        if(quesito_corrente < 0) quesito_corrente = NUM_QUESITI-1;
        aggiorna();
    }

    public void successivoClicked(View v)
    {
        quesito_corrente++;
        if(quesito_corrente > NUM_QUESITI-1) quesito_corrente = 0;
        aggiorna();
    }

    public void falsoClicked(View v)
    {
        if (!arrayQuesiti[quesito_corrente].getRisposta_counted())
        {
            arrayQuesiti[quesito_corrente].setRisposta_counted(true);
            risposteTotali++;
            if (arrayQuesiti[quesito_corrente].getRisposta() == false)
            {
                if(arrayQuesiti[quesito_corrente].getSugg_visto())
                    risposteCorretteNonValide++;
                else
                    risposteCorretteValide++;
            }
        }
        successivoClicked(null);
    }

    public void veroClicked(View v)
    {
        if (!arrayQuesiti[quesito_corrente].getRisposta_counted())
        {
            arrayQuesiti[quesito_corrente].setRisposta_counted(true);
            risposteTotali++;
            if (arrayQuesiti[quesito_corrente].getRisposta() == true)
            {
                if(arrayQuesiti[quesito_corrente].getSugg_visto())
                    risposteCorretteNonValide++;
                else
                    risposteCorretteValide++;
            }
        }
        successivoClicked(null);
    }

    //Lancio di una nuova activity
    public void suggerimentoClicked(View v)
    {
        Intent i = new Intent();
        i.setClass(getApplicationContext(), Suggerimento.class);
        i.putExtra("RISPOSTA", arrayQuesiti[quesito_corrente].getRisposta());
        i.putExtra("DOMANDA", arrayQuesiti[quesito_corrente].getTesto());
        startActivityForResult(i, 453);
    }

    public void onActivityResult(int rc, int result, Intent i)
    {
        if (rc != 453) return;
        if (result != Activity.RESULT_OK) return;
        if (i == null) return;
        boolean visto = i.getBooleanExtra("SUGGERIMENTO_VISTO", false);
        Log.d("QUIZ", "visto = " + visto);
        if(visto)
            arrayQuesiti[quesito_corrente].setSugg_visto(true);
    }

}