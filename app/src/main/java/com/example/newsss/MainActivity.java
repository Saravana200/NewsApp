package com.example.newsss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.RecoverySystem;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements CategoryRVAdapter.CategoryClickInterface{
//fe48951cb01a4ba0b78e5911b8ec6b92


    private RecyclerView newsRV,categoryRV;
    private ProgressBar loadingPB;
    private ArrayList<Articles> articlesArrayList;
    private ArrayList<CategoryRVModal> categoryRVModalArrayList;
    private CategoryRVAdapter categoryRVAdapter;
    private NewsRVAdapter newsRVAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newsRV = findViewById(R.id.idRVNews);
        categoryRV=findViewById(R.id.idRVCategories);
        loadingPB=findViewById(R.id.idBLoading);
        articlesArrayList=new ArrayList<>();
        categoryRVModalArrayList=new ArrayList<>();
        newsRVAdapter=new NewsRVAdapter(articlesArrayList,this);
        categoryRVAdapter=new CategoryRVAdapter(categoryRVModalArrayList,this,this::onCategoryClick);
        newsRV.setLayoutManager(new LinearLayoutManager(this));
        newsRV.setAdapter(newsRVAdapter);
        categoryRV.setAdapter(categoryRVAdapter);




    }

//klhjdh
    private void getCategories(){
        categoryRVModalArrayList.add(new CategoryRVModal("All","") );
        categoryRVModalArrayList.add(new CategoryRVModal("Technology","") );
        categoryRVModalArrayList.add(new CategoryRVModal("Science","") );
        categoryRVModalArrayList.add(new CategoryRVModal("Sports","") );
        categoryRVModalArrayList.add(new CategoryRVModal("General","") );
        categoryRVModalArrayList.add(new CategoryRVModal("Business","") );
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment","") );
        categoryRVModalArrayList.add(new CategoryRVModal("Health","") );
    }
    @Override
    public void onCategoryClick(int position) {

    }
}