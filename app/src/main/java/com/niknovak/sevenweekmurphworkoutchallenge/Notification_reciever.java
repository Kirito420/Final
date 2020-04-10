package com.niknovak.sevenweekmurphworkoutchallenge;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.core.app.NotificationCompat;


public class Notification_reciever extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {


        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent repeating_intent = new Intent(context, MainActivity.class);
        repeating_intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(context,100,repeating_intent,PendingIntent.FLAG_UPDATE_CURRENT);
        //build the Notification
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "noti")
                .setContentIntent(pendingIntent)
                //notification icon
                .setSmallIcon(R.mipmap.ic_launcher)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(),
                        R.mipmap.ic_launcher))
                .setContentTitle("7 Week Workout Challenge")
                .setContentText("Do your daily workout!")
                .setVibrate(new long[]{0, 500, 1000})
                .setDefaults(Notification.DEFAULT_SOUND )
                .setLights(0xff0000ff, 300, 1000) // blue color
                .setAutoCancel(true);
        if (Build.VERSION.SDK_INT >= 26) {
            builder.setChannelId(App.CHANNEL_ID);
            assert notificationManager != null;

            //notificationManager.notify(0 /* Request Code */, builder.build());
        }
        notificationManager.notify(100,builder.build());

        //notifyAsPackage("targetPackage", "tag", 100, builder);
    }
}



