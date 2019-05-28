//Onur Mert Çeldir May 2019

package com.example.healthtracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public void goToBodyMassCalculator(View v){
        Intent intent = new Intent(MainActivity.this, Calculate_bmi.class);
        startActivity(intent);
    }
    public void goToEstimatedEnergyCalculator(View v) {
        Intent intent = new Intent(MainActivity.this, Calculate_eer.class);
        startActivity(intent);
    }
    public void goToHealthTracking(View v) {
        Intent intent = new Intent(MainActivity.this, Health_Tracking.class);
        startActivity(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
