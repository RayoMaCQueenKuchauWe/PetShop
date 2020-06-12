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

    private final String APP_DIRECTORY="MisImagenes/";
    private final String MEDIA_DIRECTORY=APP_DIRECTORY+"fotos";

    private final int PHOTO_CODE=20;
    private final int SELECTOR_CODE=10;

    ImageView imageView;

    PetsDTO dto=new PetsDTO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tipo=findViewById(R.id.edtTipo);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(Main2Activity.this,R.array.TipoMascota,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipo.setAdapter(adapter);
        tipo.setOnItemSelectedListener(this);

        btnAgregar=findViewById(R.id.btnAgregar);
        btnListar=findViewById(R.id.btnRegistro);
        btnFoto=findViewById(R.id.btnFoto);

        raza = findViewById(R.id.txtRaza);
        edad = findViewById(R.id.txtEdad);
        nombre = findViewById(R.id.txtNombre);

        imageView=findViewById(R.id.Foto);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(validar())
                    {
                        Modelo mdl = new Modelo();

                        dto.setNombre(nombre.getText().toString());
                        dto.setRaza(raza.getText().toString());
                        dto.setEdad(Integer.parseInt(edad.getText().toString()));

                        int resInsert = mdl.insetPet(Main2Activity.this, dto);
                        if (resInsert == 1) {
                            Toast.makeText(Main2Activity.this, "Registro exitoso", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Main2Activity.this, "Fallo al registrar", Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (Exception ex)
                {
                    Toast.makeText(Main2Activity.this, ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final CharSequence[] options={"Tomar foto", "Elegir de galeria", "Cancelar"};
                final AlertDialog.Builder builder=new AlertDialog.Builder(Main2Activity.this);
                builder.setTitle("Elige una opción");
                builder.setItems(options, new DialogInterface.OnClickListener() {
                    @SuppressLint("IntentReset")
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (options[which].equals("Tomar foto")) {
                            openCamera();
                        } else {
                            if (options[which].equals("Elegir de galeria")) {
                                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                                intent.setType("image/");
                                startActivityForResult(Intent.createChooser(intent, "Seleccione la Aplicación"), SELECTOR_CODE);
                            } else {
                                dialog.dismiss();
                            }
                        }
                    }
                });
                builder.show();
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
                    dto.setGenero("Macho");
                }
                break;
            case R.id.radioH:
                if (marcado) {
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
    public void onNothingSelected(AdapterView<?> parent) {  }

    public void Siguiente(View v){
        Intent intent=new Intent(this,lista_registro.class);
        startActivity(intent);
    }

    private void openCamera(){
        File file=new File(Environment.getExternalStorageDirectory(), MEDIA_DIRECTORY);
        boolean isCreada=file.exists();
        String nombre="";
        if(!isCreada){
            isCreada=file.mkdirs();
        }
        if(isCreada){
            nombre=(System.currentTimeMillis()/1000)+".jpg";
        }

        path=Environment.getExternalStorageDirectory()+ File.separator + MEDIA_DIRECTORY + File.separator + nombre;
        File newFile=new File(path);

        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(newFile));
        startActivityForResult(intent, PHOTO_CODE);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PHOTO_CODE:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null, new MediaScannerConnection.OnScanCompletedListener() {
                        @Override
                        public void onScanCompleted(String path, Uri uri) {
                            Log.i("Ruta de almacenamiento", "Path: " + path);
                        }
                    });
                    Bitmap bitmap;
                    bitmap = BitmapFactory.decodeFile(path);
                    imageView.setImageBitmap(bitmap);
                    dto.setFoto(bitmap.toString());
                    break;
                case SELECTOR_CODE:
                    Uri path = data.getData();
                    imageView.setImageURI(path);
                    dto.setFoto(path.toString());
                    break;
            }
        }
    }
}
