package com.sherazbutt.employee_attendance;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Calendar;

public class Register_employee extends AppCompatActivity {
    DataBaseHelper mydb = new DataBaseHelper(this);
      Button reg_employee,cancel_reg,emp_dob;
    EditText emp_name;
    EditText emp_department;
    EditText emp_contact;
    String em_name,em_department,em_dob,em_contact;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_employee);
        emp_name=(EditText)findViewById(R.id.e_name);
        emp_department=(EditText)findViewById(R.id.e_department);
        emp_contact=(EditText)findViewById(R.id.e_contact);
        emp_dob=(Button)findViewById(R.id.dob_btn);
        reg_employee=(Button)findViewById(R.id.register_employee);
         insertempdata();
        insertData();
    }
    public void insertempdata(){
        reg_employee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();

            }
        });
    }
    public void register(){
        initialize();
        if(!validate()){
            //Toast.makeText(this,"Sign up failed",Toast.LENGTH_SHORT).show();

        }
        else{
            signupsuccess();
        }
    }
    public  void signupsuccess(){


        boolean IsInserted= mydb.insertEmployeeData(emp_name.getText().toString(),emp_department.getText().toString(),emp_dob.getText().toString(),emp_contact.getText().toString());

        if(IsInserted==true)
        {
            Toast.makeText(Register_employee.this, ""+emp_name.getText().toString()+" Added Successfully", Toast.LENGTH_SHORT).show();
            emp_name.setText("");
            emp_name.requestFocus();
            emp_department.setText("");
            emp_contact.setText("");
            emp_dob.setText("");
            emp_dob.setHint("DATE OF BIRTH");
            emp_dob.setHintTextColor(getResources().getColor(R.color.white));
            mydb.close();

        }
        else
            Toast.makeText(Register_employee.this, "Error "+em_name+" Already Exist", Toast.LENGTH_SHORT).show();


    }
    public boolean validate(){
        boolean valid=true;
        if (em_name.isEmpty()||em_name.length()>32){
            emp_name.setError("Please Enter Valid Name");
            valid = false;
        }
        if (em_department.isEmpty()){
            emp_department.setError("Please Enter Valid Email");
            valid =false;
        }
        if (em_contact.isEmpty()){
            emp_contact.setError("Please Enter Password");
            valid = false;
        }
        if (em_dob.isEmpty()||em_dob.equals("DATE OF BIRTH")){
            emp_dob.setError("Password do not match");
            valid = false;
        }
        return  valid;

    }
    public  void initialize(){
        em_name=emp_name.getText().toString().trim();
        em_department=emp_department.getText().toString().trim();
        em_contact=emp_contact.getText().toString().trim();
        em_dob=emp_dob.getText().toString().trim();

    }
    private void insertData() {

        emp_dob.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                //   System.out.println("the selected " + mDay);
                DatePickerDialog dialog = new DatePickerDialog(Register_employee.this, new mDateSetListener(), mYear, mMonth, mDay);
                dialog.show();
            }

        });
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

            emp_dob.setText(new StringBuilder()
                    // Month is 0 based so add 1
                    .append(mMonth + 1).append("/").append(mDay).append("/")
                    .append(mYear).append(" "));
            System.out.println(emp_dob.getText().toString());


        }
    }
}
