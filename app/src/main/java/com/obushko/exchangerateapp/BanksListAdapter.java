package com.obushko.exchangerateapp;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BanksListAdapter extends RecyclerView.Adapter<BanksListAdapter.BanksListViewHolder>{

    private final Context context;
    private final ArrayList<ListItemClass> banksLists;

    public BanksListAdapter( Context context, ArrayList<ListItemClass> banksLists) {
        this.context = context;
        this.banksLists = banksLists;
    }

    @NonNull
    @Override
    public BanksListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.card_item_banks,
                parent, false);
        return new BanksListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BanksListAdapter.BanksListViewHolder holder, int position) {
        ListItemClass currentItem = banksLists.get(position);

        String urlImage = currentItem.getUrlImage();
        String title = currentItem.getTitle();
        String data_1 = currentItem.getData_1();

        Picasso.get().load(urlImage).into(holder.imageView);
        holder.textViewTitle.setText(title);
        holder.textView1.setText(data_1);


    }

    @Override
    public int getItemCount() {
        return banksLists.size();
    }

    public static class BanksListViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textViewTitle;
        TextView textView1;
       // TextView textView2;


        public BanksListViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageViewBanks);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textView1 = itemView.findViewById(R.id.textView1);


        }
    }
}
