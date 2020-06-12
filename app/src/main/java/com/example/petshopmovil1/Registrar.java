package com.example.petshopmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Registrar extends AppCompatActivity {

    EditText us, pas,pas2, nom, ap,co;
    Button reg, can;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        //Hola Mundo xD
        us=findViewById(R.id.RegUser);
        pas=findViewById(R.id.RegPass);
        pas2=findViewById(R.id.RegPass2);
        nom=findViewById(R.id.RegNombre);
        ap=findViewById(R.id.RegApellido);
        co=findViewById(R.id.RegCorreo);
        reg=findViewById(R.id.btnRegRegistrar);
        can=findViewById(R.id.btnRegCancelar);

        dao=new daoUsuario(this);
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(us.getText().toString().isEmpty() || pas.getText().toString().isEmpty() || pas2.getText().toString().isEmpty() || nom.getText().toString().isEmpty() || ap.getText().toString().isEmpty() || co.getText().toString().isEmpty())
                    {
                        Toast.makeText(Registrar.this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        Usuario u = new Usuario();
                        u.setUsuario(us.getText().toString());
                        u.setPassword(pas.getText().toString());
                        u.setPassword2(pas2.getText().toString());
                        u.setNombre(nom.getText().toString());
                        u.setApellidos(ap.getText().toString());
                        u.setCorreo(co.toString());
                        if(!u.isNull()){
                            Toast.makeText(Registrar.this,"ERROR: Campos vacios",Toast.LENGTH_LONG).show();
                        }
                        else
                        {
                            if(dao.insertUsuario(u)){
                                Toast.makeText(Registrar.this,"Registro Exitoso!",Toast.LENGTH_LONG).show();
                                Intent ent = new Intent (Registrar.this, Login.class);
                                startActivity(ent);
                            }
                            else
                            {
                                Toast.makeText(Registrar.this,"Usuario ya registrado",Toast.LENGTH_LONG).show();
                            }
                        }
                    }

                } catch (Exception ex){
                    Toast.makeText(Registrar.this,ex.getMessage(),Toast.LENGTH_LONG).show();
                }
            }
        });

        can.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent can=new Intent(Registrar.this,Login.class);
                startActivity(can);
                finish();
            }
        });
    }
}
