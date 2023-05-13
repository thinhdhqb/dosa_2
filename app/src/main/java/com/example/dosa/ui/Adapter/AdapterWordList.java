package com.example.dosa.ui.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;
import com.example.dosa.data.entity.WordDetail;
import com.example.dosa.data.entity.WordList;
import com.example.dosa.ui.Activity.WordListDetailActivity;

import java.util.ArrayList;

public class AdapterWordList extends RecyclerView.Adapter<AdapterWordList.MyViewHolder> {
    public ArrayList<WordList> list;
    Context context;

    public AdapterWordList() {
        list = new ArrayList<>();
    }

    public AdapterWordList(Context context) {
        list = new ArrayList<>();
        this.context = context;
    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.word_list_row, parent, false);
        AdapterWordList.MyViewHolder viewHolder = new AdapterWordList.MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.txtTitle.setText(list.get(position).title);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WordListDetailActivity.class);
                intent.putExtra("mode", "other");
                intent.putExtra("wordListID", list.get(holder.getAdapterPosition()) .id);
                ((Activity)context).startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtTitle;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txtWordListText);
        }
    }
}
