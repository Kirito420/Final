package com.niknovak.sevenweekmurphworkoutchallenge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class SetReminderActivity extends AppCompatActivity {
    int hour;
    int minute;
    public static AlarmManager alarmManager;
    public static PendingIntent pendingIntent;
    AlertDialog.Builder builder1;


    @Override
    public void onBackPressed() {
        //do nothing
    }

    public void setTitle(String title){
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        TextView textView = new TextView(this);
        textView.setText(title);
        textView.setTextSize(20);
        textView.setTypeface(null, Typeface.BOLD);
        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        textView.setGravity(Gravity.CENTER);
        textView.setTextColor(getResources().getColor(R.color.titleColor));
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(textView);
    }

    public void cancelButtonClick(View view){
        try{
            alarmManager.cancel(pendingIntent);
        }catch(Exception e){}
        Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(intent2);
    }

    public void createNotificationChannel(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            CharSequence name = "LemubitReminderChannel";
            String description = "Channel for reminder";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel("noti", name, importance);
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_reminder);
        createNotificationChannel();

        setTitle("Set Notifications");

        findViewById(R.id.setNotificationButton).setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {

                TimePicker timePicker = findViewById(R.id.timePicker);


                Calendar calendar = Calendar.getInstance();
                //tuki zdej das notr cs
                if (Build.VERSION.SDK_INT >= 23) {
                    hour = timePicker.getHour();
                    minute = timePicker.getMinute();
                } else {
                    hour = timePicker.getCurrentHour();
                    minute = timePicker.getCurrentMinute();
                }

                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
                calendar.set(Calendar.SECOND, 0);
                long alarmTime = calendar.getTimeInMillis();


                Intent intent = new Intent(getApplicationContext(),Notification_reciever.class);
                pendingIntent = PendingIntent.getBroadcast(getApplicationContext(),100,intent,PendingIntent.FLAG_UPDATE_CURRENT);
                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,alarmTime,AlarmManager.INTERVAL_DAY,pendingIntent); //2. parameter je time notifikacije in millisec


                builder1 = new AlertDialog.Builder(SetReminderActivity.this);
                builder1.setMessage("Are you sure?")
                        //.setCancelable(true)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Intent intent2 = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent2);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.cancel();
                            }
                        });
                AlertDialog alertDialog = builder1.create();
                alertDialog.show();


            }
        });



    }
}
