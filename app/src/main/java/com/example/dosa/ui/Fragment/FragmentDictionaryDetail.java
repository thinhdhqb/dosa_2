package com.example.dosa.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dosa.databinding.FragmentTratuDecriptionBinding;
import com.example.dosa.data.entity.Definition;
import com.example.dosa.data.entity.EngVieTranslation;
import com.example.dosa.data.entity.IPA;
import com.example.dosa.data.entity.Word;
import com.example.dosa.data.entity.WordDetail;
import com.example.dosa.ui.Adapter.AdapterDictionarySection;
import com.example.dosa.ui.Adapter.AdapterSearchResult;
import com.example.dosa.ui.Adapter.AdapterTranslation;
import com.example.dosa.viewmodel.DictionaryViewModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FragmentDictionaryDetail extends Fragment {
    FragmentTratuDecriptionBinding binding;
    DictionaryViewModel dictionaryViewModel;
    SendData sendData;

    AdapterDictionarySection adapterDictionarySection;
    AdapterTranslation adapterTranslation;
    AdapterSearchResult adapterSearchResult;
    TextToSpeech tts;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTratuDecriptionBinding.inflate(inflater,container,false);

        tts = new TextToSpeech(getActivity(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

            }
        });

        dictionaryViewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);

        adapterDictionarySection = new AdapterDictionarySection();
        adapterTranslation = new AdapterTranslation(new ArrayList<>());

        binding.rcvDictionarySection.setAdapter(adapterDictionarySection);
        binding.rcvDictionarySection.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        binding.rcvTranslation.setLayoutManager(new LinearLayoutManager(this.getActivity()));



        adapterSearchResult = new AdapterSearchResult(getActivity(), new ArrayList<>(), sendData);
        binding.rcvSearchResult2.setAdapter(adapterSearchResult);
        binding.rcvSearchResult2.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        binding.rcvSearchResult2.addItemDecoration(dividerItemDecoration);
        binding.schvWord2.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterSearchResult.list.clear();
                dictionaryViewModel.getWordsAsLiveDataStartWith(newText).observe(FragmentDictionaryDetail.this.getActivity(), new Observer<List<String>>() {
                    @Override
                    public void onChanged(List<String> strings) {
                        adapterSearchResult.list.addAll(strings);
                        adapterSearchResult.notifyDataSetChanged();

                    }
                });


                return false;
            }
        });
        binding.schvWord2.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    binding.rcvSearchResult2.setVisibility(View.VISIBLE);
                }
                else {
                    binding.rcvSearchResult2.setVisibility(View.INVISIBLE);
                }
            }
        });
        return binding.getRoot();
    }

    public void updateUI(String word) {
        binding.layoutGeneralIPA.setVisibility(View.GONE);
        binding.layoutUSIPA.setVisibility(View.GONE);
        binding.layoutUKIPA.setVisibility(View.GONE);
        binding.txtWord.setText(word);
        adapterDictionarySection.list.clear();
        binding.rcvSearchResult2.setVisibility(View.INVISIBLE);

        adapterTranslation.list.clear();
        binding.txtVie.setVisibility(View.GONE);
        binding.schvWord2.clearFocus();
        fetchData(word);
        binding.schvWord2.setQuery(word, false);
        binding.imvUKAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("imvUKAudio", "onClick: ");
                tts.setLanguage(Locale.UK);
                tts.speak(word, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        binding.imvUSAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.setLanguage(Locale.US);
                tts.speak(word, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        binding.imvGeneral.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tts.setLanguage(Locale.ENGLISH);
                tts.speak(word, TextToSpeech.QUEUE_FLUSH, null);
            }
        });
    }

    private void fetchData(String word) {

        dictionaryViewModel.getWordsByWord(word).observe(FragmentDictionaryDetail.this.getActivity(), new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                fetchDefinitionsByWords(words);
            }
        });

        dictionaryViewModel.getIPAByWord(word).observe(FragmentDictionaryDetail.this.getActivity(), new Observer<List<IPA>>() {
            @Override
            public void onChanged(List<IPA> ipas) {
                for (IPA ipa : ipas) {
                    if (ipa.tag.equals("UK")) {
                        binding.txtUKIPA.setText(ipa.ipa);
                        binding.layoutUKIPA.setVisibility(View.VISIBLE);

                    }
                    if (ipa.tag.equals("US")) {
                        binding.txtUSIPA.setText(ipa.ipa);
                        binding.layoutUSIPA.setVisibility(View.VISIBLE);

                    }
                    if (ipa.tag.equals("General")) {
                        binding.txtGeneralIPA.setText(ipa.ipa);
                        binding.layoutGeneralIPA.setVisibility(View.VISIBLE);

                    }
                }
            }
        });
        dictionaryViewModel.getTranslationByWord(word).observe(FragmentDictionaryDetail.this.getActivity(), new Observer<EngVieTranslation>() {
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
                    binding.rcvTranslation.setAdapter(adapterTranslation);
                    binding.txtVie.setVisibility(View.VISIBLE);
                };
            }
        });
    }

    private void fetchDefinitionsByWords(List<Word> words) {
        for (Word word : words) {
            WordDetail.Section section = new WordDetail.Section(word.pos, new ArrayList<>());
            adapterDictionarySection.list.add(section);
            dictionaryViewModel.getDefinitionsByWordID(word.id).observe(FragmentDictionaryDetail.this.getActivity(), new Observer<List<Definition>>() {
                @Override
                public void onChanged(List<Definition> definitionList) {
                    Log.d("fetchDefinitionsByWords", "onChanged: ");

                    for (Definition definition : definitionList) {
                        WordDetail.DefinitionDetail definitionDetail = new WordDetail.DefinitionDetail(definition.definition, new ArrayList<>());
                        section.getDefinitionDetails().add(definitionDetail);
                        dictionaryViewModel.getExampleByDefinitionID(definition.id).observe(FragmentDictionaryDetail.this.getActivity(), new Observer<List<String>>() {
                            @Override
                            public void onChanged(List<String> strings) {
                                definitionDetail.getExamples().addAll(strings);
                                adapterDictionarySection.notifyDataSetChanged();
                            }
                        });
                        adapterDictionarySection.notifyDataSetChanged();
                    }

                }
            });
            adapterDictionarySection.notifyDataSetChanged();
        }
        adapterDictionarySection.notifyDataSetChanged();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SendData) {
            sendData = (SendData) context;
        } else {
            throw new RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendData = null;
    }


}
