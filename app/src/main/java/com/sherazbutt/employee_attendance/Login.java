package com.sherazbutt.employee_attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    DataBaseHelper mydb;
    EditText u_name;
    EditText passw;
    Button login;
    Button cancel;
    String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mydb = new DataBaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        u_name = (EditText) findViewById(R.id.username);
        passw = (EditText) findViewById(R.id.pass);
        login = (Button) findViewById(R.id.login);
        cancel = (Button) findViewById(R.id.btncancel);

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openstaringpoint = new Intent(Login.this, MainActivity.class);
                startActivity(openstaringpoint);
                finish();
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login() {
        initilize();
        if (!validate()) {
            Toast.makeText(this, "Login Error", Toast.LENGTH_SHORT).show();
        } else {
            loginsuccess();
        }
    }



    public void loginsuccess() {
        String str=u_name.getText().toString();
        String pass=passw.getText().toString();
        String password =mydb.searchdata(str);
        if(pass.equals(password)){
            Intent openstaringpoint = new Intent(Login.this, User_menu_new.class);
            startActivity(openstaringpoint);
            finish();
            Toast.makeText(this,"Login Successful",Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(this,"Username and Password Don't Match",Toast.LENGTH_SHORT).show();
            passw.setText("");

        }
    }

    public void initilize() {
        username = u_name.getText().toString().trim();
        password = passw.getText().toString().trim();
    }

    public boolean validate() {
        boolean valid =true;
        if (username.isEmpty()) {
            u_name.setError("Please Enter Username");
            valid=false;
        }
        if (password.isEmpty()) {
            passw.setError("Please Enter Username");
            valid=false;
        }
        return  valid;
    }


}
