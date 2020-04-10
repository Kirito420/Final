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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PhotoInstructionsActivity extends AppCompatActivity {
 ImageView imageView;
 TextView textView;
 String exercise;
 String text5;
 String text6;
 String text7;
 String text8;

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
  setContentView(R.layout.activity_photo_instructions);

  setTitle("Instructions");

  imageView = findViewById(R.id.instructionsImageView);


  Intent intetnt = getIntent();
  exercise = intetnt.getStringExtra("exercise");

  switch (exercise) {
   case ("loginChinup"):
    imageView.setImageResource(R.drawable.chinuppic);
    textView = findViewById(R.id.instructionsTextView);
    String text10 = "- hands shoulder width apart\n- chin above bar\n- squeeze your butt and abs\n- exhale on the way up";
    textView.setText(text10);
    break;
   case ("chinup"):
    imageView.setImageResource(R.drawable.chinuppic);
    textView = findViewById(R.id.instructionsTextView);
    String text = "- hands shoulder width apart\n- chin above bar\n- squeeze your butt and abs\n- exhale on the way up\n- go to the next exercise only when you complete all the repetitions of this one";
    textView.setText(text);
    break;
   case("pushup"):
    imageView.setImageResource(R.drawable.pushuppic);
    textView = findViewById(R.id.instructionsTextView);
    String text2 = "- fully extend your elbows\n- hands below shoulders\n- squeeze your butt and abs\n- exhale on the way up\n- go to the next exercise only when you complete all the repetitions of this one";
    textView.setText(text2);
    break;
   case("squat"):
    imageView.setImageResource(R.drawable.squatpic);
    textView = findViewById(R.id.instructionsTextView);
    String text3 = "- keep your back straight\n- don't let your knees past your toes\n- squeeze your butt when you come up\n- exhale on the way up\n- go to the next exercise only when you complete all the repetitions of this one";
    textView.setText(text3);
    break;
   case("jj"):
    imageView.setImageResource(R.drawable.jjpic);
    textView = findViewById(R.id.instructionsTextView);
    String text4 = "Perform this exercise\nfor about a minute.";
    textView.setText(text4);
    break;
   case("quad"):
    imageView.setImageResource(R.drawable.quad43);
    textView = findViewById(R.id.instructionsTextView);
    if(Build.VERSION.SDK_INT > 20)
     text5 = "▸ push your hip forward\n▸ hold each leg for 20s";
    else
     text5 = "- push your hip forward\n- hold each leg for 20s";
    textView.setText(text5);
    break;
   case("peck"):
    imageView.setImageResource(R.drawable.peck43);
    textView = findViewById(R.id.instructionsTextView);
    if(Build.VERSION.SDK_INT > 20)
     text6 = "▸ extend your arm\n▸ turn away from it\n▸ hold each side for 20s";
    else
     text6 = "- extend your arm\n- turn away from it\n- hold each side for 20s";
    textView.setText(text6);
    break;
   case("forward fold"):
    imageView.setImageResource(R.drawable.forwardfold43);
    textView = findViewById(R.id.instructionsTextView);
    if(Build.VERSION.SDK_INT > 20)
     text7 = "▸ keep your legs straight\n▸ look through your legs\n▸ hold position for 20s";
    else
     text7 = "- keep your legs straight\n- look through your legs\n- hold position for 20s";
    textView.setText(text7);
    break;
   case("back"):
    imageView.setImageResource(R.drawable.back43);
    textView = findViewById(R.id.instructionsTextView);
    if(Build.VERSION.SDK_INT > 20)
     text8 = "▸ round your upper back\n▸ chin to chest\n▸ hold position for 20s";
    else
     text8 = "- round your upper back\n- chin to chest\n- hold position for 20s";
    textView.setText(text8);
    break;
   default:
    //nothing
  }
 }
}
