package com.sherazbutt.employee_attendance;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import  java.util.Calendar;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Take_Attendance extends AppCompatActivity {
    DataBaseHelper mydb = new DataBaseHelper(this);
    EditText w_hours;
    private int pHour;
    private int pMinute;
    Spinner spinner_status ;
    Spinner spinner ;
    Button save,date,Timein;

    String date_field,time_in_field,value_spiner,value_spiner1;

    public static final int TIME_DIALOG_ID=999;
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {

                public void onTimeSet(TimePicker view, int hourOfDay, int minute ) {
                    pHour = hourOfDay;
                    pMinute = minute;

                    updateDisplay();

                }
            };
    private void updateDisplay() {
        String state="00";
        Timein.setText(
                new StringBuilder()
                        .append(pad(pHour)).append(":")
                        .append(pad(pMinute)).append(":").append(state));

    }

    private static String pad(int c) {
        if (c >= 10)
            return String.valueOf(c);
        else
            return "0" + String.valueOf(c);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take__attendance);
        spinner_status = (Spinner) findViewById(R.id.spinner2);
        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayList<String> list = mydb.getAllEmployees();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spiner_layout, R.id.textt, list);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select Employee Name!");
       // spinner_status.setPrompt("Select Employee Status");




        Timein = (Button) findViewById(R.id.in_time);


        save = (Button) findViewById(R.id.save_button);
        date = (Button) findViewById(R.id.date_button);
        SimpleDateFormat sdf = new SimpleDateFormat( "dd-MM-yyyy" );
        date.setText( sdf.format( new Date() ));
        insertData();
        insertattenddata();
        Timein.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog(TIME_DIALOG_ID);
                updateDisplay();
            }
        });

        final Calendar cal = Calendar.getInstance();
        pHour = cal.get(Calendar.HOUR_OF_DAY);
        pMinute = cal.get(Calendar.MINUTE);

        spinerInsertion();  //inserting spinner data through this method

        // Initializing a String Array
        String[] att_list = new String[]{
                "Select Status",
                "Present",
                "Absent",
                "Leave",
                "Sick Leave"
        };


        final List<String> att_List = new ArrayList<>(Arrays.asList(att_list));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinner2_layout,att_List){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    // First item will be use for hint
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0){
                    // Set the hint text color gray
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };



        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinner2_layout);
        spinner_status.setAdapter(spinnerArrayAdapter);

        spinner_status.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                // If user change the default selection
                // First item is disable and it is used for hint
                if(position==1){
                    Timein.setEnabled(true);
                }
                if(position==2||position==3||position==4){
                    Timein.setText("00:00:00");
                    Timein.setEnabled(false);

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }


    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, pHour, pMinute, true);
        }
        return null;

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
                DatePickerDialog dialog = new DatePickerDialog(Take_Attendance.this, new mDateSetListener(), mYear, mMonth, mDay);
                dialog.show();

                Toast.makeText(Take_Attendance.this, "Successfully added", Toast.LENGTH_SHORT).show();
            }

        });*/
    }



public  void spinerInsertion(){
    value_spiner1=String.valueOf(spinner_status.getSelectedItem());
    value_spiner=String.valueOf(spinner.getSelectedItem());

}


    public void insertattenddata(){
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                spinerInsertion();
                register();

            }
        });
    }
    public void register(){
        initialize();
        if(!validate()){
           // Toast.makeText(Take_Attendance.this,"Sign up failed",Toast.LENGTH_SHORT).show();

        }
        else{
            signupsuccess();
        }
    }
    public  void signupsuccess(){


        boolean IsInserted= mydb.insertAttendanceData(value_spiner,date.getText().toString(),Timein.getText().toString(),value_spiner1);

        if(IsInserted==true)
        {
            Toast.makeText(Take_Attendance.this, ""+value_spiner+" Attendance Recorded", Toast.LENGTH_SHORT).show();

            //date.setText("Select_Date");
            Timein.setText("Time_in");
            mydb.close();

        }
        else
            Toast.makeText(Take_Attendance.this, "Error "+value_spiner+ " Attendance Already Recorded", Toast.LENGTH_SHORT).show();


    }
    public boolean validate(){
        boolean valid=true;
        if (date_field.isEmpty()){
            valid = false;
        }
        else if(value_spiner1.equals("Select Status")){
            Toast.makeText(Take_Attendance.this, "Select Status", Toast.LENGTH_SHORT).show();
            valid=false;
        }

        else if(time_in_field.isEmpty()){
            Toast.makeText(Take_Attendance.this, "Enter Time in", Toast.LENGTH_SHORT).show();
            valid=false;
        }

        return  valid;

    }
    public  void initialize(){
        date_field=date.getText().toString().trim();
        time_in_field=Timein.getText().toString().trim();


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

