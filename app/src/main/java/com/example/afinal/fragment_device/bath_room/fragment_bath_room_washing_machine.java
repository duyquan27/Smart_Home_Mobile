package com.example.afinal.fragment_device.bath_room;

import static com.example.afinal.MyApp.CHANNEL_ID_TEMP;

import android.annotation.SuppressLint;
import android.app.Notification;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RemoteViews;
import android.widget.TextView;

import com.example.afinal.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Date;

public class fragment_bath_room_washing_machine extends Fragment {
    DatabaseReference mHome;
    private TextView tx_time, tx_status;
    private ImageButton btn_onoff_wash, btn_cotton, btn_mix, btn_sportwear, btn_babycare, btn_run;
    private boolean check_onoff = true, counterIsActive = false;
    private int check_mode = 3, check = 0;
    CountDownTimer countDownTimer;

    public void reset() {
        btn_run.setImageResource(R.drawable.icon_start_wm);
        check_onoff = true;
        switch (check_mode) {
            case 1: {
                tx_time.setText("01 : 00");
                tx_status.setText("Ready");
                break;
            }
            case 2: {
                tx_time.setText("00 : 20");
                tx_status.setText("Ready");
                break;
            }
            case 3: {
                tx_time.setText("00 : 30");
                tx_status.setText("Ready");
                break;
            }
            case 4: {
                tx_time.setText("00 : 40");
                tx_status.setText("Ready");
                break;
            }
        }
        countDownTimer.cancel();
        counterIsActive = false;
    }

    public void updateTimer(int secondsLeft) {
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - (minutes * 60);
        String secondsString = Integer.toString(seconds);
        String minutesString = Integer.toString(minutes);
        if (seconds <= 9) {
            secondsString = "0" + secondsString;
        }
        if (minutes <= 9) {
            minutesString = "0" + minutesString;
        }
        tx_time.setText(minutesString + " : " + secondsString);
    }

    public void sendNotification(int mode){
        Uri alarmSound = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        RemoteViews notificationLayout = new RemoteViews(getActivity().getPackageName(),R.layout.custom_notification_layout);
        notificationLayout.setTextViewText(R.id.title,"WASHING MACHINE");
        switch (mode){
            case 1:{
                notificationLayout.setTextViewText(R.id.info,"Finished washing mode Baby Care");
                break;
            }
            case 2:{
                notificationLayout.setTextViewText(R.id.info,"Finished washing mode Sport wear");
                break;
            }
            case 3:{
                notificationLayout.setTextViewText(R.id.info,"Finished washing mode Cotton");
                break;
            }
            case 4:{
                notificationLayout.setTextViewText(R.id.info,"Finished washing mode Mix");
                break;
            }
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm");
        String sdfDate = simpleDateFormat.format(new Date());
        notificationLayout.setTextViewText(R.id.time,sdfDate);

        Notification notification = new NotificationCompat.Builder(getContext(), CHANNEL_ID_TEMP)
                .setSmallIcon(R.drawable.icon_bath_wash)
                .setCustomContentView(notificationLayout)
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setAutoCancel(true)
                .build();
        NotificationManagerCompat notificationManagerCompat = NotificationManagerCompat.from(getContext());
        notificationManagerCompat.notify(getNotificationId(),notification);
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bath_room_washing_machine, container, false);
        tx_time = (TextView) view.findViewById(R.id.tx_time);
        tx_status = (TextView) view.findViewById(R.id.tx_status);
        btn_onoff_wash = (ImageButton) view.findViewById(R.id.btn_onoff_wash);
        btn_run = (ImageButton) view.findViewById(R.id.btn_run);
        btn_cotton = (ImageButton) view.findViewById(R.id.btn_cotton);
        btn_babycare = (ImageButton) view.findViewById(R.id.btn_babycare);
        btn_sportwear = (ImageButton) view.findViewById(R.id.btn_sportwear);
        btn_mix = (ImageButton) view.findViewById(R.id.btn_Mix);
        mHome = FirebaseDatabase.getInstance().getReference();

        mHome.child("HOME").child("Bath room").child("Washing machine").child("Status").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equals("ON")) {
                    check_onoff = true;
                    btn_onoff_wash.setImageResource(R.drawable.icon_btn_on);
                    btn_run.setEnabled(true);
                } else {
                    check_onoff = false;
                    btn_onoff_wash.setImageResource(R.drawable.icon_btn_off);
                    btn_run.setEnabled(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        mHome.child("HOME").child("Bath room").child("Washing machine").child("Regime").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.getValue().toString().equals("Baby care")) {
                    tx_time.setText("01 : 00");
                    btn_babycare.setImageResource(R.drawable.icon_baby_care_on);
                    btn_sportwear.setImageResource(R.drawable.icon_sport_wear);
                    btn_cotton.setImageResource(R.drawable.icon_cotton);
                    btn_mix.setImageResource(R.drawable.icon_mix);
                    check_mode = 1;
                } else if (snapshot.getValue().toString().equals("Sport wear")) {
                    tx_time.setText("00 : 20");
                    btn_babycare.setImageResource(R.drawable.icon_baby_care);
                    btn_sportwear.setImageResource(R.drawable.icon_sport_wear_on);
                    btn_cotton.setImageResource(R.drawable.icon_cotton);
                    btn_mix.setImageResource(R.drawable.icon_mix);
                    check_mode = 2;
                } else if (snapshot.getValue().toString().equals("Cotton")) {
                    tx_time.setText("00 : 30");
                    btn_babycare.setImageResource(R.drawable.icon_baby_care);
                    btn_sportwear.setImageResource(R.drawable.icon_sport_wear);
                    btn_cotton.setImageResource(R.drawable.icon_cotton_on);
                    btn_mix.setImageResource(R.drawable.icon_mix);
                    check_mode = 3;
                } else {
                    tx_time.setText("00 : 40");
                    btn_babycare.setImageResource(R.drawable.icon_baby_care);
                    btn_sportwear.setImageResource(R.drawable.icon_sport_wear);
                    btn_cotton.setImageResource(R.drawable.icon_cotton);
                    btn_mix.setImageResource(R.drawable.icon_mix_on);
                    check_mode = 4;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btn_run.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counterIsActive) {
                    reset();
                } else {
                    counterIsActive = true;
                    btn_run.setImageResource(R.drawable.icon_start_wm_on);
                    tx_status.setText("Start");
                    check_onoff = false;
                    switch (check_mode) {
                        case 1: {
                            countDownTimer = new CountDownTimer(60 * 1000 + 100, 1000) {
                                @Override
                                public void onTick(long l) {
                                    updateTimer((int) l / 1000);
                                }

                                @Override
                                public void onFinish() {
                                    sendNotification(check_mode);
                                    MediaPlayer mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.music1);
                                    mediaPlayer.start();
                                    btn_run.setEnabled(true);
                                    reset();
                                }
                            }.start();
                            break;
                        }
                        case 2: {
                            countDownTimer = new CountDownTimer(20 * 1000 + 100, 1000) {
                                @Override
                                public void onTick(long l) {
                                    updateTimer((int) l / 1000);
                                }

                                @Override
                                public void onFinish() {
                                    sendNotification(check_mode);
                                    MediaPlayer mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.music1);
                                    mediaPlayer.start();
                                    btn_run.setEnabled(true);
                                    reset();
                                }
                            }.start();
                            break;
                        }
                        case 3: {
                            countDownTimer = new CountDownTimer(30 * 1000 + 100, 1000) {
                                @Override
                                public void onTick(long l) {
                                    updateTimer((int) l / 1000);
                                }

                                @Override
                                public void onFinish() {
                                    sendNotification(check_mode);
                                    MediaPlayer mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.music2);
                                    mediaPlayer.start();
                                    btn_run.setEnabled(true);
                                    reset();
                                }
                            }.start();
                            break;
                        }
                        case 4: {
                            countDownTimer = new CountDownTimer(40 * 1000 + 100, 1000) {
                                @Override
                                public void onTick(long l) {
                                    updateTimer((int) l / 1000);
                                }

                                @Override
                                public void onFinish() {
                                    sendNotification(check_mode);
                                    MediaPlayer mediaPlayer = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.music2);
                                    mediaPlayer.start();
                                    btn_run.setEnabled(true);
                                    reset();
                                }
                            }.start();
                            break;
                        }
                    }
                }
            }
        });

        btn_babycare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_onoff) {
                    mHome.child("HOME").child("Bath room").child("Washing machine").child("Regime").setValue("Baby care");
                }
            }
        });
        btn_sportwear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_onoff) {
                    mHome.child("HOME").child("Bath room").child("Washing machine").child("Regime").setValue("Sport wear");
                }
            }
        });
        btn_cotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_onoff) {
                    mHome.child("HOME").child("Bath room").child("Washing machine").child("Regime").setValue("Cotton");
                }
            }
        });
        btn_mix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_onoff) {
                    mHome.child("HOME").child("Bath room").child("Washing machine").child("Regime").setValue("Mix");
                }
            }
        });

        btn_onoff_wash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (check_onoff) {
                    mHome.child("HOME").child("Bath room").child("Washing machine").child("Status").setValue("OFF");
                } else {
                    mHome.child("HOME").child("Bath room").child("Washing machine").child("Status").setValue("ON");
                }
            }
        });

        return view;
    }
    private int getNotificationId() {
        return (int) new Date().getTime();
    }
}