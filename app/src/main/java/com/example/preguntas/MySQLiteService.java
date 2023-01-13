package com.example.preguntas;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
public class MySQLiteService extends SQLiteOpenHelper {
    private static final String USER_TABLE_CREATE = "CREATE TABLE Usuarios(_id INTEGER PRIMARY KEY AUTOINCREMENT, cedula TEXT,nombre TEXT, apellido TEXT, edad INTEGER,telefono TEXT,correo TEXT,sexo TEXT,password TEXT)";
    private static final String DB_NAME = "tallerDML.sqlite";
    private static final int DB_VERSION = 1;

    public MySQLiteService(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(USER_TABLE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){}
}
