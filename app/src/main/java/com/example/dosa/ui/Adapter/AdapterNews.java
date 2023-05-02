package com.example.dosa.ui.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;
import com.example.dosa.ui.Activity.News;
import com.example.dosa.ui.Activity.ReadingActivity;

import java.util.List;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.viewHolider> {
    Context context;
    List<News> list;

    public AdapterNews(Context context, List<News> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_news, parent, false);
        viewHolider viewHolder = new viewHolider(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolider holder, int position) {
        News news = list.get(position);

        holder.txtType.setText(news.getType());
        holder.txtDecription.setText(news.getNameNews());
        holder.imgHinhAnh.setImageResource(news.getImage());

        holder.itemView.setOnClickListener(view -> {
            context.startActivity(new Intent(context, ReadingActivity.class));
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class viewHolider extends RecyclerView.ViewHolder {
        TextView txtType, txtDecription;
        ImageView imgHinhAnh;

        public viewHolider(@NonNull View itemView) {
            super(itemView);
            imgHinhAnh = itemView.findViewById(R.id.imgLogoItem);
            txtType = itemView.findViewById(R.id.txtTypeItem);
            txtDecription = itemView.findViewById(R.id.txtTitleItem);
        }
    }

}
