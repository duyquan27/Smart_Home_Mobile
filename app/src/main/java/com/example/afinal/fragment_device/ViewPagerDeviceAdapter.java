package com.example.afinal.fragment_device;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.afinal.fragment_home.fragmentHome;
import com.example.afinal.fragment_home.fragmentSensor;
import com.example.afinal.fragment_home.fragmentSetting;

public class ViewPagerDeviceAdapter extends FragmentStatePagerAdapter {
    public ViewPagerDeviceAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new fragmentLight();
            case 1:
                return new fragmentAircon();
            case 2:
                return new fragmentTV();
            default:
                return new fragmentLight();
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position){
            case 0:
                title = "Light";
                break;
            case 1:
                title = "Air Conditioner";
                break;
            case 2:
                title = "TV";
                break;
        }
        return title;
    }
}
