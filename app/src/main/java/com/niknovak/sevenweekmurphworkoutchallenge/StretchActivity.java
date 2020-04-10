package com.niknovak.sevenweekmurphworkoutchallenge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static com.niknovak.sevenweekmurphworkoutchallenge.SetReminderActivity.alarmManager;
import static com.niknovak.sevenweekmurphworkoutchallenge.SetReminderActivity.pendingIntent;

public class StretchActivity extends AppCompatActivity {
    int tappedCircle;
    Intent intent6;

    public void onBackClick(View view){
        intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "back");
        startActivity(intent6);
    }

    public void onPeckClick(View view){
        intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "peck");
        startActivity(intent6);
    }

    public void onQuadClick(View view){
        intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "quad");
        startActivity(intent6);
    }

    public void onForwardFoldClick(View view){
        intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "forward fold");
        startActivity(intent6);
    }

    public void stretchDone(View view){
        if (MainActivity.onDay == 1){
            MainActivity.onDay++;
            Intent intent = new Intent(getApplicationContext(), SetReminderActivity.class);
            startActivity(intent);
            //set reminder activity
        }
        else if (MainActivity.onDay == 49){
            try{
                alarmManager.cancel(pendingIntent);
            }catch(Exception e){}
            Intent intent = new Intent(getApplicationContext(), RateAppActivity.class);
            startActivity(intent);
        }else {

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            Intent intent2 = new Intent(getApplicationContext(), SetReminderActivity.class);

            if (MainActivity.onDay == 1 && Build.VERSION.SDK_INT < 99) { //28
                if (tappedCircle + 1 == MainActivity.onDay)
                    MainActivity.onDay++;
                startActivity(intent2);
            } else {
                if (tappedCircle + 1 == MainActivity.onDay)
                    MainActivity.onDay++;
                startActivity(intent);
            }
        }
    }

    public void setTitle(String title){
        //getSupportActionBar().setHomeButtonEnabled(true);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher); //@drawable/on_day_icon
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow);

        View view = getLayoutInflater().inflate(R.layout.action_bar, null);
        ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                ActionBar.LayoutParams.WRAP_CONTENT,
                ActionBar.LayoutParams.MATCH_PARENT,
                Gravity.CENTER);

        TextView Title = (TextView) view.findViewById(R.id.actionbar_title);
        Title.setText(title);

        getSupportActionBar().setCustomView(view,params);
        getSupportActionBar().setDisplayShowCustomEnabled(true); //show custom title
        getSupportActionBar().setDisplayShowTitleEnabled(false); //hide the default title
        Title.setTextSize(20);
        Title.setTextColor(getResources().getColor(R.color.titleColor));
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();    //Call the back button's method
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stretch);



        //It's also possible to use getSupportActionBar()

        //Sample AdMob App ID: ca-app-pub-3940256099942544~3347511713
        //Real AdMob App ID: ca-app-pub-3137351105878660~5901616023

        AdView adView;
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); //replace with real appID, spremen bannerID(iz admob ad unit) v xmlu, in v manifestu
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        Intent intetnt = getIntent();
        tappedCircle = intetnt.getIntExtra("tappedCircle", 0);


        int tc = tappedCircle + 1;

        setTitle("Stretch! (day " + tc + ")");

        /*if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setLogo(R.mipmap.ic_launcher); //@drawable/on_day_icon
            getSupportActionBar().setDisplayUseLogoEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.back_arrow);

        }*/





    }
}
