package com.sherazbutt.employee_attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class User_menu_new extends AppCompatActivity {
    ImageView time_outt,ad_emp,takee_att,us_logout;
    Button u_exitt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_menu_new);

        ad_emp=(ImageView)findViewById(R.id.ad_emplo);
        takee_att=(ImageView)findViewById(R.id.t_att);
        time_outt=(ImageView)findViewById(R.id.tim_outt);
        us_logout=(ImageView)findViewById(R.id.logou_userr);
        u_exitt=(Button)findViewById(R.id.btn_user_exitt);


        takee_att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent onclick=new Intent(User_menu_new.this,Take_Attendance.class);
                startActivity(onclick);
            }
        });


        ad_emp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent onclick=new Intent(User_menu_new.this,Register_employee.class);
                startActivity(onclick);
            }

        });
        time_outt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent onclick=new Intent(User_menu_new.this,Lunch_timeout.class);
                startActivity(onclick);
            }

        });

        us_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(User_menu_new.this, "Signed out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(User_menu_new.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(User_menu_new.this, "Signed out", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
        u_exitt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                finish();
                System.exit(0);
                moveTaskToBack(true);
                User_menu_new.this.finish();
            }
        });
    }
}
