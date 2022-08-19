package com.example.shortsnews;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.android.material.appbar.CollapsingToolbarLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class newsCategories extends AppCompatActivity {
    RecyclerView recyclerView;

    ArrayList<newModel> arrnews ;
    CollapsingToolbarLayout collapsingToolbarLayout ;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_categories);

        LoadingPogressDialog();
        collapsingToolbarLayout = findViewById(R.id.catergoryCollTollbar);



        arrnews=new ArrayList<>();
        String Url = getIntent().getStringExtra("url");
        String Category = getIntent().getStringExtra("catergory");
        ApiResponse(Url);
        collapsingToolbarLayout.setTitle(Category);


    }

    public void ApiResponse(String Url){
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, Url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    parseaJson(response.getJSONArray("data"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(newsCategories.this, "something gone wrong", Toast.LENGTH_SHORT).show();

            }
        });
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);


    }
    public void parseaJson(JSONArray data) {

        for(int i=0 ;i<data.length();i++)
            try {
                JSONObject json=data.getJSONObject(i);


                newModel model = new newModel();
                model.setAuthor(json.getString("author"));
                model.setContent(json.getString("content"));
                model.setDate(json.getString("date"));
                model.setTime(json.getString("time"));
                model.setImgUrl(json.getString("imageUrl"));
                model.setReadMoreUrl(json.getString("readMoreUrl"));
                model.setTitle(json.getString("title"));
                model.setUrl(json.getString("url"));
                arrnews.add(model);


            } catch (JSONException e) {
                progressDialog.dismiss();
                Toast.makeText(newsCategories.this, "Error in Fecthing data", Toast.LENGTH_SHORT).show();

                e.printStackTrace();
            }

        setRecycler(arrnews);
    }

    private void setRecycler(ArrayList<newModel> arrnews) {
        progressDialog.dismiss();
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        main_recycler_adapter adapter = new main_recycler_adapter(newsCategories.this,arrnews);
        recyclerView.setAdapter(adapter);
        //Toast.makeText(MainActivity.this, ""+arrnews.size(), Toast.LENGTH_SHORT).show();

    }

   private void LoadingPogressDialog(){
        progressDialog = new ProgressDialog(newsCategories.this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
   }



}