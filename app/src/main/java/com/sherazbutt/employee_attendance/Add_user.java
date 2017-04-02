package com.sherazbutt.employee_attendance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by Sheraz Butt on 08-Mar-17.
 */
public class Add_user extends Activity{
    String st_name,st_email,st_pass,st_cpass,st_age;

    DataBaseHelper mydb;
    EditText name;
    EditText email;
    EditText pass;
    EditText c_pass;
    Button signup;
    Button cancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mydb = new DataBaseHelper(this);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user__new);
        name=(EditText)findViewById(R.id.input_name);
        email=(EditText)findViewById(R.id.input_email);
        pass=(EditText)findViewById(R.id.input_password);
        c_pass=(EditText)findViewById(R.id.confirm_password);

        signup=(Button)findViewById(R.id.signup);
        cancel=(Button)findViewById(R.id.btncancel);
        insertdata();


    }
    public void insertdata(){
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();

            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent openstaringpoint = new Intent(Add_user.this, User_menu_new.class);
                startActivity(openstaringpoint);
                finish();
            }
        });
    }
    public void register(){
        initialize();
        if(!validate()){
            //Toast.makeText(this,"",Toast.LENGTH_SHORT).show();

        }
        else{
            signupsuccess();
        }
    }
    public  void signupsuccess(){

        boolean IsInserted= mydb.insertData(name.getText().toString(),email.getText().toString(),pass.getText().toString(),c_pass.getText().toString());

        if(IsInserted==true)
        {
            Toast.makeText(Add_user.this, "User Added Successfully", Toast.LENGTH_SHORT).show();
            name.setText("");
            name.requestFocus();
            email.setText("");
            pass.setText("");
            c_pass.setText("");

        }
        else
            Toast.makeText(Add_user.this, "Error...Data not Insterted", Toast.LENGTH_SHORT).show();


    }
    public boolean validate(){
        boolean valid=true;
        if (st_name.isEmpty()||st_name.length()>32){
            name.setError("Please Enter Valid Name");
            valid = false;
        }
        if (st_email.isEmpty()||!Patterns.EMAIL_ADDRESS.matcher(st_email).matches()){
            email.setError("Please Enter Valid Email");
            valid =false;
        }
        if (st_pass.isEmpty()){
            pass.setError("Please Enter Password");
            valid = false;
        }
        if (!st_cpass.equals(st_pass)||st_cpass.isEmpty()){
            c_pass.setError("Password do not match");
            valid = false;
        }

        return  valid;

    }
    public  void initialize(){
         st_name=name.getText().toString().trim();
         st_email=email.getText().toString().trim();
        st_pass=pass.getText().toString().trim();
         st_cpass=c_pass.getText().toString().trim();


    }



}
