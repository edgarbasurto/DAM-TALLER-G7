package com.example.preguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Activity_preguntas extends AppCompatActivity {
    int contador = 0;
    int score = 0;
    Pregunta[] pregunta_test = {
        new Pregunta("¿Estamos solos en el Universo?", false)
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preguntas);

        Button btnVerdadero = (Button)findViewById(R.id.btn_verdadero);
        Button btnFalso = (Button)findViewById(R.id.btn_verdadero);

        TextView txtjugador = (TextView) findViewById(R.id.txt_jugador);
        final TextView txtpregunta = (TextView) findViewById(R.id.txt_pregunta);
        final TextView txtpuntaje = (TextView) findViewById(R.id.txt_puntaje);

        Pregunta p1 = pregunta_test[0];
        txtpregunta.setText(p1.getPregunta());

        Bundle bundle = getIntent().getExtras();
        String nombre_jugador = bundle.getString("name_usuario");
        txtjugador.setText("Jugador: " + nombre_jugador);




        //EventoClick
        btnVerdadero.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                contador += 1;
                if(p1.getRespuesta())
                    score += 100;
                else
                    score -= 100;
                txtpuntaje.setText(" " + String.valueOf(score) + " ");
                Pregunta p1 = pregunta_test[contador];
                txtpregunta.setText(p1.getPregunta());
            }
        });



    }

}