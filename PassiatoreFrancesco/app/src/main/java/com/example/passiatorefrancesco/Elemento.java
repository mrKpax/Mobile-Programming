package com.example.passiatorefrancesco;

import android.graphics.drawable.Drawable;

public class Elemento {

    private String descrizione;
    private int quantita;
    public Elemento(String d, int q)
    {
        this.descrizione = d;
        this.quantita = q;
    }

    public String getDescrizione()
    {
        return descrizione;
    }

    public int getQuantita()
    {
        return quantita;
    }

    public void setDescrizione(String d)
    {
        this.descrizione = d;
    }

    public void setQuantita(int q)
    {
        this.quantita = q;
    }
}
