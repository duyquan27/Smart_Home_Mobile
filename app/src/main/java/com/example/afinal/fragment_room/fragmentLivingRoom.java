package com.example.afinal.fragment_room;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.TextView;

import com.example.afinal.DeviceActivity;
import com.example.afinal.DeviceLivingRoomActivity;
import com.example.afinal.R;

public class fragmentLivingRoom extends Fragment {
    public interface onClick {

    }
    Switch swlight, swac, swtv, swaudio;
    ImageButton imgac, imgtv, imgaudio, imglight;
    TextView lv_light_tvname, lv_light_tvdevice, lv_light_tvonoff;
    TextView lv_ac_tvname, lv_ac_tvdevice, lv_ac_tvonoff;
    TextView lv_tv_tvname, lv_tv_tvdevice, lv_tv_tvonoff;
    TextView lv_audio_tvname, lv_audio_tvdevice, lv_audio_tvonoff;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_living_room, container, false);
        //Light_Living
        lv_light_tvname = (TextView) view.findViewById(R.id.name_light);
        lv_light_tvdevice = (TextView) view.findViewById(R.id.light_device);
        lv_light_tvonoff = (TextView) view.findViewById(R.id.on_off_light);
        swlight = (Switch) view.findViewById(R.id.switch_light_off);
        imglight = (ImageButton) view.findViewById(R.id.light_off);
        //Ac_Living
        lv_ac_tvname = (TextView) view.findViewById(R.id.name_ac);
        lv_ac_tvdevice = (TextView) view.findViewById(R.id.ac_device);
        lv_ac_tvonoff = (TextView) view.findViewById(R.id.on_off_ac);
        swac = (Switch) view.findViewById(R.id.switch_ac_off);
        imgac = (ImageButton) view.findViewById(R.id.ac_off);

        //TV_Living
        lv_tv_tvname = (TextView) view.findViewById(R.id.name_tv);
        lv_tv_tvdevice = (TextView) view.findViewById(R.id.tv_device);
        lv_tv_tvonoff = (TextView) view.findViewById(R.id.on_off_tv);
        swtv = (Switch) view.findViewById(R.id.switch_tv_off);
        imgtv = (ImageButton) view.findViewById(R.id.tv_off);
        //Audio_living
        lv_audio_tvname = (TextView) view.findViewById(R.id.name_ms);
        lv_audio_tvdevice = (TextView) view.findViewById(R.id.ms_device);
        lv_audio_tvonoff = (TextView) view.findViewById(R.id.on_off_ms);
        swaudio = (Switch) view.findViewById(R.id.switch_ms_off);
        imgaudio = (ImageButton) view.findViewById(R.id.ms_off);

        swlight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    imglight.setBackgroundResource(R.drawable.bg_roomitem_on);
                    lv_light_tvname.setTextColor(getResources().getColor(R.color.tvnameon));
                    lv_light_tvdevice.setTextColor(getResources().getColor(R.color.tvdeviceon));
                    lv_light_tvonoff.setText("ON");
                    lv_light_tvonoff.setTextColor(getResources().getColor(R.color.tvdeviceon));
                }
                else {
                    imglight.setBackgroundResource(R.drawable.bg_roomitem_off);
                    lv_light_tvname.setTextColor(getResources().getColor(R.color.tvoff));
                    lv_light_tvdevice.setTextColor(getResources().getColor(R.color.tvoff));
                    lv_light_tvonoff.setText("OFF");
                    lv_light_tvonoff.setTextColor(getResources().getColor(R.color.tvoff));
                }
            }
        });

        swac.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    imgac.setBackgroundResource(R.drawable.bg_roomitem_on);
                    lv_ac_tvname.setTextColor(getResources().getColor(R.color.tvnameon));
                    lv_ac_tvdevice.setTextColor(getResources().getColor(R.color.tvdeviceon));
                    lv_ac_tvonoff.setText("ON");
                    lv_ac_tvonoff.setTextColor(getResources().getColor(R.color.tvdeviceon));
                }
                else {
                    imgac.setBackgroundResource(R.drawable.bg_roomitem_off);
                    lv_ac_tvname.setTextColor(getResources().getColor(R.color.tvoff));
                    lv_ac_tvdevice.setTextColor(getResources().getColor(R.color.tvoff));
                    lv_ac_tvonoff.setText("OFF");
                    lv_ac_tvonoff.setTextColor(getResources().getColor(R.color.tvoff));
                }
            }
        });
        swtv.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    imgtv.setBackgroundResource(R.drawable.bg_roomitem_on);
                    lv_tv_tvname.setTextColor(getResources().getColor(R.color.tvnameon));
                    lv_tv_tvdevice.setTextColor(getResources().getColor(R.color.tvdeviceon));
                    lv_tv_tvonoff.setText("ON");
                    lv_tv_tvonoff.setTextColor(getResources().getColor(R.color.tvdeviceon));
                }
                else {
                    imgtv.setBackgroundResource(R.drawable.bg_roomitem_off);
                    lv_tv_tvname.setTextColor(getResources().getColor(R.color.tvoff));
                    lv_tv_tvdevice.setTextColor(getResources().getColor(R.color.tvoff));
                    lv_tv_tvonoff.setText("OFF");
                    lv_tv_tvonoff.setTextColor(getResources().getColor(R.color.tvoff));
                }
            }
        });
        swaudio.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    imgaudio.setBackgroundResource(R.drawable.bg_roomitem_on);
                    lv_audio_tvname.setTextColor(getResources().getColor(R.color.tvnameon));
                    lv_audio_tvdevice.setTextColor(getResources().getColor(R.color.tvdeviceon));
                    lv_audio_tvonoff.setText("ON");
                    lv_audio_tvonoff.setTextColor(getResources().getColor(R.color.tvdeviceon));
                }
                else {
                    imgaudio.setBackgroundResource(R.drawable.bg_roomitem_off);
                    lv_audio_tvname.setTextColor(getResources().getColor(R.color.tvoff));
                    lv_audio_tvdevice.setTextColor(getResources().getColor(R.color.tvoff));
                    lv_audio_tvonoff.setText("OFF");
                    lv_audio_tvonoff.setTextColor(getResources().getColor(R.color.tvoff));
                }
            }
        });

        imglight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), DeviceLivingRoomActivity.class);
                startActivity(i);
            }
        });

        return view;
    }

}