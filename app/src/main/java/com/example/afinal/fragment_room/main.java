package com.example.afinal.fragment_room;

import android.os.Bundle;
import android.os.PersistableBundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.afinal.R;
import com.example.afinal.fragment_home.ViewPagerMainAdapter;
import com.google.android.material.tabs.TabLayout;

public class main extends AppCompatActivity {
    TabLayout wmTabLayout;
    ViewPager wmViewPaper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.washing_machine);

        wmTabLayout = findViewById(R.id.tabLayoutWM);
        wmViewPaper = findViewById(R.id.viewPagerWM);

        ViewPagerMainAdapter adapter = new ViewPagerMainAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        wmViewPaper.setAdapter(adapter);
        wmTabLayout.setupWithViewPager(wmViewPaper);
    }
}
