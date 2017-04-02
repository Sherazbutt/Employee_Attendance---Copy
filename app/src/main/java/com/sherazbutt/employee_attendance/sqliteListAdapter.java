package com.sherazbutt.employee_attendance;



        import java.util.ArrayList;
        import java.util.Locale;

        import android.content.Context;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.BaseAdapter;
        import android.widget.TextView;

public class sqliteListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> userID;
    ArrayList<String> UserName;
    ArrayList<String> UserDATE;
    ArrayList<String> UserTime_In;
    ArrayList<String> UserTime_out ;


    public sqliteListAdapter(
            Context context2,
            ArrayList<String> ID_ArrayList, ArrayList<String> NAME_ArrayList, ArrayList<String> DATE_ArrayList, ArrayList<String> Time_in_ArrayList, ArrayList<String> Time_out_ArrayList)
    {

        this.context = context2;
        this.userID = ID_ArrayList;
        this.UserName = NAME_ArrayList;
        this.UserDATE = DATE_ArrayList;
        this.UserTime_In = Time_in_ArrayList;
        this.UserTime_out =Time_out_ArrayList ;

    }

    public int getCount() {
        // TODO Auto-generated method stub
        return userID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            child = layoutInflater.inflate(R.layout.listviewdatalayout, null);

            holder = new Holder();

            holder.textviewid = (TextView) child.findViewById(R.id.textViewID);
            holder.textviewname = (TextView) child.findViewById(R.id.textViewNAME);
            holder.textviewdate = (TextView) child.findViewById(R.id.textViewDate);
            holder.textviewphone_number = (TextView) child.findViewById(R.id.textViewPHONE_NUMBER);
            holder.textviewsubject = (TextView) child.findViewById(R.id.textViewSUBJECT);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.textviewid.setText(userID.get(position));
        holder.textviewname.setText(UserName.get(position));
        holder.textviewdate.setText(UserDATE.get(position));

        holder.textviewphone_number.setText(UserTime_In.get(position));
        holder.textviewsubject.setText(UserTime_out.get(position));

        return child;
    }

    public void getFilter() {

    }

    public class Holder {
        TextView textviewid;
        TextView textviewname;
        TextView textviewdate;
        TextView textviewphone_number;
        TextView textviewsubject;
    }

}