package com.example.androidproject;

import Classes.DatabaseHelper;
import Classes.OnSwipeTouchListener;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.util.Log;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TheoryActivity extends AppCompatActivity {
    TextView title_theory, theory_pages;
    DatabaseHelper myDb;
    String course;
    SharedPreferences pref;

    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_theory);
        title_theory = (TextView) findViewById(R.id.title_theory);
        theory_pages = (TextView) findViewById(R.id.theory_pages);
        RelativeLayout theory_id = (RelativeLayout) findViewById(R.id.theory_id);
        Intent intent = getIntent();
        String str1 = intent.getStringExtra("course_id");
        String str2 = intent.getStringExtra("description");
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        String type = pref.getString("type",null);
        theory_id.setOnTouchListener(new OnSwipeTouchListener(TheoryActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Intent i;
                if(type == "student")
                    i = new Intent(TheoryActivity.this, ListCourseActivity.class);
                else
                    i = new Intent(TheoryActivity.this, StudentActivity.class);
                i.putExtra("id_view_course", course);
                i.putExtra("description", str2);
                startActivity(i);
            }
        });
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        myDb = new DatabaseHelper(this);
        showTheory(str1);
        //Log.d("test",str2+"");
    }
    public void showTheory(String str1){
        Cursor myCursor = myDb.getTheoryById(str1);
        if(myCursor.getCount() == 0){
            Toast.makeText(getBaseContext(),"Error.....Nothing found.",Toast.LENGTH_SHORT).show();
            return;
        }
        while(myCursor.moveToNext()) {
            title_theory.setText(myCursor.getString(2));
            theory_pages.setText(myCursor.getString(4));
            course = myCursor.getString(1);
        }
    }
}