package com.example.gps;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class Information extends AppCompatActivity {

    TextView ciboTipico;
    TextView nomeBar;
    TextView indirizzo;
    TextView telefono;
    TextView email;
    TextView tipologia;
    ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information);

        ciboTipico = findViewById(R.id.ciboTipico);
        nomeBar = findViewById(R.id.nomeBar);
        indirizzo = findViewById(R.id.indirizzo);
        telefono = findViewById(R.id.telefono);
        email = findViewById(R.id.email);
        tipologia = findViewById(R.id.tipologiaBar);



            Bundle b = getIntent().getExtras();
            Geocoder geocoder;

            List<Address> addresses = null;
            geocoder = new Geocoder(this, Locale.getDefault());


            LatLng coordinate = (LatLng) b.get("indirizzo");
            String cibi = getIntent().getStringExtra("cibi");
            String grades = getIntent().getStringExtra("tipologia");
            String conversione = null;

            if (grades.equals("⭐")) conversione = "Distributore automatico";
            if (grades.equals("⭐ ⭐")) conversione = "Bar scolastico";
            if (grades.equals("⭐ ⭐ ⭐")) conversione = "Bar standard";
            if (grades.equals("⭐ ⭐ ⭐ ⭐")) conversione = "Ristorante/Fast Food";
            if (grades.equals("⭐ ⭐ ⭐ ⭐ ⭐")) conversione = "Ristorante";


        try {
            addresses = geocoder.getFromLocation(coordinate.latitude, coordinate.longitude, 1); // converte le coordinate in un indirizzo
        } catch (IOException e) {
            e.printStackTrace();
        }
        String fullAddress = addresses.get(0).getAddressLine(0); // ottiene l'indirizzo completo calcolato nella riga 46

        nomeBar.setText(getIntent().getStringExtra("nome")); //setto il nome

        if(nomeBar.getText().equals("BAR CORNI"))
        {
            telefono.setText("059 291 7000");
            email.setText("https://www.istitutocorni.edu.it/");
        }

        else if(nomeBar.getText().equals("BAR DUECENTODODICI"))
        {
            telefono.setText("059 356 443");
            email.setText("m.facebook.com/212duecentododici/");
        }
        else if(nomeBar.getText().equals("MCDONALD'S"))
        {
            telefono.setText("059 822 133");
            email.setText("http://www.mcdonalds.it/");
        }
        else if(nomeBar.getText().equals("TRATTORIA MADONNINA"))
        {
            telefono.setText("351 928 5621");
            email.setText("http://www.trattoriamadonnina.it/");
        }


        indirizzo.setText(fullAddress); //assegno indirizzo
        tipologia.setText(conversione); //assegno tipologia
        ciboTipico.setText(cibi); //assegno cipi tipici

    }
}