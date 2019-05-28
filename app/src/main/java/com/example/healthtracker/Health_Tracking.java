package com.example.healthtracker;


import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Health_Tracking extends AppCompatActivity {
    DatabaseHelper myData;

    EditText edit_Tension, edit_blood_sugar, edit_heart_rate, edit_weight;
    Button btnAddData;
    Button btnViewGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_health__tracking);
    }
    public void graphActivity(View v){
        Intent intent = new Intent(Health_Tracking.this, graph1.class);
        startActivity(intent);
    }
    public void onButtonClick(View V){
        myData = new DatabaseHelper(this);

        edit_Tension = (EditText)findViewById(R.id.editText_tension);
        edit_blood_sugar = (EditText)findViewById(R.id.editText_blood);
        edit_heart_rate = (EditText)findViewById(R.id.editText_heart);
        edit_weight = (EditText)findViewById(R.id.editText_weight);
        if(edit_Tension.getText().toString().trim().equalsIgnoreCase("") ||
                edit_blood_sugar.getText().toString().trim().equalsIgnoreCase("")||
                edit_heart_rate.getText().toString().trim().equalsIgnoreCase("")||
                edit_weight.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(Health_Tracking.this, "Enter Value!", Toast.LENGTH_SHORT).show();
        }
        else{
            btnAddData = (Button)findViewById(R.id.button_add);
            btnViewGraph = (Button)findViewById(R.id.button_graph);

            AddData();
        }
    }
    //verileri kontrol eder ve SQL dosyasına insert eder
    public void AddData(){

        int tension_int  = Integer.parseInt(edit_Tension.getText().toString());
        int blood_sugar_int  = Integer.parseInt(edit_blood_sugar.getText().toString());
        int heart_rate_int  = Integer.parseInt(edit_heart_rate.getText().toString());
        int weight_int  = Integer.parseInt(edit_weight.getText().toString());
        if(tension_int<60 || tension_int>180 ||
                heart_rate_int <30 || heart_rate_int > 130 ||
                blood_sugar_int < 20 || blood_sugar_int > 200 ||
                weight_int < 10 || weight_int > 400){
            Toast.makeText(Health_Tracking.this,"Data not Inserted",Toast.LENGTH_LONG).show();
        }
        else {
            boolean isInserted = myData.insertData(
                    Integer.valueOf(edit_Tension.getText().toString()),
                    Integer.valueOf(edit_blood_sugar.getText().toString()),
                    Integer.valueOf(edit_heart_rate.getText().toString()),
                    Integer.valueOf(edit_weight.getText().toString()));
            Toast.makeText(Health_Tracking.this,"Data Inserted",Toast.LENGTH_LONG).show();
        }

    }
    public void goToMainMenu(View V) {
        Intent intent = new Intent(Health_Tracking.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
