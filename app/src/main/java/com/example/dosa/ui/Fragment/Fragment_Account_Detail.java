package com.example.dosa.ui.Fragment;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.dosa.databinding.FragmentAccountDetailBinding;

import java.util.Calendar;
import java.util.Date;

public class Fragment_Account_Detail extends Fragment {

    FragmentAccountDetailBinding binding;

    SendData sendData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentAccountDetailBinding.inflate(inflater, container, false);
        binding.imgBack.setOnClickListener(view->
        {
            sendData.sendData("account", null);
        });


        binding.edtDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(Fragment_Account_Detail.this.getContext());
                Date date = Calendar.getInstance().getTime();
                datePickerDialog.updateDate(date.getYear(), date.getMonth(), date.getDay());
                datePickerDialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                        }
                });
                datePickerDialog.setCanceledOnTouchOutside(false);
                datePickerDialog.show();
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof SendData){
            sendData = (SendData) context;
        }else
        {
            throw  new  RuntimeException(context.toString());
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        sendData = null;
    }
}
