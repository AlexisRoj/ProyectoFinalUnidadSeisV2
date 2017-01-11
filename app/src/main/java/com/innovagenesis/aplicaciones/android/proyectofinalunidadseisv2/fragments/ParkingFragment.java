package com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.MainActivity;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.R;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.adapters.Vehiculo;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.adapters.VehiculoAdapter;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.dialogo.DialogoAgregarVehiculo;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.preference.ServicioVehiculos;

import java.io.IOException;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParkingFragment extends Fragment
        implements DialogoAgregarVehiculo.OnAgregarVehiculoListener{

    private View view;
    private VehiculoAdapter adapter;
    public ParkingFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        view = inflater.inflate(R.layout.fragment_parking, container, false);

        String titulo = getString(R.string.nombre_app);
        String subTitulo = getString(R.string.parking);

        MainActivity activity = (MainActivity)getActivity();
        activity.updateView(titulo,subTitulo);

        /**
         * Esta es la parte del recycle view
         * */




        return view;
    }

    @Override
    public void onAgregarVehiculo(Vehiculo vehiculo) {

    }
}
