package com.example.dosa.ui.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;

import java.util.ArrayList;

public class AdapterTranslation extends RecyclerView.Adapter<AdapterTranslation.MyViewHolder> {
    public ArrayList<String> list;

    public AdapterTranslation(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.translation_row, parent, false);
        AdapterTranslation.MyViewHolder viewHolder = new AdapterTranslation.MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtTranslation.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTranslation;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTranslation = itemView.findViewById(R.id.txtTranslation);
        }
    }
}
