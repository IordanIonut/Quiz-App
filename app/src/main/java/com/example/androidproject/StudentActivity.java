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
import java.util.List;

public class StudentActivity extends AppCompatActivity {
    private ArrayList<Quiz> quiz = new ArrayList<>();
    private ArrayList<User> user = new ArrayList<>();
    private ArrayList<Theory> theory = new ArrayList<>();
    public List<Integer> medie = new ArrayList<>();
    DatabaseHelper myDb;
    TextView title_course;
    SharedPreferences pref;
    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        @SuppressLint({"WrongViewCast", "MissingInflatedId", "LocalSuppress"})
        Intent intent = getIntent();
        String str1 = intent.getStringExtra("id_view_course");
        String str2 = intent.getStringExtra("description");
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        String id_user = pref.getString("id",null);
        String type = pref.getString("type",null);
        RecyclerView recyclerView = findViewById(R.id.list_b);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new AdapterTheory(theory,this,str2,id_user,type, str1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView = findViewById(R.id.list_a);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new AdapterQuiz(quiz, user,this, medie));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        title_course = (TextView) findViewById(R.id.title_course);
        title_course.setText(""+str2);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.list_student_id);
        layout.setOnTouchListener(new OnSwipeTouchListener(StudentActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Intent i = new Intent(StudentActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });
        myDb = new DatabaseHelper(this);
        showTheory(str1);
        showUser();
        showQuiz(str1);
    }
    public void showQuiz(String str1){
        Cursor myCursor = myDb.getAvarageQuiz(str1);
        if(myCursor.getCount() == 0){
            Toast.makeText(getBaseContext(),"Error.....Nothing found.",Toast.LENGTH_SHORT).show();
            return;
        }
        while(myCursor.moveToNext()) {
            medie.add(myCursor.getInt(3));
            quiz.add(new Quiz(myCursor.getInt(0),myCursor.getString(1),myCursor.getString(2), myCursor.getInt(4)));
            Log.d("test11","Quiz: "+myCursor.getInt(0)+" "+myCursor.getString(1)+" "+myCursor.getString(2)+" "+ myCursor.getString(3)+" "+myCursor.getString(4));
        }
    }
    public void showUser(){
        Cursor myCursor = myDb.getUserType("student");
        if(myCursor.getCount() == 0){
            Toast.makeText(getBaseContext(),"Error.....Nothing found.",Toast.LENGTH_SHORT).show();
            return;
        }
        while(myCursor.moveToNext()) {
            String value = String.valueOf(myCursor.getString(3).equals("True"));
            user.add(new User(myCursor.getInt(0),myCursor.getString(1),myCursor.getString(2),
                    value, myCursor.getString(4)));
            Log.d("test","User: "+myCursor.getInt(0)+" "+myCursor.getString(1)+" "+myCursor.getString(2)+" "+ myCursor.getString(4));
        }
    }
    public void showTheory(String str1){
        Cursor myCursor = myDb.getCursorById(str1);
        if(myCursor.getCount() == 0){
            Toast.makeText(getBaseContext(),"Error.....Nothing found.",Toast.LENGTH_SHORT).show();
            return;
        }
        int course = 0;
        while(myCursor.moveToNext()) {
            boolean value =myCursor.getString(3).equals("True");
            if(!myCursor.getString(4).equals(""))
                theory.add(new Theory(myCursor.getInt(0),myCursor.getInt(1),myCursor.getString(2),
                    value, myCursor.getString(4)));
            //Log.d("test",myCursor.getInt(0)+""+myCursor.getInt(1)+""+myCursor.getString(2)+""+ myCursor.getString(4));
        }
    }
}