package com.example.afinal.fragment_room;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.afinal.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link fragmentWashingMachineEquipmentFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class fragmentWashingMachineEquipmentFragment extends Fragment {



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_washing_machine_equipment, container, false);
    }
}