package com.example.dosa.ui.Adapter;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;

import java.util.ArrayList;

public class AdapterExample extends RecyclerView.Adapter<AdapterExample.MyViewHolder> {
    public ArrayList<String> list;

    public AdapterExample(ArrayList<String> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.example_row, parent, false);
        AdapterExample.MyViewHolder viewHolder = new AdapterExample.MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtExample.setText(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtExample;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtExample = itemView.findViewById(R.id.txtExample);
        }
    }
}
