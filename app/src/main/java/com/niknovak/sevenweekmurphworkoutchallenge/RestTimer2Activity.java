package com.niknovak.sevenweekmurphworkoutchallenge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.Locale;

public class RestTimer2Activity extends AppCompatActivity {
    private static final long START_TIME_IN_MILLIS = 180000;

    private TextView mTextViewCountDown;

    private CountDownTimer mCountDownTimer;


    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    public static Intent intent;
    public int minutes;
    public static boolean boolTimer;
    public static boolean boolFinished = false;



    public void onBackPressed(){
        //mCountDownTimer.cancel();
        boolTimer = false;
        super.onBackPressed();
    }


    public void skipRest(View view){
        //mCountDownTimer.cancel();
        boolTimer = false;
        boolFinished = true;


        Intent intent3 = getIntent();
        int tappedCircle = intent3.getIntExtra("tappedCircle", 0);
        Intent intetnt2 = getIntent();
        intent = new Intent(getApplicationContext(), ThirdSetActivity.class);
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
    protected void onResume(){
        super.onResume();
        setTitle("Rest");
        setContentView(R.layout.activity_rest_timer);
        if(!boolFinished)
            boolTimer = true;


        AdView adView;
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); //replace with real appID, spremen bannerID(iz admob ad unit) v xmlu, in v manifestu
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        try {
            startTimer();
        }catch (Error e){
        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setTitle("Rest");
        setContentView(R.layout.activity_rest_timer);
        boolTimer = true;

        AdView adView;
        MobileAds.initialize(this, "ca-app-pub-3940256099942544~3347511713"); //replace with real appID, spremen bannerID(iz admob ad unit) v xmlu, in v manifestu
        adView = (AdView)findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        try {
            startTimer();
        }catch (Error e){

        }
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                if(boolTimer){
                    boolFinished = true;
                    Intent intent3 = getIntent();
                    int tappedCircle = intent3.getIntExtra("tappedCircle", 0);

                    intent = new Intent(getApplicationContext(), ThirdSetActivity.class);

                    intent.putExtra("tappedCircle", tappedCircle);
                    startActivity(intent);
                }
            }
        }.start();
    }

    private void updateCountDownText() {
        minutes = (int) (mTimeLeftInMillis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }
}
