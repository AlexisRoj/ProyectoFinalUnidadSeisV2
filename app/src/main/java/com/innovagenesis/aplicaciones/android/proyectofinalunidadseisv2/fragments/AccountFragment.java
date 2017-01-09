package com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.MainActivity;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends PreferenceFragment {


    public AccountFragment() {
        // Required empty public constructor

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.configuracion);

        String titulo = getString(R.string.nombre_app);
        String subTitulo = getString(R.string.cuenta);

   

        MainActivity activity = (MainActivity)getActivity();
        activity.updateView(titulo,subTitulo);
        
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        
    }
}
