package com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Holder que alimenta los elementos de cardview
 * Created by alexi on 09/01/2017.
 */

public class VehiculoViewHolder extends RecyclerView.ViewHolder{

    TextView txtTitulo, txtDescripcion;
    ImageView imgTipo;

    public VehiculoViewHolder(View itemView) {
        super(itemView);

        this.txtTitulo = txtTitulo;
        this.txtDescripcion = txtDescripcion;
        this.imgTipo = imgTipo;
    }
}
