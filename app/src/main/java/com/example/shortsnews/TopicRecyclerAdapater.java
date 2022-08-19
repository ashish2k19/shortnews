package com.example.shortsnews;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class TopicRecyclerAdapater extends RecyclerView.Adapter<TopicRecyclerAdapater.topicViewHolder> {

    Context context;
    ArrayList<String> topic;
    TopicRecyclerAdapater(Context context, ArrayList<String> topic)
    {
        this.context=context;
        this.topic = topic;
    }

    @NonNull
    @Override
    public topicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.topic_row,parent,false);
        topicViewHolder viewHolder = new topicViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull topicViewHolder holder, int position) {
        holder.topicTextView.setText(topic.get(position));

    }

    @Override
    public int getItemCount() {
        return topic.size();
    }

    public class topicViewHolder extends RecyclerView.ViewHolder {
        TextView topicTextView;
        CardView topicCardView;
        public topicViewHolder(@NonNull View itemView) {
            super(itemView);
            topicTextView=itemView.findViewById(R.id.topicTextView);
            topicCardView=itemView.findViewById(R.id.topicCardView);

            topicCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Url = "https://inshortsapi.vercel.app/news?category=";
                            Url= Url+topic.get(getAdapterPosition());
                    Intent catergories=new Intent(context,newsCategories.class);
                    catergories.putExtra("url",Url);
                    catergories.putExtra("catergory",topic.get(getAdapterPosition()));
                     v.getContext().startActivity(catergories);
                }
            });
        }
    }
}
