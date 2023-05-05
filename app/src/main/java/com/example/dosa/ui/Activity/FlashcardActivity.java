package com.example.dosa.ui.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;

import com.example.dosa.R;
import com.example.dosa.data.entity.Definition;
import com.example.dosa.data.entity.EngVieTranslation;
import com.example.dosa.data.entity.IPA;
import com.example.dosa.data.entity.Word;
import com.example.dosa.data.entity.WordDetail;
import com.example.dosa.databinding.ActivityFlashcardBinding;
import com.example.dosa.databinding.ActivityWordListDetailBinding;
import com.example.dosa.ui.Adapter.AdapterDictionarySectionHistory;
import com.example.dosa.ui.Adapter.AdapterTranslation;
import com.example.dosa.ui.Fragment.FragmentDictionaryDetail;
import com.example.dosa.viewmodel.DictionaryViewModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class FlashcardActivity extends AppCompatActivity {
    ActivityFlashcardBinding binding;
    boolean isFlipped = false;
    ArrayList<String> wordList;
    DictionaryViewModel dictionaryViewModel;
    ArrayList<WordDetail> wordDetails;
    AdapterDictionarySectionHistory adapter;
    AdapterTranslation adapterTranslation;
    int totalWords;
    int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFlashcardBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dictionaryViewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);

        adapter = new AdapterDictionarySectionHistory();
        adapterTranslation = new AdapterTranslation(new ArrayList<>());
        binding.rcvDefinitionFlash.setAdapter(adapter);
        binding.rcvDefinitionFlash.setLayoutManager(new LinearLayoutManager(this));
        binding.rcvTranslationFlash.setAdapter(adapterTranslation);
        binding.rcvTranslationFlash.setLayoutManager(new LinearLayoutManager(this));


        Intent intent = getIntent();
        wordList = intent.getStringArrayListExtra("wordList");
        totalWords = wordList.size();
        wordDetails = new ArrayList<>();

        // animation
        Animator frontAnim = AnimatorInflater.loadAnimator(this, R.animator.front_animator);
        Animator backAnim = AnimatorInflater.loadAnimator(this, R.animator.back_animator);
        Animator resetFrontAnim = AnimatorInflater.loadAnimator(this, R.animator.reset_front_animator);
        Animator resetBackAnim = AnimatorInflater.loadAnimator(this, R.animator.reset_back_animator);

        binding.cardViewFront.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.btnFlip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isFlipped) {
                    isFlipped = true;
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

                }
                else {
                    isFlipped = false;
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
                }
            }
        });

        binding.btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFlipped) {
                    isFlipped = false;
                    resetFrontAnim.setTarget(binding.cardViewBack);
                    resetBackAnim.setTarget(binding.cardViewFront);
                    resetFrontAnim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            binding.cardViewFront.bringToFront();
                            resetBackAnim.start();

                        }
                    });
                    resetFrontAnim.start();
                }
                currentIndex++;
                loadWordCard(currentIndex);
                if (currentIndex == totalWords-1) {
                    binding.btnNext.setEnabled(false);
                    binding.btnNext.setClickable(false);
                }
                if (currentIndex > 0) {
                    binding.btnPrev.setEnabled(true);
                    binding.btnPrev.setClickable(true);
                }
            }
        });

        binding.btnPrev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFlipped) {
                    isFlipped = false;
                    resetFrontAnim.setTarget(binding.cardViewBack);
                    resetBackAnim.setTarget(binding.cardViewFront);
                    resetFrontAnim.addListener(new AnimatorListenerAdapter() {
                        @Override
                        public void onAnimationEnd(Animator animation) {
                            super.onAnimationEnd(animation);
                            binding.cardViewFront.bringToFront();
                            resetBackAnim.start();

                        }
                    });
                    resetFrontAnim.start();
                }
                currentIndex--;
                loadWordCard(currentIndex);
                if (currentIndex == 0) {
                    binding.btnPrev.setEnabled(false);
                    binding.btnPrev.setClickable(false);
                }
                if (currentIndex < totalWords-1) {
                    binding.btnNext.setEnabled(true);
                    binding.btnNext.setClickable(true);
                }
            }
        });


        loadWordCard(0);
    }

    private void loadWordCard(int index) {
        // Looad
        String word = wordList.get(index);
        fetchData(word);
        binding.txtWordFlash.setText(word);
        binding.txtNumber.setText((index+1) + "/" + totalWords);
        adapter.list.clear();
        adapterTranslation.list.clear();
        binding.layoutUKIPAFlash.setVisibility(View.GONE);
        binding.layoutGeneralIPAFlash.setVisibility(View.GONE);
        binding.layoutUSIPAFlash.setVisibility(View.GONE);
    }

    private void fetchData(String word) {

        dictionaryViewModel.getWordsByWord(word).observe(FlashcardActivity.this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                fetchDefinitionsByWords(words);
            }
        });

        dictionaryViewModel.getIPAByWord(word).observe(FlashcardActivity.this, new Observer<List<IPA>>() {
            @Override
            public void onChanged(List<IPA> ipas) {
                for (IPA ipa : ipas) {
                    if (ipa.tag.equals("UK")) {
                        binding.layoutUKIPAFlash.setVisibility(View.VISIBLE);
                        binding.txtUKIPAFlash.setText(ipa.ipa);
                    }
                    if (ipa.tag.equals("US")) {
                        binding.layoutUSIPAFlash.setVisibility(View.VISIBLE);
                        binding.txtUSIPAFlash.setText(ipa.ipa);
                    }
                    if (ipa.tag.equals("General")) {
                        binding.layoutGeneralIPAFlash.setVisibility(View.VISIBLE);
                        binding.txtGeneralIPAFlash.setText(ipa.ipa);
                    }
                }
            }
        });
        dictionaryViewModel.getTranslationByWord(word).observe(FlashcardActivity.this, new Observer<EngVieTranslation>() {
            @Override
            public void onChanged(EngVieTranslation engVieTranslations) {
                if (engVieTranslations != null) {
                    ArrayList<String> list = new ArrayList<>();
                    Document document = Jsoup.parse(engVieTranslations.html);
                    Elements translationElement = document.select("li");
                    for (Element element : translationElement) {
                        list.add(element.text());
                    }
                    adapterTranslation.list.addAll(list);
                };
            }
        });
    }

    private void fetchDefinitionsByWords(List<Word> words) {
        for (Word word : words) {
            WordDetail.Section section = new WordDetail.Section(word.pos, new ArrayList<>());
            adapter.list.add(section);
            dictionaryViewModel.getDefinitionsByWordID(word.id).observe(FlashcardActivity.this, new Observer<List<Definition>>() {
                @Override
                public void onChanged(List<Definition> definitionList) {
                    Log.d("fetchDefinitionsByWords", "onChanged: ");

                    for (Definition definition : definitionList) {
                        WordDetail.DefinitionDetail definitionDetail = new WordDetail.DefinitionDetail(definition.definition, new ArrayList<>());
                        section.getDefinitionDetails().add(definitionDetail);
                        dictionaryViewModel.getExampleByDefinitionID(definition.id).observe(FlashcardActivity.this, new Observer<List<String>>() {
                            @Override
                            public void onChanged(List<String> strings) {
                                definitionDetail.getExamples().addAll(strings);
                            }
                        });
                        adapter.notifyDataSetChanged();
                    }
                    adapter.notifyDataSetChanged();

                }
            });
            adapter.notifyDataSetChanged();
        }
    }
}