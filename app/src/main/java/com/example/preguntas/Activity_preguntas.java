package com.example.preguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Activity_preguntas extends AppCompatActivity {
    int score = 0;
    int contador = 0;
    Pregunta[] pregunta_test = {
            new Pregunta("¿Todos pasarán la materia?", "falso"),
            new Pregunta("¿Está difícil el examen?", "falso"),
            new Pregunta("¿Ecuador fue al mundial?", "verdadero"),
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        Button btnVerdadero = (Button)findViewById(R.id.btn_verdadero);
        Button btnFalso = (Button)findViewById(R.id.btn_falso);
        Button btnBorrarDatos = (Button)findViewById(R.id.btn_eliminar_datos);

        TextView txtjugador = (TextView) findViewById(R.id.txt_jugador);
        final TextView txtpregunta = (TextView) findViewById(R.id.txt_pregunta);
        final TextView txtpuntaje = (TextView) findViewById(R.id.txt_puntaje);

        Pregunta p1 = pregunta_test[0];
        txtpregunta.setText(p1.getPregunta());

        Bundle bundle = getIntent().getExtras();
        String nombre_jugador = bundle.getString("name_usuario");
        txtjugador.setText("Jugador: " + nombre_jugador);

        btnBorrarDatos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences preferencias = getSharedPreferences("credenciales", Context.MODE_PRIVATE);

                SharedPreferences.Editor editor = preferencias.edit();
                editor.putString("usuario","");
                editor.putString("clave","");
                editor.commit();
            }
        });


        //EventoClick
        btnVerdadero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                contador = contador +1;
                if(p1.getRespuesta() == "verdadero")
                    score = score + 100;
                else
                    score = score - 100;
                txtpuntaje.setText(" " + String.valueOf(score) + " ");
                Pregunta p1 = pregunta_test[contador];
                txtpregunta.setText(p1.getPregunta());
            }
        });

        btnFalso.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                contador = contador + 1;
                if(p1.getRespuesta()== "falso")
                    score = score + 100;
                else
                    score = score - 100;
                txtpuntaje.setText(" " + String.valueOf(score) + " ");
                Pregunta p1 = pregunta_test[contador];
                txtpregunta.setText(p1.getPregunta());
            }
        });



    }

}