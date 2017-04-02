package com.sherazbutt.employee_attendance;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Att_details_Activity extends AppCompatActivity {

    DataBaseHelper helpher;
    List<Att_database_Model> dbList;
    int position;
    TextView tvid,tvname,tvdate,tvin,tvout,tvw_hours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_att_details_);



        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 5. get status value from bundle
        position = bundle.getInt("position");
        tvid =(TextView)findViewById(R.id.idd);

        tvname =(TextView)findViewById(R.id.namee);
        tvdate =(TextView)findViewById(R.id.datee);
        tvin =(TextView)findViewById(R.id.timeinn);
        tvout =(TextView)findViewById(R.id.timeoutt);
        tvw_hours =(TextView)findViewById(R.id.w_hourss);
        helpher = new DataBaseHelper(this);
        dbList= new ArrayList<Att_database_Model>();
        dbList = helpher.getDataFromDBforATT();

        if(dbList.size()>0){
            String id= dbList.get(position).getID();
            String name=dbList.get(position).getName();
            String date=dbList.get(position).getDate();
            String t_in=dbList.get(position).getT_in();
            String t_out=dbList.get(position).getT_out();
            String whours=dbList.get(position).getW_hours();
            tvid.setText(id);
            tvname.setText(name);
            tvdate.setText(date);
            tvin.setText(t_in);
            tvout.setText(t_out);
            tvw_hours.setText(whours);
        }

        Toast.makeText(Att_details_Activity.this, dbList.toString(), Toast.LENGTH_LONG);
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_details, menu);
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


}
