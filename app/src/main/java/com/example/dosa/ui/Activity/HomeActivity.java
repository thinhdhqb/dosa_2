package com.example.dosa.ui.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.MenuItem;

import com.example.dosa.R;
import com.example.dosa.ui.Fragment.SendData;
import com.example.dosa.ui.Fragment.FragmentAccount;
import com.example.dosa.ui.Fragment.FragmentHome;
import com.example.dosa.ui.Fragment.FragmentKhoTuDecriptionContinues;
import com.example.dosa.ui.Fragment.FragmentSetting;
import com.example.dosa.ui.Fragment.FragmentDictionaryDetail;
import com.example.dosa.ui.Fragment.Fragment_Account_Detail;
import com.example.dosa.ui.Fragment.FragmentDictionary;
import com.example.dosa.ui.Fragment.FragmentNews;
import com.example.dosa.ui.Fragment.FragmentkhoTuDecription;
import com.example.dosa.ui.Fragment.Fragmentkhotu;
import com.example.dosa.utils.Utils;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.dosa.databinding.ActivityMain6Binding;


public class HomeActivity extends AppCompatActivity implements SendData {
    public FragmentManager fragmentManager;
    public ActivityMain6Binding binding;
    public Fragment_Account_Detail fragment_account_detail;
    public FragmentDictionary fragmentDictionary;
    public FragmentNews fragmentNews;
    public FragmentAccount fragmentAccount;
    public FragmentHome fragmentHome;
    public Fragmentkhotu fragmentkhotu;
    public FragmentkhoTuDecription fragmentkhoTuDecription;
    public FragmentKhoTuDecriptionContinues fragmentKhoTuDecriptionContinues;
    Fragment[] fragments;
    FragmentSetting fragmentSetting;
    public FragmentDictionaryDetail fragmentDictionaryDetail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


        fragmentManager = getSupportFragmentManager();

        fragmentHome = new FragmentHome();
        fragmentDictionary = new FragmentDictionary();
        fragmentkhotu = new Fragmentkhotu();
        fragmentAccount = new FragmentAccount();
        fragment_account_detail = new Fragment_Account_Detail();
        fragmentSetting = new FragmentSetting();
        fragmentNews = new FragmentNews();
        fragmentkhoTuDecription = new FragmentkhoTuDecription();
        fragmentDictionaryDetail = new FragmentDictionaryDetail();
        fragmentKhoTuDecriptionContinues = new FragmentKhoTuDecriptionContinues();
        fragments = new Fragment[]{fragmentHome, fragmentDictionary, fragmentkhotu, fragmentAccount, fragment_account_detail,
                fragmentSetting, fragmentNews, fragmentkhoTuDecription, fragmentKhoTuDecriptionContinues, fragmentDictionaryDetail};


        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragments) {
            fragmentTransaction.add(R.id.frameLayout, fragment, null).hide(fragment);
        }
        fragmentTransaction.show(fragmentHome).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.menuHome:
                    showFragment(fragmentHome);
                    return true;
                case R.id.menuTuDien:
                    showFragment(fragmentDictionary);
                    return true;
                case R.id.menuKhoTu:
                    showFragment(fragmentkhotu);
                    return true;
                case R.id.menuTaiKhoan:
                    showFragment(fragmentAccount);
                    return true;
            }
            return false;
        }
    };

//    @Override
//    public void sendData(String a) {
//        if (a.equals("account_detail")) {
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frameLayout, new Fragment_Account_Detail(), null);
//            fragmentTransaction.commit();
//        }else if(a.equals("account")){
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frameLayout, new FragmentAccount(), null);
//            fragmentTransaction.commit();
//        }else if (a.equals("setting")){
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frameLayout, new FragmentSetting(), null);
//            fragmentTransaction.commit();
//        }else if(a.equals("news")){
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frameLayout, new Fragment_TuDien(), null);
//            fragmentTransaction.commit();
//        }else if (a.equals("tratu_decription")){
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            FragmentTraTuDecription fragment = new FragmentTraTuDecription();
//            fragmentTransaction.replace(R.id.frameLayout,fragment, null);
//            fragmentTransaction.commit();
//        }else if(a.equals("khotu_decription")){
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frameLayout, new FragmentkhoTuDecription(), null);
//            fragmentTransaction.commit();
//        }else if (a.equals("khotu_continues")){
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//            fragmentTransaction.replace(R.id.frameLayout, new FragmentKhoTuDecriptionContinues(), null);
//            fragmentTransaction.commit();
//        }
//    }

    @Override
    public void sendData(String a, Bundle data) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (a.equals("account_detail")) {
            fragmentTransaction.replace(R.id.frameLayout, fragment_account_detail, null);
            fragmentTransaction.commit();
        }else if(a.equals("account")){
            fragmentTransaction.replace(R.id.frameLayout, fragmentAccount, null);
            fragmentTransaction.commit();
        }else if (a.equals("setting")){
            fragmentTransaction.replace(R.id.frameLayout, fragmentSetting, null);
            fragmentTransaction.commit();
        }else if(a.equals("news")){
            showFragment(fragmentNews);
        }else if (a.equals("tratu_decription")){
            fragmentDictionaryDetail.updateUI(data.getString("word"));
            showFragment(fragmentDictionaryDetail);
        }else if(a.equals("khotu_decription")){
            fragmentTransaction.replace(R.id.frameLayout, fragmentkhoTuDecription, null);
            fragmentTransaction.commit();
        }else if (a.equals("khotu_continues")){
            fragmentTransaction.replace(R.id.frameLayout, fragmentKhoTuDecriptionContinues, null);
            fragmentTransaction.commit();
        }
    }

    private void showFragment(Fragment targetFragment) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        for (Fragment fragment : fragments) {
            if (!fragment.isHidden())
                fragmentTransaction.hide(fragment);
        }

        fragmentTransaction.show(targetFragment).commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == 123) {
                String[] lookupWords = data.getStringArrayExtra("lookupWords");
                for (String word : lookupWords) {
                    Utils.saveLookupHistory(word, HomeActivity.this);
                }
                fragmentDictionary.updateUI();
            }
        }
    }

    @Override
    public void onBackPressed() {
        if (fragmentDictionaryDetail.isVisible()) {
            showFragment(fragmentDictionary);
            return;
        }
        Intent a = new Intent(Intent.ACTION_MAIN);
        a.addCategory(Intent.CATEGORY_HOME);
        a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(a);
    }
}