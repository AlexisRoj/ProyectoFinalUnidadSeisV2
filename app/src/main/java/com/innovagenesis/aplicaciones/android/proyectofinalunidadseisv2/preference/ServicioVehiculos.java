package com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.preference;

import android.content.Context;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.adapters.Vehiculo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Created by alexi on 10/01/2017.
 */

public class ServicioVehiculos {

    private ArrayList<Vehiculo> vehiculos;
    private final String nombreArchivo = "vehiculos.txt";
    private static ServicioVehiculos instance;
    private Context context;

    private ServicioVehiculos(Context context) throws ClassNotFoundException, IOException {
        try {
            this.context = context;
            this.vehiculos = new ArrayList<>();
            cargarDatos();
        }catch (IOException e){
            guardarVehiculo(new Vehiculo("836157", "110210888", Vehiculo.Tipo.SEDAN));
            guardarVehiculo(new Vehiculo("203016", "21020077", Vehiculo.Tipo.OFFROAD));
            guardarVehiculo(new Vehiculo("XXX969", "403340822", Vehiculo.Tipo.PICKUP));
            this.context = context;
        }
    }

    public static ServicioVehiculos getInstance(Context context) throws IOException, ClassNotFoundException {
        if (instance == null)
            instance = new ServicioVehiculos(context);
        return instance;
    }

    public void guardarVehiculo(Vehiculo vehiculo) throws IOException {
        vehiculos.add(vehiculo);
        ObjectOutputStream output =
                new ObjectOutputStream(context.openFileOutput(nombreArchivo,
                        Context.MODE_PRIVATE));
        output.writeObject(vehiculos);
        output.close();
    }

    public ArrayList<Vehiculo> cargarDatos() throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(context.openFileInput(nombreArchivo));
        vehiculos = (ArrayList<Vehiculo>) input.readObject();
        input.close();
        return vehiculos;
    }

    public void eliminar(int position) throws IOException {
        vehiculos.remove(position);
        ObjectOutputStream output = new ObjectOutputStream(context.openFileOutput(nombreArchivo
                , Context.MODE_PRIVATE));
        output.writeObject(vehiculos);
        output.close();
    }
}
