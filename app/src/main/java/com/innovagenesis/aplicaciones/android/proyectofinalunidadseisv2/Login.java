package com.innovagenesis.aplicaciones.android.proyectofinalunidadseisv2;

import android.content.Intent;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        this.setTitle(R.string.title_Login);

        int color = R.color.colorPrimary;
        int cambiarColor = ContextCompat.getColor(this.getBaseContext(), color);


        if (Build.VERSION.SDK_INT >= 21) {
            /** Cambia colores apartir de la version 21*/
            this.getWindow().setStatusBarColor(cambiarColor);
        }

        final EditText userNameLogin = (EditText)findViewById(R.id.userNameLogin);
        final EditText userNamePass = (EditText)findViewById(R.id.userNamePass);

       Button button = (Button)findViewById(R.id.ingresarLogin);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String login = userNameLogin.getText().toString();
                String pass = userNamePass.getText().toString();

                if (login.isEmpty()||pass.isEmpty()){
                    Toast.makeText(
                            Login.this,"No pueden existir campos vacios",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(Login.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }

            }
        });

   }
}
