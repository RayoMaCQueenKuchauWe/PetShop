package com.example.petshopmovil1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.File;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    EditText nombre,raza,edad;
    Button btnAgregar,btnFoto,btnListar;
    Spinner tipo;
    String path;

    ImageView imageView;

    PetsDTO dto=new PetsDTO();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tipo = findViewById(R.id.edtTipo);
        String[] valores={getString(R.string.spinnerP),getString(R.string.spinnerG),getString(R.string.spinnerA)};

        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.spinner_modify,valores);
        adapter.setDropDownViewResource(R.layout.spinner_modify);
        tipo.setAdapter(adapter);
        tipo.setOnItemSelectedListener(this);

        btnAgregar = findViewById(R.id.btnAgregar);
        btnListar = findViewById(R.id.btnRegistro);
        btnFoto = findViewById(R.id.btnFoto);

        raza = findViewById(R.id.txtRaza);
        edad = findViewById(R.id.txtEdad);
        nombre = findViewById(R.id.txtNombre);

        imageView = findViewById(R.id.Foto);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (validar()) {
                        Modelo mdl = new Modelo();

                        dto.setNombre(nombre.getText().toString());
                        dto.setRaza(raza.getText().toString());
                        dto.setEdad(Integer.parseInt(edad.getText().toString()));

                        int resInsert = mdl.insetPet(Main2Activity.this, dto);
                        if (resInsert == 1) {
                            Toast.makeText(Main2Activity.this, getString(R.string.win_registro_exitoso), Toast.LENGTH_SHORT).show();
                            nombre.setText("");
                            edad.setText("");
                            raza.setText("");
                            tipo.setSelection(0);

                            imageView.setImageResource(R.mipmap.ic_launcher);

                        } else {
                            Toast.makeText(Main2Activity.this, getString(R.string.error_falloRegistrar), Toast.LENGTH_SHORT).show();
                        }
                    }
                } catch (Exception ex) {
                    Toast.makeText(Main2Activity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/");
                startActivityForResult(Intent.createChooser(intent, getString(R.string.seleccioneApp)), 10);
            }
        });
    }
    public boolean validar(){
        boolean retorno=true;

        nombre.setError(null);
        raza.setError(null);
        edad.setError(null);

        String Nombre=nombre.getText().toString();
        String Rz=raza.getText().toString();
        String Edad=edad.getText().toString();

        if(Nombre.isEmpty()){
            nombre.setError(getString(R.string.error_campoObligatorio));
            nombre.requestFocus();
            retorno=false;
        }
        if (Rz.isEmpty()){
            raza.setError(getString(R.string.error_campoObligatorio));
            raza.requestFocus();
            retorno=false;
        }
        if(Edad.isEmpty()){
            edad.setError(getString(R.string.error_campoObligatorio));
            edad.requestFocus();
            retorno=false;
        }else{
            if(Integer.parseInt(Edad)<0 || Integer.parseInt(Edad)>100){
                edad.setError(getString(R.string.error_valorEntre0y100));
                edad.requestFocus();
                retorno=false;
            }
        }
        return retorno;
    }
    public void onRadioButtonClicked (View view) {
        boolean marcado = ((RadioButton)view).isChecked();
        switch (view.getId()) {
            case R.id.radioM:
                if (marcado) {
                    dto.setGenero(getString(R.string.radioM));
                }
                break;
            case R.id.radioH:
                if (marcado) {
                    dto.setGenero(getString(R.string.radioH));
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
    public void onNothingSelected(AdapterView<?> parent) {  }

    public void Siguiente(View v){
        Intent intent=new Intent(this,lista_registro.class);
        startActivity(intent);
        finish();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {

            Uri path = data.getData();
            imageView.setImageURI(path);
            dto.setFoto(path.toString());
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
