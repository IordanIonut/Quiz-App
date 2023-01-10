package Classes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.androidproject.*;
import lombok.extern.java.Log;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;
import static androidx.core.content.ContextCompat.startActivity;

public class AdapterCourse extends RecyclerView.Adapter<AdapterCourse.ViewHolder>{
    private ArrayList<Course> courses;
    private static Context context;
    public static String type;
    public AdapterCourse(ArrayList<Course> courses, Context context, String type) {
        this.courses = courses;
        this.context = context;
        this.type = type;
    }
    @NonNull
    @Override
    public AdapterCourse.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ViewHolder(view);
    }@Override
    public void onBindViewHolder(@NonNull AdapterCourse.ViewHolder holder, int position) {
        final Course course = courses.get(position);
        holder.description.setText(course.getDescription());
        holder.id_view_course.setText(course.getId()+"");
    }
    @Override
    public int getItemCount() {
        return courses.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView description, id_view_course;
        Button btn_start_quiz;
        public ViewHolder(@NonNull  View itemView) {
            super(itemView);
            description = itemView.findViewById(R.id.describe_course);
            id_view_course = itemView.findViewById(R.id.id_view_course);
            btn_start_quiz = itemView.findViewById(R.id.btn_start_quiz);
            btn_start_quiz.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(type == "student") {
                        Toast.makeText(context.getApplicationContext(), "Start the course: " + description.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, ListCourseActivity.class);
                        intent.putExtra("id_view_course", id_view_course.getText().toString());
                        intent.putExtra("description", description.getText().toString());
                        context.startActivity(intent);
                    }else if(type == "teacher"){
                        //Toast.makeText(context.getApplicationContext(), "Start the course: " + description.getText(), Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(context, StudentActivity.class);
                        intent.putExtra("id_view_course", id_view_course.getText().toString());
                        intent.putExtra("description", description.getText().toString());
                        context.startActivity(intent);
                    }
                }
            });
        }
    }
}
