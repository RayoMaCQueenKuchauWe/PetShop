package com.example.petshopmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class lista_registro extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_registro);

        PetsDTO dto=new PetsDTO();
        ListView list= findViewById(R.id.lista);
        Modelo mod=new Modelo(this);
        ArrayList<PetsDTO> listR=mod.selectPets();

        ArrayList<String> lst= new ArrayList<>();
        for(PetsDTO petsDTO:listR){
            lst.add("Nombre: "+petsDTO.getNombre()+" Mascota: "+petsDTO.getTipo()+" Raza: \r\n"+petsDTO.getRaza()+"Género: "+petsDTO.getGenero()+" Edad(meses): "+petsDTO.getEdad());
        }
        ArrayAdapter<String> s= new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, lst);
        list.setAdapter(s);
    }
}
