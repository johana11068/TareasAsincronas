package com.example.aula7.tareasasincronas;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    //Declarar componentes
    ProgressBar progressBar;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Referenciar Componentes
        progressBar = (ProgressBar) findViewById(R.id.id_pb_loatdata);
        textView = (TextView) findViewById(R.id.id_tv_data);
    }
    //Revisar estado de conexion
    public Boolean isOnLine(){
        //Obtener el servicio de la conectividad en android
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);//instancia de conne.. y llama al metodo get...
        //Obtener la informacion del estado de la red (activa)
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null){
            return true;
        }else{
            return false;
        }
    }

    //Evento de Boton = cargar Datos
    public void loadData(View view){

    }
}
