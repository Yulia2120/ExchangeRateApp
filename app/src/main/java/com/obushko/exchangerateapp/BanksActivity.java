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

            Elements rows = table.children();
            for (Element row : rows) {
                Element imgElement = row.select("img").first();
                String src = imgElement.absUrl("src");

                ListItemClass items = new ListItemClass();
                items.setUrlImage(src);
                items.setTitle(row.child(0).text());
                items.setData_1(row.child(1).text());
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