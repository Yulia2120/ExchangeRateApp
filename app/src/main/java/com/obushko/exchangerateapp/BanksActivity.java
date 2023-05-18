package com.obushko.exchangerateapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class BanksActivity extends AppCompatActivity {

    private Document doc;
    private Thread secondThread;
    private Runnable runnable;
    private ArrayList<ListItemClass> banksLists;
    private RecyclerView recyclerViewBanks;
    private BanksListAdapter banksListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_banks);
        init();
        setTitle("Курс валют в банках Украины");

    }

    private void init(){
        recyclerViewBanks = findViewById(R.id.recyclerViewBanks);
        recyclerViewBanks.hasFixedSize();
        recyclerViewBanks.setLayoutManager(new LinearLayoutManager(this));
        banksLists = new ArrayList<>();


        runnable = new Runnable() {
            @Override
            public void run() {
                 getWeb();
            }
        };
        secondThread = new Thread(runnable);
        secondThread.start();


    }

    private void getWeb() {
        try {
            doc = Jsoup.connect("https://minfin.com.ua/currency/").get();
            Elements tables = doc.getElementsByTag("tbody");
            Element table = tables.get(2);

            Elements elements_from_table = table.children();
//            Element dollar = elements_from_table.get(0);
//            Elements dollar_elements = dollar.children();

            Log.d("SecondLog", "Tbody size: " + elements_from_table.get(0).text());
            //Log.d("SecondLog", "Tbody size: "+tables.get(2).text());


           // Elements links = doc.getElementsByTag("src");

            for (int i = 0; i < table.childrenSize(); i++) {
                ListItemClass items = new ListItemClass();

              //  items.setUrlImage(table.children().get(i).child(0).text());
                items.setTitle(table.children().get(i).child(0).text());
                items.setData_1(table.children().get(i).child(1).text());
                items.setData_2(table.children().get(i).child(2).text());
                banksLists.add(items);

            }


            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    banksListAdapter = new BanksListAdapter(BanksActivity.this, banksLists);
                    recyclerViewBanks.setAdapter(banksListAdapter);
                }
            });
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}