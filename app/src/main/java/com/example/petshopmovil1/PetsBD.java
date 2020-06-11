package com.example.petshopmovil1;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PetsBD extends SQLiteOpenHelper {
    final String TABLA_MASCOTAS = "CREATE TABLE IF NOT EXISTS mascotas (idMascota INTEGER PRIMARY KEY AUTOINCREMENT, tipo TEXT,raza TEXT, edad INTEGER, genero TEXT)";

    public PetsBD(Context context, String name,SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(TABLA_MASCOTAS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int il) {

    }
}
