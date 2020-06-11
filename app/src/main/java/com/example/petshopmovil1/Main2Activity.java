package com.example.petshopmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    PetsDTO dto=new PetsDTO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Spinner tipo=findViewById(R.id.edtTipo);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(Main2Activity.this,R.array.TipoMascota,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(adapter);
        tipo.setOnItemSelectedListener(this);

        Button btnAgregar=findViewById(R.id.btnAgregar);
        Button btnListar=findViewById(R.id.btnRegistro);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Modelo mdl=new Modelo();
                EditText raza=findViewById(R.id.edtRaza);
                EditText edad=findViewById(R.id.edtEdad);

                dto.setRaza(raza.getText().toString());
                dto.setEdad(Integer.parseInt(edad.getText().toString()));

                int resInsert=mdl.insetPet(Main2Activity.this,dto);
                if(resInsert==1){
                    Toast.makeText(Main2Activity.this,"Registro exitoso",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Main2Activity.this,"Fallo al registrar",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void onRadioButtonClicked (View view) {
        boolean marcado = ((RadioButton)view).isChecked();
        switch (view.getId()) {
            case R.id.radioM:
                if (marcado==true) {
                    dto.setGenero("Macho");
                }
                break;
            case R.id.radioH:
                if (marcado==true) {
                    dto.setGenero("Hembra");
                }
                break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        dto.setTipo(text);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    public void Siguiente(View v){
        Intent intent=new Intent(this,lista_registro.class);
        startActivity(intent);
    }
}
