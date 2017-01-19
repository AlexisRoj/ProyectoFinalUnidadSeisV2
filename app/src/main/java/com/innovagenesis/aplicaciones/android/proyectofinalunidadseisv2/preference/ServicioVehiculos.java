package com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.preference;

import android.content.Context;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.adapters.Vehiculo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * Administra los eventos sobre los elementos
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
        } catch (IOException e) {
            Toast.makeText(context, "No existen items registrados", Toast.LENGTH_SHORT).show();
        }
    }

    public static ServicioVehiculos getInstance(Context context) throws IOException, ClassNotFoundException {
        if (instance == null)
            instance = new ServicioVehiculos(context);
        return instance;
    }

    /**
     * Guarda un elemento de vehiculo
     */
    public void guardarVehiculo(Vehiculo vehiculo) throws IOException {
        vehiculos.add(vehiculo);
        ObjectOutputStream output =
                new ObjectOutputStream(context.openFileOutput(nombreArchivo,
                        Context.MODE_PRIVATE));
        output.writeObject(vehiculos);
        output.close();
    }

    /**
     * Carga los datos del ArrayList<Vehiculo>
     **/
    public ArrayList<Vehiculo> cargarDatos() throws IOException, ClassNotFoundException {
        ObjectInputStream input = new ObjectInputStream(context.openFileInput(nombreArchivo));
        vehiculos = (ArrayList<Vehiculo>) input.readObject();
        input.close();
        return vehiculos;
    }



    public void eliminar(int position) throws IOException {
        /** Elimina uno por uno*/
        vehiculos.remove(position);
        ObjectOutputStream output = new ObjectOutputStream(context.openFileOutput(nombreArchivo
                , Context.MODE_PRIVATE));
        output.writeObject(vehiculos);
        output.close();
    }

    public void eliminarRegistros() throws IOException {

        /** Encargado de limpiar el registro*/
        ObjectOutputStream output = new ObjectOutputStream(context.openFileOutput(nombreArchivo
                , Context.MODE_PRIVATE));
        vehiculos.clear();
        output.close();


    }

    public void recorrer() throws IOException {

        ObjectInputStream ois = null;

        try {
            FileInputStream fis = context.openFileInput(nombreArchivo);

            ois = new ObjectInputStream(fis);
            while (true) {


                Vehiculo vehiculo = (Vehiculo) ois.readObject();

                System.out.println("1: "+vehiculo.getNombre());
                System.out.println("2: "+vehiculo.getTipo());
                System.out.println("3: "+vehiculo.getDescripcion());

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            ois.close();
        }
    }


}
