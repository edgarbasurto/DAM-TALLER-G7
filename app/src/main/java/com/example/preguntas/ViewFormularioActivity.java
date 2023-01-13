package com.example.preguntas;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ViewFormularioActivity extends AppCompatActivity {

    int registroActual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_formulario);


        Button btn_cancelar_registro = (Button)findViewById(R.id.btn_cancelarRegistro);
        btn_cancelar_registro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                Intent call_principal = new Intent(v.getContext(), Login.class);
                startActivity(call_principal);
            }
        });
    }

    //Metodo para buscar datos
    @SuppressLint("Range")
    public void onBuscar(View v) {
        EditText txtbuscar = (EditText) findViewById(R.id.txtCedula);
        EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
        EditText txtEmail = (EditText) findViewById(R.id.txtCorreo);
        EditText txtApellido = (EditText) findViewById(R.id.txtApellido);
        EditText txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        TextView lblCedula = (TextView) findViewById(R.id.lblCedula);

        //limpia datos en el front
        txtNombre.setText("");
        txtEmail.setText("");
        txtApellido.setText("");
        txtApellido.setText("");
        lblCedula.setText("");
        registroActual=-1;

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
                        registroActual=Integer.parseInt( cur.getString(cur.getColumnIndex("_id")).toString());
                        String Cedula = cur.getString(cur.getColumnIndex("cedula")).toString() ;
                        String Nombre = cur.getString(cur.getColumnIndex("nombre")).toString() ;
                        String Apellido =   cur.getString(cur.getColumnIndex("apellido")).toString();
                        String Email = cur.getString(cur.getColumnIndex("correo")).toString();
                        String Telefono= cur.getString(cur.getColumnIndex("telefono")).toString()  ;

                        lblCedula.setText(Cedula);
                        txtNombre.setText(Nombre);
                        txtEmail.setText(Email);
                        txtApellido.setText(Apellido);
                        txtTelefono.setText(Telefono);
                        txtbuscar.setText("");
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

    public void onActualizar(View v) {

        if (registroActual != -1) {
            EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
            EditText txtEmail = (EditText) findViewById(R.id.txtCorreo);
            EditText txtApellido = (EditText) findViewById(R.id.txtApellido);
            EditText txtTelefono = (EditText) findViewById(R.id.txtTelefono);
            TextView lblCedula = (TextView) findViewById(R.id.lblCedula);


            //valida si existe cedula en la BD
            MySQLiteService dbService = new MySQLiteService(this);
            final SQLiteDatabase bd = dbService.getWritableDatabase();

            if (bd != null) {

                ContentValues cv = new ContentValues();
                cv.put("nombre", txtNombre.getText().toString());
                cv.put("apellido", txtApellido.getText().toString());
                cv.put("telefono", txtTelefono.getText().toString());
                cv.put("correo", txtEmail.getText().toString());
                String[] selectionArgs = {String.valueOf(registroActual)};

                int  count = bd.update("Usuarios",cv,"_id =?" , selectionArgs);

                if (count >0) {
                    //limpia datos en el front
                    txtNombre.setText("");
                    txtEmail.setText("");
                    txtApellido.setText("");
                    txtTelefono.setText("");
                    lblCedula.setText("");
                    registroActual=-1;
                    Toast.makeText(getApplicationContext(), "Registro actualizado!", Toast.LENGTH_SHORT).show();
                }
            }
            bd.close();
        }
        else{ Toast.makeText(getApplicationContext(), "No se encuentra registro para eliminar!", Toast.LENGTH_SHORT).show();           }
    }


    public void onEliminar(View v) {

        if (registroActual != -1) {
            EditText txtNombre = (EditText) findViewById(R.id.txtNombre);
            EditText txtEmail = (EditText) findViewById(R.id.txtCorreo);
            EditText txtApellido = (EditText) findViewById(R.id.txtApellido);
            EditText txtTelefono = (EditText) findViewById(R.id.txtTelefono);
            TextView lblCedula = (TextView) findViewById(R.id.lblCedula);


            //valida si existe cedula en la BD
            MySQLiteService dbService = new MySQLiteService(this);
            final SQLiteDatabase bd = dbService.getWritableDatabase();

            if (bd != null) {

                String[] selectionArgs = {String.valueOf(registroActual)};
                int deletedRows =  bd.delete("Usuarios","_id =?" , selectionArgs);
                if (  deletedRows >0) {
                    //limpia datos en el front
                    txtNombre.setText("");
                    txtEmail.setText("");
                    txtApellido.setText("");
                    txtTelefono.setText("");
                    lblCedula.setText("");
                    registroActual=-1;
                    Toast.makeText(getApplicationContext(), "Registro eliminado!", Toast.LENGTH_SHORT).show();
                }
            }
            bd.close();
        }
        else{ Toast.makeText(getApplicationContext(), "No se encuentra registro para eliminar!", Toast.LENGTH_SHORT).show();           }
    }
}