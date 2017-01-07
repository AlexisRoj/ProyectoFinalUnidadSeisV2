package com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.MainActivity;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment {


    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        String titulo = getString(R.string.nombre_app);
        String subTitulo = getString(R.string.cuenta);

        MainActivity activity = (MainActivity)getActivity();
        activity.updateView(titulo,subTitulo);

        return inflater.inflate(R.layout.fragment_account, container, false);

    }





}
