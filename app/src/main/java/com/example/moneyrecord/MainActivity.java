package com.example.moneyrecord;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.example.moneyrecord.fragments.Inputfragment;
import com.example.moneyrecord.fragments.Calendarfragment;
import com.example.moneyrecord.fragments.Graphfragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import androidx.fragment.app.Fragment;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 初期画面をInputFragmentに
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.fragmentContainerView, new Inputfragment())
                .commit();

        // BottomNavigationViewの処理
        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnItemSelectedListener(item -> {
            Fragment selectedFragment;

            int id = item.getItemId();

            if (id == R.id.nav_calendar) {
                selectedFragment = new Calendarfragment();
            } else if (id == R.id.nav_graph) {
                selectedFragment = new Graphfragment();
            } else {
                selectedFragment = new Inputfragment();
            }


            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragmentContainerView, selectedFragment)
                    .commit();

            return true;
        });





    }
}

