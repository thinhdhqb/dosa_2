package com.example.dosa.ui.Adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;

import java.util.ArrayList;

public class AdapterWordInWordList extends RecyclerView.Adapter<AdapterWordInWordList.MyViewHolder> implements Filterable {
    public ArrayList<String> list;
    public ArrayList<String> filteredList;

    public AdapterWordInWordList() {
        list = new ArrayList<>();
        filteredList = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.word_row, parent, false);
        AdapterWordInWordList.MyViewHolder viewHolder = new AdapterWordInWordList.MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtWord.setText(filteredList.get(position));
    }

    @Override
    public int getItemCount() {
        return filteredList.size();
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

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtWord;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtWordName);
        }
    }

}
