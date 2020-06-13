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
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(this,Main2Activity.class);
        startActivity(intent);
        finish();
    }

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
            lst.add(getString(R.string.lblNombre)+" "+petsDTO.getNombre()+" "+getString(R.string.lblTipo)+" "+petsDTO.getTipo()+" "+getString(R.string.lblRaza)+" "+petsDTO.getRaza()+" "+getString(R.string.lblGenero)+" "+petsDTO.getGenero()+" "+getString(R.string.lblEdad)+" "+petsDTO.getEdad());
        }
        ArrayAdapter<String> s= new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, lst);
        list.setAdapter(s);


    }
}
