package com.sherazbutt.employee_attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class User_menu extends AppCompatActivity {
    Button attendance,employee,report,user_logout,user_exit,emp_time_out;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu);
        attendance=(Button)findViewById(R.id.take_attendance);
        employee =(Button)findViewById(R.id.register_user);
        user_logout=(Button)findViewById(R.id.u_logout);
        user_exit=(Button)findViewById(R.id.u_exit);
        emp_time_out=(Button)findViewById(R.id.time_out_button);

        attendance.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent onclick=new Intent(User_menu.this,Take_Attendance.class);
                startActivity(onclick);
            }
        });


        employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent onclick=new Intent(User_menu.this,Register_employee.class);
                startActivity(onclick);
            }

        });
        emp_time_out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent onclick=new Intent(User_menu.this,Lunch_timeout.class);
                startActivity(onclick);
            }

        });

        user_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(User_menu.this, "Signed out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(User_menu.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(User_menu.this, "Signed out", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
        user_exit.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
                moveTaskToBack(true);
                User_menu.this.finish();
            }
        });

    }

}
