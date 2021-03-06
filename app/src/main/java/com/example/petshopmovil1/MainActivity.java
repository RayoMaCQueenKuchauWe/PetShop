package com.example.petshopmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button btnPresentacion, btnRegistro, btnIniciar, btnConfig,btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPresentacion=findViewById(R.id.btnSomos);
        btnRegistro=findViewById(R.id.btnRegistro);
        btnIniciar=findViewById(R.id.btnIngreso);
        btnConfig=findViewById(R.id.btnConfig);
        btnSalir=findViewById(R.id.btnSalir);

        //Presentacón de la Tienda
        btnPresentacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pres = new Intent(MainActivity.this, Presentacion.class);
                startActivity(pres);
                finish();
            }
        });

        //Lista de usuarios
        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resg = new Intent(MainActivity.this, Mostrar.class);
                startActivity(resg);
                finish();
            }
        });

        //Agregar mascota
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent most= new Intent(MainActivity.this, Main2Activity.class);
                startActivity(most);
                finish();
            }
        });

        //cambiarIdioma
        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent most= new Intent(MainActivity.this, cambiar_idioma.class);
                startActivity(most);
                finish();
            }
        });
        //Salir
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent most= new Intent(MainActivity.this, Login.class);
                startActivity(most);
                finish();
            }
        });
    }
}
