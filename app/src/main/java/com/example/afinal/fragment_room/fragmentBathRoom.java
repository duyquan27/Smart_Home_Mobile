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

public class fragmentBathRoom extends Fragment {

    Switch swlight, swpana, swdryer, swwash;
    ImageButton imgpana, imgdryer, imgwash, imglight;
    TextView bath_light_tvname, bath_light_tvdevice, bath_light_tvonoff;
    TextView bath_pana_tvname, bath_pana_tvdevice, bath_pana_tvonoff;
    TextView bath_dryer_tvname, bath_dryer_tvdevice, bath_dryer_tvonoff;
    TextView bath_wash_tvname, bath_wash_tvdevice, bath_wash_tvonoff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bath_room, container, false);
        //Light_bath
        bath_light_tvname = (TextView) view.findViewById(R.id.name_light_bath);
        bath_light_tvdevice = (TextView) view.findViewById(R.id.light_bath_device);
        bath_light_tvonoff = (TextView) view.findViewById(R.id.on_off_light_bath);
        swlight = (Switch) view.findViewById(R.id.switch_light_bath_off);
        imglight = (ImageButton) view.findViewById(R.id.light_bath_off);
        //pana_bath
        bath_pana_tvname = (TextView) view.findViewById(R.id.name_pana);
        bath_pana_tvdevice = (TextView) view.findViewById(R.id.pana_device);
        bath_pana_tvonoff = (TextView) view.findViewById(R.id.on_off_pana);
        swpana = (Switch) view.findViewById(R.id.switch_pana_off);
        imgpana = (ImageButton) view.findViewById(R.id.pana_off);
        //dryer_bath
        bath_dryer_tvname = (TextView) view.findViewById(R.id.name_dryer);
        bath_dryer_tvdevice = (TextView) view.findViewById(R.id.dryer_device);
        bath_dryer_tvonoff = (TextView) view.findViewById(R.id.on_off_dryer);
        swdryer = (Switch) view.findViewById(R.id.switch_dryer_off);
        imgdryer = (ImageButton) view.findViewById(R.id.dryer_off);
        //Wash_bath
        bath_wash_tvname = (TextView) view.findViewById(R.id.name_wash);
        bath_wash_tvdevice = (TextView) view.findViewById(R.id.wash_device);
        bath_wash_tvonoff = (TextView) view.findViewById(R.id.on_off_wash);
        swwash = (Switch) view.findViewById(R.id.switch_wash_off);
        imgwash = (ImageButton) view.findViewById(R.id.wash_off);

        swlight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    imglight.setBackgroundResource(R.drawable.bg_roomitem_on);
                    bath_light_tvname.setTextColor(getResources().getColor(R.color.tvnameon));
                    bath_light_tvdevice.setTextColor(getResources().getColor(R.color.tvdeviceon));
                    bath_light_tvonoff.setText("ON");
                    bath_light_tvonoff.setTextColor(getResources().getColor(R.color.tvdeviceon));
                }
                else {
                    imglight.setBackgroundResource(R.drawable.bg_roomitem_off);
                    bath_light_tvname.setTextColor(getResources().getColor(R.color.tvoff));
                    bath_light_tvdevice.setTextColor(getResources().getColor(R.color.tvoff));
                    bath_light_tvonoff.setText("OFF");
                    bath_light_tvonoff.setTextColor(getResources().getColor(R.color.tvoff));
                }
            }
        });
        swpana.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    imgpana.setBackgroundResource(R.drawable.bg_roomitem_on);
                    bath_pana_tvname.setTextColor(getResources().getColor(R.color.tvnameon));
                    bath_pana_tvdevice.setTextColor(getResources().getColor(R.color.tvdeviceon));
                    bath_pana_tvonoff.setText("ON");
                    bath_pana_tvonoff.setTextColor(getResources().getColor(R.color.tvdeviceon));
                }
                else {
                    imgpana.setBackgroundResource(R.drawable.bg_roomitem_off);
                    bath_pana_tvname.setTextColor(getResources().getColor(R.color.tvoff));
                    bath_pana_tvdevice.setTextColor(getResources().getColor(R.color.tvoff));
                    bath_pana_tvonoff.setText("OFF");
                    bath_pana_tvonoff.setTextColor(getResources().getColor(R.color.tvoff));
                }
            }
        });
        swdryer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    imgdryer.setBackgroundResource(R.drawable.bg_roomitem_on);
                    bath_dryer_tvname.setTextColor(getResources().getColor(R.color.tvnameon));
                    bath_dryer_tvdevice.setTextColor(getResources().getColor(R.color.tvdeviceon));
                    bath_dryer_tvonoff.setText("ON");
                    bath_dryer_tvonoff.setTextColor(getResources().getColor(R.color.tvdeviceon));
                }
                else {
                    imgdryer.setBackgroundResource(R.drawable.bg_roomitem_off);
                    bath_dryer_tvname.setTextColor(getResources().getColor(R.color.tvoff));
                    bath_dryer_tvdevice.setTextColor(getResources().getColor(R.color.tvoff));
                    bath_dryer_tvonoff.setText("OFF");
                    bath_dryer_tvonoff.setTextColor(getResources().getColor(R.color.tvoff));
                }
            }
        });
        swwash.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    imgwash.setBackgroundResource(R.drawable.bg_roomitem_on);
                    bath_wash_tvname.setTextColor(getResources().getColor(R.color.tvnameon));
                    bath_wash_tvdevice.setTextColor(getResources().getColor(R.color.tvdeviceon));
                    bath_wash_tvonoff.setText("ON");
                    bath_wash_tvonoff.setTextColor(getResources().getColor(R.color.tvdeviceon));
                }
                else {
                    imgwash.setBackgroundResource(R.drawable.bg_roomitem_off);
                    bath_wash_tvname.setTextColor(getResources().getColor(R.color.tvoff));
                    bath_wash_tvdevice.setTextColor(getResources().getColor(R.color.tvoff));
                    bath_wash_tvonoff.setText("OFF");
                    bath_wash_tvonoff.setTextColor(getResources().getColor(R.color.tvoff));
                }
            }
        });

        return view;
    }
}