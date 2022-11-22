package com.example.afinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.afinal.fragment_device.bed_room.ViewPagerBedRoomDeviceAdapter;
import com.example.afinal.fragment_device.bed_room.fragment_bed_room_air_conditioner;
import com.example.afinal.fragment_device.bed_room.fragment_bed_room_light;
import com.example.afinal.fragment_device.bed_room.fragment_bed_room_tv;
import com.example.afinal.fragment_device.living_room.ViewPagerLingRoomDeviceAdapter;
import com.example.afinal.fragment_device.living_room.fragment_living_room_air_conditioner;
import com.example.afinal.fragment_device.living_room.fragment_living_room_light;
import com.example.afinal.fragment_device.living_room.fragment_living_room_tv;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class DeviceLivingRoomActivity extends AppCompatActivity {
    ViewPager2 viewPager;
    TabLayout tabLayout;
    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_living_room);
        btnBack = findViewById(R.id.btnBack);
        tabLayout = findViewById(R.id.tab_layout_device_living);
        viewPager = findViewById(R.id.view_pager);
        ViewPagerLingRoomDeviceAdapter adapter = new ViewPagerLingRoomDeviceAdapter(this);
        viewPager.setAdapter(adapter);

        fragment_living_room_light fragmentLivingRoomLight = new fragment_living_room_light();
        fragment_living_room_air_conditioner fragmentLivingRoomAirConditioner = new fragment_living_room_air_conditioner();
        fragment_living_room_tv fragmentLivingRoomTv = new fragment_living_room_tv();

        View view2 = getLayoutInflater().inflate(R.layout.customtab, null);
        view2.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_light);
        View view4 = getLayoutInflater().inflate(R.layout.customtab, null);
        view4.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_air_condition);
        View view6 = getLayoutInflater().inflate(R.layout.customtab, null);
        view6.findViewById(R.id.icon).setBackgroundResource(R.drawable.icon_tv);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DeviceLivingRoomActivity.this,MainActivity.class));
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