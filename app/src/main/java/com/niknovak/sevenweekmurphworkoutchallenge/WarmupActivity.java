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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class WarmupActivity extends AppCompatActivity {



    int tappedCircle;
    Intent intent6;

    public void onRollClick(View view){
        intent6 = new Intent(getApplicationContext(), TextInstructionsActivity.class);
        startActivity(intent6);
    }

    public void onJJClick(View view){
        if(Build.VERSION.SDK_INT > 18)
            intent6 = new Intent(getApplicationContext(), GifInstructionsActivity.class);
        else
            intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise", "jj");
        startActivity(intent6);
    }

    public void warmupDone(View view){

        Intent intent = new Intent(getApplicationContext(), WorkoutActivity.class);

        intent.putExtra("tappedCircle", tappedCircle);
        startActivity(intent);
        //finish();
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
        setContentView(R.layout.activity_warmup);


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
        setTitle("Warmup (day " + tc + ")");
    }
}
