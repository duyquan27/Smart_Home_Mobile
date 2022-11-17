package com.example.afinal.fragment_room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

public class ViewPagerWashingMachineAdapter extends FragmentStatePagerAdapter {
    public ViewPagerWashingMachineAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new fragmentWashingMachineEquipmentFragment();
            case 1:
                return new fragmentWashingMachineWork();
            default:
                return new fragmentWashingMachineEquipmentFragment();
        }

    }

    @Override
    public int getCount() {
        return 2;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        String title = "";
        switch (position) {
            case 0:
                title = "Equipment";
                break;
            case 1:
                title = "Work";
                break;
        }
        return title;
    }
}
