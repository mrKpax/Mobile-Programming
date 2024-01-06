package com.example.quiz;

public class Quesito
{
    private String testo;
    private boolean risposta;
    private boolean risposta_counted;
    private boolean sugg_visto;

    public Quesito(String testo, boolean risposta)
    {
        this.testo = testo;
        this.risposta = risposta;
        this.risposta_counted = false;
        this.sugg_visto = false;
    }

    public String getTesto()
    {
        return testo;
    }

    public void setTesto(String testo)
    {
        this.testo = testo;
    }

    public boolean getRisposta()
    {
        return risposta;
    }

    public void setRisposta(boolean risposta)
    {
        this.risposta = risposta;
    }

    public boolean getRisposta_counted()
    {
        return risposta_counted;
    }

    public void setRisposta_counted(boolean v)
    {
        risposta_counted = v;
    }

    public boolean getSugg_visto()
    {
        return sugg_visto;
    }

    public void setSugg_visto(boolean v)
    {
        sugg_visto = v;
    }
}
