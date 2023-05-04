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

public class AdapterTraTu extends RecyclerView.Adapter<AdapterTraTu.MyViewHolder>{
    Context context;
    SendData sendData;
    public ArrayList<WordDetail> list;

    public AdapterTraTu(Context context, ArrayList<WordDetail> list, SendData sendData) {
        this.context = context;
        this.list = list;
        this.sendData = sendData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_tratu, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        WordDetail wordDetail = list.get(position);
        holder.txtWord.setText(wordDetail.getWord());
        if (wordDetail.getUkIPA() != null) {
            holder.txtUKIPA.setText(wordDetail.getUkIPA());
            holder.layoutUKIPA.setVisibility(View.VISIBLE);
        }
        if (wordDetail.getUsIPA() != null) {
            holder.txtUSIPA.setText(wordDetail.getUsIPA());
            holder.layoutUSIPA.setVisibility(View.VISIBLE);
        }
        if (wordDetail.getGeneralIPA() != null) {
            holder.txtGeneralIPA.setText(wordDetail.getGeneralIPA());
            holder.layoutGeneralIPA.setVisibility(View.VISIBLE);
        }
        holder.rcvDictionarySection.setAdapter(new AdapterDictionarySectionHistory(wordDetail.getSections()));
        if (!wordDetail.getTranslations().isEmpty())
            holder.txtVie.setVisibility(View.VISIBLE);
        holder.rcvTranslation.setAdapter(new AdapterTranslation(wordDetail.getTranslations()));
        holder.itemView.setOnClickListener(view -> {
            Bundle bundle = new Bundle();
            bundle.putString("word", wordDetail.getWord());
            sendData.sendData("tratu_decription", bundle);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView txtWord, txtUSIPA, txtUKIPA, txtGeneralIPA, txtVie;
        RecyclerView rcvDictionarySection, rcvTranslation;
        LinearLayout layoutUSIPA, layoutUKIPA, layoutGeneralIPA;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txtWord = itemView.findViewById(R.id.txtWordHistory);
            txtUSIPA = itemView.findViewById(R.id.txtUSIPAHistory);
            txtUKIPA = itemView.findViewById(R.id.txtUKIPAHistory);
            txtGeneralIPA = itemView.findViewById(R.id.txtGeneralIPAHistory);
            txtVie = itemView.findViewById(R.id.txtVieHistory);
            rcvDictionarySection = itemView.findViewById(R.id.rcvDictionarySectionHistory);
            rcvTranslation = itemView.findViewById(R.id.rcvTranslationHistory);
            layoutUKIPA = itemView.findViewById(R.id.layoutUKIPAHistory);
            layoutUSIPA = itemView.findViewById(R.id.layoutUSIPAHistory);
            layoutGeneralIPA = itemView.findViewById(R.id.layoutGeneralIPAHistory);
            rcvDictionarySection.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
            rcvTranslation.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
}
