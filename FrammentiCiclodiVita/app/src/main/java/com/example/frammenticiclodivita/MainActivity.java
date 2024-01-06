package com.example.frammenticiclodivita;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private void log(String str)
    {
        Log.d("MYDEBUG", "Main: " + str);
    }

    FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fm = getFragmentManager();
        log("onCreate");
    }

    public void pulsanteInserisciApremuto(View v)
    {
        FragmentA f = new FragmentA();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.contenitoreFrammenti,f,"tagFrammentoA");
        ft.commit();
    }

    public void pulsanteInserisciBpremuto(View v)
    {
        FragmentB f = new FragmentB();
        FragmentTransaction ft = fm.beginTransaction();
        ft.add(R.id.contenitoreFrammenti,f,"tagFrammentoB");
        ft.commit();
    }

    public void pulsanteRimuoviApremuto(View v)
    {
        Fragment f = fm.findFragmentByTag("tagFrammentoA");
        FragmentTransaction ft = fm.beginTransaction();
        if (f != null) {
            ft.remove(f);
        }
        ft.commit();
    }

    public void pulsanteRimuoviBpremuto(View v)
    {
        Fragment f = fm.findFragmentByTag("tagFrammentoB");
        FragmentTransaction ft = fm.beginTransaction();
        if (f != null) {
            ft.remove(f);
        }
        ft.commit();
    }

    public void pulsantedaAaBpremuto(View v)
    {
        FragmentTransaction ft = fm.beginTransaction();
        FragmentB f = new FragmentB();
        ft.replace(R.id.contenitoreFrammenti,f,"tagFrammentoB");
        ft.commit();
    }

    public void pulsantedaBaApremuto(View v)
    {
        FragmentTransaction ft = fm.beginTransaction();
        FragmentA f = new FragmentA();
        ft.replace(R.id.contenitoreFrammenti,f,"tagFrammentoA");
        ft.commit();
    }

    public void pulsanteAttachApremuto(View v)
    {
        Fragment f = fm.findFragmentByTag("tagFrammentoA");
        if (f != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.attach(f);
            ft.commit();
        }
    }

    public void pulsanteDetachApremuto(View v)
    {
        Fragment f = fm.findFragmentByTag("tagFrammentoA");
        if (f != null) {
            FragmentTransaction ft = fm.beginTransaction();
            ft.detach(f);
            ft.commit();
        }
    }

}
