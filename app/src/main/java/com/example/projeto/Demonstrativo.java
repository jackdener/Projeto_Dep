package com.example.projeto;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.widget.ViewPager2;

import android.graphics.Color;
import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

public class Demonstrativo extends AppCompatActivity {
    TabLayout tabl;
    ViewPager2 pager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demonstrativo);
        getSupportActionBar().hide();
        tabl = findViewById(R.id.tabl);
        pager = findViewById(R.id.pager);
        FragmentManager fm = getSupportFragmentManager();
        FragmentAdapter adaptadorDemonstrativo = new FragmentAdapter(fm,getLifecycle());
        pager.setAdapter(adaptadorDemonstrativo);
        tabl.setSelectedTabIndicatorColor(Color.parseColor("#FF0000"));
        tabl.setTabTextColors(Color.parseColor("#000000"), Color.parseColor("#FF0000"));
        tabl.addTab(tabl.newTab().setText("Médias"));
        tabl.addTab(tabl.newTab().setText("Sugestões"));
        tabl.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                pager.setCurrentItem(tab.getPosition());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        pager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                tabl.selectTab(tabl.getTabAt(position));
            }
        });
    }
}