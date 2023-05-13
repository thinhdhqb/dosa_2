package com.example.dosa.ui.Activity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.dosa.R;
import com.example.dosa.ui.Fragment.FragmentLikedArticle;
import com.example.dosa.ui.Fragment.FragmentLikedWord;
import com.example.dosa.ui.Fragment.SendData;
import com.example.dosa.ui.Fragment.FragmentAccount;
import com.example.dosa.ui.Fragment.FragmentHome;
import com.example.dosa.ui.Fragment.FragmentSetting;
import com.example.dosa.ui.Fragment.FragmentDictionaryDetail;
import com.example.dosa.ui.Fragment.Fragment_Account_Detail;
import com.example.dosa.ui.Fragment.FragmentDictionary;
import com.example.dosa.ui.Fragment.FragmentNews;
import com.example.dosa.ui.Fragment.FragmentWordList;
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
    public FragmentWordList fragmentWordList;
    public FragmentLikedWord fragmentLikedWord;
    public FragmentLikedArticle fragmentLikedArticle;
    boolean singleBack = false;


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
        fragmentWordList = new FragmentWordList();
        fragmentAccount = new FragmentAccount();
        fragmentNews = new FragmentNews();
        fragmentDictionaryDetail = new FragmentDictionaryDetail();
        fragmentLikedArticle = new FragmentLikedArticle();
        fragmentLikedWord = new FragmentLikedWord();
        fragments = new Fragment[]{fragmentHome, fragmentDictionary, fragmentWordList, fragmentAccount,
                 fragmentNews , fragmentDictionaryDetail, fragmentLikedWord, fragmentLikedArticle};


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
                    showFragment(fragmentWordList);
                    return true;
                case R.id.menuTaiKhoan:
                    showFragment(fragmentAccount);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void sendData(String a, Bundle data) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if (a.equals("likedWords")) {
            fragmentLikedWord.fetchLikedWords();
            showFragment(fragmentLikedWord);
        }else if(a.equals("account")){
            showFragment(fragmentAccount);
        }else if (a.equals("likedArticles")){
            fragmentLikedArticle.fetchArticlesID();
            showFragment(fragmentLikedArticle);
        }else if(a.equals("news")){
            fragmentNews.fetchArticles();
            showFragment(fragmentNews);
        }else if (a.equals("tratu_decription")){
            fragmentDictionaryDetail.updateUI(data.getString("word"));
            showFragment(fragmentDictionaryDetail);
        }else if (a.equals("account")){
            showFragment(fragmentAccount);
        }
    }

    public void showFragment(Fragment targetFragment) {
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
        if (fragmentNews.isVisible()) {
            showFragment(fragmentHome);
            return;
        }

        if (singleBack) {
            Intent a = new Intent(Intent.ACTION_MAIN);
            a.addCategory(Intent.CATEGORY_HOME);
            a.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(a);
            return;
        }
        this.singleBack = true;
        Toast.makeText(this, "Nhấn nút trở về lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                singleBack=false;
            }
        }, 2000);

    }

}