package com.niknovak.sevenweekmurphworkoutchallenge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import static com.niknovak.sevenweekmurphworkoutchallenge.WorkoutActivity.*;

public class ThirdSetActivity extends AppCompatActivity {
    Button squatsButton;
    Button pushupsButton;
    Button chinupsButton;
    int squatsAmount;
    int pushupsAmount;
    int chinupsAmount;


    int tappedCircle;
    Intent intent6;

    public void onBackPressed(){
        RestTimer2Activity.boolTimer = false;
        super.onBackPressed();
    }

    public void onSquatClick (View view){
        if(Build.VERSION.SDK_INT > 18)
            intent6 = new Intent(getApplicationContext(), GifInstructionsActivity.class);
        else
            intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "squat");
        startActivity(intent6);
    }

    public void onPushupClick (View view){
        if(Build.VERSION.SDK_INT > 18)
            intent6 = new Intent(getApplicationContext(), GifInstructionsActivity.class);
        else
            intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "pushup");
        startActivity(intent6);
    }

    public void onChinupClick (View view){
        if(Build.VERSION.SDK_INT > 18)
            intent6 = new Intent(getApplicationContext(), GifInstructionsActivity.class);
        else
            intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "chinup");
        startActivity(intent6);
    }

    public void workoutDone(View view){
        Intent intent = new Intent(getApplicationContext(), RestTimer3Activity.class);
        intent.putExtra("tappedCircle", tappedCircle);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();    //Call the back button's method
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTitle(String title){
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.workout);

        AdView adView;
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); //replace with real appID, spremen bannerID(iz admob ad unit) v xmlu, in v manifestu
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        Intent intetnt = getIntent();
        tappedCircle = intetnt.getIntExtra("tappedCircle", 0);

        int tc = tappedCircle + 1;
        setTitle("Third Set (day " + tc + ")");

        if (MainActivity.modeP == 1) {
            squatsReps = 30;
            restSquatsReps = 10;

            pushupsReps = 20;
            restPushupsReps = 7;

            chinupReps = 10;
            restChinupReps = 4;

            squatsMultiplier = 3;
            pushupsMultiplier = 2;
            chinupsMultiplier = 1;
        } else if (MainActivity.modeP == 2){
            squatsReps = 60;
            restSquatsReps = 20;

            pushupsReps = 40;
            restPushupsReps = 14;

            chinupReps = 20;
            restChinupReps = 7;

            squatsMultiplier = 6;
            pushupsMultiplier = 4;
            chinupsMultiplier = 2;
        } else if (MainActivity.modeP == 3){
            squatsReps = 84;
            restSquatsReps = 30;

            pushupsReps = 56;
            restPushupsReps = 20;

            chinupReps = 28;
            restChinupReps = 10;

            squatsMultiplier =  9;
            pushupsMultiplier = 6;
            chinupsMultiplier = 3;
        }

        if(tappedCircle % 2 == 0){
            squatsAmount = squatsReps + squatsMultiplier*(tappedCircle/2);
            pushupsAmount = pushupsReps + pushupsMultiplier*(tappedCircle/2);
            chinupsAmount = chinupReps + chinupsMultiplier*(tappedCircle/2);

            switch(squatsAmount % 4){
                case 0:
                    squatsAmount = squatsAmount / 4; break;
                case 1:
                    squatsAmount = squatsAmount / 4; break;
                case 2:
                    squatsAmount = squatsAmount / 4 + 1; break;
                case 3:
                    squatsAmount = squatsAmount / 4 + 1; break;
            }
            switch(pushupsAmount % 4){
                case 0:
                    pushupsAmount = pushupsAmount / 4; break;
                case 1:
                    pushupsAmount = pushupsAmount / 4; break;
                case 2:
                    pushupsAmount = pushupsAmount / 4 + 1; break;
                case 3:
                    pushupsAmount = pushupsAmount / 4 + 1; break;
            }
            switch(chinupsAmount % 4){
                case 0:
                    chinupsAmount = chinupsAmount / 4; break;
                case 1:
                    chinupsAmount = chinupsAmount / 4; break;
                case 2:
                    chinupsAmount = chinupsAmount / 4 + 1; break;
                case 3:
                    chinupsAmount = chinupsAmount / 4 + 1; break;
            }

        } else {
            squatsAmount = 30;
            pushupsAmount = 20;
            chinupsAmount = 10;
        }

        if(squatsAmount==0){
            Toast.makeText(this, "There was an error.\nEverything is fine.", Toast.LENGTH_LONG).show();
            Intent intent9 = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent9);
        }

        squatsButton = findViewById(R.id.chinupButton);
        pushupsButton = findViewById(R.id.peckStretchButton);
        chinupsButton = findViewById(R.id.quadStretchButton);
        String squatsButtonText = String.format("%d  SQUATS", squatsAmount);
        String pushupsButtonText = String.format("%d  PUSHUPS", pushupsAmount);
        String chinupsButtonText = String.format("%d  PULLUPS", chinupsAmount);
        squatsButton.setText(squatsButtonText);
        pushupsButton.setText(pushupsButtonText);
        chinupsButton.setText(chinupsButtonText);
        squatsButton.setTextSize(20);
        pushupsButton.setTextSize(20);
        chinupsButton.setTextSize(20);

    }
}