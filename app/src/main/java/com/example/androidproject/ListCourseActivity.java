package com.example.androidproject;

import Classes.*;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListCourseActivity extends AppCompatActivity {
    TextView title_course;
    private ArrayList<Theory> theory = new ArrayList<>();
    DatabaseHelper myDb;
    SharedPreferences pref;
    @SuppressLint({"MissingInflatedId", "ClickableViewAccessibility"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_course);
        Intent intent = getIntent();
        String str1 = intent.getStringExtra("id_view_course");
        String str2 = intent.getStringExtra("description");
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        String id_user = pref.getString("id",null);
        String type = pref.getString("type",null);
        RecyclerView recyclerView = findViewById(R.id.list_a);
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new AdapterTheory(theory,this,str2,id_user,type,str1));
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        title_course = (TextView) findViewById(R.id.title_course);
        title_course.setText(""+str2);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.list_course_id);
        layout.setOnTouchListener(new OnSwipeTouchListener(ListCourseActivity.this) {
            @Override
            public void onSwipeLeft() {
                super.onSwipeLeft();
                Intent i = new Intent(ListCourseActivity.this,HomeActivity.class);
                startActivity(i);
            }
        });
        myDb = new DatabaseHelper(this);
        showTheory(str1);
    }
    public void showTheory(String str1){
        Cursor myCursor = myDb.getCursorById(str1);
        if(myCursor.getCount() == 0){
            Toast.makeText(getBaseContext(),"Error.....Nothing found.",Toast.LENGTH_SHORT).show();
            return;
        }
        while(myCursor.moveToNext()) {
            boolean value =myCursor.getString(3).equals("true");
            //if(!myCursor.getString(4).equals(""))
            theory.add(new Theory(myCursor.getInt(0),myCursor.getInt(1),myCursor.getString(2),
                    value, myCursor.getString(4)));
            //Log.d("test",myCursor.getInt(0)+""+myCursor.getInt(1)+""+myCursor.getString(2)+""+ myCursor.getString(4));
        }
    }
}