package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import androidx.viewpager2.widget.ViewPager2;

import android.widget.ImageButton;

import com.example.afinal.fragment_device.bed_room.ViewPagerBedRoomDeviceAdapter;
import com.example.afinal.fragment_device.bed_room.fragment_bed_room_lamp;
import com.example.afinal.fragment_device.bed_room.fragment_bed_room_air_conditioner;
import com.example.afinal.fragment_device.bed_room.fragment_bed_room_tv;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DeviceBedRoomActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    TabLayout tabLayout;
    ImageButton btnBack;
    private String select;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_bed_room);
        btnBack = findViewById(R.id.btnBack);
        tabLayout = findViewById(R.id.tab_layout_device_bed_room);
        viewPager = findViewById(R.id.view_pager_device_bed_room);
        ViewPagerBedRoomDeviceAdapter adapter = new ViewPagerBedRoomDeviceAdapter(this);
        viewPager.setAdapter(adapter);
        fragment_bed_room_lamp fragmentBedRoomLight = new fragment_bed_room_lamp();
        fragment_bed_room_air_conditioner fragmentBedRoomAirConditioner = new fragment_bed_room_air_conditioner();
        fragment_bed_room_tv fragmentBedRoomTv = new fragment_bed_room_tv();

        View view2 = getLayoutInflater().inflate(R.layout.customtab, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_light);
        View view4 = getLayoutInflater().inflate(R.layout.customtab, null);
        view4.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_air_condition);
        View view6 = getLayoutInflater().inflate(R.layout.customtab, null);
        view6.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_tv);
        select = getIntent().getStringExtra("selector");

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        viewPager.setCurrentItem(Integer.parseInt(select));

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