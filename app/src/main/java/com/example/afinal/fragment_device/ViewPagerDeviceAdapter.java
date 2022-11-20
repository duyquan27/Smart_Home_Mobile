package com.example.afinal.fragment_device;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.afinal.fragment_home.fragmentHome;
import com.example.afinal.fragment_home.fragmentSensor;
import com.example.afinal.fragment_home.fragmentSetting;

public class ViewPagerDeviceAdapter extends FragmentStateAdapter {
    public ViewPagerDeviceAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position)
        {
            case 0:
                return new fragmentLight();
            case 1:
                return new fragmentAircon();
            case 2:
                return new fragmentTV();
        }
        return null;
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
