package com.sherazbutt.employee_attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin_menu extends AppCompatActivity {
    Button adduser;
    Button viewAttendance,employeedetails,logot,Exit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_menu);
        adduser = (Button) findViewById(R.id.add_user);
        viewAttendance = (Button) findViewById(R.id.view_attendance);
        employeedetails = (Button) findViewById(R.id.employee_details);
        logot = (Button) findViewById(R.id.logoutt);
        Exit = (Button) findViewById(R.id.exit_button);

        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openstaringpoint = new Intent(Admin_menu.this, Add_user.class);
                startActivity(openstaringpoint);
            }
        });
        employeedetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openattendence = new Intent(Admin_menu.this,SecondActivity.class);
                startActivity(openattendence);
            }
        });
        viewAttendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openattendence = new Intent(Admin_menu.this,Att_Second_Activity.class);
                startActivity(openattendence);
            }
        });

        logot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Admin_menu.this, "Signed out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Admin_menu.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(Admin_menu.this, "Signed out", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
        Exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AppExit();
                finish();
                moveTaskToBack(true);
                Admin_menu.this.finish();
                System.exit(0);
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




}
