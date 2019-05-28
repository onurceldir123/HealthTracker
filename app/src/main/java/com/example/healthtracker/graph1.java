package com.example.healthtracker;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class graph1 extends AppCompatActivity {

    private static final  String TAG = "GraphActivity";
    private LineChart mChart,mChart2,mChart3,mChart4;
    DatabaseHelper myData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_graph1);

        myData = new DatabaseHelper(this);

        mChart = (LineChart) findViewById(R.id.chart1);
        mChart2 = (LineChart) findViewById(R.id.chart2);
        mChart3 = (LineChart) findViewById(R.id.chart3);
        mChart4 = (LineChart) findViewById(R.id.chart4);


        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        mChart2.setDragEnabled(true);
        mChart2.setScaleEnabled(false);
        mChart3.setDragEnabled(true);
        mChart3.setScaleEnabled(false);
        mChart4.setDragEnabled(true);
        mChart4.setScaleEnabled(false);

        Cursor res = myData.getAlldata();
        StringBuffer buffer = new StringBuffer();

        ArrayList<Entry> yValuesTension = new ArrayList<>();
        ArrayList<Entry> yValuesSugar = new ArrayList<>();
        ArrayList<Entry> yValuesHeartRate = new ArrayList<>();
        ArrayList<Entry> yValuesWeight = new ArrayList<>();

        while (res.moveToNext()) {
            yValuesTension.add(new Entry( Integer.parseInt(res.getString(0)),Integer.parseInt(res.getString(1))));
            yValuesSugar.add(new Entry( Integer.parseInt(res.getString(0)),Integer.parseInt(res.getString(2))));
            yValuesHeartRate.add(new Entry( Integer.parseInt(res.getString(0)),Integer.parseInt(res.getString(3))));
            yValuesWeight.add(new Entry( Integer.parseInt(res.getString(0)),Integer.parseInt(res.getString(4))));
        }

        LineDataSet setTension = new LineDataSet(yValuesTension,"Tension (mm/hg)");
        setTension.setFillAlpha(110);
        setTension.setColor(Color.rgb(0, 240, 0));
        ArrayList<ILineDataSet> dataSets =new ArrayList<>();
        dataSets.add(setTension);

        LineDataSet setSugar = new LineDataSet(yValuesSugar,"Blood Sugar (mg/dl)");
        setSugar.setFillAlpha(110);
        setSugar.setColor(Color.rgb(240, 0, 0));
        ArrayList<ILineDataSet> dataSets1 =new ArrayList<>();
        dataSets1.add(setSugar);

        LineDataSet setHeartRate = new LineDataSet(yValuesHeartRate,"HeartRate (beats)");
        setHeartRate.setFillAlpha(110);
        setHeartRate.setColor(Color.rgb(0, 0, 240));
        ArrayList<ILineDataSet> dataSets2 =new ArrayList<>();
        dataSets2.add(setHeartRate);

        LineDataSet setWeight = new LineDataSet(yValuesWeight,"Weight (kg)");
        setWeight.setFillAlpha(110);
        setWeight.setColor(Color.rgb(0, 0, 0));
        ArrayList<ILineDataSet> dataSets3 =new ArrayList<>();
        dataSets3.add(setWeight);

        LineData data = new LineData(dataSets);
        LineData data1 = new LineData(dataSets1);
        LineData data2 = new LineData(dataSets2);
        LineData data3 = new LineData(dataSets3);

        mChart.setData(data);
        mChart2.setData(data1);
        mChart3.setData(data2);
        mChart4.setData(data3);
    }
    public void goToMainMenu(View v) {
        Intent intent = new Intent(graph1.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
    public void goToBack(View v) {
        Intent intent = new Intent(graph1.this, Health_Tracking.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
