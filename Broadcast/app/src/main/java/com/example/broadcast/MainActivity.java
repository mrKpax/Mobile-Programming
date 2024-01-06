package com.example.broadcast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    BroadcastReceiver dReceiver;
    BroadcastReceiver sReceiver;
    BroadcastReceiver receiver;
    LocalBroadcastManager lbm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setBroadcastReceiver();
        lbm = LocalBroadcastManager.getInstance(getApplicationContext());
    }

    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter =
                new IntentFilter("com.example.broadcast.my_string");

        //From API26 cannot use anymore static registration of receivers
        //Registering it dynamically
        sReceiver = new Receiver();
        lbm.registerReceiver(sReceiver, intentFilter);

        //Dynamically created receiver
        dReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Log.d("DEBUG", "dynamically registered receiver");
                Toast.makeText(context,
                        "Intent received by the dynamically registered receiver",
                        Toast.LENGTH_SHORT).show();
            }
        };
        lbm.registerReceiver(dReceiver, intentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(sReceiver);
        unregisterReceiver(dReceiver);
    }

    public void sendLocalBroadcast(View v) {
        lbm.sendBroadcast(new Intent("com.example.broadcast.my_string"));
    }

    public void sendGlobalBroadcast(View v) {
        sendBroadcast(new Intent("myACTION_TIME_TICK"));
    }

    private void setBroadcastReceiver() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction(Intent.ACTION_TIME_TICK);
        intentFilter.addAction("myACTION_TIME_TICK");

        receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Toast.makeText(context,
                        "Received global broadcast myACTION_TIME_TICK",
                        Toast.LENGTH_SHORT).show();
            }
        };

        registerReceiver(receiver, intentFilter);
    }
}
