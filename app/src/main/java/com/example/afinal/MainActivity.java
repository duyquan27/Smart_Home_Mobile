package com.example.afinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.example.afinal.custom_textView.RobotoBoldTextView;
import com.example.afinal.fragment_home.ViewPagerMainAdapter;
import com.example.afinal.fragment_home.fragmentHome;
import com.example.afinal.fragment_home.fragmentSensor;
import com.example.afinal.fragment_home.fragmentSetting;
import com.example.afinal.fragment_room.ViewPagerWashingMachineAdapter;
import com.example.afinal.widget.CustomViewPager;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {
    CustomViewPager viewPager;
    BottomNavigationView bottomNavigationView;
    private RobotoBoldTextView username;
    private View mView;
    public String userID = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_page);
        viewPager = (CustomViewPager) findViewById(R.id.view_pager);
        bottomNavigationView = findViewById(R.id.bottom_menubar);
        ViewPagerMainAdapter adapter = new ViewPagerMainAdapter(getSupportFragmentManager(),FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnable(false);
        fragmentSetting settingFragment = new fragmentSetting();
        fragmentSensor sensorFragment = new fragmentSensor();
        fragmentHome homeFragment = new fragmentHome();

        Intent myIntent = getIntent();
        Bundle myBundle = myIntent.getBundleExtra("USER_INFO");
        userID = myBundle.getString("userID");


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position)
                {
                    case 0:
                        bottomNavigationView.getMenu().findItem(R.id.menu_home).setChecked(true);
                        break;
                    case 1:
                        bottomNavigationView.getMenu().findItem(R.id.menu_sensor).setChecked(true);
                        break;
                    case 2:
                        bottomNavigationView.getMenu().findItem(R.id.menu_setting).setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.menu_home:;
                        viewPager.setCurrentItem(0);
                    break;
                    case R.id.menu_sensor:;
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.menu_setting:;
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });
    }

}