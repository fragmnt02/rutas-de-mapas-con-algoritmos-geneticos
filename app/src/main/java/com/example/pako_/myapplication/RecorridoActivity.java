package com.example.pako_.myapplication;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import Grafo.Recorrido;

public class RecorridoActivity extends AppCompatActivity {
    ArrayList<Integer> recorrido;
    String[] nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recorrido);
        String rec=getIntent().getExtras().getString("recorridoCadena");
        TextView txt= (TextView) findViewById(R.id.textView3);
        txt.setText(rec);
        recorrido=getIntent().getExtras().getIntegerArrayList("recorrido");
        nombres=getIntent().getExtras().getStringArray("nombres");
        FloatingActionButton x = (FloatingActionButton) findViewById(R.id.floatingActionButton);
        x.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(RecorridoActivity.this, MapsActivity.class);
                myIntent.putExtra("recorrido", recorrido);
                myIntent.putExtra("nombres", nombres);
                RecorridoActivity.this.startActivity(myIntent);
            }

        });
    }


}
