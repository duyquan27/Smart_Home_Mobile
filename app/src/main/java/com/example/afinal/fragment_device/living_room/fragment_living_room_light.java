package com.example.afinal.fragment_device.living_room;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.afinal.R;

import me.tankery.lib.circularseekbar.CircularSeekBar;

public class fragment_living_room_light extends Fragment{
    CircularSeekBar circularSeekBar;
    TextView txIntensity;
    ImageButton imgBtnOnOff;
    boolean checkOn = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_living_room_light, container, false);

        circularSeekBar = view.findViewById(R.id.circularSeekBarLightLiving);
        txIntensity = view.findViewById(R.id.txLightLiving);
        imgBtnOnOff = view.findViewById(R.id.imgBtn_on_off_living);

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
                    txIntensity.setText("75 %");
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