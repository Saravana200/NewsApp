package com.example.newsss;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.RecoverySystem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
        getCategories();
        getNews("ALL");
        newsRVAdapter.notifyDataSetChanged();




    }
    private void getCategories(){
        categoryRVModalArrayList.add(new CategoryRVModal("All","https://images.unsplash.com/photo-1676027274842-6195f8cdace7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDV8QkpKTXR0ZURKQTR8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60") );
        categoryRVModalArrayList.add(new CategoryRVModal("Technology","https://images.unsplash.com/photo-1676027274842-6195f8cdace7?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHx0b3BpYy1mZWVkfDV8QkpKTXR0ZURKQTR8fGVufDB8fHx8&auto=format&fit=crop&w=500&q=60") );
        categoryRVModalArrayList.add(new CategoryRVModal("Science","https://images.unsplash.com/photo-1451187580459-43490279c0fa?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fHNjaWVuY2V8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60") );
        categoryRVModalArrayList.add(new CategoryRVModal("Sports","https://images.unsplash.com/photo-1541252260730-0412e8e2108e?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTF8fHNwb3J0c3xlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60") );
        categoryRVModalArrayList.add(new CategoryRVModal("General","https://images.unsplash.com/photo-1434030216411-0b793f4b4173?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NHx8Z2VuZXJhbHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60") );
        categoryRVModalArrayList.add(new CategoryRVModal("Business","https://images.unsplash.com/photo-1610374792793-f016b77ca51a?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8Nnx8YnVpc25lc3N8ZW58MHx8MHx8&auto=format&fit=crop&w=500&q=60") );
        categoryRVModalArrayList.add(new CategoryRVModal("Entertainment","https://images.unsplash.com/photo-1514525253161-7a46d19cd819?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8NXx8ZW50ZXJ0YWlubWVudHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60") );
        categoryRVModalArrayList.add(new CategoryRVModal("Health","https://images.unsplash.com/photo-1535914254981-b5012eebbd15?ixlib=rb-4.0.3&ixid=MnwxMjA3fDB8MHxzZWFyY2h8MTd8fGhlYWx0aHxlbnwwfHwwfHw%3D&auto=format&fit=crop&w=500&q=60") );
        categoryRVAdapter.notifyDataSetChanged();

    }
    private void getNews(String category){
        loadingPB.setVisibility(View.VISIBLE);
        articlesArrayList.clear();
        String categoryURL="https://newsapi.org/v2/top-headlines?country=in&category="+category+"&apiKey=452ea4d140ef4ca29a771b76d33f225c";
        String url="https://newsapi.org/v2/top-headlines?country=in&excludeDomains=stackoverflow.com&sortBy=publishedAt&language=en&apiKey=452ea4d140ef4ca29a771b76d33f225c";
        String BASE_URL="https://newsapi.org/";
        Retrofit retrofit =new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.class)
                .build();
        RetrofitAPI retrofitAPI=retrofit.create(RetrofitAPI.class);
        Call<NewsModal> call;
        if(category.equals("ALL"))
        {
            call=retrofitAPI.getAllNews(url);

        }
        else{
            call=retrofitAPI.getNewsByCategory(categoryURL);
        }

        call.enqueue(new Callback<NewsModal>() {
            @Override
            public void onResponse(Call<NewsModal> call, Response<NewsModal> response) {
                NewsModal newsModal=response.body();
                loadingPB.setVisibility(View.GONE);
                ArrayList<Articles> articles=newsModal.getArticles();
                for(int i=0;articles.size();i++)
                {
                    articlesArrayList.add(new Articles(articles.get(i).getTitle(),articles.get(i).getDescription(),articles.get(i).getUrlToImage(),articles.get(i).getUrl(),articles.get(i).getContent()));

                }
                newsRVAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<NewsModal> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Fail to get news", Toast.LENGTH_SHORT).show();

            }
        });




    }
    @Override
    public void onCategoryClick(int position) {
        String category=categoryRVModalArrayList.get(position).getCategory();
        getNews(category);

    }
}