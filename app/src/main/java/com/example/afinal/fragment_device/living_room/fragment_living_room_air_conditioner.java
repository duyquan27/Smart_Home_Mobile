package com.example.afinal.fragment_device.living_room;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinal.R;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class fragment_living_room_air_conditioner extends Fragment {
    private CircularSeekBar circularSeekBar;
    private TextView txview,tx_onoff, tx_mode;
    private ImageButton imgbtn_onoff,imgbtn_dry,imgbtn_fan, imgbtn_cool;
    private boolean check_onoff = true;
    private int check_mode = 0;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_living_room_air_conditioner, container, false);
        circularSeekBar = (CircularSeekBar) view.findViewById(R.id.circularSeekBar4);
        txview = (TextView) view.findViewById(R.id.textView8);
        imgbtn_onoff = (ImageButton) view.findViewById(R.id.btn_onoff);
        imgbtn_cool = (ImageButton) view.findViewById(R.id.btn_cool);
        imgbtn_dry = (ImageButton) view.findViewById(R.id.btn_dry);
        imgbtn_fan = (ImageButton) view.findViewById(R.id.btn_fan);
        tx_onoff = (TextView) view.findViewById(R.id.tx_onoff);
        tx_mode = (TextView) view.findViewById(R.id.tx_name_mode);

        circularSeekBar.setEnabled(false);
        imgbtn_cool.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_mode == 1){
                    tx_mode.setText("Cooling");
                    circularSeekBar.setProgress(20);
                    txview.setText("20°C");
                    circularSeekBar.setCircleProgressColor(getResources().getColor(R.color.cool));
                    imgbtn_cool.setImageResource(R.drawable.icon_cool_air_on);
                    imgbtn_fan.setImageResource(R.drawable.icon_fan_air);
                    imgbtn_dry.setImageResource(R.drawable.icon_dry_air);
                }
            }
        });
        imgbtn_dry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_mode == 1){
                    tx_mode.setText("Drying");
                    circularSeekBar.setProgress(30);
                    txview.setText("30°C");
                    circularSeekBar.setCircleProgressColor(getResources().getColor(R.color.dry));
                    imgbtn_dry.setImageResource(R.drawable.icon_dry_air_on);
                    imgbtn_cool.setImageResource(R.drawable.icon_cool_air);
                    imgbtn_fan.setImageResource(R.drawable.icon_fan_air);
                }
            }
        });
        imgbtn_fan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_mode == 1){
                    tx_mode.setText("Fan");
                    circularSeekBar.setProgress(25);
                    txview.setText("25°C");
                    circularSeekBar.setCircleProgressColor(getResources().getColor(R.color.fan));
                    imgbtn_fan.setImageResource(R.drawable.icon_fan_air_on);
                    imgbtn_cool.setImageResource(R.drawable.icon_cool_air);
                    imgbtn_dry.setImageResource(R.drawable.icon_dry_air);
                }
            }
        });
        imgbtn_onoff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_onoff){
                    circularSeekBar.setEnabled(true);
                    imgbtn_onoff.setImageResource(R.drawable.icon_on_air);
                    tx_onoff.setText("On");
                    check_mode = 1;
                    check_onoff = false;
                }
                else {
                    circularSeekBar.setEnabled(false);
                    imgbtn_onoff.setImageResource(R.drawable.icon_air_btn_off);
                    tx_onoff.setText("Off");
                    check_mode = 0;
                    check_onoff = true;
                }
            }
        });
        circularSeekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                int tmp = (int)progress;
                txview.setText(tmp + "°C");
            }

            @Override
            public void onStopTrackingTouch(CircularSeekBar seekBar) {

            }

            @Override
            public void onStartTrackingTouch(CircularSeekBar seekBar) {

            }
        });
        return view;
    }

}