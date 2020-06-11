package com.example.petshopmovil1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class Modelo {
    Context c;
    SQLiteDatabase db;
    ArrayList<PetsDTO> list;
    PetsDTO pets;
    String bd="dbpets";

    public Modelo(Context c){
        this.c=c;
        db=c.openOrCreateDatabase(bd,c.MODE_PRIVATE,null);
        pets=new PetsDTO();
    }

    public Modelo(){}

    public SQLiteDatabase getConn(Context context){
        PetsBD petsBD=new PetsBD(context, "dbpets",null,1);
        SQLiteDatabase db=petsBD.getWritableDatabase();
        return db;
    }

    int insetPet(Context context,PetsDTO dto){
        int res=0;
        String sql="INSERT INTO mascotas(tipo,raza,edad,genero) VALUES('"+dto.getTipo()+"','"+dto.getRaza()+"','"+dto.getEdad()+"','"+dto.getGenero()+"')";
        SQLiteDatabase db=this.getConn(context);
        try{
            db.execSQL(sql);
            res=1;
        }catch (Exception ex){
            throw ex;
        }
        return res;
    }

    public ArrayList<PetsDTO> selectPets(){
        ArrayList<PetsDTO> list=new ArrayList<PetsDTO>();
        list.clear();
        Cursor cr=db.rawQuery("SELECT * FROM mascotas",null);
        if(cr!=null && cr.moveToFirst()){
            do{
                PetsDTO pets=new PetsDTO();
                pets.setTipo(cr.getString(1));
                pets.setRaza(cr.getString(2));
                pets.setGenero(cr.getString(4));
                pets.setEdad(cr.getInt(3));
                list.add(pets);
            }while(cr.moveToNext());
        }
        return list;
    }
}
