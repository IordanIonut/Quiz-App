package com.example.androidproject;

import Classes.DatabaseHelper;
import Classes.OnSwipeTouchListener;
import Classes.User;
import android.content.Intent;
import android.database.Cursor;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;

public class TeacherRegisterActivity extends AppCompatActivity {
    EditText et_name, et_email, et_password, et_repassword;
    String name, email, password, type;
    Button btn_register;
    DatabaseHelper myDb;
    User myUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_register);
        LinearLayout layout = (LinearLayout) findViewById(R.id.register_id2);
        et_name = (EditText) findViewById(R.id.et_name);
        et_email = (EditText) findViewById(R.id.et_email);
        et_password = (EditText) findViewById(R.id.et_password);
        et_repassword = (EditText) findViewById(R.id.et_repassword);
        btn_register = (Button)  findViewById(R.id.btn_register);
        myDb = new DatabaseHelper(this);

        layout.setOnTouchListener(new OnSwipeTouchListener(TeacherRegisterActivity.this) {
            @Override
            public void onSwipeDown() {
                super.onSwipeDown();
                Intent i = new Intent(TeacherRegisterActivity.this,MainActivity.class);
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
        if (isEmpty(et_name)) {
            et_name.setError("You must enter name to register!");
            ok = false;
        }
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
        if (isEmpty(et_repassword)) {
            et_repassword.setError("Lassword is required!");
            ok = false;
        }
        if(isEmpty(et_password) != isEmpty(et_repassword)) {
            Toast.makeText(this, "The Password and Re-Try Password are not the same!", Toast.LENGTH_SHORT).show();
            ok = false;
        }
        return  ok;
    }
    public void Register1(View view) {
        if(checkDataEntered()){
            name = et_name.getText().toString();
            email = et_email.getText().toString();
            password = et_password.getText().toString();
            type = "teacher";
            myUser = new User(1,name, email, password, type);
            if (myDb.insertDataUser(myUser)) {
                Toast.makeText(getBaseContext(), "User Register!", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(TeacherRegisterActivity.this,MainActivity.class);
                startActivity(i);
            }else
                Toast.makeText(getBaseContext(), "This email is use!", Toast.LENGTH_SHORT).show();
            //viewData(myUser);
            //showMessage("test", "test");
            viewData();
        }
    }
    public void viewData() {
                Cursor myCursor = myDb.getAllDataUser();
                StringBuffer buffer = new StringBuffer();
                if(myCursor.getCount() == 0){
                    Log.d("Error.....","Nothing found.");
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
    }
}