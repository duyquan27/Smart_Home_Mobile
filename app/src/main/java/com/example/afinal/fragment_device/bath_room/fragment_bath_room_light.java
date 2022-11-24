package com.example.afinal.fragment_device.bath_room;


import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.afinal.R;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class fragment_bath_room_light extends Fragment {
    TextView txIntensity;
    CircularSeekBar circularSeekBar;
    ImageButton imgBtnOnOff;
    boolean checkOn = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bath_room_light, container, false);

        circularSeekBar = view.findViewById(R.id.circularSeekBarBathLight);
        txIntensity = view.findViewById(R.id.txLightBath);
        imgBtnOnOff = view.findViewById(R.id.img_btn_on_off_bath);

        // Init
        circularSeekBar.setEnabled(false);
        imgBtnOnOff.setImageResource(R.drawable.icon_btn_off);
        txIntensity.setText("0 %");
        circularSeekBar.setProgress(0);

        imgBtnOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkOn) {
                    circularSeekBar.setEnabled(true);
                    imgBtnOnOff.setImageResource(R.drawable.icon_btn_on);
                    circularSeekBar.setProgress(75);
                    txIntensity.setText("65 %");
                    checkOn = false;
                }
                else {
                    circularSeekBar.setEnabled(false);
                    imgBtnOnOff.setImageResource(R.drawable.icon_btn_off);
                    circularSeekBar.setProgress(0);
                    txIntensity.setText("0 %");
                    checkOn = true;
                }
            }
        });

        circularSeekBar.setOnSeekBarChangeListener(new CircularSeekBar.OnCircularSeekBarChangeListener() {
            @Override
            public void onProgressChanged(CircularSeekBar circularSeekBar, float progress, boolean fromUser) {
                int intensity = (int)progress;
                txIntensity.setText(intensity + " %");
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