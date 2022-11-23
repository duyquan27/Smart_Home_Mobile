package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.afinal.fragment_device.bath_room.ViewPagerBathRoomDeviceAdapter;
import com.example.afinal.fragment_device.bath_room.fragment_bath_room_light;
import com.example.afinal.fragment_device.bath_room.fragment_bath_room_washing_machine;
import com.example.afinal.fragment_device.bed_room.ViewPagerBedRoomDeviceAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DeviceBathRoomActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    TabLayout tabLayout;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_bath_room);
        btnBack = findViewById(R.id.btnBackBathRoom);
        tabLayout = findViewById(R.id.tab_layout_device_bath_room);
        viewPager = findViewById(R.id.view_pager_device_bath_room);
        ViewPagerBathRoomDeviceAdapter adapter = new ViewPagerBathRoomDeviceAdapter(this);
        viewPager.setAdapter(adapter);
        fragment_bath_room_light fragmentBathRoomLight = new fragment_bath_room_light();
        fragment_bath_room_washing_machine fragmentWashingMachine = new fragment_bath_room_washing_machine();

        View view2 = getLayoutInflater().inflate(R.layout.customtab, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_light);
        View view4 = getLayoutInflater().inflate(R.layout.customtab, null);
        view4.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_wm);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeviceBathRoomActivity.this,MainActivity.class));
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
                }
            }
        }).attach();

    }
}