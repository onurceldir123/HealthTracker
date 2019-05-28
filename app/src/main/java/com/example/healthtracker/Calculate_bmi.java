package com.example.healthtracker;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import java.text.DecimalFormat;

public class Calculate_bmi extends AppCompatActivity {
    double bmi_value = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_bmi);
    }
    public void goToMainMenu(View v) {
        Intent intent = new Intent(Calculate_bmi.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void goToBMI(View v) {
       bmiCalculator();
    }
    public void bmiCalculator() {
        EditText e1 = (EditText) findViewById(R.id.heightText);
        EditText e2 = (EditText) findViewById(R.id.weightText);
        if(e1.getText().toString().trim().equalsIgnoreCase("") || e2.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(Calculate_bmi.this, "Enter Value!", Toast.LENGTH_SHORT).show();
        }
        else{
            double height = Integer.parseInt(e1.getText().toString());
            double weight = Integer.parseInt(e2.getText().toString());

            if (weight > 300 || weight <= 0) { //controlling entry values
                Toast.makeText(Calculate_bmi.this, "Weight is not True", Toast.LENGTH_SHORT).show();
            } else if (height > 300 || height <= 0) { //controlling entry values
                Toast.makeText(Calculate_bmi.this, "Height is not True", Toast.LENGTH_SHORT).show();
            } else if (height < 300 || height > 0) {
                bmi_value = (weight / height / height) * 10000; // cm2 to m2
                //bmi_value = funFloor(bmi_value); // flooring
                printResult(bmi_value);
            }
        }
    }

    public void printResult(double bmi_value){
        AlertDialog.Builder builder = new AlertDialog.Builder(Calculate_bmi.this);
        builder.setMessage("BMI: " + (int)bmi_value);
        //what is your bmi
        if(bmi_value < 18.5){
            builder.setTitle("Underweight");
        }
        else if(18.5 < bmi_value && bmi_value <= 24.9){
            builder.setTitle("Normalweight");
        }
        else if(24.9 < bmi_value && bmi_value <= 29.9){
            builder.setTitle("Overweight");
        }
        else if(bmi_value >= 30){
            builder.setTitle("Obesity");
        }
        builder.setNegativeButton("Okey", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){}
        });
        builder.show();
    }
    private double funFloor(double bodyMass) {
        DecimalFormat df=new DecimalFormat("#.##");
        String dx=df.format(bodyMass);
        return Double.valueOf(dx);
    }
}

