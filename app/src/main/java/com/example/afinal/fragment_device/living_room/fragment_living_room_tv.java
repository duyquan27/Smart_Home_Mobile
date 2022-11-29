package com.example.afinal.fragment_device.living_room;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.afinal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class fragment_living_room_tv extends Fragment {
    DatabaseReference mRoom;
    ImageButton btnVolUp, btnVolDown, btnOnOff, btnChUp, btnChDown;
    ImageView imgCh, iconVolume;
    TextView txCh, txVol;
    ConstraintLayout layoutOff;
    boolean checkOn;
    int channel, volume;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_living_room_tv, container, false);
        mRoom = FirebaseDatabase.getInstance().getReference();
        btnVolUp = view.findViewById(R.id.vol_up_living);
        btnVolDown = view.findViewById(R.id.vol_down_living);
        btnChUp = view.findViewById(R.id.ch_up_living);
        btnChDown = view.findViewById(R.id.ch_down_living);
        btnOnOff = view.findViewById(R.id.imgBtn_on_off_living);
        imgCh = view.findViewById(R.id.img_ch);
        iconVolume = view.findViewById(R.id.icon_volume);
        txCh = view.findViewById(R.id.CH);
        txVol = view.findViewById(R.id.volume);
        layoutOff = view.findViewById(R.id.bg_off);

        // Init from firebase
        mRoom.child("HOME").child("Living room").child("TV").child("Status").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equals("ON")) {
                    checkOn = true;
                    btnOnOff.setImageResource(R.drawable.icon_btn_on);
                    layoutOff.setBackgroundColor(view.getResources().getColor(R.color.no));
                    txCh.setBackgroundColor(view.getResources().getColor(R.color.white));
                    txVol.setBackgroundColor(view.getResources().getColor(R.color.white));
                    iconVolume.setBackgroundColor(view.getResources().getColor(R.color.white));
                }
                else {
                    checkOn = false;
                    btnOnOff.setImageResource(R.drawable.icon_btn_off);
                    layoutOff.setBackgroundColor(view.getResources().getColor(R.color.black));
                    txCh.setBackgroundColor(view.getResources().getColor(R.color.black));
                    txVol.setBackgroundColor(view.getResources().getColor(R.color.black));
                    iconVolume.setBackgroundColor(view.getResources().getColor(R.color.black));
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mRoom.child("HOME").child("Living room").child("TV").child("Channel").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                channel = new Integer(snapshot.getValue().toString());
                txCh.setText("CH " + channel);
                if (snapshot.getValue().toString().equals("1")) {
                    imgCh.setImageResource(R.drawable.channel01);
                }
                else if (snapshot.getValue().toString().equals("2")) {
                    imgCh.setImageResource(R.drawable.channel02);
                }
                else if (snapshot.getValue().toString().equals("3")) {
                    imgCh.setImageResource(R.drawable.channel03);
                }
                else if (snapshot.getValue().toString().equals("4")) {
                    imgCh.setImageResource(R.drawable.channel04);
                }
                else {
                    imgCh.setImageResource(R.drawable.channel05);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mRoom.child("HOME").child("Living room").child("TV").child("Volume").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                volume = new Integer(snapshot.getValue().toString());
                txVol.setText("" + volume);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkOn) {
                    // push to firebase
                    mRoom.child("HOME").child("Living room").child("TV").child("Status").setValue("OFF");
                }
                else {
                    // push to firebase
                    mRoom.child("HOME").child("Living room").child("TV").child("Status").setValue("ON");
                }
            }
        });

        btnChUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkOn) {
                    if (channel == 5) {
                        channel = 1;
                    }
                    else {
                        channel ++;
                    }
                    mRoom.child("HOME").child("Living room").child("TV").child("Channel").setValue(channel);
                }
            }
        });

        btnChDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkOn) {
                    if (channel == 1)
                        channel = 5;
                    else {
                        channel --;
                    }
                    mRoom.child("HOME").child("Living room").child("TV").child("Channel").setValue(channel);
                }
            }
        });

        btnVolUp.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (checkOn) {
                    if (volume == 100) {
                        volume = 100;
                    }
                    else {
                        volume ++;
                        iconVolume.setImageResource(R.drawable.icon_audio);
                    }
                    mRoom.child("HOME").child("Living room").child("TV").child("Volume").setValue(volume);
                }
                return false;
            }
        });

        btnVolDown.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (checkOn) {
                    if (volume == 0) {
                        iconVolume.setImageResource(R.drawable.icon_audio_mute);
                    }
                    else {
                        volume --;
                    }
                    mRoom.child("HOME").child("Living room").child("TV").child("Volume").setValue(volume);
                }
                return false;
            }
        });

        return view;
    }
}