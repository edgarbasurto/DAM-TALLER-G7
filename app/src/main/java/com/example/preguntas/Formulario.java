package com.example.preguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Arrays;

public class Formulario extends AppCompatActivity implements
        AdapterView.OnItemSelectedListener{
    String[] operadoras = { "Claro", "Movistar", "CNT", "Tuenti", "Otro"};
    EditText txt_nombre;
    EditText txt_apellido;
    EditText txt_edad;
    EditText txt_correo;
    EditText txt_telefono;
    EditText txt_contrasenia;
    RadioButton rdb_masculino;
    RadioButton rdb_femenino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        Spinner spin = (Spinner) findViewById(R.id.spn_tipo_telefono);
        spin.setOnItemSelectedListener(this);

        //Creating the ArrayAdapter instance having the country list
        ArrayAdapter aa = new ArrayAdapter(this,android.R.layout.simple_spinner_item,operadoras);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        //Setting the ArrayAdapter data on the Spinner
        spin.setAdapter(aa);

        Button btn_cancelar_registro = (Button)findViewById(R.id.btn_cancelar_registro);
        Button btn_guardar_registro = (Button)findViewById(R.id.btn_guardar);
        btn_cancelar_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent call_principal = new Intent(v.getContext(), Login.class);
                startActivity(call_principal);
            }
        });

        btn_guardar_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent call_principal = new Intent(v.getContext(), Login.class);
                startActivity(call_principal);
                Toast.makeText(getApplicationContext(), "Se ha guardado con éxito.", Toast.LENGTH_SHORT).show();
            }
        });


    }

    //Performing action onItemSelected and onNothing selected
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {
        Toast.makeText(getApplicationContext(),operadoras[position] , Toast.LENGTH_LONG).show();
    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()){
            case R.id.rdb_masculino:
                if (checked)
                    Toast.makeText(getApplicationContext(), "Seleccionó Masculino", Toast.LENGTH_SHORT).show();
                break;
            case R.id.rdb_femenino:
                if(checked)
                    Toast.makeText(getApplicationContext(), "Seleccionó Femenino", Toast.LENGTH_SHORT).show();
                break;

        }
    }

    public void reset(View view){
        txt_nombre = (EditText) findViewById(R.id.txt_nombres);
        txt_apellido = (EditText) findViewById(R.id.txt_apellidos);
        txt_edad = (EditText) findViewById(R.id.txt_edad);
        txt_correo = (EditText) findViewById(R.id.txt_correo);
        txt_telefono = (EditText) findViewById(R.id.txt_telefono);
        txt_contrasenia = (EditText) findViewById(R.id.txt_contrasenia);
        rdb_masculino = (RadioButton) findViewById(R.id.rdb_masculino);
        rdb_femenino = (RadioButton) findViewById(R.id.rdb_femenino);

        txt_nombre.setText("");
        txt_apellido.setText("");
        txt_edad.setText("");
        txt_correo.setText("");
        txt_telefono.setText("");
        txt_contrasenia.setText("");
        //rdb_masculino = (RadioButton) findViewById(R.id.rdb_masculino;
        //rdb_femenino = (RadioButton) findViewById(R.id.rdb_femenino;
    }
}