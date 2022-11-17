package com.example.afinal.fragment_room;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.afinal.R;

public class fragmentBedRoom extends Fragment {

    Switch swlamp, swac, swtv, swss;
    ImageButton imgac, imgtv, imgss, imglamp;
    TextView bed_light_tvname, bed_light_tvdevice, bed_light_tvonoff;
    TextView bed_ac_tvname, bed_ac_tvdevice, bed_ac_tvonoff;
    TextView bed_tv_tvname, bed_tv_tvdevice, bed_tv_tvonoff;
    TextView bed_ss_tvname, bed_ss_tvdevice, bed_ss_tvonoff;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bed_room, container, false);
        //Light_bed
        bed_light_tvname = (TextView) view.findViewById(R.id.name_lamp);
        bed_light_tvdevice = (TextView) view.findViewById(R.id.lamp_device);
        bed_light_tvonoff = (TextView) view.findViewById(R.id.on_off_lamp);
        swlamp = (Switch) view.findViewById(R.id.switch_lamp_off);
        imglamp = (ImageButton) view.findViewById(R.id.lamp_off);
        //Ac_Bed
        bed_ac_tvname = (TextView) view.findViewById(R.id.name_ac_bed_off);
        bed_ac_tvdevice = (TextView) view.findViewById(R.id.ac_bed_device);
        bed_ac_tvonoff = (TextView) view.findViewById(R.id.on_off_ac_bed);
        swac = (Switch) view.findViewById(R.id.switch_ac_bed_off);
        imgac = (ImageButton) view.findViewById(R.id.ac_bed_off);
        //TV_Bed
        bed_tv_tvname = (TextView) view.findViewById(R.id.name_tv_bed);
        bed_tv_tvdevice = (TextView) view.findViewById(R.id.tv_bed_device);
        bed_tv_tvonoff = (TextView) view.findViewById(R.id.on_off_tv_bed);
        swtv = (Switch) view.findViewById(R.id.switch_tv_bed_off);
        imgtv = (ImageButton) view.findViewById(R.id.tv_bed_off);
        //SS_Bed
        bed_ss_tvname = (TextView) view.findViewById(R.id.name_ss);
        bed_ss_tvdevice = (TextView) view.findViewById(R.id.ss_device);
        bed_ss_tvonoff = (TextView) view.findViewById(R.id.on_off_ss);
        swss = (Switch) view.findViewById(R.id.switch_ss_off);
        imgss = (ImageButton) view.findViewById(R.id.ss_off);

        swlamp.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    imglamp.setBackgroundResource(R.drawable.bg_roomitem_on);
                    bed_light_tvname.setTextColor(getResources().getColor(R.color.tvnameon));
                    bed_light_tvdevice.setTextColor(getResources().getColor(R.color.tvdeviceon));
                    bed_light_tvonoff.setText("ON");
                    bed_light_tvonoff.setTextColor(getResources().getColor(R.color.tvdeviceon));
                }
                else {
                    imglamp.setBackgroundResource(R.drawable.bg_roomitem_off);
                    bed_light_tvname.setTextColor(getResources().getColor(R.color.tvoff));
                    bed_light_tvdevice.setTextColor(getResources().getColor(R.color.tvoff));
                    bed_light_tvonoff.setText("OFF");
                    bed_light_tvonoff.setTextColor(getResources().getColor(R.color.tvoff));
                }
            }
        });
        swac.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    imgac.setBackgroundResource(R.drawable.bg_roomitem_on);
                    bed_ac_tvname.setTextColor(getResources().getColor(R.color.tvnameon));
                    bed_ac_tvdevice.setTextColor(getResources().getColor(R.color.tvdeviceon));
                    bed_ac_tvonoff.setText("ON");
                    bed_ac_tvonoff.setTextColor(getResources().getColor(R.color.tvdeviceon));
                }
                else {
                    imgac.setBackgroundResource(R.drawable.bg_roomitem_off);
                    bed_ac_tvname.setTextColor(getResources().getColor(R.color.tvoff));
                    bed_ac_tvdevice.setTextColor(getResources().getColor(R.color.tvoff));
                    bed_ac_tvonoff.setText("OFF");
                    bed_ac_tvonoff.setTextColor(getResources().getColor(R.color.tvoff));
                }
            }
        });
        swtv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    imgtv.setBackgroundResource(R.drawable.bg_roomitem_on);
                    bed_tv_tvname.setTextColor(getResources().getColor(R.color.tvnameon));
                    bed_tv_tvdevice.setTextColor(getResources().getColor(R.color.tvdeviceon));
                    bed_tv_tvonoff.setText("ON");
                    bed_tv_tvonoff.setTextColor(getResources().getColor(R.color.tvdeviceon));
                }
                else {
                    imgtv.setBackgroundResource(R.drawable.bg_roomitem_off);
                    bed_tv_tvname.setTextColor(getResources().getColor(R.color.tvoff));
                    bed_tv_tvdevice.setTextColor(getResources().getColor(R.color.tvoff));
                    bed_tv_tvonoff.setText("OFF");
                    bed_tv_tvonoff.setTextColor(getResources().getColor(R.color.tvoff));
                }
            }
        });
        swss.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    imgss.setBackgroundResource(R.drawable.bg_roomitem_on);
                    bed_ss_tvname.setTextColor(getResources().getColor(R.color.tvnameon));
                    bed_ss_tvdevice.setTextColor(getResources().getColor(R.color.tvdeviceon));
                    bed_ss_tvonoff.setText("ON");
                    bed_ss_tvonoff.setTextColor(getResources().getColor(R.color.tvdeviceon));
                }
                else {
                    imgss.setBackgroundResource(R.drawable.bg_roomitem_off);
                    bed_ss_tvname.setTextColor(getResources().getColor(R.color.tvoff));
                    bed_ss_tvdevice.setTextColor(getResources().getColor(R.color.tvoff));
                    bed_ss_tvonoff.setText("OFF");
                    bed_ss_tvonoff.setTextColor(getResources().getColor(R.color.tvoff));
                }
            }
        });



        return view;
    }
}