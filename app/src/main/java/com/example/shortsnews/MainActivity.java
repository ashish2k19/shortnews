package com.example.shortsnews;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;

    ArrayList<String> topic = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.topicRecycler);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        topicAdd(topic);


        TopicRecyclerAdapater adapter = new TopicRecyclerAdapater(MainActivity.this, topic);
        recyclerView.setAdapter(adapter);


        RequestQueue queue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();


        //arrnews=new ArrayList<>();
        String Url = "https://inshortsapi.vercel.app/news?category=science";
        //ApiResponse(Url);

        Intent catergories = new Intent(this, newsCategories.class);
        catergories.putExtra("url", Url);


    }

    private void topicAdd(ArrayList<String> topic) {
        topic.add("all");
        topic.add("national");
        topic.add("business");
        topic.add("sports");
        topic.add("world");
        topic.add("politics");
        topic.add("technology");
        topic.add("startup");
        topic.add("entertainment");
        topic.add("miscellaneous");
        topic.add("hatke");
        topic.add("science");
        topic.add("automobile");
    }


}