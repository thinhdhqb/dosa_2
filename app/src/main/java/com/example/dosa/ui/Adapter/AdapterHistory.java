package com.example.dosa.ui.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;
import com.example.dosa.ui.Fragment.SendData;

import java.util.List;

public class AdapterHistory extends RecyclerView.Adapter<AdapterHistory.Viewholider> {
    List<String> list;
    Context context;
    SendData sendData;

    public AdapterHistory(List<String> list, Context context,SendData sendData) {
        this.list = list;
        this.context = context;
        this.sendData = sendData;
    }

    @NonNull
    @Override
    public Viewholider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_history, parent, false);
        Viewholider viewHolder = new Viewholider(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull Viewholider holder, int position) {
        String item = list.get(position);
        holder.txtItem.setText(item);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData.sendData("khotu_decription", null);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class Viewholider extends RecyclerView.ViewHolder {
        TextView txtItem;
        public Viewholider(@NonNull View itemView) {
            super(itemView);
             txtItem = itemView.findViewById(R.id.btnItem);
        }
    }
}
