package com.example.dosa.ui.Fragment;

import android.content.Context;
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

import com.example.dosa.local.entity.Definition;
import com.example.dosa.local.entity.EngVieTranslation;
import com.example.dosa.local.entity.IPA;
import com.example.dosa.local.entity.Word;
import com.example.dosa.local.entity.WordDetail;
import com.example.dosa.ui.Adapter.AdapterSearchResult;
import com.example.dosa.ui.Adapter.AdapterTraTu;
import com.example.dosa.ui.Adapter.AdapterTranslation;
import com.example.dosa.viewmodel.DictionaryViewModel;
import com.example.dosa.ui.Activity.TraTu;
import com.example.dosa.databinding.FragmentTudienNewBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

public class Fragment_TraTuNew  extends Fragment {
    FragmentTudienNewBinding fragmentTudienNewBinding;
    DictionaryViewModel  dictionaryViewModel;
    AdapterTraTu adapterTraTu;
    SendData sendData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
        String userID = currentUser.getUid();
        dictionaryViewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);



        fragmentTudienNewBinding = FragmentTudienNewBinding.inflate(inflater,container,false);

        fragmentTudienNewBinding.recyclerTraTu.setLayoutManager(new LinearLayoutManager(getContext()));
        adapterTraTu = new AdapterTraTu(getContext(), new ArrayList<>(), sendData);
        fragmentTudienNewBinding.recyclerTraTu.setAdapter(adapterTraTu);
        fetchHistory(userID);


        DictionaryViewModel dictionaryViewModel = new ViewModelProvider(this).get(DictionaryViewModel.class);


        AdapterSearchResult adapterSearchResult = new AdapterSearchResult(getContext(), new ArrayList<>(), sendData);
        fragmentTudienNewBinding.rcvSearchResult.setAdapter(adapterSearchResult);
        fragmentTudienNewBinding.rcvSearchResult.setLayoutManager(new LinearLayoutManager(getContext()));
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL);
        fragmentTudienNewBinding.rcvSearchResult.addItemDecoration(dividerItemDecoration);
        fragmentTudienNewBinding.schvWord.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterSearchResult.list.clear();
                dictionaryViewModel.getWordsAsLiveDataStartWith(newText).observe(Fragment_TraTuNew.this.getActivity(), new Observer<List<String>>() {
                    @Override
                    public void onChanged(List<String> strings) {
                        adapterSearchResult.list.addAll(strings);
                        adapterSearchResult.notifyDataSetChanged();

                    }
                });


                return false;
            }
        });
        fragmentTudienNewBinding.schvWord.setOnQueryTextFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                if (b) {
                    fragmentTudienNewBinding.rcvSearchResult.setVisibility(View.VISIBLE);
                }
                else {
                    fragmentTudienNewBinding.rcvSearchResult.setVisibility(View.INVISIBLE);
                }
            }
        });

        return fragmentTudienNewBinding.getRoot();
    }

    private void fetchHistory(String userID) {
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("LookupHistory")
                .whereEqualTo("userID", userID)
                .orderBy("timestamp", Query.Direction.DESCENDING)
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                WordDetail wordDetail = new WordDetail();
                                wordDetail.setWord(document.get("word").toString());
                                Log.d("HistoryLookup", "onComplete: " + document.get("word").toString());
                                adapterTraTu.list.add(wordDetail);
                                fetchData(wordDetail);
                            }
                        } else {
                        }
                    }
                });
    }

    private void fetchData(WordDetail wordDetail) {

        dictionaryViewModel.getWordsByWord(wordDetail.getWord()).observe(Fragment_TraTuNew.this.getActivity(), new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                fetchDefinitionsByWords(words, wordDetail);
            }
        });

        dictionaryViewModel.getIPAByWord(wordDetail.getWord()).observe(Fragment_TraTuNew.this.getActivity(), new Observer<List<IPA>>() {
            @Override
            public void onChanged(List<IPA> ipas) {
                for (IPA ipa : ipas) {
                    if (ipa.tag.equals("UK")) {
                        wordDetail.setUkIPA(ipa.ipa);
                    }
                    if (ipa.tag.equals("US")) {
                        wordDetail.setUsIPA(ipa.ipa);
                    }
                    if (ipa.tag.equals("General")) {
                        wordDetail.setGeneralIPA(ipa.ipa);
                    }
                }
            }
        });
        dictionaryViewModel.getTranslationByWord(wordDetail.getWord()).observe(Fragment_TraTuNew.this.getActivity(), new Observer<EngVieTranslation>() {
            @Override
            public void onChanged(EngVieTranslation engVieTranslations) {
                if (engVieTranslations != null) {
                    ArrayList<String> list = new ArrayList<>();
                    Document document = Jsoup.parse(engVieTranslations.html);
                    Elements translationElement = document.select("li");
                    for (Element element : translationElement) {
                        list.add(element.text());
                    }
                    wordDetail.setTranslations(list);
                };
            }
        });
    }

    private void fetchDefinitionsByWords(List<Word> words, WordDetail wordDetail) {
        for (Word word : words) {
            WordDetail.Section section = new WordDetail.Section(word.pos, new ArrayList<>());
            wordDetail.getSections().add(section);
            dictionaryViewModel.getDefinitionsByWordID(word.id).observe(Fragment_TraTuNew.this.getActivity(), new Observer<List<Definition>>() {
                @Override
                public void onChanged(List<Definition> definitionList) {
                    Log.d("fetchDefinitionsByWords", "onChanged: ");

                    for (Definition definition : definitionList) {
                        WordDetail.DefinitionDetail definitionDetail = new WordDetail.DefinitionDetail(definition.definition, new ArrayList<>());
                        section.getDefinitionDetails().add(definitionDetail);
                        dictionaryViewModel.getExampleByDefinitionID(definition.id).observe(Fragment_TraTuNew.this.getActivity(), new Observer<List<String>>() {
                            @Override
                            public void onChanged(List<String> strings) {
                                definitionDetail.getExamples().addAll(strings);
                                adapterTraTu.notifyDataSetChanged();
                            }
                        });
                        adapterTraTu.notifyDataSetChanged();
                    }

                }
            });
            adapterTraTu.notifyDataSetChanged();
        }
        adapterTraTu.notifyDataSetChanged();
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
