package com.example.afinal.fragment_home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.afinal.R;
import com.example.afinal.fragment_room.ViewPagerHomeAdapter;
import com.google.android.material.tabs.TabLayout;

public class fragmentHome extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View mView;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout = mView.findViewById(R.id.tab_layout);
        viewPager = mView.findViewById(R.id.room_viewpager);

        ViewPagerHomeAdapter adapter = new ViewPagerHomeAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return mView;
    }
}