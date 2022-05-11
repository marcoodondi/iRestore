package com.example.gps;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.gps.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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

        checkUserPermission();

        //SETTINGS OF MAPS
        mMap.setMyLocationEnabled(true);
        mMap.getUiSettings().setAllGesturesEnabled(true);
        mMap.getUiSettings().setRotateGesturesEnabled(true);
        mMap.setBuildingsEnabled(true);
        mMap.getUiSettings().setMyLocationButtonEnabled(true);


        LocationManager lm = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        LatLng posizioneAttuale = new LatLng(getLatitude(location), getLongitude(location));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(posizioneAttuale, 13)); //zoom iniziale
        mMap.setOnInfoWindowClickListener(this::onMarkerClick);

        //primo marker
        mMap.addMarker(new MarkerOptions().position(new LatLng(44.6477813035214, 10.889741865446506)).title("BAR CORNI").snippet("⭐ ⭐")).setTag("Pasticceria, Tramezzini, Caffetteria, ecc...");

        //secondo marker
        mMap.addMarker(new MarkerOptions().position(new LatLng(44.63888667025113, 10.886487575399395)).title("BAR DUECENTODODICI").snippet("⭐ ⭐ ⭐")).setTag("Panini, Buffet vario, Pizza, ecc...");

        //terzo marker
        mMap.addMarker(new MarkerOptions().position(new LatLng(44.654958023373396, 10.90626121561688)).title("MCDONALD'S").snippet("⭐ ⭐ ⭐ ⭐")).setTag("Big MC, Chicken Nuggets, Double Chicken BBQ, Crispy MC Becon ecc...");

        //quarto marker
        mMap.addMarker(new MarkerOptions().position(new LatLng(44.65590478887225, 10.904486617634102)).title("TRATTORIA MADONNINA").snippet("⭐ ⭐ ⭐ ⭐ ⭐")).setTag("Vino Tipico, Gnocchetti, Tortellini, Tortelloni, ecc...");

    }

    public void onMarkerClick(Marker marker) { //cliccando il marker crea nuova pagina
        //cliccando parte una nuova activity
        Toast.makeText(MapsActivity.this, "Recupero Informazioni", Toast.LENGTH_LONG).show();

        Intent information = new Intent();
        information.setClass(getApplicationContext(), Information.class);
        information.putExtra("nome", marker.getTitle());
        information.putExtra("indirizzo", marker.getPosition());
        information.putExtra("cibi", marker.getTag().toString());
        information.putExtra("tipologia", marker.getSnippet());
        information.putExtra("telefono", marker.getTitle());
        startActivity(information);

    }

    public void checkUserPermission() { //permesso e controllo di posizione attiva sul telefono

        String[] perms = {"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION"};
        ActivityCompat.requestPermissions(this, perms, 200);

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
}