package com.example.multiactivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;


public class Activity1 extends AppCompatActivity {
    final String TAG = "MADEBUG";
    final String STR = "Activity1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity1);
        Log.d(TAG,STR+" onCreate()");
    }

    public void lanciaActivity(View v) {
        Intent i = new Intent(getApplicationContext(),Activity2.class);
        //Settando il seguente flag l'activity che viene lanciata non verrà salvata nel backstack!
        //i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
        startActivity(i);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG,STR+" onStart()");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.d(TAG,STR+" onRestart()");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,STR+" onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG,STR+" onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG,STR+" onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG,STR+" onDestroy()");
    }
}
