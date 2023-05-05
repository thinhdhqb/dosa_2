package com.example.dosa.ui.Adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dosa.R;
import com.example.dosa.data.entity.NewsArticle;
import com.example.dosa.ui.Activity.ReadingActivity;
import com.example.dosa.utils.Utils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class AdapterNews extends RecyclerView.Adapter<AdapterNews.viewHolider> implements Filterable {
    Context context;
    public List<NewsArticle> list;
    public List<NewsArticle> filteredList;

    public AdapterNews(Context context, List<NewsArticle> list) {
        this.context = context;
        this.list = list;
        this.filteredList = list;
    }

    @NonNull
    @Override
    public viewHolider onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.item_news, parent, false);
        viewHolider viewHolder = new viewHolider(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolider holder, int position) {
        NewsArticle newsArticle = filteredList.get(position);

        if (newsArticle.getKeywords() == null) {
            holder.txtType.setText("Other");
        }
        else holder.txtType.setText(newsArticle.getKeywords().get(0));
        holder.txtDecription.setText(newsArticle.getTitle());
        holder.imgHinhAnh.setImageBitmap(newsArticle.getImage());

        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(context, ReadingActivity.class);
            intent.putExtra("id", newsArticle.getId());
            intent.putExtra("title", newsArticle.getTitle());
            intent.putExtra("content", newsArticle.getContent());
            intent.putExtra("source", newsArticle.getSource());
            intent.putExtra("keyword", newsArticle.getKeywords().get(0));
            intent.putExtra("date", newsArticle.getDate());

            // save image
            try {
                //Write file
                String filename = "bitmap.png";
                Bitmap bmp = newsArticle.getImage();
                FileOutputStream stream = context.openFileOutput(filename, Context.MODE_PRIVATE);
                bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);

                //Cleanup
                stream.close();
//                bmp.recycle();
                intent.putExtra("image", filename);

                //Pop intent
            } catch (Exception e) {
                e.printStackTrace();
            }
            ((Activity) context).startActivityForResult(intent, 123);

        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredList = list;
                } else {
                    filteredList = new ArrayList<>();
                    for (NewsArticle article : list) {
                        if (article.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(article);
                        }
                    }
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredList;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredList = (ArrayList<NewsArticle>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    class viewHolider extends RecyclerView.ViewHolder {
        TextView txtType, txtDecription;
        ImageView imgHinhAnh;

        public viewHolider(@NonNull View itemView) {
            super(itemView);
            imgHinhAnh = itemView.findViewById(R.id.imgLogoItem);
            txtType = itemView.findViewById(R.id.txtTypeItem);
            txtDecription = itemView.findViewById(R.id.txtTitleItem);
        }
    }


}
