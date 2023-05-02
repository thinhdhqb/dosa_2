package com.example.dosa.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;
import com.example.dosa.ui.Fragment.SendData;
import com.example.dosa.ui.Activity.TraTu;

import java.util.List;

public class AdapterTraTu extends RecyclerView.Adapter<AdapterTraTu.Viewholier>{
    Context context;
    SendData sendData;
    List<TraTu> list;

    public AdapterTraTu(Context context, List<TraTu> list, SendData sendData) {
        this.context = context;
        this.list = list;
        this.sendData = sendData;
    }

    @NonNull
    @Override
    public Viewholier onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_tratu, parent, false);
        Viewholier viewHolder = new Viewholier(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholier holder, int position) {
        holder.itemView.setOnClickListener(view -> {
            sendData.sendData("tratu_decription", null);
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholier extends RecyclerView.ViewHolder {
        public Viewholier(@NonNull View itemView) {
            super(itemView);
        }
    }
}
