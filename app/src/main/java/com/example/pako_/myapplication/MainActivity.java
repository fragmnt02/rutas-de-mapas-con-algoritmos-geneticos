package com.example.pako_.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.android.gms.maps.MapView;

import java.util.ArrayList;

import Grafo.Grafo;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Grafo mapa= new Grafo(this);
        Spinner origen = (Spinner) findViewById(R.id.origen);
        Spinner destino = (Spinner) findViewById(R.id.destino);
        recorrer(origen,destino,mapa);
    }
    public final static String EXTRA_MESSAGE = "com.example.pako_.myapplication.MapsActivity";
    public void recorrer(final Spinner origen, final Spinner destino,final Grafo mapa) {

        Button x= (Button) findViewById(R.id.boton);

        x.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, RecorridoActivity.class);
                ArrayList<Integer> tempo=mapa.recorrer(String.valueOf(origen.getSelectedItem()),String.valueOf(destino.getSelectedItem()),100,50);
                String rec=mapa.getCaden();
                System.out.println(tempo.toString());
                myIntent.putExtra("recorridoCadena",rec);
                myIntent.putExtra("recorrido", tempo);
                myIntent.putExtra("nombres", mapa.getContenidos());
                MainActivity.this.startActivity(myIntent);
            }

        });
    }
}
