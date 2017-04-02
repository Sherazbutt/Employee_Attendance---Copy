package com.sherazbutt.employee_attendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Lunch_timeout extends AppCompatActivity {
    DataBaseHelper mydb = new DataBaseHelper(this);
    Cursor cursor;
    SQLiteDatabase _db;
    String GetSQliteQuery;
    EditText Time_out,date_show,w_hours;
    Button date,T_w_hours,tin,tout;
    Spinner spinner_Employe_name;
    private int pHour;
    private int pMinute;
    String time_out_field,value_spiner,time_in_field,working_hours;
    public static final int TIME_DIALOG_ID=1;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {

                public void onTimeSet(TimePicker view, int hourOfDay, int minute ) {
                    pHour = hourOfDay;
                    pMinute = minute;

                    updateDisplay();
                    initialize();

                }
            };
    private void updateDisplay() {
        String state="00";
        tout.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)).append(":").append(state));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        spinner_Employe_name=(Spinner)findViewById(R.id.spinner);
        setContentView(R.layout.activity_lunch_timeout);
        //Time_out = (EditText) findViewById(R.id.out);
        tin=(Button) findViewById(R.id.t_in);
        tout=(Button) findViewById(R.id.t_out);
        T_w_hours=(Button)findViewById(R.id.w_hours);

        tin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ini();
                gettime();
            }
        });
        T_w_hours.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ini();
                cal_working_hours();
            }
        });
        tout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                showDialog(TIME_DIALOG_ID);
            }
        });

        final Calendar cal = Calendar.getInstance();
        pHour = cal.get(Calendar.HOUR_OF_DAY);
        pMinute = cal.get(Calendar.MINUTE);
        Button save=(Button)findViewById(R.id.time_out_save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
        date=(Button)findViewById(R.id.date_btn);
        insertData();
        mydb.getReadableDatabase();
        spinner_Employe_name = (Spinner) findViewById(R.id.spinner);
        ArrayList<String> list = mydb.getAllEmployees();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.time_out_spinner, R.id.timespinner, list);
        spinner_Employe_name.setAdapter(adapter);
        spinner_Employe_name.setPrompt("Select Employee Name!");
        // spinner_status.setPrompt("Select Employee Status");

        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" );
        date.setText( sdf.format( new Date() ));



    }

    public  void gettime() {
        //GetSQliteQuery = "SELECT * FROM attendance where name ='"+value_spiner+"'";//AND date='"+date_value+"'";
        GetSQliteQuery = "SELECT * FROM attendance where date ='"+date.getText().toString()+"' AND name='"+value_spiner+"'";
        _db = openOrCreateDatabase("employee.db", Context.MODE_PRIVATE, null);

        // cursor = _db.rawQuery(mydb.ATT_COL_2 + "=?" + " and "  + mydb.ATT_COL_3 + "=?",
        //new String[] {value_spiner,date_value,null , null , null});
        /*cursor=_db.query(true, mydb.TABLE_Attendance, new String[] {
                        mydb.ATT_COL_4,},
               mydb.ATT_COL_2 + "=?" + " and "  +
                        mydb.ATT_COL_3 + "=?",
                new String[] {value_spiner,date_value},
                null, null, null , null);*/

        cursor = _db.rawQuery(GetSQliteQuery, null);
        if (cursor != null&& cursor.moveToFirst())
            if ( cursor.moveToFirst()){
                do {
                    GetSQLiteDatabaseRecords();
                }while (cursor.moveToNext());
            }else{
                Toast.makeText(Lunch_timeout.this, "No match", Toast.LENGTH_SHORT).show();
            }

    }

    public void cal_working_hours(){

        //HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(time_in_field);
            d2 = format.parse(time_out_field);

            //in milliseconds
            long diff = d2.getTime() - d1.getTime();

            long diffSeconds = (diff / 1000 % 60);
            long diffMinutes = diff / (60 * 1000) % 60;
            long diffHours = diff / (60 * 60 * 1000) % 24;
            long diffDays = diff / (24 * 60 * 60 * 1000);

            System.out.print(diffDays + " days, ");
            System.out.print(diffHours + " hours, ");
            System.out.print(diffMinutes + " minutes, ");
            System.out.print(diffSeconds + " seconds.");
            //showing Toatal working hours
            //Toast.makeText(Lunch_timeout.this, T_w_hours.toString(), Toast.LENGTH_SHORT).show();
            T_w_hours.setText(diffHours + " HOURS "+diffMinutes + " Minutes ");


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void GetSQLiteDatabaseRecords()
    {
        tin.setText(cursor.getString(3).toString());
    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, pHour, pMinute, true);

        }
        return null;

    }
    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);

    }
    public void register(){
        initialize();
        if(!validate()){
          //  Toast.makeText(Lunch_timeout.this,"Sign up failed",Toast.LENGTH_SHORT).show();

        }
        else{
            signupsuccess();
        }
    }
    public  void signupsuccess(){


        boolean IsInserted= mydb.updateAttData(value_spiner,tout.getText().toString(),date.getText().toString(),T_w_hours.getText().toString());

        if(IsInserted==true)
        {
            Toast.makeText(Lunch_timeout.this, ""+value_spiner+ " Attendance Recorded", Toast.LENGTH_SHORT).show();



            tout.setHint("TIME OUT");
            tout.setText("");
            tin.setHint("TIME IN");
            tin.setText("");
            T_w_hours.setHint("WORKING HOURS");
            T_w_hours.setText("");
           // Time_out.setEnabled(false);
            mydb.close();

        }
        else
            Toast.makeText(Lunch_timeout.this, "Error "+value_spiner+" Attendance Already Exist", Toast.LENGTH_SHORT).show();

    }
    public boolean validate(){
        boolean valid=true;

        if (time_in_field.isEmpty()){
            Toast.makeText(Lunch_timeout.this, "select time in First", Toast.LENGTH_SHORT).show();
            valid = false;
        }

        else if (time_out_field.isEmpty()){
            Toast.makeText(Lunch_timeout.this, "time out not selected", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        else if (working_hours.isEmpty()){

            Toast.makeText(Lunch_timeout.this, "working hours not selected", Toast.LENGTH_SHORT).show();
            valid = false;
        }
        return  valid;
    }
    public void ini(){
        time_in_field=tin.getText().toString().trim();
        value_spiner=String.valueOf(spinner_Employe_name.getSelectedItem());
        working_hours=T_w_hours.getText().toString().trim();
    }
    public  void initialize(){
        time_in_field=tin.getText().toString().trim();
        working_hours=T_w_hours.getText().toString().trim();
        time_out_field=tout.getText().toString().trim();
        value_spiner=String.valueOf(spinner_Employe_name.getSelectedItem());
    }
    private void insertData() {

       /* date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                //   System.out.println("the selected " + mDay);
                DatePickerDialog dialog = new DatePickerDialog(Lunch_timeout.this, new mDateSetListener(), mYear, mMonth, mDay);
                dialog.show();

                Toast.makeText(Lunch_timeout.this, "Successfully added", Toast.LENGTH_SHORT).show();
            }

        });*/
    }

class mDateSetListener implements DatePickerDialog.OnDateSetListener {

    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear,
                          int dayOfMonth) {
        // TODO Auto-generated method stub
        // getCalender();
        int mYear = year;
        int mMonth = monthOfYear;
        int mDay = dayOfMonth;

        /*date.setText(new StringBuilder()
                // Month is 0 based so add 1
                .append(mMonth + 1).append("/").append(mDay).append("/")
                .append(mYear).append(" "));
        System.out.println(date.getText().toString());*/


    }
}
}