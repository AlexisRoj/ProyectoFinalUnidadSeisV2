package com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2;

import android.content.DialogInterface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.adapters.Vehiculo;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.adapters.VehiculoAdapter;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.dialogo.DialogoAgregarVehiculo;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.fragments.AccountFragment;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.fragments.ParkingFragment;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.preference.ServicioVehiculos;

import java.io.IOException;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DialogoAgregarVehiculo.OnAgregarVehiculoListener {

    DrawerLayout drawerLayout;
    NavigationView navigationView;
    private VehiculoAdapter adapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */

    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


   /*     getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_main, new ParkingFragment())
                .commit();*/

        /** Se instancia el toolbar*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /** Se instancian el drawer y el navigation*/

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);

        /**
         * Boton Flotante
         * */

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                /**
                 * Ejecuta el dialogo que guarda el elemento*/
                DialogoAgregarVehiculo dialogo = new DialogoAgregarVehiculo();
                dialogo.show(getSupportFragmentManager(), DialogoAgregarVehiculo.TAG);

                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        crearRecycleView();

        navigationView.setNavigationItemSelectedListener(this);

    }

    private void crearRecycleView() {
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        try {
            adapter = new VehiculoAdapter(this, ServicioVehiculos.getInstance(this).cargarDatos(),
                    new VehiculoAdapter.OnItemClickListener() {
                        @Override
                        public void onItemClick(VehiculoAdapter.VehiculoViewHolder holder, int position) {
                            confirmacion(position);
                        }
                    });
        }catch (IOException e){
            Toast.makeText(MainActivity.this, "Error al cargar el archivo", Toast.LENGTH_SHORT).show();
        }catch (ClassNotFoundException e){
            Toast.makeText(MainActivity.this, "Error al cargar la lista", Toast.LENGTH_SHORT).show();
        }

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


    public void confirmacion(final int position){
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setMessage("¿Está seguro de que desea eliminar el elemento?")
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            ServicioVehiculos.getInstance(MainActivity.this).eliminar(position);
                        } catch (IOException e) {
                            Toast.makeText(MainActivity.this, "Error al actualizar el archivo", Toast.LENGTH_SHORT).show();
                        } catch (ClassNotFoundException e) {
                            Toast.makeText(MainActivity.this, "Error al eliminar el elemento", Toast.LENGTH_SHORT).show();
                        }
                        adapter.notifyDataSetChanged();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).create().show();
    }


    public void updateView(String title, String subTitle) {
        /** Actualiza el titulo y subtitulo del toolbar*/
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        if (toolbar != null) {
            toolbar.setTitle(title);
            toolbar.setSubtitle(subTitle);
        }
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawerLayout.addDrawerListener(toggle);

        toggle.syncState();

        /**
         *  Muestra y oculta el boton flotante
         * */

        if (!subTitle.equals(getString(R.string.cuenta))) {
            fab.setVisibility(View.VISIBLE);
        } else {
            fab.setVisibility(View.INVISIBLE);
        }

    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        Fragment fragment = null;

        switch (id) {
            case R.id.parking: {
                crearRecycleView();
                //fragment = new ParkingFragment();
                break;
            }
            case R.id.account: {
                getFragmentManager().beginTransaction()
                        .replace(R.id.content_main, new AccountFragment())
                        .commit();
                break;
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);


        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_main, fragment)
                    .commit();
        }

        return true;
    }


    @Override
    public void onAgregarVehiculo(Vehiculo vehiculo) {

        try {
            ServicioVehiculos.getInstance(this).guardarVehiculo(vehiculo);
        } catch (IOException e) {
            Toast.makeText(MainActivity.this, "Error al actualizar el archivo", Toast.LENGTH_SHORT).show();
        } catch (ClassNotFoundException e) {
            Toast.makeText(MainActivity.this, "Error al guardar elemento en la lista", Toast.LENGTH_SHORT).show();
        }
        adapter.notifyDataSetChanged();

    }

}
