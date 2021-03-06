package com.example.petshopmovil1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Mostrar extends AppCompatActivity {

    ListView lista;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar);

        lista=(ListView)findViewById(R.id.lista);
        dao=new daoUsuario(this);
        ArrayList<Usuario> l= dao.selectUsuarios();
        ArrayList<String> list = new ArrayList<String>();
        for (Usuario u:l) {
            list.add(getString(R.string.txtRNombre)+": "+u.getNombre()+" "+getString(R.string.txtRApellido)+": "+u.getApellidos()+" "+getString(R.string.txtRUsuario)+": "+u.getUsuario());
        }
        ArrayAdapter<String> a = new ArrayAdapter<String>(this,android.R.layout.simple_expandable_list_item_1,list);
        lista.setAdapter(a);
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();

        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }
}
