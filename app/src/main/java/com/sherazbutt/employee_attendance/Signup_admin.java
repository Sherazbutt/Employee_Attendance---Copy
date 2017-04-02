package com.sherazbutt.employee_attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Signup_admin extends AppCompatActivity {

    DataBaseHelper mydb;
    EditText _email;
    EditText _pass;
    EditText _c_pass;
    EditText _company_name;
    Button _signup;
    Button _cancel;
    String st_company_name,st_email,st_pass,st_cpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mydb = new DataBaseHelper(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_admin);
        _company_name = (EditText) findViewById(R.id.company_name);
        _email = (EditText) findViewById(R.id.Email);
        _pass = (EditText) findViewById(R.id.s_password);
        _c_pass = (EditText) findViewById(R.id.confirm_password);
        _signup = (Button) findViewById(R.id.sign_up);
        _cancel = (Button) findViewById(R.id.cancel);
        insertdata();


    }
    public void insertdata(){
        _signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Signup_admin.this,"Working",Toast.LENGTH_SHORT).show();
                register();

            }
        });
    }
    public void register(){
        initialize();
        if(!validate()){
            Toast.makeText(this,"Sign up failed",Toast.LENGTH_SHORT).show();

        }
        else{
            signupsuccess();
        }
    }
    public  void signupsuccess(){

        boolean IsInserted= mydb.insertAdminData(_company_name.getText().toString(),_email.getText().toString(),_pass.getText().toString(),_c_pass.getText().toString());
        if(IsInserted==true)
        {
            Toast.makeText(Signup_admin.this, "Sign up Successfully", Toast.LENGTH_SHORT).show();
            _company_name.setText("");
            _company_name.requestFocus();
            _email.setText("");
            _pass.setText("");
            _c_pass.setText("");

        }
        else
            Toast.makeText(Signup_admin.this, "Error...Data not Insterted", Toast.LENGTH_SHORT).show();


    }
    public boolean validate(){
        boolean valid=true;
        if (st_company_name.isEmpty()||st_company_name.length()>32){
            _company_name.setError("Please Enter Valid Name");
            valid = false;
        }
        if (st_email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(st_email).matches()){
            _email.setError("Please Enter Valid Email");
            valid =false;
        }
        if (st_pass.isEmpty()){
            _pass.setError("Please Enter Password");
            valid = false;
        }
        if (!st_cpass.equals(st_pass)||st_cpass.isEmpty()){
            _c_pass.setError("Password do not match");
            valid = false;
        }

        return  valid;

    }
    public  void initialize(){
        st_company_name=_company_name.getText().toString().trim();
        st_email=_email.getText().toString().trim();
        st_pass=_pass.getText().toString().trim();
        st_cpass=_c_pass.getText().toString().trim();


    }
}
