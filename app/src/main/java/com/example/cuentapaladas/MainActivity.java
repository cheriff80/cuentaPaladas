package com.example.cuentapaladas;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.cuentapaladas.beans.Cronometro;

public class MainActivity extends AppCompatActivity implements Runnable {

    Button boton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) findViewById(R.id.buttonActivida2);

        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,PantallaCuentaPaldasActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void run() {

    }
}
