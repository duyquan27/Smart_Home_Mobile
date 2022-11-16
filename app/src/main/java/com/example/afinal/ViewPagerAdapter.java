package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new fragmentLivingRoom();
            case 1:
                return new fragmentBedRoom();
            case 2:
                return new fragmentBathRoom();
            default:
                return new fragmentLivingRoom();
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
                title = "Living Room";
                break;
            case 1:
                title = "BedRoom";
                break;
            case 2:
                title = "BathRoom";
                break;
        }
        return title;
    }
}
