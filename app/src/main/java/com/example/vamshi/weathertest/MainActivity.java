package com.example.vamshi.weathertest;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static TextView place;
    static TextView temp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        place = (TextView) findViewById(R.id.place);
        temp = (TextView) findViewById(R.id.temp);

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        String provider = locationManager.getBestProvider(new Criteria(), false);
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        Location location = locationManager.getLastKnownLocation(provider);

        Double lat=location.getLatitude();
        Double lng=location.getLongitude();

        Weather getData = new Weather();
        getData.execute("http://api.openweathermap.org/data/2.5/weather?lat="+ String.valueOf(lat) + "&lon=" + String.valueOf(lng) +"&appid=36ac85ed8626ccd0b1d87cbc63bcbbfd");


    }

}
