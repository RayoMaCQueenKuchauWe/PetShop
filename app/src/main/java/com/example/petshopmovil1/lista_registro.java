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

    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_registro);

        btnRegresar=findViewById(R.id.btnSalir);
        PetsDTO dto=new PetsDTO();
        ListView list= findViewById(R.id.lista);
        Modelo mod=new Modelo(this);
        ArrayList<PetsDTO> listR=mod.selectPets();

        ArrayList<String> lst= new ArrayList<>();
        for(PetsDTO petsDTO:listR){
            lst.add("Nombre: "+petsDTO.getNombre()+" Mascota: "+petsDTO.getTipo()+" Raza: "+petsDTO.getRaza()+" Género: "+petsDTO.getGenero()+" Edad: "+petsDTO.getEdad());
        }
        ArrayAdapter<String> s= new ArrayAdapter<>(this, android.R.layout.simple_expandable_list_item_1, lst);
        list.setAdapter(s);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ref=new Intent(lista_registro.this, MainActivity.class);
                startActivity(ref);
            }
        });
    }
}
