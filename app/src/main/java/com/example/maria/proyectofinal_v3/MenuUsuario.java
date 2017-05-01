package com.example.maria.proyectofinal_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import javabean.DatosServicio;
import javabean.DatosUsuario;

public class MenuUsuario extends AppCompatActivity {

    private DatosServicio datSer;
    private DatosUsuario datUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_usuario);

        //Recojo los datos del intent
        Intent int1=this.getIntent();
        datSer=(DatosServicio)int1.getSerializableExtra("serv");
        datUser=(DatosUsuario)int1.getSerializableExtra("user");

    }

    public void misServicios(View v){

        //Lanza la actividad HistorialUsuario
        Intent intent=new Intent(this, HistorialUsuario.class);
        startActivity(intent);

    }


    //cada uno de estos métodos (imagebutton) lanza la actividad CategoriaUsuario

    public void fontanero(View v){

        Intent intent=new Intent(this, CategoriaUsuario.class);

        //Datos que lanzamos a la siguiente actividad
        //Meto la categoría según el botón en el javabean y lo mando

        datSer.setCategoria("fontanero");
        intent.putExtra("servicio", datSer);
        intent.putExtra("usuario", datUser);

        startActivity(intent);



    }
}
