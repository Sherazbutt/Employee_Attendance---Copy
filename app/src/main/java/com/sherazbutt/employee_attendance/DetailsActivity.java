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

public class DetailsActivity extends AppCompatActivity {
    DataBaseHelper helpher;
    List<DatabaseModel> dbList;
    int position;
    TextView tvname,tv_id,tv_dep,tvaddress,tvbranch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


       // Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
      //  setSupportActionBar(toolbar);
       // getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        // 5. get status value from bundle
        position = bundle.getInt("position");

        tvname =(TextView)findViewById(R.id.textView26);
        tv_id =(TextView)findViewById(R.id.textView22);
        tv_dep =(TextView)findViewById(R.id.textView27);
        tvaddress =(TextView)findViewById(R.id.textView28);
        tvbranch =(TextView)findViewById(R.id.textView29);
        helpher = new DataBaseHelper(this);
        dbList= new ArrayList<DatabaseModel>();
        dbList = helpher.getDataFromDB();

        if(dbList.size()>0){
            String name= dbList.get(position).getName();
            String _id=dbList.get(position).getID();
            String dep=dbList.get(position).getDepart();
            String _contact=dbList.get(position).getContact();
            String _dob=dbList.get(position).getDob();
            tvname.setText(name);
            tv_id.setText(_id);
            tv_dep.setText(dep);
            tvaddress.setText(_contact);
            tvbranch.setText(_dob);
        }

        Toast.makeText(DetailsActivity.this, dbList.toString(), Toast.LENGTH_LONG);
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
