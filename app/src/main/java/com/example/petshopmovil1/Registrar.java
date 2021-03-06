package com.example.petshopmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class Registrar extends AppCompatActivity {

    EditText us, pas,pas2, nom, ap,co;
    Button reg, can;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

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
                    if (us.getText().toString().isEmpty() || pas.getText().toString().isEmpty() || pas2.getText().toString().isEmpty() || nom.getText().toString().isEmpty() || ap.getText().toString().isEmpty() || co.getText().toString().isEmpty()) {
                        Toast.makeText(Registrar.this, getString(R.string.error_camposV), Toast.LENGTH_LONG).show();
                    } else {
                        if (pas.length() < 6) {
                            Toast.makeText(Registrar.this, getString(R.string.error_contraseñaCorta), Toast.LENGTH_LONG).show();
                        } else {
                            if(validarEmail(co.getText().toString())){
                                if (pas.getText().toString().equals(pas2.getText().toString())) {

                                    Usuario u = new Usuario();
                                    u.setUsuario(us.getText().toString());
                                    u.setPassword(pas.getText().toString());
                                    u.setPassword2(pas2.getText().toString());
                                    u.setNombre(nom.getText().toString());
                                    u.setApellidos(ap.getText().toString());
                                    u.setCorreo(co.toString());

                                    if (dao.insertUsuario(u)) {
                                        Toast.makeText(Registrar.this, getString(R.string.win_registro_exitoso), Toast.LENGTH_LONG).show();
                                        Intent ent = new Intent(Registrar.this, Login.class);
                                        startActivity(ent);
                                    } else {
                                        Toast.makeText(Registrar.this, getString(R.string.error_UsuarioYaRegistrado), Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(Registrar.this, getString(R.string.error_contraseñaCoincide), Toast.LENGTH_LONG).show();
                                }
                            }else{
                                co.setError(getString(R.string.error_email));
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
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(this,Login.class);
        startActivity(intent);
        finish();
    }
    private boolean validarEmail(String email){
        Pattern pattern= Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }
}
