package com.example.dosa.ui.Fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.dosa.ui.Adapter.AdapterNews;
import com.example.dosa.R;
import com.example.dosa.ui.Activity.MultipleChoiceActivity;
import com.example.dosa.ui.Activity.News;
import com.example.dosa.databinding.FragmentHomeBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;


import java.util.ArrayList;
import java.util.List;

public class FragmentHome extends Fragment {
    FragmentHomeBinding binding;
    List<News> list;
    SendData sendData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        list = new ArrayList<>();
        list.add(new News("1", "The world’s happiest countries for 2023", "Văn hóa", "CNC:", "There’s cause for optimism in the latest report on world happiness.\n" +
                "For one, benevolence is about 25% higher than it was pre-pandemic.\n" +
                "“Benevolence to others, especially the helping of strangers, which went up dramatically in 2021, stayed high in 2022,” John Helliwell, one of the authors of the World Happiness Report, said in an interview with CNN.\n" +
                "And global happiness has not taken a hit in the three years of the Covid-19 pandemic. Life evaluations from 2020 to 2022 have been “remarkably resilient,” the report says, with global averages basically in line with the three years preceding the pandemic.\n" +
                "“Even during these difficult years, positive emotions have remained twice as prevalent as negative ones, and feelings of positive social support twice as strong as those of loneliness,” Helliwell said in a news release.\n" +
                "The report, which is a publication of the UN Sustainable Development Solutions Network, draws on global survey data from people in more than 150 countries. Countries are ranked on happiness based on their average life evaluations over the three preceding years, in this case 2020 to 2022.\n" +
                "The report, which was released on Monday, identifies the happiest nations, those at the very bottom of the happiness scale and everything in between, plus the factors that tend to lead to greater happiness. March 20 is the International Day of Happiness, a day designated by the United Nations that’s marking its 10th anniversary in 2023.", R.drawable.img_9));
        list.add(new News("2", "The world’s happiest countries for 2023", "Văn hóa", "CNC:", "abc", R.drawable.img_9));
        list.add(new News("1", "The world’s happiest countries for 2023", "Văn hóa", "CNC:", "There’s cause for optimism in the latest report on world happiness.\n" +
                "For one, benevolence is about 25% higher than it was pre-pandemic.\n" +
                "“Benevolence to others, especially the helping of strangers, which went up dramatically in 2021, stayed high in 2022,” John Helliwell, one of the authors of the World Happiness Report, said in an interview with CNN.\n" +
                "And global happiness has not taken a hit in the three years of the Covid-19 pandemic. Life evaluations from 2020 to 2022 have been “remarkably resilient,” the report says, with global averages basically in line with the three years preceding the pandemic.\n" +
                "“Even during these difficult years, positive emotions have remained twice as prevalent as negative ones, and feelings of positive social support twice as strong as those of loneliness,” Helliwell said in a news release.\n" +
                "The report, which is a publication of the UN Sustainable Development Solutions Network, draws on global survey data from people in more than 150 countries. Countries are ranked on happiness based on their average life evaluations over the three preceding years, in this case 2020 to 2022.\n" +
                "The report, which was released on Monday, identifies the happiest nations, those at the very bottom of the happiness scale and everything in between, plus the factors that tend to lead to greater happiness. March 20 is the International Day of Happiness, a day designated by the United Nations that’s marking its 10th anniversary in 2023.", R.drawable.img_9));
        list.add(new News("3", "The world’s happiest countries for 2023", "Văn hóa", "CNC:", "There’s cause for optimism in the latest report on world happiness.\n" +
                "For one, benevolence is about 25% higher than it was pre-pandemic.\n" +
                "“Benevolence to others, especially the helping of strangers, which went up dramatically in 2021, stayed high in 2022,” John Helliwell, one of the authors of the World Happiness Report, said in an interview with CNN.\n" +
                "And global happiness has not taken a hit in the three years of the Covid-19 pandemic. Life evaluations from 2020 to 2022 have been “remarkably resilient,” the report says, with global averages basically in line with the three years preceding the pandemic.\n" +
                "“Even during these difficult years, positive emotions have remained twice as prevalent as negative ones, and feelings of positive social support twice as strong as those of loneliness,” Helliwell said in a news release.\n" +
                "The report, which is a publication of the UN Sustainable Development Solutions Network, draws on global survey data from people in more than 150 countries. Countries are ranked on happiness based on their average life evaluations over the three preceding years, in this case 2020 to 2022.\n" +
                "The report, which was released on Monday, identifies the happiest nations, those at the very bottom of the happiness scale and everything in between, plus the factors that tend to lead to greater happiness. March 20 is the International Day of Happiness, a day designated by the United Nations that’s marking its 10th anniversary in 2023.", R.drawable.img_9));
        list.add(new News("4", "The world’s happiest countries for 2023", "Văn hóa", "CNC:", "There’s cause for optimism in the latest report on world happiness.\n" +
                "For one, benevolence is about 25% higher than it was pre-pandemic.\n" +
                "“Benevolence to others, especially the helping of strangers, which went up dramatically in 2021, stayed high in 2022,” John Helliwell, one of the authors of the World Happiness Report, said in an interview with CNN.\n" +
                "And global happiness has not taken a hit in the three years of the Covid-19 pandemic. Life evaluations from 2020 to 2022 have been “remarkably resilient,” the report says, with global averages basically in line with the three years preceding the pandemic.\n" +
                "“Even during these difficult years, positive emotions have remained twice as prevalent as negative ones, and feelings of positive social support twice as strong as those of loneliness,” Helliwell said in a news release.\n" +
                "The report, which is a publication of the UN Sustainable Development Solutions Network, draws on global survey data from people in more than 150 countries. Countries are ranked on happiness based on their average life evaluations over the three preceding years, in this case 2020 to 2022.\n" +
                "The report, which was released on Monday, identifies the happiest nations, those at the very bottom of the happiness scale and everything in between, plus the factors that tend to lead to greater happiness. March 20 is the International Day of Happiness, a day designated by the United Nations that’s marking its 10th anniversary in 2023.", R.drawable.img_9));

        AdapterNews adapterNews = new AdapterNews(getContext(), list);
        binding.recyclerView.setAdapter(adapterNews);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        binding.recyclerView.setHasFixedSize(true);

        binding.imgNews.setOnClickListener(view -> {
            sendData.sendData("news", null);
        });

        binding.btnLuyenTap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getContext(), MultipleChoiceActivity.class));
            }
        });

        return binding.getRoot();
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
