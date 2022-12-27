package com.example.preguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AcercaDe extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acerca_de);

        Button btn_volver = (Button)findViewById(R.id.btn_volver_login);
        btn_volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent call_principal = new Intent(v.getContext(), Login.class);
                startActivity(call_principal);
            }
        });
    }
}