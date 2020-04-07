package com.personal.covid_19;

import android.app.LauncherActivity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.personal.covid_19.model.newsallcn;
import com.squareup.picasso.Picasso;

import java.util.List;

public class newadapter extends RecyclerView.Adapter<newadapter.ViewHolder> {

    private List<newsallcn> newsitems;
    private Context context;

    public newadapter(List<newsallcn> newsitems, Context context) {
        this.newsitems = newsitems;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.newscontainer,parent,false);
        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        newsallcn newsallcn= newsitems.get(position);
        holder.title.setText(newsallcn.getTitle().substring(0,50)+".");
        holder.author.setText(newsallcn.getAuthor());
        holder.news.setText(newsallcn.getDescription());
        Picasso.with(context)
                .load(newsallcn.getUrlToImage())
                .into(holder.newsimage);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(newsallcn.getUrl()));
                context.startActivity(browserIntent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsitems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView author;
        public  TextView news;
        public ImageView newsimage;
        public CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.newstitle);
            author=itemView.findViewById(R.id.newsauthor);
            news=itemView.findViewById(R.id.newscontaining);
            newsimage=itemView.findViewById(R.id.newsimage);
            cardView=itemView.findViewById(R.id.readmorenews);

        }
    }
}
