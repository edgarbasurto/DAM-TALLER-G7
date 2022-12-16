package com.example.preguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.preguntas.R.id;

import java.util.Arrays;


public class Login extends AppCompatActivity {
    EditText txt_nombre;
    EditText txt_clave;
    Usuario[] usuarios_test = {
            new Usuario("ebasurto", "1234","Edgar Basurto Cruz"),
            new Usuario("elarrea", "1234","Edwin Larrea Buste"),
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar_taller);
        //setSupportActionBar(myToolbar);

        Button btn_aceptar = (Button)findViewById(R.id.btn_aceptar);
        Button btn_nuevo = (Button)findViewById(R.id.btn_nuevo);
        txt_nombre = (EditText) findViewById(R.id.txt_nombre);
        txt_clave = (EditText)findViewById(R.id.txt_password);

        btn_aceptar.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v){
               Usuario search = Arrays.asList(usuarios_test).stream()
                       .filter(user->user.getUsuario().equals(txt_nombre.getText().toString()))
                               .limit(1)
                               .findFirst().orElse(null);
               if (search != null) {
                   if (search.getContrasenia().equals(txt_clave.getText().toString())){
                       Intent call_principal = new Intent(v.getContext(), Activity_preguntas.class);
                       call_principal.putExtra("name_usuario", search.getNombresCompletos());
                       startActivity(call_principal);
                   }else{
                       Toast.makeText(getApplicationContext(), "Contraseña incorrecta", Toast.LENGTH_SHORT).show();
                   }
               } else {
                   Toast.makeText(getApplicationContext(), "Usuario incorrecto", Toast.LENGTH_SHORT).show();
               }

           }
        });

        btn_nuevo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent call_principal = new Intent(v.getContext(), Formulario.class);
                startActivity(call_principal);

            }
        });

    }
}