package com.example.dosa.ui.Fragment;

import android.content.Context;
import android.inputmethodservice.AbstractInputMethodService;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
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
import com.example.dosa.local.entity.Definition;
import com.example.dosa.local.entity.Example;
import com.example.dosa.local.entity.IPA;
import com.example.dosa.local.entity.Word;
import com.example.dosa.ui.Adapter.AdapterDictionarySection;
import com.example.dosa.ui.Adapter.AdapterSearchResult;
import com.example.dosa.viewmodel.DictionaryViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class FragmentTraTuDecription extends Fragment {
    FragmentTratuDecriptionBinding binding;
    DictionaryViewModel dictionaryViewModel;
    SendData sendData;

    AdapterDictionarySection adapterDictionarySection;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentTratuDecriptionBinding.inflate(inflater,container,false);

        dictionaryViewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);

        adapterDictionarySection = new AdapterDictionarySection();

        binding.rcvDictionarySection.setAdapter(adapterDictionarySection);
        binding.rcvDictionarySection.setLayoutManager(new LinearLayoutManager(this.getActivity()));

        AdapterSearchResult adapterSearchResult = new AdapterSearchResult(getContext(), new ArrayList<>(), sendData);
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
                dictionaryViewModel.getWordsAsLiveDataStartWith(newText).observe(FragmentTraTuDecription.this.getActivity(), new Observer<List<String>>() {
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

        Bundle data = getArguments();
        if (data.containsKey("word")) {
            String word = data.getString("word");
            binding.txtWord.setText(word);
            fetchData(word);
            binding.schvWord2.setQuery(word, false);
        }


        return binding.getRoot();
    }

    private void fetchData(String word) {

        dictionaryViewModel.getWordsByWord(word).observe(FragmentTraTuDecription.this.getActivity(), new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                fetchDefinitionsByWords(words);
            }
        });

        dictionaryViewModel.getIPAByWord(word).observe(FragmentTraTuDecription.this.getActivity(), new Observer<List<IPA>>() {
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
    }

    private void fetchDefinitionsByWords(List<Word> words) {
        for (Word word : words) {
            Pair<String, ArrayList<Pair<String, ArrayList<String>>>> positionSection = new Pair<>(word.pos, new ArrayList<>());
            adapterDictionarySection.list.add(positionSection);
            dictionaryViewModel.getDefinitionsByWordID(word.id).observe(FragmentTraTuDecription.this.getActivity(), new Observer<List<Definition>>() {
                @Override
                public void onChanged(List<Definition> definitionList) {
                    Log.d("fetchDefinitionsByWords", "onChanged: ");

                    for (Definition definition : definitionList) {
                        Pair<String, ArrayList<String>> definitionExample = new Pair<>(definition.definition, new ArrayList<>());
                        positionSection.second.add(definitionExample);
                        dictionaryViewModel.getExampleByDefinitionID(definition.id).observe(FragmentTraTuDecription.this.getActivity(), new Observer<List<String>>() {
                            @Override
                            public void onChanged(List<String> strings) {
                                definitionExample.second.addAll(strings);
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
