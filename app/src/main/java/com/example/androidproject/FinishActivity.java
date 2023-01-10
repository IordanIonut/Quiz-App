package com.example.androidproject;

import Classes.Course;
import Classes.DatabaseHelper;
import Classes.Quiz;
import Classes.Theory;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Handler;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.androidproject.R;

import java.util.Arrays;

public class FinishActivity extends AppCompatActivity {
    TextView tvScor;
    SharedPreferences pref;
    DatabaseHelper myDb;
    Quiz myQuiz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);
        tvScor = findViewById(R.id.score1);
        Intent intent = getIntent();
        String str = intent.getStringExtra("score");
        String[] str2 = str.split("Scor: ");
        String str1 = intent.getStringExtra("course_id");
        String str3 = intent.getStringExtra("test");
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        String id_user = pref.getString("id", null);
        String type = pref.getString("type", null);
        int score;
            score = Integer. parseInt(str2[1]);
        tvScor.setText("Your score is: " +score);
        myDb = new DatabaseHelper(this);
        if (type == "student"){
            myQuiz = new Quiz(1, str1, id_user, score);
            myDb.insertDataQuiz(myQuiz);
        }
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                Intent intent = new Intent(FinishActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        }, 4000);
        showCourses();
    }
    public void showCourses(){
        Cursor myCursor = myDb.getAllDataQuiz();
        if(myCursor.getCount() == 0){
            Toast.makeText(getBaseContext(),"Error.....Nothing found.",Toast.LENGTH_SHORT).show();
            return;
        }
        while(myCursor.moveToNext()) {
            Log.d("test1",myCursor.getInt(0)+" "+myCursor.getString(1)+" "+myCursor.getString(2)+" "+myCursor.getString(3));
        }
    }
}