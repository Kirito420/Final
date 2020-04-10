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
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class GifInstructionsActivity extends AppCompatActivity {
    WebView webView;
    TextView textView;
    String exercise;
    String text5;
    String text3;
    String text2;
    String text;
    String text4;

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();    //Call the back button's method
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void setTitle(String title) {
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
        setContentView(R.layout.activity_gif_instructions);

        setTitle("Instructions");

        webView = findViewById(R.id.gifWebView);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        Intent intetnt = getIntent();
        exercise = intetnt.getStringExtra("exercise");
        try {
            switch (exercise) {
                case ("loginChinup"):
                    String file5 = "file:android_asset/resizedchinup.gif";
                    webView.loadUrl(file5); //ne dela na api 18
                    textView = findViewById(R.id.instructionsTextView);
                    if(Build.VERSION.SDK_INT > 20)
                        text5 = "▸ hands shoulder width apart\n▸ squeeze your butt and abs\n▸ chin above bar\n▸ exhale on the way up";
                    else
                        text5 = "- hands shoulder width apart\n- squeeze your butt and abs\n- chin above bar\n- exhale on the way up";
                    textView.setText(text5);
                    break;
                case ("chinup"):
                    String file = "file:android_asset/resizedchinup.gif";
                    webView.loadUrl(file); //ne dela na api 18
                    textView = findViewById(R.id.instructionsTextView);
                    if(Build.VERSION.SDK_INT > 20)
                        text = "▸ hands shoulder width apart\n▸ squeeze your butt and abs\n▸ chin above bar\n▸ exhale on the way up\n▸ go to the next exercise only when you complete all the repetitions of this one";
                    else
                        text = "- hands shoulder width apart\n- squeeze your butt and abs\n- chin above bar\n- exhale on the way up\n- go to the next exercise only when you complete all the repetitions of this one";
                    textView.setText(text);
                    break;
                case ("pushup"):
                    String file2 = "file:android_asset/resizedpushup.gif";
                    webView.loadUrl(file2);
                    textView = findViewById(R.id.instructionsTextView);
                    if(Build.VERSION.SDK_INT > 20)
                        text2 = "▸ fully extend your elbows\n▸ hands below shoulders\n▸ squeeze your butt and abs\n▸ exhale on the way up\n▸ go to the next exercise only when you complete all the repetitions of this one";
                    else
                        text2 = "- fully extend your elbows\n- hands below shoulders\n- squeeze your butt and abs\n- exhale on the way up\n- go to the next exercise only when you complete all the repetitions of this one";
                    textView.setText(text2);
                    break;
                case ("squat"):
                    String file3 = "file:android_asset/resizedsqut.gif";
                    webView.loadUrl(file3);
                    textView = findViewById(R.id.instructionsTextView);
                    if(Build.VERSION.SDK_INT > 20)
                        text3 = "▸ keep your back straight\n▸ don't let your knees past your toes\n▸ squeeze your butt when you come up\n▸ exhale on the way up\n▸ go to the next exercise only when you complete all the repetitions of this one";
                    else
                        text3 = "- keep your back straight\n- don't let your knees past your toes\n- squeeze your butt when you come up\n- exhale on the way up\n- go to the next exercise only when you complete all the repetitions of this one";
                    textView.setText(text3);
                    break;
                case ("jj"):
                    String file4 = "file:android_asset/resizedjj.gif";
                    webView.loadUrl(file4);
                    textView = findViewById(R.id.instructionsTextView);
                    String text4 = "Perform this exercise\nfor about a minute.";
                    textView.setText(text4);
                    break;
                default:
                    //nothing
            }
        }catch(Error e){

        }
    }
}
