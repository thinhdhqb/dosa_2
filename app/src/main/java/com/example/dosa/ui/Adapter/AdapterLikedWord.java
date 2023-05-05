package com.example.dosa.ui.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;
import com.example.dosa.data.entity.WordDetail;
import com.example.dosa.ui.Fragment.SendData;

import java.util.ArrayList;

public class AdapterLikedWord extends RecyclerView.Adapter<AdapterLikedWord.MyViewHolder>{
    Context context;
    SendData sendData;
    public ArrayList<String> list;

    public AdapterLikedWord(Context context, ArrayList<String> list, SendData sendData) {
        this.context = context;
        this.list = list;
        this.sendData = sendData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.word_row, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtWord.setText(list.get(position));
        holder.itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("word", list.get(position));
            sendData.sendData("tratu_decription", bundle);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtWord;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtWordName);
        }
    }
}
