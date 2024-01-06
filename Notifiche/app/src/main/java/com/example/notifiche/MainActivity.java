package com.example.notifiche;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void showToast(View v) {
        Toast.makeText(getApplicationContext(), "Toast!", Toast.LENGTH_LONG).show();
    }

    public void showCustomToast(View v) {
        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(getLayoutInflater().inflate(R.layout.custom_toast, null));
        toast.show();
    }

    public void showDialog(View v) {
        DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        Toast.makeText(getApplicationContext(), "Ok!", Toast.LENGTH_LONG).show();
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        Toast.makeText(getApplicationContext(), "Azione annullata", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        };

        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Stai per ripartire da capo. Sei sicuro?")
                .setPositiveButton("Si", dialogClickListener)
                .setNegativeButton("No", dialogClickListener).show();
         */

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Stai per ripartire da capo. Sei sicuro?");
        builder.setPositiveButton("Si", dialogClickListener);
        builder.setNegativeButton("No", dialogClickListener);
        builder.show();

        return;
    }

    @RequiresApi(Build.VERSION_CODES.O) //Api 26
    public void showNotification(View v) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            Toast.makeText(this,"API " +Build.VERSION.SDK_INT+ " <"+Build.VERSION_CODES.O,Toast.LENGTH_LONG).show();
            return;
        }
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String NOTIFICATION_CHANNEL_ID = "channel01";

        NotificationChannel notificationChannel = new NotificationChannel(NOTIFICATION_CHANNEL_ID, "My Notifications", NotificationManager.IMPORTANCE_HIGH);
        notificationChannel.setDescription("This is a description of the channel");
        notificationChannel.enableLights(true);
        notificationChannel.setLightColor(Color.GREEN);
        notificationChannel.setVibrationPattern(new long[]{0, 600, 300, 600});
        notificationChannel.enableVibration(true);
        notificationManager.createNotificationChannel(notificationChannel);

        // Create an explicit intent for an Activity in your app
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, NOTIFICATION_CHANNEL_ID);

        notificationBuilder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.drawable.anchor) //For Api > 28 the small icon has to be one color over transparent
                .setPriority(Notification.PRIORITY_MAX)
                .setContentTitle("Questo è il titolo della mia notifica")
                .setContentText("Qui ci sono altre informazioni")
                .setContentIntent(pendingIntent);

        notificationManager.notify(1, notificationBuilder.build());
    }

    public void cancelNotification(View v) {
        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        mNotificationManager.cancel(1);
    }

}
