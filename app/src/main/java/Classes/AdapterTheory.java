package Classes;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidproject.*;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static android.app.PendingIntent.getActivity;
import static android.view.Gravity.CENTER;


public class AdapterTheory extends RecyclerView.Adapter<AdapterTheory.ViewHolder>{
    private ArrayList<Theory> theory;
    private Context context;
    Read myRead;
    Intent intent;
    DatabaseHelper myDb;
    Cursor myCursor, myCursor1;
    private String text, str1, id_user, type;
    public AdapterTheory(ArrayList<Theory> theory, Context context, String text, String id_user, String type, String str1) {
        this.theory = theory;
        this.context = context;
        this.text = text;
        this.type = type;
        this.id_user = id_user;
        this.str1 = str1;
        myDb = new DatabaseHelper(context);
    }
    @NonNull
    @Override
    public AdapterTheory.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.courses,parent,false);
        return new AdapterTheory.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        final Theory theor = theory.get(position);
        holder.title.setText(theor.getTitle());
        holder.course_id.setText(theor.getId()+" "+theor.getId_course());
        String str = holder.course_id.getText().toString();
        String[] splitStr = str.split("\\s+");
        if(type == "teacher") {
            holder.checkFalse.setVisibility(View.INVISIBLE);
            holder.checkTrue.setVisibility(View.INVISIBLE);
            holder.start_course.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        }
        if(holder.title.getText().toString().equals("Recapitulare")){
            holder.checkTrue.setVisibility(View.INVISIBLE);
            holder.start_course.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            holder.checkFalse.setVisibility(View.INVISIBLE);
            myCursor = myDb.getQuestionByIdAndIncorrect(str1, id_user, "incorrect");
            myCursor1 = myDb.getQuestionByIdAndIncorrect(str1, id_user, "correct");
            if (myCursor.getCount() == 0 || myCursor.getCount() == 0) {
                Toast.makeText(context.getApplicationContext(), "Nu puteti da testul de Recapitulare pana nu dati test-ul Quiz..", Toast.LENGTH_SHORT).show();
                holder.start_course.setEnabled(false);
            }else if (myCursor.getCount() != 0 || myCursor.getCount() != 0) {
                //Toast.makeText(context.getApplicationContext(), "Nu puteti da testul de Recapitulare pana nu dati test-ul Quiz..", Toast.LENGTH_SHORT).show();
                holder.start_course.setEnabled(true);
            }
        }
        holder.start_course.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!holder.title.getText().toString().equals("Quiz") && !holder.title.getText().toString().equals("Recapitulare")) {
                    intent = new Intent(context, TheoryActivity.class);
                    intent.putExtra("course_id", splitStr[0]);
                    intent.putExtra("description",text);
                    //Log.d("test",splitStr[0]);
                    if(type == "student") {
                        myRead = new Read(1, splitStr[1], id_user, splitStr[0], true);
                        myDb.insertDataRead(myRead);
                    }
                    //Log.d("test",Integer.parseInt(splitStr[1])+""+ Integer.parseInt(id_user)+""+ Integer.parseInt(splitStr[0])+""+false);
                } else if(holder.title.getText().toString().equals("Quiz") && type == "student"){
                    intent = new Intent(context, QuizActivity.class);
                    intent.putExtra("test"," ");
                    if(type == "student") {
                        myRead = new Read(1, splitStr[1], id_user, splitStr[0], true);
                        myDb.insertDataRead(myRead);
                    }
                    intent.putExtra("course_id", splitStr[1]);
                }else if(holder.title.getText().toString().equals("Recapitulare")){
                    intent = new Intent(context, QuizActivity.class);
                    intent.putExtra("test","test");
                    intent.putExtra("course_id", splitStr[1]);
                    holder.start_course.setEnabled(true);
                    holder.checkTrue.setVisibility(View.INVISIBLE);
                    holder.checkFalse.setVisibility(View.INVISIBLE);
                }
                else{
                    intent = new Intent(context, ScoreActivity.class);
                    intent.putExtra("description",text);
                    intent.putExtra("course_id", str1);
                }
                context.startActivity(intent);
            }
        });
        Cursor myCursor = myDb.getReadByIdUser(id_user,splitStr[1],splitStr[0]);
        if (myCursor.getCount() == 0) {
            return;
        }
        while (myCursor.moveToNext()) {
            holder.checkTrue.setVisibility(View.VISIBLE);
            holder.checkFalse.setVisibility(View.INVISIBLE);
        }
    }
    @Override
    public int getItemCount() {
        return theory.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, checkFalse, checkTrue;
        TextView course_id;
        Button start_course;
        Intent intent = new Intent();
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            start_course = itemView.findViewById(R.id.start_course);
            course_id = itemView.findViewById(R.id.course_id);
            checkTrue = itemView.findViewById(R.id.checkTrue1);
            checkFalse = itemView.findViewById(R.id.checkFalse1);
            String str = course_id.getText().toString();
            String[] splitStr = str.split("\\s+");
            start_course.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!title.getText().toString().equals("Quiz") && type == "student") {
                        intent = new Intent(context, TheoryActivity.class);
                        intent.putExtra("course_id", splitStr[0]);
                        intent.putExtra("description",text);
                    }
                    else if(title.getText().toString().equals("Quiz") && type == "student"){
                        intent = new Intent(context, QuizActivity.class);
                        intent.putExtra("course_id", splitStr[1]);
                        checkFalse.setVisibility(View.INVISIBLE);
                        checkTrue.setVisibility(View.INVISIBLE);
                    }else if(title.getText().toString().equals("Recapitulare")){
                        intent = new Intent(context, QuizActivity.class);
                        Log.d("aaaaa","testessss");
                        intent.putExtra("test","test");
                    }
                    if(type == "teacher"){
                        checkFalse.setVisibility(View.INVISIBLE);
                        checkTrue.setVisibility(View.INVISIBLE);
                    }
                    context.startActivity(intent);
                }
            });
        }
    }
}
