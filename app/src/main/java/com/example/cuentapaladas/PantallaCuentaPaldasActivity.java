package com.example.cuentapaladas;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.cuentapaladas.beans.Cronometro;

import java.util.Timer;
import java.util.TimerTask;

public class PantallaCuentaPaldasActivity extends AppCompatActivity{

    TextView textoCronometro,textoPaladas;
    Button boton;
    boolean cronometroActivo= false;
    String min="",seg="",mil="";
    Integer minutos,segundos,milesimas;
    Cronometro cronometro;
    //Timer cronometro;
    //TimerTask cronoTask;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pantalla_cuenta_paldas);


        textoCronometro = (TextView) findViewById(R.id.tvCronometro);
        textoPaladas = (TextView) findViewById(R.id.textViewTiempoCuentaPaladas);
        boton = (Button) findViewById(R.id.buttonInicio);
        //cronometro = new Cronometro(boton,textoCronometro,this);

        //cronometro = new Timer();


    }

    /**
    public void task(){
        cronoTask = new TimerTask() {
            @Override
            public void run() {
                 minutos=0;
                 segundos=0;
                 milesimas=0;

                try{

                     * Mientras el cronometro esté activo seguirá aumentando el tiempo
                     while(cronometroActivo) {
                        //Thread.sleep(4);
                        //incrementamos 4 milesimas de segundo
                        milesimas += 4;
                        if (milesimas == 1000) {
                            milesimas = 0;
                            segundos += 1;
                            //si los segundos llegan a 60 incrementamos 1 el minuto y los segundos
                            //vuelven a 0
                            if (segundos == 60) {
                                segundos = 0;
                                minutos++;
                            }
                        }

                        /**
                         * Mostramos el cronometro en pantalla

                        if (minutos < 10) {
                            min = "0" + minutos;
                        } else {
                            min = minutos.toString();
                        }
                        if (segundos < 10) {
                            seg = "0" + segundos;
                        } else {
                            seg = segundos.toString();
                        }
                        if (milesimas < 10) {
                            mil = "00" + milesimas;
                        } else if (milesimas < 100) {
                            mil = "0" + milesimas;
                        } else {
                            mil = milesimas.toString();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textoCronometro.setText(min + ":" + seg + ":" + mil);
                            }
                        });
                    }
                    cancel();

                }catch (Exception e){
                    System.out.println("Hola");
                }
            }
        };
    }
*/
    public String paladasMin(){

        String paladasMin="";
        //obtengo el tiempo total en milésimas
        int tiempoTotal= cronometro.getMinutos()*60000+cronometro.getSegundos()*1000+cronometro.getMilesimas();

        /**
         * El número de paladas al contar con el móvil es 4
         * El tiempo palada es en milésimas
         */
        int tiempoPalada=tiempoTotal/4;

        Integer paladasMinInt = tiempoPalada*60/1000;
        paladasMin = paladasMinInt.toString();

        return paladasMin;
    }


    public void pulsarBoton(View view) {

        boton = (Button) view;

        //si el texto del botón es START
        if(boton.getText().equals("START")){

            boton.setText("STOP");
            cronometro = new Cronometro(boton,textoCronometro,this);
            cronometro.setCronometroActivo(true);
            cronometro.iniciarCronometro();
            //task();
            //cronometro.schedule(cronoTask,0,);

        }else if(boton.getText().equals("STOP")){
            cronometro.setCronometroActivo(false);
            //cronometro.cancel();
            cronometro.detenerCronometro();
            boton.setText("RES");
            textoPaladas.setText(paladasMin()+" paladas/min");

        }else{
            min="00";
            seg="00";
            mil="00";
            textoCronometro.setText(min + ":" + seg + ":" + mil);
            //cronometro = new Timer();
            boton.setText("START");
        }
    }

}


