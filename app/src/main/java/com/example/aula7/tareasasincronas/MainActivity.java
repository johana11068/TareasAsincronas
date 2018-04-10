package com.example.aula7.tareasasincronas;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

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
    //Metodo para Validad estado de la red
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
        if (isOnLine()){
            MyTask myTask = new MyTask();
            myTask.execute();
        }else{
            Toast.makeText(this, "No hay conexion a internet", Toast.LENGTH_SHORT).show();
        }
    }

    //**********************************************************************************************

    //Metodo para procesar los datos
    public void processData(String s){
        textView.setText("Numero: " + s);
        textView.setTextSize(Integer.parseInt(s));
    }
    //Clase para crear una tarea asincrona
    public class MyTask extends AsyncTask <String, String, String>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override//Mostrar lista de 50 Numeros, no se muestra datos a los componentes
        protected String doInBackground(String... strings) {
            for (int i = 1; i <= 50;i++){
                publishProgress(String.valueOf(i));//metodo encargado de pasar de un hilo a uno !=
            }
            return "Fin";
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            processData(values[0]);//establezco el valor nuevo en mi textview
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            progressBar.setVisibility(View.GONE);//Quitar progressBar
        }
    }
}
