package com.obushko.exchangerateapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder>{

    private final Context context;
    private final ArrayList<ListItemClass> itemClasses;

    public ListAdapter( Context context, ArrayList<ListItemClass> itemClasses) {
        this.context = context;
        this.itemClasses = itemClasses;
    }

    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item,
                parent, false);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListViewHolder holder, int position) {
        ListItemClass currentItem = itemClasses.get(position);

        String data_1 = currentItem.getData_1();
        String data_2 = currentItem.getData_2();
        String data_3 = currentItem.getData_3();
        String data_4 = currentItem.getData_4();
        String data_5 = currentItem.getData_5();
        String data_6 = currentItem.getData_6();

        holder.tvData1.setText(data_1);
        holder.tvData2.setText(data_2);
        holder.tvData3.setText(data_3);
        holder.tvData4.setText(data_4);
        holder.tvData5.setText(data_5);
        holder.tvData6.setText(data_6);
    }

    @Override
    public int getItemCount() {
        return itemClasses.size();
    }

    public static class ListViewHolder extends RecyclerView.ViewHolder{

    TextView tvData1;
    TextView tvData2;
    TextView tvData3;
    TextView tvData4;
    TextView tvData5;
    TextView tvData6;


    public ListViewHolder(@NonNull View itemView) {
        super(itemView);
        tvData1 = itemView.findViewById(R.id.tvData1);
        tvData2 = itemView.findViewById(R.id.tvData2);
        tvData3 = itemView.findViewById(R.id.tvData3);
        tvData4 = itemView.findViewById(R.id.tvData4);
        tvData5 = itemView.findViewById(R.id.tvData5);
        tvData6 = itemView.findViewById(R.id.tvData6);
    }
}



}
