package com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParkingFragment extends Fragment {


    public ParkingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_parking, container, false);
    }

}
