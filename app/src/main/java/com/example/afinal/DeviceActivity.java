package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList; // import the ArrayList class

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.example.afinal.fragment_device.ViewPagerDeviceAdapter;
import com.example.afinal.fragment_device.fragmentLight;
import com.example.afinal.fragment_device.fragmentAircon;
import com.example.afinal.fragment_device.fragmentTV;
import com.example.afinal.R;
import com.example.afinal.fragment_home.ViewPagerMainAdapter;
import com.example.afinal.widget.CustomViewPager;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DeviceActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    TabLayout tabLayout;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device);
        btnBack = findViewById(R.id.btnBack);
        tabLayout = findViewById(R.id.tab_layout);
        viewPager = findViewById(R.id.view_pager);
        ViewPagerDeviceAdapter adapter = new ViewPagerDeviceAdapter(this);
        viewPager.setAdapter(adapter);
        fragmentLight fragmentLight = new fragmentLight();
        fragmentAircon fragmentAircon = new fragmentAircon();
        fragmentTV fragmentTV = new fragmentTV();

        View view1 = getLayoutInflater().inflate(R.layout.customtab, null);
        view1.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_light_select);
        View view2 = getLayoutInflater().inflate(R.layout.customtab, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_light);
        View view3 = getLayoutInflater().inflate(R.layout.customtab, null);
        view3.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_aic_on);
        View view4 = getLayoutInflater().inflate(R.layout.customtab, null);
        view4.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_air_condition);
        View view5 = getLayoutInflater().inflate(R.layout.customtab, null);
        view5.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_tv_on);
        View view6 = getLayoutInflater().inflate(R.layout.customtab, null);
        view6.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_tv);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeviceActivity.this,MainActivity.class));
            }
        });
        new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                switch (position)
                {
                    case 0:
                        tab.setCustomView(view2);
                        break;
                    case 1:
                        tab.setCustomView(view4);
                        break;
                    case 2:
                        tab.setCustomView(view6);
                        break;
                }
            }
        }).attach();

    }
}