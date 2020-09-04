package com.android.rolap.Activity;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.rolap.Fragment.HomeFragment;
import com.android.rolap.Fragment.KategoriFragment;
import com.android.rolap.Fragment.OrderFragment;
import com.android.rolap.Fragment.ProfilFragment;
import com.android.rolap.Fragment.SpaFragment;
import com.android.rolap.Helper.Helper;
import com.android.rolap.Helper.PrefManager;
import com.android.rolap.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MenuActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private Helper helper;
    private PrefManager prefmanager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        helper = new Helper(this);
        prefmanager = new PrefManager(this);

        BottomNavigationView bnview = findViewById(R.id.bnvMenu);
        bnview.setOnNavigationItemSelectedListener(this);

        String fragment = getIntent().getStringExtra("fragment");
        if(fragment != null && fragment.equalsIgnoreCase("tutor")){
            bnview.setSelectedItemId(R.id.tutor);
            replaceFragment(new KategoriFragment());
        }else if(fragment != null && fragment.equalsIgnoreCase("order")) {
            bnview.setSelectedItemId(R.id.order);
            replaceFragment(new OrderFragment());
        }else if(fragment != null && fragment.equalsIgnoreCase("profil")) {
            bnview.setSelectedItemId(R.id.order);
            replaceFragment(new ProfilFragment());
        }else {
            replaceFragment(new HomeFragment());
        }
    }

    @Override
    public void onBackPressed() {
        int count = getSupportFragmentManager().getBackStackEntryCount();

        if (count == 0) {
            super.onBackPressed();
            //additional code
        } else {
            getSupportFragmentManager().popBackStack();
        }
        overridePendingTransition(R.anim.slide_in, R.anim.slide_out);
    }

    public void replaceFragment(@NonNull Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.container, fragment, fragment.getClass().getSimpleName());
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        ft.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int id = menuItem.getItemId();

        if (id == R.id.home) {
            replaceFragment(new HomeFragment());
        } else if (id == R.id.tutor) {
            replaceFragment(new KategoriFragment());
        } else if (id == R.id.spa) {
            replaceFragment(new SpaFragment());
        } else if (id == R.id.order) {
            replaceFragment(new OrderFragment());
        }else if (id == R.id.profil) {
            replaceFragment(new ProfilFragment());
        }
        return true;
    }
}
