package com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2.Preference.PreferenceConstant;

public class Login extends AppCompatActivity {


    /** Administra las preferencias */
    private SharedPreferences pref;
    String username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        pref = getSharedPreferences(PreferenceConstant.PREFERENCE_LOGIN, MODE_PRIVATE);
        username = pref.getString(PreferenceConstant.PREF_KEY_USERNAME, null);


        if (username != null){
            cargarActivity();
        }


        this.setTitle(R.string.title_Login);

        int color = R.color.colorPrimary;
        int cambiarColor = ContextCompat.getColor(this.getBaseContext(), color);


        if (Build.VERSION.SDK_INT >= 21) {
            /** Cambia colores apartir de la version 21*/
            this.getWindow().setStatusBarColor(cambiarColor);
        }

        final EditText userNameLogin = (EditText) findViewById(R.id.userNameLogin);
        final EditText userNamePass = (EditText) findViewById(R.id.userNamePass);

        Button button = (Button) findViewById(R.id.ingresarLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /**
                 *  variables para validar contenido del objeto, si algún elemento
                 * se encuentra vacio, no permite la continuidad, ejecuta intent cuando
                 * los dos elementos son falsos
                 * */
                String login = userNameLogin.getText().toString();
                String pass = userNamePass.getText().toString();

                if (login.isEmpty() || pass.isEmpty()) {
                    Toast.makeText(
                            Login.this, "No pueden existir campos vacios", Toast.LENGTH_SHORT).show();
                } else {

                    SharedPreferences.Editor edit = pref.edit();
                    edit.putString(PreferenceConstant.PREF_KEY_USERNAME, login);
                    edit.apply();

                    cargarActivity();
                }

            }
        });

    }

    private void cargarActivity() {
        Intent intent = new Intent(Login.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}
