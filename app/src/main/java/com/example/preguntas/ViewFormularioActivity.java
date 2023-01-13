package com.example.preguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewFormularioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_formulario);
    }

    //Metodo para buscar datos
    public void onBuscar(View v) {
        EditText txtbuscar = (EditText) findViewById(R.id.txtCedula);
        TextView txtNombre = (TextView) findViewById(R.id.txtNombre);
        TextView txtEmail = (TextView) findViewById(R.id.txtEmail);
        TextView txtGenero = (TextView) findViewById(R.id.txtGenero);
        TextView txtTelefono = (TextView) findViewById(R.id.txtTelefono);

        //limpia datos en el front
        txtNombre.setText("");
        txtEmail.setText("");
        txtGenero.setText("");
        txtTelefono.setText("");

        //valida si existe cedula en la BD
        MySQLiteService dbService = new MySQLiteService(this);
        final SQLiteDatabase bd = dbService.getWritableDatabase();
        String pCedula= txtbuscar.getText().toString().trim();
         if ( pCedula.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Debe ingresar una cédula para realizar la busqueda", Toast.LENGTH_SHORT).show();
        }
        else {
            if (bd != null) {
                Cursor cur= bd.rawQuery("SELECT * FROM Usuarios WHERE  cedula LIKE '%"+ pCedula+"%'", null);
                if (cur!= null ){
                    if(cur.getCount()>=1) {
                        cur.moveToFirst();
                        do {
                            @SuppressLint("Range")
                            String NombreCompleto = cur.getString(cur.getColumnIndex("nombre")).toString() +" " + cur.getString(cur.getColumnIndex("apellido")).toString();
                            @SuppressLint("Range")
                            String Email = cur.getString(cur.getColumnIndex("correo")).toString();
                            @SuppressLint("Range")
                            String Telefono= cur.getString(cur.getColumnIndex("telefono")).toString() +" " + cur.getString(cur.getColumnIndex("operadora")).toString() ;

                            @SuppressLint("Range")
                            String Genero= cur.getString(cur.getColumnIndex("edad")).toString() +" " + cur.getString(cur.getColumnIndex("genero")).toString() ;

                            txtNombre.setText(NombreCompleto);
                            txtEmail.setText(Email);
                            txtGenero.setText(Genero);
                            txtTelefono.setText(Telefono);
                            txtbuscar.setText("");
                        }
                        while (cur.moveToNext());
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "No encontrado!", Toast.LENGTH_SHORT).show();
                    }
                }
                cur.close();
            }
            bd.close();

        }
    }
}