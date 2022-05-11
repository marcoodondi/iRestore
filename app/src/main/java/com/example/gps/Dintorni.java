package com.example.gps;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.SphericalUtil;

public class Dintorni extends AppCompatActivity {

    public final static LatLng CORNI = new LatLng(44.6477813035214, 10.889741865446506);
    public final static LatLng DUECENTODODICI = new LatLng(44.63888667025113, 10.886487575399395);
    public final static LatLng MADONNINA = new LatLng(44.654958023373396, 10.90626121561688);
    public final static LatLng MCDONALDS = new LatLng(44.65590478887225, 10.904486617634102);

    public Button start;
    public TextView distanza1;
    public TextView distanza2;
    public TextView distanza3;
    public TextView distanza4;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dintorni);

        start = findViewById(R.id.startMap);
        distanza1 = findViewById(R.id.distC);
        distanza2 = findViewById(R.id.distD);
        distanza3 = findViewById(R.id.distM);
        distanza4 = findViewById(R.id.distMAD);


        checkUserPermission();

        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
        LatLng posizioneAttuale = new LatLng(getLatitude(location), getLongitude(location));

        //CALCOLO DISTANZE

        double P1 = SphericalUtil.computeDistanceBetween(posizioneAttuale, CORNI);
        double P2 = SphericalUtil.computeDistanceBetween(posizioneAttuale, DUECENTODODICI);
        double P3 = SphericalUtil.computeDistanceBetween(posizioneAttuale, MADONNINA);
        double P4 = SphericalUtil.computeDistanceBetween(posizioneAttuale, MCDONALDS);

        distanza1.setText(String.valueOf(P1));
        distanza2.setText(String.valueOf(P2));
        distanza3.setText(String.valueOf(P3));
        distanza4.setText(String.valueOf(P4));


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent1 = new Intent();
                intent1.setClass(getApplicationContext(), MapsActivity.class);
                startActivity(intent1);
            }
        });
    }

    public double getLatitude(Location location) { //serve per ottenere la latituide

        try {
            return location.getLatitude();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public double getLongitude(Location location) { //serve per ottenere la longitudine

        try {
            return location.getLongitude();
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }

    public void checkUserPermission() { //permesso e controllo di posizione attiva sul telefono

        String[] perms = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        ActivityCompat.requestPermissions(this, perms, 200);
    }
}