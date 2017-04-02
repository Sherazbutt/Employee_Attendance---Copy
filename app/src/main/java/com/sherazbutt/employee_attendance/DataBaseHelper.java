
        package com.sherazbutt.employee_attendance;

        import android.content.ContentValues;
        import android.content.Context;
        import android.database.Cursor;
        import android.database.SQLException;
        import android.database.sqlite.SQLiteDatabase;
        import android.database.sqlite.SQLiteDatabase.CursorFactory;
        import android.database.sqlite.SQLiteOpenHelper;
        import android.util.Log;

        import java.util.ArrayList;
        import java.util.List;

        class DataBaseHelper extends SQLiteOpenHelper {






    public static  final String DATABASE_NAME="employee.db";
    public static  final String DATABASE_TABLE="user";

    public static  final String COL_1="id";
    public static  final String COL_2="name";
    public static  final String COL_3="email";
    public static  final String COL_4="password";
    public static  final String COL_5="confirm_pass";


    public static final String TABLE_ADMIN = "admin";
    public static  final String ADM_COL_1="id";
    public static  final String ADM_COL_2="name";
    public static  final String ADM_COL_3="email";
    public static  final String ADM_COL_4="password";
    public static  final String ADM_COL_5="confirm_pass";

    public static final String TABLE_Employe = "employe";
    public static  final String EMP_COL_1="id";
    public static  final String EMP_COL_2="name";
    public static  final String EMP_COL_3="department";
    public static  final String EMP_COL_4="contact";
    public static  final String EMP_COL_5="DOB";

            public static final String TABLE_Attendance = "attendance";
            public static  final String ATT_COL_1="_id";
            public static  final String ATT_COL_2="name";
            public static  final String ATT_COL_3="date";
            public static  final String ATT_COL_4="timein";
            public static  final String ATT_COL_5="timeout";
            public static  final String ATT_COL_6="Attendance";
            public static  final String ATT_COL_7="workinghours";
            //private static final String TAG = null;


            public DataBaseHelper(Context context) {
        super(context,DATABASE_NAME , null, 11);


    }

    @Override
    public void onCreate(SQLiteDatabase _db) {
        try {
            _db.execSQL("create table "+DATABASE_TABLE+"(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email VARCHAR,password VARCHAR,confirm_pass VARCHAR)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            _db.execSQL("create table "+TABLE_ADMIN+"(id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT,email VARCHAR,password VARCHAR,confirm_pass VARCHAR,age INTEGER)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            _db.execSQL("create table "+TABLE_Employe+"(id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR UNIQUE,department VARCHAR,contact VARCHAR,DOB INTEGER)");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            _db.execSQL("create table "+TABLE_Attendance+"(_id INTEGER PRIMARY KEY AUTOINCREMENT,name VARCHAR,date VARCHAR,timein VARCHAR,timeout VARCHAR,Attendance VARCHAR,workinghours VARCHAR,UNIQUE(name,date))");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }



    @Override
    public void onUpgrade(SQLiteDatabase _db, int _oldVersion, int _newVersion) {
        _db.execSQL("DROP TABLE IF EXISTS"+DATABASE_TABLE);
        _db.execSQL("DROP TABLE IF EXISTS"+TABLE_ADMIN);
        _db.execSQL("DROP TABLE IF EXISTS"+TABLE_Employe);
        _db.execSQL("DROP TABLE IF EXISTS"+TABLE_Attendance);
        onCreate(_db);

    }
    public boolean insertData(String name,String email,String passwd,String c_pass){



        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,email);
        contentValues.put(COL_4,passwd);
        contentValues.put(COL_5,c_pass);

        long result=db.insert(DATABASE_TABLE,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
            public long createAdmin(String name, String pass,
                                      String c_pass) {
                SQLiteDatabase db= this.getWritableDatabase();

                ContentValues initialValues = new ContentValues();
                initialValues.put(ADM_COL_3, name);
                initialValues.put(ADM_COL_4, pass);
                initialValues.put(ADM_COL_5, c_pass);

                return db.insert(TABLE_ADMIN, null, initialValues);
            }
            public void insertsomedata(){

                createAdmin("acuity111@gmail.com","acuity111","acuity111");
                createAdmin("acuity123@gmail.com","acuity123","acuity123");
                createAdmin("a@gmail.com","a","a");
            }
            public boolean deletesomedata(){
                SQLiteDatabase db= this.getWritableDatabase();

                int doneDelete = 0;
                doneDelete = db.delete(TABLE_ADMIN, null , null);
                 Integer.toString(doneDelete);
                return doneDelete > 0;
            }



    public boolean insertEmployeeData(String name,String depart,String contc,String dob){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(EMP_COL_2,name);
        contentValues.put(EMP_COL_3,depart);
        contentValues.put(EMP_COL_4,contc);
        contentValues.put(EMP_COL_5,dob);
        long result=db.insert(TABLE_Employe,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;


    }


            public boolean insertAttendanceData(String name,String date,String time_in ,String attend) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                contentValues.put(ATT_COL_2,name);
                contentValues.put(ATT_COL_3,date);
                contentValues.put(ATT_COL_4,time_in);
                contentValues.put(ATT_COL_6,attend);

                long result = db.insert(TABLE_Attendance, null, contentValues);
                if (result == -1)
                    return false;
                else
                    return true;
            }
                public  boolean  updateAttData(String name,String time_out,String date,String w_hours) {
                SQLiteDatabase db = this.getWritableDatabase();
                ContentValues contentValues = new ContentValues();
                    contentValues.put(ATT_COL_2,name);
                    contentValues.put(ATT_COL_5,time_out);
                    contentValues.put(ATT_COL_3,date);
                    contentValues.put(ATT_COL_7,w_hours);

                long result = db.update(TABLE_Attendance,contentValues,ATT_COL_2+ " = ? AND " + ATT_COL_3+ " = ?",
                        new String[] { name, date } );
                if (result == -1)
                    return false;
                else
                    return true;
            }



    public boolean insertAdminData(String name,String email,String passwd,String c_pass){
        SQLiteDatabase db= this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(ADM_COL_2,name);
        contentValues.put(ADM_COL_3,email);
        contentValues.put(ADM_COL_4,passwd);
        contentValues.put(ADM_COL_5,c_pass);
        long result=db.insert(TABLE_ADMIN,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;


    }

    public String searchdata( String email) {

        final String TABLE_NAME = "user";

        String selectQuery = "SELECT  email ,password FROM " + TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String c,d;
        d="not found";
        if (cursor.moveToFirst()) {
            do {
                c=cursor.getString(0);
                if (c.equals(email)) {

                    d = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }

        return d;

    }

    public String searchAdmindata( String email) {

        final String TABLE_NAME = "admin";

        String selectQuery = "SELECT  email ,password FROM " + TABLE_NAME;
        SQLiteDatabase db  = this.getReadableDatabase();
        Cursor cursor      = db.rawQuery(selectQuery, null);
        String a,b;
        b="not found";
        if (cursor.moveToFirst()) {
            do {
                a=cursor.getString(0);
                if (a.equals(email)) {

                    b = cursor.getString(1);
                    break;
                }
            } while (cursor.moveToNext());
        }

        return b;

    }
    public ArrayList<String> getAllEmployees(){

        ArrayList<String> list=new ArrayList<String>();
        // Open the database for reading
        SQLiteDatabase db = this.getReadableDatabase();
        // Start the transaction.
        db.beginTransaction();


        try
        {


            String selectQuery = "SELECT  name FROM "+ TABLE_Employe;
            Cursor cursor = db.rawQuery(selectQuery, null);
            if(cursor.getCount() >0)

            {
                while (cursor.moveToNext()) {
                    // Add name to arraylist
                    String pname= cursor.getString(cursor.getColumnIndex("name"));
                    list.add(pname);

                }


            }
            db.setTransactionSuccessful();

        }
        catch (Exception e)
        {
            e.printStackTrace();

        }
        finally
        {
            db.endTransaction();
            // End the transaction.
            db.close();

            // Close database
        }
        return list;


    }

            /* Retrive  data from database for employees */
            public List<DatabaseModel> getDataFromDB(){
                List<DatabaseModel> modelList = new ArrayList<DatabaseModel>();
                String query = "select * from "+TABLE_Employe;

                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(query,null);

                if (cursor.moveToFirst()){
                    do {
                        DatabaseModel model = new DatabaseModel();
                        model.setName(cursor.getString(1));
                        model.setID(cursor.getString(0));
                        model.setDepart(cursor.getString(2));
                        model.setContact(cursor.getString(3));
                        model.setDob(cursor.getString(4));

                        modelList.add(model);
                    }while (cursor.moveToNext());
                }


                Log.d("Employee Details", modelList.toString());


                return modelList;
            }

            public List<Att_database_Model> getDataFromDBforATT(){
                List<Att_database_Model> modelList = new ArrayList<Att_database_Model>();
                String query = "select * from "+TABLE_Attendance;

                SQLiteDatabase db = this.getWritableDatabase();
                Cursor cursor = db.rawQuery(query,null);

                if (cursor.moveToFirst()){
                    do {
                        Att_database_Model model = new Att_database_Model();
                        model.setID(cursor.getString(0));
                        model.setName(cursor.getString(1));
                        model.setDate(cursor.getString(2));
                        model.setT_in(cursor.getString(3));
                        model.setT_out(cursor.getString(4));
                        model.setW_hours(cursor.getString(6));

                        modelList.add(model);
                    }while (cursor.moveToNext());
                }


                Log.d("Attendance Details", modelList.toString());


                return modelList;
            }




        }