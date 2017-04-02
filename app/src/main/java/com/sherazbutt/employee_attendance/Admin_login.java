package com.sherazbutt.employee_attendance;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin_login extends AppCompatActivity {
    DataBaseHelper mydb;
    EditText u_name_admin;
    EditText passw_admin;
    Button signin_admin;
    Button new_account_admin;
    String username_admin, password_admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mydb = new DataBaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        Typeface face= Typeface.createFromAsset(getAssets(),"fonts/fontawesome.otf");
        u_name_admin = (EditText) findViewById(R.id.username_admin);
        u_name_admin.setTypeface(face);
        passw_admin = (EditText) findViewById(R.id.pass_admin);
        signin_admin = (Button) findViewById(R.id.login_admin);
        new_account_admin = (Button) findViewById(R.id.btncreate_admin);

        new_account_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AppExit();
                System.exit(0);
                finish();


            }
        });
        signin_admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
                Toast.makeText(Admin_login.this,"Working",Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void AppExit()
    {

        this.finish();
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
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
        
        String strr=u_name_admin.getText().toString();
        String passwr=passw_admin.getText().toString();
        String password =mydb.searchAdmindata(strr);
        if(passwr.equals(password)) {
            Intent openstaringpoint = new Intent(Admin_login.this, Menu_Admin_new.class);
            startActivity(openstaringpoint);
            finish();
            Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();


        }else{
            Toast.makeText(this,"Username and Password Don't Match",Toast.LENGTH_SHORT).show();
            passw_admin.setText("");

        }
    }

    public void initilize() {
        username_admin = u_name_admin.getText().toString().trim();
        password_admin = passw_admin.getText().toString().trim();
    }

    public boolean validate() {
        boolean valid =true;
        if (username_admin.isEmpty()) {
            u_name_admin.setError("Please Enter Username");
            valid=false;
        }
        if (password_admin.isEmpty()) {
            passw_admin.setError("Please Enter Username");
            valid=false;
        }
        return  valid;
    }
}
