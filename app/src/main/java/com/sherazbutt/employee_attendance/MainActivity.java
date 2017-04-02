package com.sherazbutt.employee_attendance;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.Locale;
import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

public class MainActivity extends AppCompatActivity {
    DataBaseHelper db=new DataBaseHelper(this);
    TextView textView,textView2;
    ImageView adm,userr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Typeface face= Typeface.createFromAsset(getAssets(),"fonts/r_medium.ttf");
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        adm=(ImageView)findViewById(R.id.admin_image) ;
        userr=(ImageView)findViewById(R.id.user_image) ;


        db.deletesomedata();
        db.insertsomedata();



        userr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openstaringpoint = new Intent(MainActivity.this, Login.class);
                startActivity(openstaringpoint);
            }
        });


        adm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent openstaringpoint = new Intent(MainActivity.this, Admin_login.class);
                startActivity(openstaringpoint);
            }
        });

    }

}
