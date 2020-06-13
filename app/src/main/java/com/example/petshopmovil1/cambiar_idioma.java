package com.example.petshopmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;

public class cambiar_idioma extends AppCompatActivity {

    Button btnEspañol;
    Button btnIngles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cambiar_idioma);

        btnEspañol=findViewById(R.id.btnEspañol);
        btnIngles=findViewById(R.id.btnIngles);

        btnEspañol.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale localizacion=new Locale("es", "ES");

                Locale.setDefault(localizacion);
                Configuration config=new Configuration();
                config.locale=localizacion;
                getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

                Intent most= new Intent(cambiar_idioma.this, MainActivity.class);
                startActivity(most);
                finish();
            }
        });
        btnIngles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Locale localizacion=new Locale("en", "EN");

                Locale.setDefault(localizacion);
                Configuration config=new Configuration();
                config.locale=localizacion;
                getBaseContext().getResources().updateConfiguration(config,getBaseContext().getResources().getDisplayMetrics());

                Intent most= new Intent(cambiar_idioma.this, MainActivity.class);
                startActivity(most);
                finish();
            }
        });

    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
