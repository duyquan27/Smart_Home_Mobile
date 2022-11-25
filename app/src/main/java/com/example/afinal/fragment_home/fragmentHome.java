package com.example.afinal.fragment_home;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.afinal.MainActivity;
import com.example.afinal.R;
import com.example.afinal.custom_textView.RobotoBoldTextView;
import com.example.afinal.custom_textView.RobotoLightTextView;
import com.example.afinal.custom_textView.RobotoMediumTextView;
import com.example.afinal.fragment_room.ViewPagerHomeAdapter;
import com.example.afinal.login.SignInActivity;
import com.google.android.material.tabs.TabLayout;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public class fragmentHome extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private View mView;
    private MainActivity mMainActivity;
    private RobotoBoldTextView username;
    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mMainActivity = (MainActivity) getActivity();
        Calendar calendar = Calendar.getInstance();
        String currentDate = DateFormat.getDateInstance().format(calendar.getTime());

        // Inflate the layout for this fragment
        mView =  inflater.inflate(R.layout.fragment_home, container, false);
        tabLayout = mView.findViewById(R.id.tab_layout_device_living);
        viewPager = mView.findViewById(R.id.room_viewpager);
        username = mView.findViewById(R.id.welcome_username);
        RobotoMediumTextView local = mView.findViewById(R.id.local);
        //test
        RobotoLightTextView tvDate =  mView.findViewById(R.id.time);
        tvDate.setText(currentDate);
//        username.setText(mMainActivity.getStrUser().toString());

        ViewPagerHomeAdapter adapter = new ViewPagerHomeAdapter(getChildFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        return mView;
    }
}