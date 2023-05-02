package com.example.dosa.ui.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;
import com.example.dosa.ui.Fragment.SendData;

import java.util.ArrayList;

public class AdapterDictionarySection extends RecyclerView.Adapter<AdapterDictionarySection.MyViewHolder> {
    public ArrayList<Pair<String, ArrayList<Pair<String, ArrayList<String>>>>> list;
    private Context context;
    private SendData sendData;

    public AdapterDictionarySection() {
        this.list = new ArrayList<Pair<String, ArrayList<Pair<String, ArrayList<String>>>>>();
    }


    public AdapterDictionarySection(ArrayList<Pair<String, ArrayList<Pair<String, ArrayList<String>>>>> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.dictionary_position_section, parent, false);
        AdapterDictionarySection.MyViewHolder viewHolder = new AdapterDictionarySection.MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvPosition.setText(list.get(position).first);
        holder.rcvDefinition.setAdapter(new AdapterDefinition(list.get(position).second));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvPosition;
        RecyclerView rcvDefinition;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvPosition = itemView.findViewById(R.id.txtPosition);
            rcvDefinition = itemView.findViewById(R.id.rcvDefinition);
            rcvDefinition.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
}
