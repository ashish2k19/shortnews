package com.example.shortsnews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class detailView extends AppCompatActivity {
    ArrayList<newModel> arrnews = new ArrayList<>();
    TextView title,content,viewMore;
    ImageView image_detail;
    String[] strings = new String[8];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);


        title = findViewById(R.id.title);
        content = findViewById(R.id.content);
        viewMore = findViewById(R.id.viewMore);
        image_detail = findViewById(R.id.image_detail);


        int postion= getIntent().getIntExtra("position",0);
        strings=getIntent().getStringArrayExtra("data");


        title.setText(strings[3]);
        content.setText(strings[4]);
        //viewMore.setText(strings[1]);
        viewMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(detailView.this, ""+strings[0], Toast.LENGTH_SHORT).show();

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(strings[1]));
                startActivity(i);
            }
        });


        Glide.with(this).load(strings[6]).diskCacheStrategy(DiskCacheStrategy.AUTOMATIC).placeholder(R.drawable.a).into(image_detail);









    }
}