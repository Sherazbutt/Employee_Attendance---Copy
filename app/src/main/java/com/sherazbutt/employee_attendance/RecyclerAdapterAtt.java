package com.sherazbutt.employee_attendance;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapterAtt extends RecyclerView.Adapter<RecyclerAdapterAtt.ViewHolder> {

    static List<Att_database_Model> dbList;
    static Context context;
    LinearLayoutManager ml;

    RecyclerAdapterAtt(Context context, List<Att_database_Model> dbList) {
        this.dbList = new ArrayList<Att_database_Model>();
        this.context = context;
        this.dbList = dbList;



    }

    @Override
    public RecyclerAdapterAtt.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemLayoutView = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.att_row_item, null);

        // create ViewHolder

        ViewHolder viewHolder = new ViewHolder(itemLayoutView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapterAtt.ViewHolder holder, int position) {

        holder.name.setText(dbList.get(position).getDate());
        holder.id.setText(dbList.get(position).getName());

    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView name, id;

        public ViewHolder(View itemLayoutView) {
            super(itemLayoutView);
            name = (TextView) itemLayoutView.findViewById(R.id.att_rvidd);
            id = (TextView) itemLayoutView.findViewById(R.id.att_rvnamee);
            itemLayoutView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(context, Att_details_Activity.class);

            Bundle extras = new Bundle();
            extras.putInt("position", getAdapterPosition());
            intent.putExtras(extras);

            /*
            int i=getAdapterPosition();
            intent.putExtra("position", getAdapterPosition());*/
            context.startActivity(intent);
            Toast.makeText(RecyclerAdapterAtt.context, "You have clicked Row " + getAdapterPosition(), Toast.LENGTH_LONG).show();
        }
    }
}
