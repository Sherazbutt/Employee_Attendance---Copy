package com.sherazbutt.employee_attendance;

import android.content.Intent;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Menu_Admin_new extends AppCompatActivity {
    ImageView ad_user,em_detail,em_att,ad_logout;
    Button exitt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu__admin_new);
        ad_user=(ImageView)findViewById(R.id.ad_userr);
        em_att=(ImageView)findViewById(R.id.v_att);
        em_detail=(ImageView)findViewById(R.id.em_detailss);
        ad_logout=(ImageView)findViewById(R.id.logou_em);
        exitt=(Button)findViewById(R.id.btn_exitt);




        ad_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openstaringpoint = new Intent(Menu_Admin_new.this, Add_user.class);
                startActivity(openstaringpoint);
            }
        });
        em_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openattendence = new Intent(Menu_Admin_new.this,SecondActivity.class);
                startActivity(openattendence);
            }
        });
        em_att.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openattendence = new Intent(Menu_Admin_new.this,Att_Second_Activity.class);
                startActivity(openattendence);
            }
        });

        ad_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Menu_Admin_new.this, "Signed out", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Menu_Admin_new.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                Toast.makeText(Menu_Admin_new.this, "Signed out", Toast.LENGTH_SHORT).show();
                startActivity(intent);
                finish();
            }
        });
        exitt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                AppExit();
                finish();
                moveTaskToBack(true);
                Menu_Admin_new.this.finish();
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
