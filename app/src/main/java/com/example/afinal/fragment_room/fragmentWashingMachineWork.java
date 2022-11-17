package com.example.afinal.fragment_room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.example.afinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentWashingMachineWork#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentWashingMachineWork extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_washing_machine_work, container, false);
    }
}