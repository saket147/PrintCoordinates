package com.example.saket.printcoordinates;


import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=(TextView)findViewById(R.id.txtLocation);
        LocationManager locationManager=(LocationManager) getSystemService(LOCATION_SERVICE);
        List<String> providers=locationManager.getAllProviders();
        Log.d("Provider",providers.toString());
        try {

            TelephonyManager tm= (TelephonyManager) getSystemService(TELEPHONY_SERVICE);

            SmsManager sms=SmsManager.getDefault();
            sms.sendTextMessage("07042215128",null,"Hello",null,null);
            Toast.makeText(this,"Sent",Toast.LENGTH_LONG).show();


            if(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION)== PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION)==PackageManager.PERMISSION_GRANTED)
            {
                Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                double latitude= location.getLatitude();
                double longitude= location.getLongitude();
                tv.setText(String.valueOf(latitude)+" "+String.valueOf(longitude));
                Log.d("Longitude",String.valueOf(longitude));
                Log.d("Latitude",String.valueOf(latitude));
                Toast.makeText(this,"Hello",Toast.LENGTH_LONG).show();
            }
            else
            {
                Toast.makeText(this,"Hi",Toast.LENGTH_LONG).show();
                Log.e("Permission","Location Service might be disabled");
            }
        }
        catch (Exception ex)
        {
            Toast.makeText(this,ex.getMessage(),Toast.LENGTH_LONG).show();
            Log.e("Exception",ex.getMessage());
        }
    }
}
