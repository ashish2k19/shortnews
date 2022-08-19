package com.example.shortsnews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ComponentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class main_recycler_adapter extends RecyclerView.Adapter<main_recycler_adapter.ViewHolder> implements View.OnClickListener{

    Context context;
   ArrayList<newModel> arrnews;
   // ArrayList<model> arrnews;
    main_recycler_adapter(Context context, ArrayList<newModel> arrnews)
    {
        this.context=context;
        this.arrnews = arrnews;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.news_recycler_layout,parent,false);
        //class viewholder
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       holder.textView.setText(arrnews.get(position).title);
        holder.textView2.setText(arrnews.get(position).date+"       "+arrnews.get(position).time);
        Glide.with(context).load(arrnews.get(position).imgUrl).placeholder(R.drawable.a)
                .diskCacheStrategy(DiskCacheStrategy.ALL).into(holder.imageView);


       // holder.imageView.setImageResource(R.drawable.a);
      //  holder.textView.setText(arrnews.get(position).one);
       // holder.textView2.setText(arrnews.get(position).second);


    }

    @Override
    public int getItemCount() {
        return arrnews.size();
    }

    @Override
    public void onClick(View v) {
        v.getVerticalScrollbarPosition();

    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView textView,textView2;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView= itemView.findViewById(R.id.image);
            textView = itemView.findViewById(R.id.textView);
            textView2= itemView.findViewById(R.id.textView2);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Intent intent = new Intent(context,detailView.class);
                    String[] strings = new String[8];
                    strings[0]=arrnews.get(getAdapterPosition()).author;
                    strings[1]=arrnews.get(getAdapterPosition()).readMoreUrl;
                    strings[2]=arrnews.get(getAdapterPosition()).time;
                    strings[3]=arrnews.get(getAdapterPosition()).title;
                    strings[4]=arrnews.get(getAdapterPosition()).content;
                    strings[5]=arrnews.get(getAdapterPosition()).date;
                    strings[6]=arrnews.get(getAdapterPosition()).imgUrl;
                    strings[7]=arrnews.get(getAdapterPosition()).url;



                    intent.putExtra("data",strings);
                    intent.putExtra("position",getAdapterPosition());
                    v.getContext().startActivity(intent);

                }
            });



        }
    }
}
