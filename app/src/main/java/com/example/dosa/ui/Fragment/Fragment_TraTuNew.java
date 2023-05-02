package com.example.dosa.ui.Fragment;

import android.content.Context;
import android.os.Bundle;
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

import com.example.dosa.ui.Adapter.AdapterSearchResult;
import com.example.dosa.ui.Adapter.AdapterTraTu;
import com.example.dosa.viewmodel.DictionaryViewModel;
import com.example.dosa.ui.Activity.TraTu;
import com.example.dosa.databinding.FragmentTudienNewBinding;


import java.util.ArrayList;
import java.util.List;

public class Fragment_TraTuNew  extends Fragment {
    FragmentTudienNewBinding fragmentTudienNewBinding;
    SendData sendData;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       fragmentTudienNewBinding = FragmentTudienNewBinding.inflate(inflater,container,false);


        List<TraTu> list = new ArrayList<>();
        list.add(new TraTu("1", "1", "1", "1", "1", "1"));
        list.add(new TraTu("1", "1", "1", "1", "1", "1"));
        list.add(new TraTu("1", "1", "1", "1", "1", "1"));
        list.add(new TraTu("1", "1", "1", "1", "1", "1"));
        list.add(new TraTu("1", "1", "1", "1", "1", "1"));
        list.add(new TraTu("1", "1", "1", "1", "1", "1"));

        AdapterTraTu adapterTraTu = new AdapterTraTu(getContext(), list, new SendData() {
            @Override
            public void sendData(String a, Bundle data) {
                sendData.sendData("tratu_decription", null);
            }
        });

        fragmentTudienNewBinding.recyclerTraTu.setAdapter(adapterTraTu);
        fragmentTudienNewBinding.recyclerTraTu.setLayoutManager(new LinearLayoutManager(getContext()));



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
