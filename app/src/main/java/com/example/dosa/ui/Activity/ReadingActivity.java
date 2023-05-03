package com.example.dosa.ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.UtteranceProgressListener;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.dosa.data.entity.Definition;
import com.example.dosa.data.entity.EngVieTranslation;
import com.example.dosa.data.entity.IPA;
import com.example.dosa.data.entity.NewsArticle;
import com.example.dosa.data.entity.Word;
import com.example.dosa.data.entity.WordDetail;
import com.example.dosa.databinding.ActivityMain9Binding;

import com.example.dosa.R;
import com.example.dosa.ui.Adapter.AdapterDictionarySectionHistory;
import com.example.dosa.ui.Adapter.AdapterTranslation;
import com.example.dosa.ui.Fragment.FragmentTraTuDecription;
import com.example.dosa.utils.Utils;
import com.example.dosa.viewmodel.DictionaryViewModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ReadingActivity extends AppCompatActivity {
    ActivityMain9Binding binding;
    boolean Check = false;
    Dialog dialog;
    DictionaryViewModel dictionaryViewModel;
    AdapterDictionarySectionHistory adapterEN;
    AdapterTranslation adapterVN;
    TextView txtWord, txtUSIPA, txtUKIPA, txtLanguage, txtGeneralIPA;
    LinearLayout layoutUSIPA, layoutUKIPA, layoutGeneralIPA;
    RecyclerView rcvSection;
    TextToSpeech tts;
    Boolean speaking = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain9Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        dictionaryViewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);



        // Get intent
        Intent intent = getIntent();
        String title = intent.getStringExtra("title");
        String keyword = intent.getStringExtra("keyword");
        String source = intent.getStringExtra("source");
        String date = intent.getStringExtra("date");
        String content = intent.getStringExtra("content");



        binding.txtTitleReading.setText(title);
        binding.txtTopicReading.setText(keyword);
        binding.txtSourceReading.setText(source + "  |  " + date);

        // get image bitmap
        Bitmap bmp = null;
        String filename = getIntent().getStringExtra("image");
        try {
            FileInputStream is = this.openFileInput(filename);
            bmp = BitmapFactory.decodeStream(is);
            is.close();
            binding.imvArticleImage.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //set word clickable
        binding.txtContentReading.setText(content);
        Utils.setSpannableText(binding.txtContentReading, content, this);
        binding.txtContentReading.setMovementMethod(LinkMovementMethod.getInstance());

        // text to speech content
        tts = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {
                binding.imgListen.setVisibility(View.VISIBLE);
                tts.setOnUtteranceProgressListener(new UtteranceProgressListener() {
                    @Override
                    public void onStart(String s) {

                    }

                    @Override
                    public void onDone(String s) {
                    }

                    @Override
                    public void onError(String s) {

                    }

                    @Override
                    public void onRangeStart(String utteranceId, int start, int end, int frame) {

                        super.onRangeStart(utteranceId, start, end, frame);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                 Utils.highlightWord(binding.txtContentReading, start, end, content, ReadingActivity.this);
                            }
                        });
                    }
                });
            }
        });


        binding.imgListen.setOnClickListener(view -> {
            if (!speaking) {
                tts.setLanguage(Locale.ENGLISH);
                tts.speak(content, TextToSpeech.QUEUE_FLUSH, null, TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID);
                binding.imgListen.setImageDrawable(getDrawable(R.drawable.baseline_pause_24));
                speaking = true;
            }
            else {
                tts.stop();
                binding.imgListen.setImageResource(R.drawable.img_33);
                speaking = false;
            }
        });

        binding.imgTranlate.setOnClickListener(view -> {
            Dialog dialog = new Dialog(ReadingActivity.this);
            dialog.setContentView(R.layout.dialog_setting);
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            dialog.show();
            dialog.getWindow().setGravity(Gravity.BOTTOM);
        });

        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_bottom);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        txtWord = dialog.findViewById(R.id.txtWordDialog);
        txtLanguage = dialog.findViewById(R.id.txtLanguage);
        txtUKIPA = dialog.findViewById(R.id.txtUKIPADialog);
        txtUSIPA = dialog.findViewById(R.id.txtUSIPADialog);
        txtGeneralIPA = dialog.findViewById(R.id.txtGeneralIPADialog);
        layoutGeneralIPA = dialog.findViewById(R.id.layoutGeneralIPADialog);
        layoutUKIPA = dialog.findViewById(R.id.layoutUKIPADialog);
        layoutUSIPA = dialog.findViewById(R.id.layoutUSIPADialog);
        dialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                binding.txtContentReading.setText(content);
                Utils.setSpannableText( binding.txtContentReading, content, ReadingActivity.this);
            }
        });

        rcvSection = dialog.findViewById(R.id.rcvSectionDialog);
        rcvSection.setLayoutManager(new LinearLayoutManager(this));
        adapterEN = new AdapterDictionarySectionHistory();
        adapterVN = new AdapterTranslation(new ArrayList<>());
        txtLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (txtLanguage.getText().toString().equals("VN")){
                    txtLanguage.setText("EN");
                    rcvSection.setAdapter(adapterVN);
                }else{
                    txtLanguage.setText("VN");
                    rcvSection.setAdapter(adapterEN);
                }
            }
        });

        dialog.getWindow().setGravity(Gravity.BOTTOM);
    }

    public void updateDialog(String word) {
        adapterEN.list.clear();
        layoutUKIPA.setVisibility(View.GONE);
        layoutUSIPA.setVisibility(View.GONE);
        layoutGeneralIPA.setVisibility(View.GONE);
        ((TextView)dialog.findViewById(R.id.txtWordDialog)).setText(word);
        rcvSection.setAdapter(adapterEN);
        fetchData(word);
        dialog.show();
    }

    private void fetchData(String word) {
        dictionaryViewModel.getWordsByWord(word).observe(ReadingActivity.this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                fetchDefinitionsByWords(words);
            }
        });

        dictionaryViewModel.getIPAByWord(word).observe(ReadingActivity.this, new Observer<List<IPA>>() {
            @Override
            public void onChanged(List<IPA> ipas) {
                for (IPA ipa : ipas) {
                    if (ipa.tag.equals("UK")) {
                        txtUKIPA.setText(ipa.ipa);
                        layoutUKIPA.setVisibility(View.VISIBLE);
                    }
                    if (ipa.tag.equals("US")) {
                        txtUSIPA.setText(ipa.ipa);
                        layoutUSIPA.setVisibility(View.VISIBLE);
                    }
                    if (ipa.tag.equals("General")) {
                        txtGeneralIPA.setText(ipa.ipa);
                        layoutGeneralIPA.setVisibility(View.VISIBLE);
                    }
                }
            }
        });
        dictionaryViewModel.getTranslationByWord(word).observe(ReadingActivity.this, new Observer<EngVieTranslation>() {
            @Override
            public void onChanged(EngVieTranslation engVieTranslations) {
                if (engVieTranslations != null) {
                    ArrayList<String> list = new ArrayList<>();
                    Document document = Jsoup.parse(engVieTranslations.html);
                    Elements translationElement = document.select("li");
                    for (Element element : translationElement) {
                        list.add(element.text());
                    }
                    adapterVN.list = list;
//                    binding.txtVie.setVisibility(View.VISIBLE);
                };
            }
        });
    }

    private void fetchDefinitionsByWords(List<Word> words) {
        for (Word word : words) {
            WordDetail.Section section = new WordDetail.Section(word.pos, new ArrayList<>());
            adapterEN.list.add(section);
            dictionaryViewModel.getDefinitionsByWordID(word.id).observe(ReadingActivity.this, new Observer<List<Definition>>() {
                @Override
                public void onChanged(List<Definition> definitionList) {

                    for (Definition definition : definitionList) {
                        WordDetail.DefinitionDetail definitionDetail = new WordDetail.DefinitionDetail(definition.definition, new ArrayList<>());
                        section.getDefinitionDetails().add(definitionDetail);
                        adapterEN.notifyDataSetChanged();
                    }

                }
            });
            adapterEN.notifyDataSetChanged();
        }
        adapterEN.notifyDataSetChanged();
    }
}