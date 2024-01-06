package com.example.playmusicservice;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class Client extends Activity {
    Intent musicPlayService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        musicPlayService = new Intent(getApplicationContext(), com.example.playmusicservice.MusicPlayService.class);
    }

    public void startService(View v) {
        Log.d("DEBUG","Starting Music Service");
        startService(musicPlayService);
    }

    public void stopService(View v) {
        Log.d("DEBUG","Stopping Music Service");
        stopService(musicPlayService);
    }

}
