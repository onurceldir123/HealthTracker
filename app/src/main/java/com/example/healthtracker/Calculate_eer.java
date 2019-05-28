package com.example.healthtracker;

import android.app.Activity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import android.content.Context;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;

import android.widget.Toast;


import java.util.Arrays;
import java.util.List;

public class Calculate_eer extends AppCompatActivity {
    double eer_value;

    private Context mContext;
    private Activity mActivity;
    private RelativeLayout mRelativeLayout;
    private ListView mListView;

    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private Button btnDisplay;
    double gender_c, weight_c, height_c, age_c = 19, pa_c; // cinsiyet katsayısı, ağırlık, boy, yaş, fiziksel aktivite
    boolean man;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculate_eer);
        functionSelector();
        NumberPicker np = (NumberPicker) findViewById(R.id.np);
        np.setMinValue(19);
        np.setMaxValue(99);
        np.setWrapSelectorWheel(true);
        np.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal){
                age_c = newVal;
            }
        });
    }
    public void onButtonClick(View v) {
        //man, age_c, pa_c,
        addListenerOnButton();
        functionSelector();
        EditText e1 = (EditText) findViewById(R.id.heightText1);
        EditText e2 = (EditText) findViewById(R.id.weightText7);
        if(e1.getText().toString().trim().equalsIgnoreCase("") || e2.getText().toString().trim().equalsIgnoreCase("")){
            Toast.makeText(Calculate_eer.this, "Enter Value!", Toast.LENGTH_SHORT).show();
        }
        else{
            height_c = Integer.parseInt(e1.getText().toString());
            weight_c = Integer.parseInt(e2.getText().toString());

            if (weight_c > 300 || weight_c <= 0) { //controlling entry values
                Toast.makeText(Calculate_eer.this, " Weight is not True", Toast.LENGTH_SHORT).show();
            } else if (height_c > 300 || height_c <= 0) { //controlling entry values
                Toast.makeText(Calculate_eer.this, " Height is not True", Toast.LENGTH_SHORT).show();
            }
            else if((height_c < 300 || height_c > 0)&&(weight_c < 300 || weight_c >= 0)) {
                //physcial activity calculator this met
                if (man == true && pa_c == 1) {
                    pa_c = 1;
                } else if (man == true && pa_c == 2) {
                    pa_c = 1.11;
                } else if (man == true && pa_c == 3) {
                    pa_c = 1.25;
                } else if (man == true && pa_c == 4) {
                    pa_c = 1.45;
                } else if (man == false && pa_c == 1) {
                    pa_c = 1;
                } else if (man == false && pa_c == 2) {
                    pa_c = 1.12;
                } else if (man == false && pa_c == 3) {
                    pa_c = 1.27;
                } else if (man == false && pa_c == 4) {
                    pa_c = 1.45;
                }

                //eer Calculator(man, pa_c, age_c, weight_c, height_c);
                eer_value = eerCalculator(man, pa_c, age_c, weight_c, height_c);

                printResult(eer_value);
            }
        }
    }
    public void printResult(double eer_value){
        AlertDialog.Builder builder = new AlertDialog.Builder(Calculate_eer.this);
        builder.setTitle("Estimated energy Requirement: ");
        builder.setMessage((int)eer_value + " kcal/day");
        builder.setNegativeButton("Okey", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){}
        });
        builder.show();
    }
    private double eerCalculator(boolean man, double pa_c, double age_c, double weight_c, double height_c){
        double eer_value;
        if(man){
            eer_value = 662-(9.53*age_c)+ (pa_c*((15.91*weight_c)+((539.6*height_c))/100));
            return eer_value;
        }
        else{
            eer_value = 354-(6.91*age_c)+ (pa_c*((9.36*weight_c)+((726*height_c)/100)));
            return eer_value;
        }
    }
    public void goToMainMenu(View v) {
        Intent intent = new Intent(Calculate_eer.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void addListenerOnButton() {
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
                // get selected radio button from radioGroup
                int selectedId = radioSexGroup.getCheckedRadioButtonId();
                // find the radiobutton by returned id
                radioSexButton = (RadioButton) findViewById(selectedId);
                String selection = radioSexButton.getText().toString();
                if(selection.equals("Male")){
                    man = true;
                }
                else if(selection.equals("Female")){
                    man = false;
                }
                //Toast.makeText(Calculate_eer.this, radioSexButton.getText(), Toast.LENGTH_SHORT).show();
    }
    public void functionSelector(){
        mContext = getApplicationContext();
        mActivity = Calculate_eer.this;
        mListView = (ListView) findViewById(R.id.lv);
        List<String> trees = Arrays.asList(
                "Sedentary",
                "Low Active",
                "Active",
                "Very Active"
        );
        ArrayAdapter<String> adapter = new ArrayAdapter(
                this,
                android.R.layout.simple_list_item_1,
                trees
        );
        mListView.setAdapter(adapter);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String item = (String) adapterView.getItemAtPosition(i);
                switch (item) {
                    case "Sedentary" : pa_c=1; break;
                    case "Low Active" : pa_c=2; break;
                    case "Active" : pa_c=3; break;
                    case "Very Active" : pa_c=4; break;
                }
            }
        });
    }
}
