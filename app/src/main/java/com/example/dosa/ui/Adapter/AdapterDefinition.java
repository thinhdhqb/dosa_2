package com.example.dosa.ui.Adapter;

import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;
import com.example.dosa.local.entity.WordDetail;

import java.util.ArrayList;
import java.util.Arrays;

public class AdapterDefinition extends RecyclerView.Adapter<AdapterDefinition.MyViewHolder> {
    public ArrayList<WordDetail.DefinitionDetail> list;

    public AdapterDefinition(ArrayList<WordDetail.DefinitionDetail>list) {
        this.list = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.definition_row, parent, false);
        AdapterDefinition.MyViewHolder viewHolder = new AdapterDefinition.MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtDefinition.setText(list.get(position).getDefinition());
        holder.rcvExample.setAdapter(new AdapterExample(list.get(position).getExamples()));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtDefinition;
        RecyclerView rcvExample;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtDefinition = itemView.findViewById(R.id.txtDefinition);
            rcvExample = itemView.findViewById(R.id.rcvExample);
            rcvExample.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
}
