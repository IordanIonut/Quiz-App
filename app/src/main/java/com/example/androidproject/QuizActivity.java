package com.example.androidproject;

import Classes.Answer;
import Classes.DatabaseHelper;
import Classes.ListQues;
import Classes.Question;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Handler;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class QuizActivity extends AppCompatActivity {
    Button raspA,raspB,raspC,raspD;
    TextView tvScor,tvIntrebare, score1;
    private ListQues vectIntrebari;
    public Answer myAnswer;
    public List<String> vectIntreb = new ArrayList<String>();
    public List<List<String>> vectRasp = new ArrayList<List<String>>();
    public List<Integer> raspCorecte = new ArrayList<Integer>();
    private int rasp;
    private int scor=0;
    private int nrIntrebari;
    static int index=0;
    public String str1, str2;
    DatabaseHelper myDb;
    SharedPreferences pref;//session for login
    public List<String> id_question = new ArrayList<String>();
    String answer;
    public int ii = 0;
    Cursor myCursor, myCursor1;

    private ArrayList<Question> questions = new ArrayList<>();
    @SuppressLint({"MissingInflatedId", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        myDb = new DatabaseHelper(this);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        String id_user = pref.getString("id",null);
        String type = pref.getString("type",null);
        Intent intent = getIntent();
        str1 = intent.getStringExtra("course_id");
        str2 = intent.getStringExtra("test");
        int i=0;
        if(str2.equals(" ")) {
            myCursor = myDb.getQuestionById(str1);
            if (myCursor.getCount() == 0) {
                return;
            }
            while (myCursor.moveToNext()) {
                id_question.add(String.valueOf(myCursor.getInt(0)));
                questions.add(new Question(myCursor.getInt(0), myCursor.getInt(1), myCursor.getString(2), myCursor.getInt(3),
                        myCursor.getString(4), myCursor.getString(5), myCursor.getString(6), myCursor.getString(7)));
                //Log.d("asdasdasd",myCursor.getInt(0)+" "+myCursor.getInt(1)+" "+myCursor.getString(2)+" "+myCursor.getInt(3)+" "+
                //        myCursor.getString(4)+" "+myCursor.getString(5)+" "+myCursor.getString(6)+" "+myCursor.getString(7));
                vectIntreb.add(myCursor.getString(2));
                vectRasp.add(new ArrayList<String>());
                vectRasp.get(i).add(myCursor.getString(4));
                vectRasp.get(i).add(myCursor.getString(5));
                vectRasp.get(i).add(myCursor.getString(6));
                vectRasp.get(i).add(myCursor.getString(7));
                Log.d("test", String.valueOf(id_question.size()));
                i++;
                raspCorecte.add(Integer.valueOf(myCursor.getString(3)));
            }
        }else if(str2.equals("test")) {
            Log.d("test2", str1+" "+id_user+"");
            myCursor = myDb.getQuestionByIdAndIncorrect(str1, id_user, "incorrect");
            myCursor1 = myDb.getQuestionByIdAndIncorrect(str1, id_user, "correct");
            i = 0;
            if (myCursor.getCount() == 0) {
                intent  =new Intent(getApplicationContext(), FinishActivity.class);
                intent.putExtra("score"," Nu ati parcurs Quiz-ul");
                intent.putExtra("course_id", str1);
                intent.putExtra("test","test");
                startActivity(intent);
            }
            while (myCursor.moveToNext()) {
                //Log.d("test22",myCursor.getInt(0)+" "+myCursor.getString(1)+" "+
                //        myCursor.getString(2)+" "+myCursor.getString(3)+" "+myCursor.getString(4));
                Log.d("test22",myCursor.getInt(0)+"");
                Cursor myCursor2 = myDb.getQuestionByIdQuestion(myCursor.getString(0));
                myCursor2.moveToNext();
                id_question.add(String.valueOf(myCursor2.getInt(0)));
                //questions.add(new Question(myCursor.getInt(0),myCursor.getInt(1),myCursor.getString(2),myCursor.getInt(3),
                //      myCursor.getString(4),myCursor.getString(5),myCursor.getString(6),myCursor.getString(7)));
                //Log.d("test22",myCursor2.getInt(0)+" "+myCursor2.getInt(1)+" "+myCursor2.getString(2)+" "+myCursor2.getInt(3)+" "+
                 //       myCursor2.getString(4)+" "+myCursor2.getString(5)+" "+myCursor2.getString(6)+" "+myCursor2.getString(7));
                vectIntreb.add(myCursor2.getString(2));
                vectRasp.add(new ArrayList<String>());
                vectRasp.get(i).add(myCursor2.getString(4));
                vectRasp.get(i).add(myCursor2.getString(5));
                vectRasp.get(i).add(myCursor2.getString(6));
                vectRasp.get(i).add(myCursor2.getString(7));
                Log.d("test", String.valueOf(id_question.size()));
                i++;
                raspCorecte.add(Integer.valueOf(myCursor2.getString(3)));
            }
        }
        String[] vectIntreb_1 = new String[vectIntreb.size()];
        String[] vectRasp_1 = new String[4];
        String[][] vectRasp_2 = new String[vectRasp.size()][4];
        Integer[] raspCorecte_1 = new Integer[raspCorecte.size()];
        //Log.d("test", Arrays.toString(vectIntreb.toArray(vectIntreb_1)));
        //Log.d("test", Arrays.toString(vectRasp.stream().toArray()));
        //Log.d("test", Arrays.toString(raspCorecte.toArray(raspCorecte_1)));
        for(int j=0; j<vectRasp.size(); j++){
            vectRasp.get(j).toArray(vectRasp_1);
            String sir= Arrays.toString(vectRasp_1);
            String[] test = sir.split(",|\\[|\\]");
            vectRasp_2[j][0]=test[1];
            vectRasp_2[j][1]=test[2];
            vectRasp_2[j][2]=test[3];
            vectRasp_2[j][3]=test[4];
        }
        vectIntrebari = new ListQues(vectIntreb.toArray(vectIntreb_1),
                vectRasp_2,
                raspCorecte.toArray(raspCorecte_1));
        //Log.d("test", vectIntrebari.toString());
        nrIntrebari=vectIntrebari.vectIntreb.length;
        raspA=findViewById(R.id.btnR1);
        raspB=findViewById(R.id.btnR2);
        raspC=findViewById(R.id.btnR3);
        raspD=findViewById(R.id.btnR4);
        tvIntrebare=findViewById(R.id.text);
        tvIntrebare.setMovementMethod(new ScrollingMovementMethod());
        tvScor=findViewById(R.id.score);
        score1=findViewById(R.id.score1);
        tvScor.setText("Scor: "+scor);
        score1.setText(0+"/"+myCursor.getCount());
        index=0;
        actIntrebare(index);
        raspA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(1==rasp){
                    scor++;
                    tvScor.setText("Scor: "+scor);
                    raspA.setTextColor(Color.GREEN);
                    answer = "correct";
                }
                else {
                    raspA.setTextColor(Color.RED);
                    answer = "incorrect";
                }
                pref = getSharedPreferences("user_details", MODE_PRIVATE);
                String id_user = pref.getString("id", null);
                String id = id_question.get(ii);
                score1.setText(ii+"/"+myCursor.getCount());
                myAnswer = new Answer(1,str1,id_user,id,answer);
                myDb.insertDataAnsowere(myAnswer);
                ii++;
                index++;allBotton();
                pauseDisplay(2000);
            }
        });
        raspB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(2 == rasp){
                    scor++;
                    tvScor.setText("Scor: "+scor);
                    raspB.setTextColor(Color.GREEN);
                    answer = "correct";
                }
                else {
                    raspB.setTextColor(Color.RED);
                    answer = "incorrect";
                }
                pref = getSharedPreferences("user_details", MODE_PRIVATE);
                String id_user = pref.getString("id", null);
                score1.setText(ii+"/"+myCursor.getCount());
                String id = id_question.get(ii);
                myAnswer = new Answer(1,str1,id_user,id,answer);
                myDb.insertDataAnsowere(myAnswer);
                ii++;
                index++;allBotton();
                pauseDisplay(2000);
            }
        });
        raspC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(3 == rasp){
                    scor++;
                    tvScor.setText("Scor: "+scor);
                    raspC.setTextColor(Color.GREEN);
                    answer = "correct";
                }
                else {
                    raspC.setTextColor(Color.RED);
                    answer = "incorrect";
                }
                pref = getSharedPreferences("user_details", MODE_PRIVATE);
                String id_user = pref.getString("id", null);
                String id = id_question.get(ii);
                score1.setText(ii+"/"+myCursor.getCount());
                myAnswer = new Answer(1,str1,id_user,id,answer);
                myDb.insertDataAnsowere(myAnswer);
                ii++;
                index++;allBotton();
                pauseDisplay(2000);
            }
        });
        raspD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(4 == rasp){
                    scor++;
                    tvScor.setText("Scor: "+scor);
                    raspD.setTextColor(Color.GREEN);
                    answer = "correct";
                }
                else {
                    raspD.setTextColor(Color.RED);
                    answer = "incorrect";
                }
                pref = getSharedPreferences("user_details", MODE_PRIVATE);
                String id_user = pref.getString("id", null);
                String id = id_question.get(ii);
                myAnswer = new Answer(1,str1,id_user,id,answer);
                score1.setText(ii+"/"+myCursor.getCount());
                myDb.insertDataAnsowere(myAnswer);
                ii++;
                index++;
                allBotton();
                pauseDisplay(2000);
            }
        });
    }
    public void showCourses(){
        Cursor myCursor = myDb.getAllDataAnswore();
        if(myCursor.getCount() == 0){
            //Toast.makeText(getBaseContext(),"Error.....Nothing found.",Toast.LENGTH_SHORT).show();
            return;
        }
        while(myCursor.moveToNext()) {
            Log.d("test1",myCursor.getInt(0)+" "+myCursor.getString(1)+" "+myCursor.getString(2)+" "+myCursor.getString(3)+" "+myCursor.getString(4));
        }
        Log.d("test1","------------------------------------------");
    }
    private void allBotton(){
        raspA.setEnabled(false);
        raspB.setEnabled(false);
        raspC.setEnabled(false);
        raspD.setEnabled(false);
    }
    private void pauseDisplay(int a){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                allBotton();
                if(index == nrIntrebari){
                    Intent intent =new Intent(getApplicationContext(), FinishActivity.class);
                    String str = tvScor.getText().toString();
                    pref = getSharedPreferences("user_details", MODE_PRIVATE);
                    intent.putExtra("score", str);
                    intent.putExtra("course_id", str1);
                    startActivity(intent);
                }
                actIntrebare(index);
            }
        }, a);
    }
    private void actIntrebare(int i){
        if (index == nrIntrebari){
            return;
        }
        tvIntrebare.setText(vectIntrebari.getIntrebare(i));
        raspA.setText(vectIntrebari.getRaspA(i));
        raspB.setText(vectIntrebari.getRaspB(i));
        raspC.setText(vectIntrebari.getRaspC(i));
        raspD.setText(vectIntrebari.getRaspD(i));
        rasp= vectIntrebari.getRaspCorect(i);
        raspA.setTextColor(Color.parseColor("#00695C"));
        raspB.setTextColor(Color.parseColor("#00695C"));
        raspC.setTextColor(Color.parseColor("#00695C"));
        raspD.setTextColor(Color.parseColor("#00695C"));
        raspA.setEnabled(true);
        score1.setText(ii+"/"+myCursor.getCount());
        raspB.setEnabled(true);
        raspC.setEnabled(true);
        raspD.setEnabled(true);
        showCourses();
    }
}