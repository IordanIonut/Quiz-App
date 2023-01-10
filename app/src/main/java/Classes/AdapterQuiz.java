package Classes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidproject.FinishActivity;
import com.example.androidproject.ListCourseActivity;
import com.example.androidproject.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AdapterQuiz extends RecyclerView.Adapter<AdapterQuiz.ViewHolder>{
    private ArrayList<Quiz> quiz;
    private ArrayList<User> user;
    public List<Integer> medie = new ArrayList<>();
    public int i;
    private Context context;
    public AdapterQuiz(ArrayList<Quiz> courses,ArrayList<User> user, Context context, List<Integer> medie) {
        this.quiz = courses;
        this.user = user;
        this.context = context;
        this.medie = medie;
    }
    @NonNull
    @Override
    public AdapterQuiz.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.teacher,parent,false);
        return new AdapterQuiz.ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterQuiz.ViewHolder holder, int position) {
        final Quiz course = quiz.get(position);
        holder.score.setText(course.getScore()+"");
        holder.medie.setText(medie.get(i++)+"");
        final User user1 = user.get(position);
        holder.name.setText(user1.getName());
        holder.email.setText(user1.getEmail());
    }
    @Override
    public int getItemCount() {
        return quiz.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name, email, medie, score;
        Button start_course;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            email = itemView.findViewById(R.id.email);
            medie = itemView.findViewById(R.id.medie);
            score = itemView.findViewById(R.id.score);
        }
    }
}
