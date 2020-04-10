package com.niknovak.sevenweekmurphworkoutchallenge;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    int amountOfChinups;
    Intent intent6;

    EditText amountOfChinupsEditText;

    @Override
    public void onBackPressed() {

        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
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

    public void onChinupClick(View view){
        if(Build.VERSION.SDK_INT > 18)
            intent6 = new Intent(getApplicationContext(), GifInstructionsActivity.class);
        else
            intent6 = new Intent(getApplicationContext(), PhotoInstructionsActivity.class);
        intent6.putExtra("exercise","loginChinup");
        startActivity(intent6);
    }



    public void loginDone(View view){
        amountOfChinupsEditText = findViewById(R.id.chinupsAmountEditText);
        if (!amountOfChinupsEditText.getText().toString().isEmpty()) {
            boolean fitEnough = true;
            try {
                amountOfChinups = Integer.parseInt(amountOfChinupsEditText.getText().toString());
            } catch (Exception e) {
                amountOfChinups = 0;
            }
            if (amountOfChinups <= 0) {
                Toast.makeText(LoginActivity.this, "Sorry, this app is not for you.", Toast.LENGTH_SHORT).show();
                fitEnough = false;

            } else if (amountOfChinups < 4) {
                Toast.makeText(LoginActivity.this, "EASY MODE", Toast.LENGTH_SHORT).show();
                MainActivity.mode = 1;


            } else if (amountOfChinups < 6) {
                Toast.makeText(LoginActivity.this, "NORMAL MODE", Toast.LENGTH_SHORT).show();
                MainActivity.mode = 2;

            } else {
                Toast.makeText(LoginActivity.this, "HARD MODE", Toast.LENGTH_SHORT).show();
                MainActivity.mode = 3;

            }
            if (fitEnough) {
                MainActivity.modePrefrence = getApplicationContext().getSharedPreferences("PREFS_NAME", 0);
                SharedPreferences.Editor editor2 = MainActivity.settings.edit();
                editor2.putInt("modePr", MainActivity.mode);
                editor2.apply();

                SharedPreferences.Editor editor = MainActivity.prefrences.edit();
                editor.putBoolean("firstTimeInstall", false);
                editor.apply();


                MainActivity.intent2 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(MainActivity.intent2);
            }
        }else
            Toast.makeText(this, "Please enter a number", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        amountOfChinupsEditText = (EditText) findViewById(R.id.chinupsAmountEditText);

        setTitle("7 Week Workout Challenge");


    }
}
