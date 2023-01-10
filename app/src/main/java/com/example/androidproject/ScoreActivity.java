package com.example.androidproject;

import Classes.*;
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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ScoreActivity extends AppCompatActivity {
    private ArrayList<Answer> answer = new ArrayList<>();
    private ArrayList<Question> question = new ArrayList<>();
    private ArrayList<Theory> theory = new ArrayList<>();
    public List<Integer> medie1 = new ArrayList<>();
    public List<Integer> idQuestion1 = new ArrayList<>();
    public List<Integer> idQuestion2 = new ArrayList<>();
    public List<Integer> a = new ArrayList<>();
    public List<Integer> b = new ArrayList<>();
    public List<Integer> medie2 = new ArrayList<>();
    DatabaseHelper myDb;
    Intent intent = getIntent();
    TextView title_course;
    SharedPreferences pref;
    public String type1;
    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        intent = getIntent();
        String str1 = intent.getStringExtra("course_id");
        String str2 = intent.getStringExtra("description");
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        String id_user = pref.getString("id",null);
        String type = pref.getString("type",null);
        Log.d("test2",str1+" ");
        myDb = new DatabaseHelper(this);
        showAnswer(str1);
        RecyclerView recyclerView = findViewById(R.id.list_a1);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new AdapterScore(answer,question,this, medie1, type1, 0, 0,idQuestion1, a));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        title_course = (TextView) findViewById(R.id.title_course1);
        title_course.setText(""+str2);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.list_course_id1);
        layout.setOnTouchListener(new OnSwipeTouchListener(ScoreActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Intent i = new Intent(ScoreActivity.this,StudentActivity.class);
                startActivity(i);
            }
        });
    }
    public void showAnswer(String str1){
        Log.d("test","-----------------------------");
        Cursor myCursor = myDb.getAvarageAnswer(str1);
        if(myCursor.getCount() == 0){
            Toast.makeText(getBaseContext(),"Error.....Nothing found.",Toast.LENGTH_SHORT).show();
            return;
        }
        while(myCursor.moveToNext()) {
            a.add(myCursor.getInt(2));
            answer.add(new Answer(myCursor.getInt(0),myCursor.getString(1),myCursor.getString(2), myCursor.getString(3), myCursor.getString(4)));
            Log.d("test","Answer: "+myCursor.getInt(0)+" "+myCursor.getString(1)+" "
                    +myCursor.getString(2)+" "+ myCursor.getString(3)+" "+myCursor.getString(4));
        }
        Log.d("test","-----------------------------");
        myCursor = myDb.getQuestionById(str1);
        if(myCursor.getCount() == 0){
            Toast.makeText(getBaseContext(),"Error.....Nothing found.",Toast.LENGTH_SHORT).show();
            return;
        }
        while(myCursor.moveToNext()) {
            b.add(myCursor.getInt(0));
            question.add(new Question(myCursor.getInt(0),myCursor.getInt(1),myCursor.getString(2), myCursor.getInt(3), myCursor.getString(4), myCursor.getString(5),myCursor.getString(6), myCursor.getString(7)));
        }
        Log.d("test","-----------------------------");
        myCursor = myDb.getAvarageAnswerTotal1(str1);
        if(myCursor.getCount() == 0){
            Toast.makeText(getBaseContext(),"Error.....Nothing found.",Toast.LENGTH_SHORT).show();
            return;
        }
        while(myCursor.moveToNext()) {
            medie1.add(myCursor.getInt(3));
            idQuestion1.add(myCursor.getInt(2));
            //answer.add(new Answer(myCursor.getInt(0),myCursor.getString(1),myCursor.getString(2), myCursor.getString(3), myCursor.getString(4)));
            Log.d("test","Total: "+myCursor.getInt(0)+" "+myCursor.getString(1)+" "
                    +myCursor.getString(2)+" "+ myCursor.getString(3));
        }
        Log.d("test","correct");
        Log.d("test","-----------------------------");
        myCursor = myDb.getAvarageAnswerTotal2(str1);
        if(myCursor.getCount() == 0){
            Toast.makeText(getBaseContext(),"Error.....Nothing found.",Toast.LENGTH_SHORT).show();
            return;
        }
        while(myCursor.moveToNext()) {
            medie2.add(myCursor.getInt(3));
            idQuestion2.add(myCursor.getInt(2));
            //answer.add(new Answer(myCursor.getInt(0),myCursor.getString(1),myCursor.getString(2), myCursor.getString(3), myCursor.getString(4)));
            Log.d("test","Total: "+myCursor.getInt(0)+" "+myCursor.getString(1)+" "
                    +myCursor.getString(2)+" "+ myCursor.getString(3));
        }
        Log.d("test","incorrect");
        if(medie1.size()<medie2.size()){
            medie1.clear();
            medie1.addAll(medie2);
            idQuestion1.clear();
            idQuestion1.addAll(idQuestion2);
            type1 = "incorrect";
        }else{
            type1 = "correct";
        }
        Log.d("test","-----------------------------");
        Log.d("test","-----------------------------");
        Log.d("test","-----------------------------");
        Log.d("test", medie1.toString()+"   medie");
        Log.d("test", idQuestion1.toString()+"   idQuestion");
        Log.d("test", a.toString()+"    a");
        Log.d("test", b.toString()+"    b");

    }
}