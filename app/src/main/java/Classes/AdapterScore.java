package Classes;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidproject.R;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class AdapterScore extends RecyclerView.Adapter<AdapterScore.ViewHolder>{
    private ArrayList<Answer> answores;
    private ArrayList<Question> questions;
    private Context context;
    public List<Integer> a = new ArrayList<>();
    public List<Integer> medie = new ArrayList<>();
    public List<Integer> idQuestion = new ArrayList<>();
    double result, d;
    public String type;
    public int i;
    public int ii;
    private static final DecimalFormat df = new DecimalFormat("0.0");
    public AdapterScore(ArrayList<Answer> courses, ArrayList<Question> questions, Context context, List<Integer> medie,
                        String type, int i, int ii, List<Integer> idQuestion, List<Integer> a) {
        this.answores = courses;
        this.questions = questions;
        this.context = context;
        this.medie = medie;
        this.type = type;
        this.i = i;
        this.ii = ii;
        this.idQuestion = idQuestion;
        this.a = a;
    }
    @NonNull
    @Override
    public AdapterScore.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher,parent,false);
        return new AdapterScore.ViewHolder(view);
    }
    public double calculatePercentage(double obtained, double total) {
        return obtained * 100 / total;
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterScore.ViewHolder holder, int position) {
        final Answer course = answores.get(position);
        holder.incorrect.setText(course.getAnswer());
        df.setRoundingMode(RoundingMode.UP);
        holder.incorrect.setTextColor(Color.RED);
        if(a.get(i).intValue() == idQuestion.get(ii).intValue()) {
            if (type == "correct") {
                result = calculatePercentage(medie.get(i), Double.parseDouble(course.getAnswer()));
                //result = 1;
            } else if (type == "incorrect") {
                result = calculatePercentage(Double.parseDouble(course.getAnswer()) - medie.get(i), Double.parseDouble(course.getAnswer()));
                //result = 2;
            }
            //Log.d("test", a.get(i).intValue()+" "+idQuestion.get(ii).intValue()+"    "
            //        + type +"    "+course.getAnswer().toString() +"    "+medie.get(i));
            ii++;
        }
        i++;
        //Log.d("test",a.get(i)+" "+idQuestion.get(ii)+"    "+ii+"   "+i);
        d = 100 - result;
        holder.correct.setText(df.format(result) + "%");
        holder.incorrect.setText(df.format(d) + "%");
        holder.correct.setTextColor(Color.GREEN);
        final Question question = questions.get(position);
        holder.name.setText(question.getQuestion());
    }
    @Override
    public int getItemCount() {
        return answores.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, correct, incorrect, email;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            correct = itemView.findViewById(R.id.score);
            incorrect = itemView.findViewById(R.id.medie);
        }
    }
}
