package com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.Preference.PreferenceConstant;

public class Login extends AppCompatActivity {

    TextInputLayout userNameLogin;
    TextInputLayout userNamePass;

    /**
     * Administra las preferencias
     */
    private SharedPreferences pref;
    String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        /**
         *  Carga las preferencias
         * */
        pref = getSharedPreferences(PreferenceConstant.PREFERENCE_LOGIN, MODE_PRIVATE);
        username = pref.getString(PreferenceConstant.PREF_KEY_USERNAME, null);

        borrarPreference();

        if (username != null) {
            /**
             * Si usuario trae información carga la actividad
             * */
            cargarActivity();
        }

        this.setTitle(R.string.title_Login); // Cambia el titulo

        int color = R.color.colorPrimary;
        int cambiarColor = ContextCompat.getColor(this.getBaseContext(), color);


        if (Build.VERSION.SDK_INT >= 21) {
            /**
             * Cambia colores apartir de la version 21
             * */
            this.getWindow().setStatusBarColor(cambiarColor);
        }

        userNameLogin = (TextInputLayout) findViewById(R.id.userNameLogin);
        userNamePass = (TextInputLayout) findViewById(R.id.userNamePass);

        Button button = (Button) findViewById(R.id.ingresarLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickEvent();
            }
        });
    }

    private void onClickEvent() {
        CheckBox checkBox = (CheckBox) findViewById(R.id.recordarmeLogin);
        Boolean iniciarActivity = false;
        /**
         * Valida los campos que no vengan vacios, ni nulos
         * */
        String login = null;
        if (userNameLogin != null && userNameLogin.getEditText() != null) {

            login = userNameLogin.getEditText().toString();

            if (login != null && login.isEmpty()) {
                iniciarActivity = true;
            }else
                userNameLogin.setError(getString(R.string.emptyText));
        }

        String pass;
        if (userNamePass != null && userNamePass.getEditText() != null) {

            pass = userNamePass.getEditText().toString();

            if (pass != null && pass.isEmpty()) {
                iniciarActivity = true;
            }else
                userNamePass.setError(getString(R.string.emptyText));
        }

        if (iniciarActivity) {
            /**
             * Constrola que los checkbox no vengan vacios
             * */
            if (checkBox.isChecked()) {
                /**
                 * Valida si el checkbox esta selecionado y guarda la preferencia
                 * del sistema
                 * */
                SharedPreferences.Editor edit = pref.edit();
                edit.putString(PreferenceConstant.PREF_KEY_USERNAME, login);
                edit.apply();
                cargarActivity();
            } else
            /**
             * Carga actividad si no se encuentra selecionado el checkbox
             * vuelve a pedir contraseña
             * */
                cargarActivity();
        }
    }


    private void cargarActivity() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void borrarPreference() {

        /** Borra las preferencias */
        SharedPreferences.Editor edit = pref.edit();
        edit.remove(PreferenceConstant.PREF_KEY_USERNAME);
        edit.apply();

    }

}
