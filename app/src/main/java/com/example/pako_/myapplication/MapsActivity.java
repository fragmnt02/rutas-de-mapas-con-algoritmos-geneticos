package com.example.pako_.myapplication;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.ButtCap;
import com.google.android.gms.maps.model.CustomCap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RoundCap;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLng[] ubicaciones=new LatLng[96];
    ArrayList<Integer> recorrido;
    String[] nombres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        recorrido=getIntent().getExtras().getIntegerArrayList("recorrido");
        crearUbicaciones();
        nombres=getIntent().getExtras().getStringArray("nombres");
    }

    protected void crearUbicaciones(){
        ubicaciones[0]=new LatLng(17.77235,-91.2796);
        ubicaciones[1]=new LatLng(17.34677,-93.59173);
        ubicaciones[2]=new LatLng(17.70027,-92.26249);
        ubicaciones[3]=new LatLng(17.80944,-91.5366);
        ubicaciones[4]=new LatLng(18.43763,-93.23856);
        ubicaciones[5]=new LatLng(17.77761,-92.60884);
        ubicaciones[6]=new LatLng(17.97198,-93.60011);
        ubicaciones[7]=new LatLng(17.45306,-91.42875);
        ubicaciones[8]=new LatLng(18.41144,-93.17353);
        ubicaciones[9]=new LatLng(17.44697,-91.46019);
        ubicaciones[10]=new LatLng(18.41144,-93.17353);
        ubicaciones[11]=new LatLng(18.11361,-93.29833);
        ubicaciones[12]=new LatLng(17.62159,-92.47276);
        ubicaciones[13]=new LatLng(17.76331,-91.32526);
        ubicaciones[14]=new LatLng(18.40698,-92.64703);
        ubicaciones[15]=new LatLng(18.43898,-93.22358);
        ubicaciones[16]=new LatLng(17.85563,-91.78062);
        ubicaciones[17]=new LatLng(18.42722,-93.11305);
        ubicaciones[18]=new LatLng(17.88218,-92.48253);
        ubicaciones[19]=new LatLng(17.57046,-92.92258);
        ubicaciones[20]=new LatLng(18.26115,-93.22168);
        ubicaciones[21]=new LatLng(18.29218,-93.86934);
        ubicaciones[22]=new LatLng(17.44326,-92.75959);
        ubicaciones[23]=new LatLng(18.07215,-93.17124);
        ubicaciones[24]=new LatLng(18.14999,-92.86666);
        ubicaciones[25]=new LatLng(17.726,-91.79476);
        ubicaciones[26]=new LatLng(18.43113,-93.1894);
        ubicaciones[27]=new LatLng(18.38063,-93.61952);
        ubicaciones[28]=new LatLng(18.42527,-93.15083);
        ubicaciones[29]=new LatLng(17.71111,-91.70555);
        ubicaciones[30]=new LatLng(18.24772,-92.83215);
        ubicaciones[31]=new LatLng(18.33017,-93.53428);
        ubicaciones[32]=new LatLng(17.8706,-91.62082);
        ubicaciones[33]=new LatLng(17.9224,-91.17012);
        ubicaciones[34]=new LatLng(17.62944,-91.59888);
        ubicaciones[35]=new LatLng(18.21087,-94.01611);
        ubicaciones[36]=new LatLng(17.74091,-91.76605);
        ubicaciones[37]=new LatLng(17.82138,-93.41666);
        ubicaciones[38]=new LatLng(18.40668,-92.64705);
        ubicaciones[39]=new LatLng(17.38079,-92.7503);
        ubicaciones[40]=new LatLng(17.37405,-93.64714);
        ubicaciones[41]=new LatLng(18.53339,-92.64605);
        ubicaciones[42]=new LatLng(18.33605,-93.04807);
        ubicaciones[43]=new LatLng(18.15711,-93.25787);
        ubicaciones[44]=new LatLng(17.55241,-92.99791);
        ubicaciones[45]=new LatLng(17.54713,-92.94542);
        ubicaciones[46]=new LatLng(17.83204,-93.39181);
        ubicaciones[47]=new LatLng(18.31175,-93.06956);
        ubicaciones[48]=new LatLng(18.08818,-93.25288);
        ubicaciones[49]=new LatLng(17.82828,-92.85708);
        ubicaciones[50]=new LatLng(17.71927,-92.81119);
        ubicaciones[51]=new LatLng(18.17479,-93.07014);
        ubicaciones[52]=new LatLng(18.09108,-92.13643);
        ubicaciones[53]=new LatLng(17.4421,-92.75928);
        ubicaciones[54]=new LatLng(17.44418,-92.7618);
        ubicaciones[55]=new LatLng(17.89999,-92.36666);
        ubicaciones[56]=new LatLng(18.10394,-94.03945);
        ubicaciones[57]=new LatLng(17.75832,-91.80502);
        ubicaciones[58]=new LatLng(17.87795,-93.79177);
        ubicaciones[59]=new LatLng(17.57046,-92.92258);
        ubicaciones[60]=new LatLng(17.59033,-91.29149);
        ubicaciones[61]=new LatLng(17.7607,-92.5936);
        ubicaciones[62]=new LatLng(17.33771,-93.59914);
        ubicaciones[63]=new LatLng(18.36307,-93.67097);
        ubicaciones[64]=new LatLng(17.37848,-92.74805);
        ubicaciones[65]=new LatLng(18.16974,-93.01506);
        ubicaciones[66]=new LatLng(17.38083,-92.74999);
        ubicaciones[67]=new LatLng(18.35244,-92.34304);
        ubicaciones[68]=new LatLng(18.40061,-93.21271);
        ubicaciones[69]=new LatLng(17.99606,-92.92807);
        ubicaciones[70]=new LatLng(18.43039,-92.99493);
        ubicaciones[71]=new LatLng(18.61525,-92.68629);
        ubicaciones[72]=new LatLng(18.49038,-92.78648);
        ubicaciones[73]=new LatLng(18.43369,-92.92787);
        ubicaciones[74]=new LatLng(17.506,-91.55212);
        ubicaciones[75]=new LatLng(18.40557,-93.20276);
        ubicaciones[76]=new LatLng(17.98228,-92.92945);
        ubicaciones[77]=new LatLng(17.33556,-91.15883);
        ubicaciones[78]=new LatLng(17.95638,-91.885);
        ubicaciones[79]=new LatLng(17.49877,-91.13956);
        ubicaciones[80]=new LatLng(18.4424,-92.79962);
        ubicaciones[81]=new LatLng(17.76908,-91.29814);
        ubicaciones[82]=new LatLng(17.59698,-92.82655);
        ubicaciones[83]=new LatLng(17.46221,-92.77796);
        ubicaciones[84]=new LatLng(18.01015,-92.97209);
        ubicaciones[85]=new LatLng(17.56093,-92.95198);
        ubicaciones[86]=new LatLng(18.16936,-93.01935);
        ubicaciones[87]=new LatLng(18.17641,-93.06271);
        ubicaciones[88]=new LatLng(17.47266,-91.42505);
        ubicaciones[89]=new LatLng(17.37842,-92.74677);
        ubicaciones[90]=new LatLng(17.63418,-91.60009);
        ubicaciones[91]=new LatLng(18.17637,-93.90966);
        ubicaciones[92]=new LatLng(17.44562,-92.7616);
        ubicaciones[93]=new LatLng(18.28273,-93.33339);
        ubicaciones[94]=new LatLng(17.98945,-92.9475);
        ubicaciones[95]=new LatLng(18.00054,-92.80409);

    }

    protected void mostrarUbicaciones(){
        for (int i=0;i<recorrido.size();i++){
            mMap.addMarker(new MarkerOptions().position(ubicaciones[recorrido.get(i)]).title(nombres[recorrido.get(i)]));
            if(i!=0){mMap.addPolyline(new PolylineOptions()
                    .add(ubicaciones[recorrido.get(i-1)], ubicaciones[recorrido.get(i)])
                    .width(5)
                    .color(Color.RED).endCap(new ButtCap()));}
        }

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        mostrarUbicaciones();
        mMap.setMinZoomPreference((float) 6.7);
        LatLng tabasco = new LatLng(17.9473787,-93.6801852);
        mMap.moveCamera(CameraUpdateFactory.newLatLng(tabasco));
        // Add a marker in Sydney and move the camera
        //LatLng sydney = new LatLng(-34, 151);
        //mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
