package com.example.cuentapaladas.beans;


import android.app.Activity;

import android.widget.Button;

import android.widget.TextView;

public class Cronometro implements Runnable {

    /**
     * Tiempos al iniciar, pulsa START
     * el usuario cuenta cuatro paladas y pulsa STOP
     */
    private int tiempoInicial, tiempoFinal;
    private Button botonCronometro;
    private TextView cronometro;
    private boolean cronometroActivo;
    private Activity context;
    private Thread hilo;
    String min = "", seg = "", mil = "";
    Integer minutos = 0, segundos = 0, milesimas = 0;


    public Cronometro(Button botonCronometro, TextView cronometro, Activity context) {
        this.context = context;
        this.cronometro = cronometro;
        this.botonCronometro = botonCronometro;
        this.tiempoInicial = 0;//el tiempo inicial es 0
        cronometroActivo = false;
    }

    public int getTiempoInicial() {
        return tiempoInicial;
    }

    public void setTiempoInicial(int tiempoInicial) {
        this.tiempoInicial = tiempoInicial;
    }

    public int getTiempoFinal() {
        return tiempoFinal;
    }

    public void setTiempoFinal(int tiempoFinal) {
        this.tiempoFinal = tiempoFinal;
    }

    public Button getBotonCronometro() {
        return botonCronometro;
    }

    public void setBotonCronometro(Button botonCronometro) {
        this.botonCronometro = botonCronometro;
    }

    public TextView getCronometro() {
        return cronometro;
    }

    public void setCronometro(TextView cronometro) {
        this.cronometro = cronometro;
    }

    public boolean isCronometroActivo() {
        return cronometroActivo;
    }

    public void setCronometroActivo(boolean cronometroActivo) {
        this.cronometroActivo = cronometroActivo;
    }

    public Activity getContext() {
        return context;
    }

    public void setContext(Activity context) {
        this.context = context;
    }

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getSeg() {
        return seg;
    }

    public void setSeg(String seg) {
        this.seg = seg;
    }

    public String getMil() {
        return mil;
    }

    public void setMil(String mil) {
        this.mil = mil;
    }

    public Integer getMinutos() {
        return minutos;
    }

    public void setMinutos(Integer minutos) {
        this.minutos = minutos;
    }

    public Integer getSegundos() {
        return segundos;
    }

    public void setSegundos(Integer segundos) {
        this.segundos = segundos;
    }

    public Integer getMilesimas() {
        return milesimas;
    }

    public void setMilesimas(Integer milesimas) {
        this.milesimas = milesimas;
    }

    public void iniciarCronometro() {

        hilo = new Thread(this);
        hilo.start();
    }

    public void detenerCronometro() {
        cronometroActivo = false;

    }

    @Override
    public void run() {


        try {
            /**
             * Mientras el cronometro esté activo seguirá aumentando el tiempo
             */
            while (cronometroActivo) {
                Thread.sleep(4);
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
                 */
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

                context.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        cronometro.setText(min + ":" + seg + ":" + mil);
                    }
                });

            }

        } catch (Exception e) {
            System.out.println("Hola");
        }
    }


}
