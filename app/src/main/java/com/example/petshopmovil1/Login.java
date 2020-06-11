package com.example.petshopmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import static android.widget.Toast.LENGTH_LONG;
import static android.widget.Toast.makeText;

public class Login extends AppCompatActivity {

    EditText user,pass;
    Button btnEntrar, btnRegistrar;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user=findViewById(R.id.User);
        pass=findViewById(R.id.Pass);
        btnEntrar=findViewById(R.id.btnEntrar);
        btnRegistrar=findViewById(R.id.btnRegistrar);

        dao=new daoUsuario(this);
        btnEntrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String u=user.getText().toString();
                    String p=pass.getText().toString();
                    if(u.equals("")&&p.equals("")){
                        Toast.makeText(Login.this,"ERROR: Campos vacios", LENGTH_LONG).show();
                    }
                    else
                    {
                        if(dao.login(u,p)==1){
                            Usuario ux=dao.getUsuario(u,p);
                            Toast.makeText(Login.this,"Datos Correctos", LENGTH_LONG).show();
                            Intent entra=new Intent(Login.this, MainActivity.class);
                            entra.putExtra("Id",ux.getId());
                            startActivity(entra);
                        }
                        else
                        {
                            Toast.makeText(Login.this,"Usuario y/o contraseña incorrectos", LENGTH_LONG).show();
                        }
                    }
                } catch (Exception ex){
                    Toast.makeText(Login.this, ex.getMessage(), LENGTH_LONG).show();
                }
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent cancel=new Intent(Login.this, Registrar.class);
                startActivity(cancel);
            }
        });

    }
}
