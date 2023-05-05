package com.example.dosa.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;

import com.example.dosa.R;
import com.example.dosa.databinding.ActivityFlashcardBinding;
import com.example.dosa.databinding.ActivityWordListDetailBinding;
import com.example.dosa.viewmodel.DictionaryViewModel;

import java.util.ArrayList;

public class FlashcardActivity extends AppCompatActivity {
    ActivityFlashcardBinding binding;
    boolean isFront = true;
    ArrayList<String> wordList;
    DictionaryViewModel dictionaryViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFlashcardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        DictionaryViewModel dictionaryViewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);


        Intent intent = getIntent();
        wordList = intent.getStringArrayListExtra("wordList");
        binding.txtNumber.setText("1/" + wordList.size());




//        binding.cardViewFront.setCameraDistance(getResources().getDisplayMetrics().density);
//        binding.cardViewBack.setCameraDistance(getResources().getDisplayMetrics().density);

        Animator frontAnim = AnimatorInflater.loadAnimator(this, R.animator.front_animator);
        Animator backAnim = AnimatorInflater.loadAnimator(this, R.animator.back_animator);

        binding.cardViewFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frontAnim.setTarget(binding.cardViewFront);
                backAnim.setTarget(binding.cardViewBack);

                frontAnim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        binding.cardViewBack.bringToFront();
                        backAnim.start();

                    }
                });
                frontAnim.start();
                Log.d("Animation", "onClick: front");
            }
        });
        binding.cardViewBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frontAnim.setTarget(binding.cardViewBack);
                backAnim.setTarget(binding.cardViewFront);

                frontAnim.addListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        super.onAnimationEnd(animation);
                        binding.cardViewFront.bringToFront();
                        backAnim.start();

                    }
                });
                frontAnim.start();
                Log.d("Animation", "onClick: back");

            }
        });
    }

    private void fetchDefinition(String word) {

    }
}