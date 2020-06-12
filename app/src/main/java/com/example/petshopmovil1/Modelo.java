package com.example.petshopmovil1;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

class Modelo {
    private Context c;
    private SQLiteDatabase db;
    ArrayList<PetsDTO> list;
    private PetsDTO pets;
    private String bd="dbpets";

    Modelo(Context c){
        this.c=c;
        db=c.openOrCreateDatabase(bd, Context.MODE_PRIVATE,null);
        pets=new PetsDTO();
    }

    Modelo(){}

    private SQLiteDatabase getConn(Context context){
        PetsBD petsBD=new PetsBD(context, "dbpets",null,1);
        SQLiteDatabase db=petsBD.getWritableDatabase();
        return db;
    }

    int insetPet(Context context,PetsDTO dto){
        int res;
        String sql="INSERT INTO mascotas VALUES(NULL,'"+dto.getNombre()+"','"+dto.getTipo()+"','"+dto.getRaza()+"','"+dto.getEdad()+"','"+dto.getGenero()+"','"+dto.getFoto()+"')";
        SQLiteDatabase db=this.getConn(context);
        try{
            db.execSQL(sql);
            res=1;
        }catch (Exception ex){
            throw ex;
        }
        return res;
    }

    ArrayList<PetsDTO> selectPets(){
        ArrayList<PetsDTO> list= new ArrayList<>();
        list.clear();
        Cursor cr=db.rawQuery("SELECT * FROM mascotas",null);
        if(cr!=null && cr.moveToFirst()){
            do{
                PetsDTO pets=new PetsDTO();
                pets.setNombre(cr.getString(1));
                pets.setTipo(cr.getString(2));
                pets.setRaza(cr.getString(3));
                pets.setGenero(cr.getString(5));
                pets.setEdad(cr.getInt(4));
                list.add(pets);
            }while(cr.moveToNext());
        }
        return list;
    }
}
