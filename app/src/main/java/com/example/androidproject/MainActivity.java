package com.example.androidproject;

import Classes.OnSwipeTouchListener;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    SharedPreferences pref;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RelativeLayout layout = findViewById(R.id.relativeLayout);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        layout.setOnTouchListener(new OnSwipeTouchListener(MainActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Intent i = new Intent(MainActivity.this,RegisterActivity.class);
                startActivity(i);
            }
            @Override
            public void onSwipeRight() {
                super.onSwipeRight();
                Intent i = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(i);
            }
            @Override
            public void onSwipeUp() {
                super.onSwipeUp();
                Intent i = new Intent(MainActivity.this,TeacherRegisterActivity.class);
                startActivity(i);
            }
            @Override
            public void onSwipeDown() {
                super.onSwipeDown();
                Intent i = new Intent(MainActivity.this,TeacherLoginActivity.class);
                startActivity(i);
            }
        });
    }
}