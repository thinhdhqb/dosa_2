package com.example.dosa.ui.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.dosa.R;
import com.example.dosa.ui.Fragment.SendData;
import com.example.dosa.ui.Fragment.FragmentAccount;
import com.example.dosa.ui.Fragment.FragmentHome;
import com.example.dosa.ui.Fragment.FragmentKhoTuDecriptionContinues;
import com.example.dosa.ui.Fragment.FragmentSetting;
import com.example.dosa.ui.Fragment.FragmentTraTuDecription;
import com.example.dosa.ui.Fragment.Fragment_Account_Detail;
import com.example.dosa.ui.Fragment.Fragment_TraTuNew;
import com.example.dosa.ui.Fragment.Fragment_TuDien;
import com.example.dosa.ui.Fragment.FragmentkhoTuDecription;
import com.example.dosa.ui.Fragment.Fragmentkhotu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.dosa.databinding.ActivityMain6Binding;


public class HomeActivity extends AppCompatActivity implements SendData {
    FragmentManager fragmentManager;
    ActivityMain6Binding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMain6Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.frameLayout, new FragmentHome(), null);
        //fragmentTransaction.replace(R.id.container, new FragmentA(), null);
        fragmentTransaction.commit();

        binding.bottomNavigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);


    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.menuHome:

                    fragmentTransaction.replace(R.id.frameLayout, new FragmentHome(), null);
                    //fragmentTransaction.replace(R.id.container, new FragmentA(), null);
                    fragmentTransaction.commit();
                    return true;
                case R.id.menuTuDien:
                    fragmentTransaction.replace(R.id.frameLayout, new Fragment_TraTuNew(), null);
                    //fragmentTransaction.replace(R.id.container, new FragmentA(), null);
                    fragmentTransaction.commit();
                    return true;
                case R.id.menuKhoTu:
                    fragmentTransaction.replace(R.id.frameLayout, new Fragmentkhotu(), null);
                    //fragmentTransaction.replace(R.id.container, new FragmentA(), null);
                    fragmentTransaction.commit();
                    return true;
                case R.id.menuTaiKhoan:
                    fragmentTransaction.replace(R.id.frameLayout, new FragmentAccount(), null);
                    //fragmentTransaction.replace(R.id.container, new FragmentA(), null);
                    fragmentTransaction.commit();
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
        if (a.equals("account_detail")) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new Fragment_Account_Detail(), null);
            fragmentTransaction.commit();
        }else if(a.equals("account")){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new FragmentAccount(), null);
            fragmentTransaction.commit();
        }else if (a.equals("setting")){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new FragmentSetting(), null);
            fragmentTransaction.commit();
        }else if(a.equals("news")){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new Fragment_TuDien(), null);
            fragmentTransaction.commit();
        }else if (a.equals("tratu_decription")){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            FragmentTraTuDecription fragment = new FragmentTraTuDecription();
            fragment.setArguments(data);
            fragmentTransaction.replace(R.id.frameLayout,fragment, null);
            fragmentTransaction.commit();
        }else if(a.equals("khotu_decription")){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new FragmentkhoTuDecription(), null);
            fragmentTransaction.commit();
        }else if (a.equals("khotu_continues")){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.frameLayout, new FragmentKhoTuDecriptionContinues(), null);
            fragmentTransaction.commit();
        }
    }
}