package com.example.dosa.ui.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;
import com.example.dosa.ui.Fragment.SendData;

import java.util.ArrayList;

public class AdapterLikedArticle extends RecyclerView.Adapter<AdapterLikedArticle.MyViewHolder> implements Filterable {
    Context context;
    SendData sendData;
    public ArrayList<String> list;
    public ArrayList<String> filteredList;

    public AdapterLikedArticle(Context context, ArrayList<String> list, SendData sendData) {
        this.context = context;
        this.list = list;
        this.sendData = sendData;
        filteredList = list;

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
        holder.txtWord.setText(filteredList.get(position));
        holder.itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("word", filteredList.get(position));
            sendData.sendData("tratu_decription", bundle);
        });
    }


    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = list;
                } else {
                    filteredList = new ArrayList<>();
                    for (String s : list) {
                        if (s.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(s);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (ArrayList<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtWord;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtWordName);
        }
    }
}
