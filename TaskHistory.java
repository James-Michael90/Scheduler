package com.visione.taskreminder.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.visione.taskreminder.R;

import java.util.ArrayList;

public class TaskHistory extends AppCompatActivity {
    BarChart barChart;
    LineChart lineChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_history);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        barChart = findViewById(R.id.bar_chart);
        lineChart = findViewById(R.id.line_chart);
        barChart.getDescription().setEnabled(false);

        setBarData(10);
    }

    private void setLineData(int count, int range) {
        ArrayList<Entry> yValues = new ArrayList<>();
        for(int i = 0 ; i < count ; i++){
            float value = (float) (Math.random()*range);
            yValues.add(new BarEntry(i,  (int) value));
        }
        LineDataSet lineDataSet = new LineDataSet(yValues, "Data set");
        lineDataSet.setColor(getResources().getColor(R.color.colorAccent));
        lineDataSet.setDrawCircles(true);
        lineDataSet.setLineWidth(3f);
        LineData lineData = new LineData(lineDataSet);
        lineChart.setData(lineData);
        lineChart.animateX(1000);
        barChart.setVisibility(View.GONE);
        lineChart.setVisibility(View.VISIBLE);
    }

    private void setBarData(int count) {
        ArrayList<BarEntry> yValues = new ArrayList<>();

        for(int i = 0 ; i < count ; i++){
            float value = (float) (Math.random()*100);
            yValues.add(new BarEntry(i, (int) value));
        }
        BarDataSet barDataSet = new BarDataSet(yValues, "Data Set");
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        barDataSet.setDrawValues(true);

        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        barChart.invalidate();
        barChart.animateY(1000);
        barChart.setFitBars(true);
        barChart.setVisibility(View.VISIBLE);
        lineChart.setVisibility(View.GONE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.chart_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_bar_chart) {
            //show bar chart
            setBarData(10);
            return true;
        } else if(id == R.id.action_line_chart){
            //show line chart
            setLineData(10, 100);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
