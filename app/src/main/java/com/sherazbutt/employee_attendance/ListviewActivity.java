package com.sherazbutt.employee_attendance;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.SearchView;

public class ListviewActivity extends Activity {

    DataBaseHelper DataBaseHelper;
    SQLiteDatabase SQLITEDATABASE;
    Cursor cursor;
    sqliteListAdapter ListAdapter ;

    ArrayList<String> ID_ArrayList = new ArrayList<String>();
    ArrayList<String> NAME_ArrayList = new ArrayList<String>();
    ArrayList<String> DATE_ArrayList = new ArrayList<String>();

    ArrayList<String> Time_in_ArrayList = new ArrayList<String>();
    ArrayList<String> Time_out_ArrayList = new ArrayList<String>();
    ListView LISTVIEW;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        LISTVIEW = (ListView) findViewById(R.id.listView1);

        DataBaseHelper= new DataBaseHelper(this);

    }


    @Override
    protected void onResume() {

        ShowSQLiteDBdata() ;

        super.onResume();
    }

    private void ShowSQLiteDBdata() {

        SQLITEDATABASE = DataBaseHelper.getWritableDatabase();

        cursor = SQLITEDATABASE.rawQuery("SELECT * FROM attendance", null);

        ID_ArrayList.clear();
        NAME_ArrayList.clear();
        DATE_ArrayList.clear();
        Time_in_ArrayList.clear();
        Time_out_ArrayList.clear();

        if (cursor.moveToFirst()) {
            do {
                ID_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.ATT_COL_1)));

                NAME_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.ATT_COL_2)));
                DATE_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.ATT_COL_3)));

                Time_in_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.ATT_COL_4)));

                Time_out_ArrayList.add(cursor.getString(cursor.getColumnIndex(DataBaseHelper.ATT_COL_5)));

            } while (cursor.moveToNext());
        }
        ListAdapter= new sqliteListAdapter(ListviewActivity.this,ID_ArrayList,DATE_ArrayList,NAME_ArrayList,Time_in_ArrayList,Time_out_ArrayList);

        ListAdapter= new sqliteListAdapter(this, ID_ArrayList, NAME_ArrayList,DATE_ArrayList, Time_in_ArrayList,Time_out_ArrayList);

        LISTVIEW.setAdapter(ListAdapter);

        cursor.close();
    }
}

