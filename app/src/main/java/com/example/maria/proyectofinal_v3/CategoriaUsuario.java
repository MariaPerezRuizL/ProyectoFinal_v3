package com.example.maria.proyectofinal_v3;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import javabean.DatosServicio;
import javabean.DatosUsuario;
import modelo.GestionComs;
import modelo.GestionHistorial;

public class CategoriaUsuario extends AppCompatActivity {

    private TextView fecha;
    private EditText edtDni, edtDireccion;
    private CheckBox cbDireccion;
    private DatosServicio datServ;
    private DatosUsuario datUser;
    private GestionComs gComs;
    private GestionHistorial gHist;
    //private ArrayList<DatosServicio> servicios;
    private LocationManager lm;
    private Location loc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_usuario);

        //Obtengo referencias a los widgets
        fecha=(TextView)findViewById(R.id.tvFecha);
        edtDni=(EditText)findViewById(R.id.edtDni);
        edtDireccion=(EditText)findViewById(R.id.edtDireccion);
        cbDireccion=(CheckBox)findViewById(R.id.cbDireccion);

        //Obtengo el intent
        Intent intent=this.getIntent();

        //Recojo los datos de servicio iniciales y los del usuario
        datServ=(DatosServicio) intent.getSerializableExtra("servicio");
        datUser=(DatosUsuario) intent.getSerializableExtra("usuario");

        //servicios=new ArrayList<>();


    }

    //Configuramos la solicitud del servicio
    public void solicitar (View v){

        //Rellenamos los datos faltantes: dni, dirección

        datServ.setDni(Integer.parseInt(edtDni.getText().toString()));

        //La dirección la recogemos o bien del check box o bien del editext
        if (cbDireccion.isChecked())
        {
            //Obtenemos el objeto que representa el servicio de localización

            lm=(LocationManager)this.getSystemService(this.LOCATION_SERVICE);
            loc=lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            String datos="Altitud:"+loc.getAltitude()+" Longitud:"+loc.getLongitude()+" Latitud: ";
            datos+=+loc.getLatitude();
            datServ.setDireccion(datos);


        }

        else{
            datServ.setDireccion(edtDireccion.getText().toString());
        }


        //Realizamos la comunicación con el servidor en tarea asíncrona

        ComunicacionTask com=new ComunicacionTask();

        //Ejecuto enviando al doInBackground ambos javabean: usuario y servicio
        com.execute(datUser, datServ);

        //Guardamos el servicio solicitado para mostrarlo en el historial


        gHist=new GestionHistorial(this);

        gHist.guardarHistorial(datServ);

        gHist.cerrar();

        /*

        //Gestionamos el array list: si está vacio muestra toast con NO DISPONIBILIDAD


        if (servicios.isEmpty())
        {
            Toast.makeText(CategoriaUsuario.this,
                    "No hay ningún servicio disponible",
                    Toast.LENGTH_LONG).show();
        }

        else
        {
            //Lanzamos la actividad del mapa
            Intent intent =new Intent(this, MapaActivity.class);
            intent.putExtra("usuario", datUser);
            intent.putExtra("servicio",servicios);

            this.startActivity(intent);

        }

*/

    }



    //Método que saca un cuadro calendario para elegir la fecha y lo vuelca en el textView

    @SuppressLint("NewApi")
    public void fecha (View v){

        //Creamos el cuadro de diálogo y lo mostramos
        new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        //Mostramos la fecha en el text view
                        String fech=dayOfMonth + "-" + (month + 1) + "-" + year;

                        //Ponemos la fecha en el editText
                        fecha.setText(fech);
                        //Metemos la fecha en el javabean
                        datServ.setFecha(fech);
                    }
                },
                2017,
                4,
                1).show();
    }

    public class ComunicacionTask extends AsyncTask<Object, Void, ArrayList<DatosServicio>>{

        @Override
        protected ArrayList<DatosServicio> doInBackground(Object... params) {

            //busco los profesionales
            gComs=new GestionComs();

            return gComs.buscarProfesionales((DatosUsuario)params[0], (DatosServicio)params[1]);

        }

        @Override
        protected void onPostExecute(ArrayList<DatosServicio> datosServicios) {

            if (datosServicios.isEmpty())
            {
                Toast.makeText(CategoriaUsuario.this,
                        "No hay ningún servicio disponible",
                        Toast.LENGTH_LONG).show();
            }

            else
            {
                //Lanzamos la actividad del mapa
                Intent intent =new Intent(CategoriaUsuario.this, MapaActivity.class);
                intent.putExtra("usuario", datUser);
                intent.putExtra("servicio",datosServicios);

                CategoriaUsuario.this.startActivity(intent);

            }

          // servicios=datosServicios;

        }
    }
}
