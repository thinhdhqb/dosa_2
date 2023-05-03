package com.example.dosa.ui.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;
import com.example.dosa.local.entity.Word;
import com.example.dosa.ui.Fragment.SendData;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AdapterSearchResult extends RecyclerView.Adapter<AdapterSearchResult.MyViewHolder> {
    public ArrayList<String> list;
    private Context context;
    private SendData sendData;

    public AdapterSearchResult() {
        this.list = new ArrayList<String>();
    }


    public AdapterSearchResult(Context context, ArrayList<String> list, SendData sendData) {
        this.list = list;
        this.context = context;
        this.sendData = sendData;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.search_result_row, parent, false);
        AdapterSearchResult.MyViewHolder viewHolder = new AdapterSearchResult.MyViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvName.setText(list.get(position));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                String word = list.get(holder.getAdapterPosition());
                bundle.putString("word", word);
                saveLookupHistory(word);
                sendData.sendData("tratu_decription", bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.txtSearchResultDictionary);
        }
    }

    private void saveLookupHistory(String word) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = currentUser.getUid();
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> user = new HashMap<>();
        user.put("userID", userID);
        user.put("word", word);

        // Add a new document with a generated ID
        db.collection("LookupHistory")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                    }
                })
                .addOnFailureListener(new OnFailureListener()    {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }
}
