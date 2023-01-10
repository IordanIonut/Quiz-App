package com.example.androidproject;

import Classes.DatabaseHelper;
import Classes.OnSwipeTouchListener;
import Classes.User;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TeacherLoginActivity extends AppCompatActivity {
    EditText et_email, et_password;
    String email, password, type;
    Button btn_login;
    DatabaseHelper myDb;
    User myUser;
    SharedPreferences pref;//session for login
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        RelativeLayout layout = (RelativeLayout) findViewById(R.id.login_id2);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        btn_login = (Button) findViewById(R.id.btn_login);
        myDb = new DatabaseHelper(this);
        pref = getSharedPreferences("user_details",MODE_PRIVATE);
        layout.setOnTouchListener(new OnSwipeTouchListener(TeacherLoginActivity.this) {
            @Override
            public void onSwipeUp() {
                super.onSwipeUp();
                Intent i = new Intent(TeacherLoginActivity.this,MainActivity.class);
                startActivity(i);
            }
        });
    }
    boolean isEmail(EditText text) {
        CharSequence email = text.getText().toString();
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }
    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }
    boolean checkDataEntered() {
        boolean ok = true;
        if(isEmpty(et_email)){
            et_email.setError("Email is required!");
            ok = false;
        }
        if (!isEmail(et_email)) {
            et_email.setError("Enter valid email!");
            ok = false;
        }
        if (isEmpty(et_password)) {
            et_password.setError("Password is required!");
            ok = false;
        }
        return ok;
    }
    public void Login1(View view) {
        if(checkDataEntered()){
            email = et_email.getText().toString();
            password = et_password.getText().toString();
            type = "teacher";
            myUser = new User(email, password, type);
            Cursor myCursor = myDb.getUserLogin(myUser);
            Log.d("aaaaaaa",myCursor.toString());
            if (myCursor.getCount() != 0) {
                myCursor.moveToNext();
                Toast.makeText(getBaseContext(), "Login teacher "+
                        myCursor.getString(1)+"!", Toast.LENGTH_SHORT).show();
                SharedPreferences.Editor editor = pref.edit();
                editor.putString("name",myCursor.getString(1));
                editor.putString("password",myCursor.getString(2));
                editor.putString("type",type);
                editor.putString("id",myCursor.getString(0));
                editor.commit();
                Intent i = new Intent(TeacherLoginActivity.this,HomeActivity.class);
                startActivity(i);
            }else
                Toast.makeText(getBaseContext(), "This email or password is wrong! Pleas try again!", Toast.LENGTH_SHORT).show();
            //viewData(myUser);
            //showMessage("test", "test");
        }
    }
    public void viewData(User myUser) {
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor myCursor = myDb.getUserLogin(myUser);
                StringBuilder buffer = new StringBuilder();
                if(myCursor.getCount() == 0){
                    showMessage("Error.....","Nothing found.");
                    return;
                }
                while(myCursor.moveToNext())
                {
                    Log.d("asadsdasdada",myUser.toString());
                    Log.d("asadsdasdada","id :" +myCursor.getString(0)+ "\n"+
                            "name :" +myCursor.getString(1)+ "\n"+
                            "email :" + myCursor.getString(2)+ "\n" +
                            "password :" + myCursor.getString(3)+ "\n" +
                            "type :" + myCursor.getString(4)+ "\n"+"\n");
                    buffer.append("id :" +myCursor.getString(0)+ "\n"+
                            "name :" +myCursor.getString(1)+ "\n"+
                            "email :" + myCursor.getString(2)+ "\n" +
                            "password :" + myCursor.getString(3)+ "\n" +
                            "type :" + myCursor.getString(4)+ "\n"+"\n");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }
    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}