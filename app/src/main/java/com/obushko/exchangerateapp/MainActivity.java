package com.obushko.exchangerateapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private Document doc;
    private Thread secondThread;
    private Runnable runnable;
    private ArrayList<ListItemClass> itemClasses;
    private RecyclerView recyclerView;
    private ListAdapter listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.hasFixedSize();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        itemClasses = new ArrayList<>();


        runnable = new Runnable() {
            @Override
            public void run() {
                getWeb();
            }
        };
        secondThread = new Thread(runnable);
        secondThread.start();
    }

    private void getWeb(){
        try {
            doc = Jsoup.connect("https://minfin.com.ua/currency/").get();
            Elements tables = doc.getElementsByTag("tbody");
            Element our_table = tables.get(0);
            Element second_table = tables.get(1);
      //      Elements elements_from_table = our_table.children();
//            Element dollar = elements_from_table.get(0);
//            Elements dollar_elements = dollar.children();

           // Log.d("MyLog", "Tbody size: "+dollar_elements.get(0).text());


            for(int i = 0; i < our_table.childrenSize(); i++ ){
                ListItemClass items = new ListItemClass();
                items.setData_1(our_table.children().get(i).child(0).text());
                items.setData_2(our_table.children().get(i).child(1).text().substring(0,5));
                items.setData_3(our_table.children().get(i).child(2).text().substring(0,5));
                items.setData_4(our_table.children().get(i).child(3).text().substring(0,5));
                items.setData_5(second_table.children().get(i).child(1).text().substring(0,5));
                items.setData_6(second_table.children().get(i).child(2).text().substring(0,5));
                itemClasses.add(items);
            }
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    listAdapter = new ListAdapter(MainActivity.this, itemClasses);
                    recyclerView.setAdapter(listAdapter);
                }
            });



        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void onClick(View view) {
        Intent intent = new Intent(this, BanksActivity.class);
        startActivity(intent);
    }

    public void onClickChange(View view) {
        Intent intent = new Intent(this, ChangeActivity.class);
        startActivity(intent);
    }
}