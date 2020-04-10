package com.niknovak.sevenweekmurphworkoutchallenge;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.niknovak.sevenweekmurphworkoutchallenge.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    public static int onDay;

    public static SharedPreferences prefrences;
    public static SharedPreferences modePrefrence;
    public static SharedPreferences modePrefrence3;

    public static SharedPreferences settings;
    public static SharedPreferences settings3;
    public static SharedPreferences.Editor editor3;

    public static Boolean firstTime;

    public static Intent intent2;

    public static int mode;
    public static int modeP;

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    public static Toolbar toolbar;






    //public static View circleView;

    public void choose(View view){

        int tappedCircle = Integer.parseInt(view.getTag().toString());
        if (tappedCircle + 1 <= onDay) {
            Intent intent = new Intent(getApplicationContext(), WarmupActivity.class);
            intent.putExtra("tappedCircle", tappedCircle);
            startActivity(intent);
            //finish();
        }
    }

    @Override
    public void onBackPressed() {
        /*if(onDay == 2 && Build.VERSION.SDK_INT < 28){
            Intent intent5 = new Intent(getApplicationContext(), SetReminderActivity.class);
            startActivity(intent5);
        }*/
        //do nothing
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
            drawerLayout.closeDrawer(GravityCompat.START);
        else{
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
        }

    }

    public void setTitle(String title){


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
        setContentView(R.layout.activity_main);

        //navigationView.bringToFront();
        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_view);
        toolbar = findViewById(R.id.toolbar);


        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout,toolbar,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);


        //setTitle("7 Week Workout Challenge");


        try{
            MainActivity.modePrefrence3 = getApplicationContext().getSharedPreferences("PREFS_NAME3", 0);
            editor3 = MainActivity.settings3.edit();
            editor3.putInt("modePr3", MainActivity.onDay);
            editor3.apply();
        }catch(Exception e){}

        try{
            settings3 = getApplicationContext().getSharedPreferences("PREFS_NAME3", 0);
            onDay = settings3.getInt("modePr3", 0);
        }catch(Exception e){}

        settings = getApplicationContext().getSharedPreferences("PREFS_NAME", 0);
        modeP = settings.getInt("modePr", 0);

        //check if app is opened for the first time
        prefrences = getSharedPreferences("PREFRENCES", MODE_PRIVATE);
        firstTime = prefrences.getBoolean("firstTimeInstall", true);

        //onDay je tmno modre barve(tag == onDay), beli so ze narjeni (tag < onDay), plavi so se zaklenjeni (tag > onDay)

        //na vseh tagih (razen 0), imas 3 plasti krogcov, ki se ppelajo dol
        //int circleTag = Integer.parseInt(circleView.getTag().toString());

        StringBuilder buttonName = new StringBuilder();
        buttonName.append("imageButton");
        StringBuilder darkBlueButtonName = new StringBuilder();
        darkBlueButtonName.append("darkblueButton"); //original: "darkblueButton" odvisno od dizajna
        StringBuilder onDAYButtonName  = new StringBuilder();
        onDAYButtonName.append("imageButton");
        try {
            for (int b = 1; b < onDay; b++) {
                //peel two lears
                buttonName.append(b);
                darkBlueButtonName.append(b);
                String bn = buttonName.toString();
                String dbbn = darkBlueButtonName.toString();
                ImageButton circleButton = findViewById(getResources().getIdentifier(bn, "id", getPackageName()));
                ImageButton darkblueCircleButton = findViewById(getResources().getIdentifier(dbbn, "id", getPackageName()));
                circleButton.setAlpha(0);
                darkblueCircleButton.setAlpha(0);
                if (Integer.toString(b).length() == 2) {
                    buttonName.deleteCharAt(buttonName.length() - 1);
                    darkBlueButtonName.deleteCharAt(darkBlueButtonName.length() - 1);
                }
                buttonName.deleteCharAt(buttonName.length() - 1);
                darkBlueButtonName.deleteCharAt(darkBlueButtonName.length() - 1);
            }
            //peel one layer
            onDAYButtonName.append(onDay);
            String odbn = onDAYButtonName.toString();
            ImageButton onDayCircleButton = findViewById(getResources().getIdentifier(odbn, "id", getPackageName()));
            onDayCircleButton.setAlpha(0);

        }catch(Exception e){}


        if(firstTime){
            //if app was opened for the first time k
            onDay = 1;
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.on_day:
                //namesto tega dej nov activity
                //getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new SetOnDayFragment()).commit();
                Intent intent3 = new Intent(MainActivity.this, SetOnDayActivity.class);
                startActivity(intent3);
                break;
            case R.id.reminder:
                Intent intent9 = new Intent(MainActivity.this, SetReminderActivity.class);
                startActivity(intent9);
                break;
            case R.id.reset:
                //are you sure
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Are you sure you want to reset the app?")
                        .setCancelable(true)
                        .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                onDay = 1;
                                firstTime = true;
                                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                                startActivity(intent);
                                //finish();
                                MainActivity.super.onBackPressed();
                            }
                        })

                        .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();



                break;
            case R.id.review:
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("market://details?id=" + getPackageName())));
                } catch (ActivityNotFoundException e) {
                    startActivity(new Intent(Intent.ACTION_VIEW,
                            Uri.parse("http://play.google.com/store/apps/details?id=" + getPackageName())));
                }
                break;
            //default:
                //break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }
}
