package com.example.dosa.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.dosa.databinding.ActivityMain9Binding;

import com.example.dosa.R;

public class ReadingActivity extends AppCompatActivity {
    ActivityMain9Binding binding;
    boolean Check = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain9Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.imgListen.setOnClickListener(view -> {
            Dialog dialog = new Dialog(ReadingActivity.this);
            dialog.setContentView(R.layout.dialog_bottom);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            TextView lang1 = dialog.findViewById(R.id.txtLanguage);
            TextView lang2 = dialog.findViewById(R.id.txtLang);
            TextView dr = dialog.findViewById(R.id.txtDecription1);

            lang1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Check == false){
                        lang1.setText("VN");
                        lang2.setText("VN");
                        Check = true;
                        dr.setText("Sự lạc quan");
                    }else{
                        lang1.setText("EN");
                        lang2.setText("EN");
                        Check = false;
                        dr.setText("the quality of being full of hope and emphasizing the good parts of a situation, or a belief that something good will happen");
                    }
                }
            });

            dialog.show();
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        });

        binding.imgTranlate.setOnClickListener(view -> {
            Dialog dialog = new Dialog(ReadingActivity.this);
            dialog.setContentView(R.layout.dialog_setting);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            dialog.show();
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        });

    }
}