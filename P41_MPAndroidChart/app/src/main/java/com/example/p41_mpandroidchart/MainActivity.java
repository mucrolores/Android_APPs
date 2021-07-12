package com.example.p41_mpandroidchart;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class MainActivity extends AppCompatActivity {

    private Button PauseButton;
    private LineChart DataLineChart;

    private LineDataSet DataXLineDataSet, DataYLineDataSet, DataZLineDataSet;
    private LineData DataLineData;

    private Queue<Float> x_DataQueue = new LinkedList<>();
    private Queue<Float> y_DataQueue = new LinkedList<>();
    private Queue<Float> z_DataQueue = new LinkedList<>();

    private Float[] tmpX, tmpY, tmpZ;

    private int DATA_LINE_CHART_WINDOW_SIZE = 4096;
    private int QueueMaxSize = 4096;
    private boolean Pause = false;
    private long TimeTag;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Find_UI();
        SetPauseButton();
        SetupDataLineChart();

        for(int i=0;i<QueueMaxSize;i++){
            x_DataQueue.add(0f);
            y_DataQueue.add(0f);
            z_DataQueue.add(0f);
        }

        Thread GenerateDataThread = new Thread(() -> {
            float X,Y,Z;
            TimeTag = System.currentTimeMillis();
            for(int Counter = 0;!Pause;Counter++)
            {
                X = (float) Math.random()*10;
                Y = (float) Math.random()*10;
                Z = (float) Math.random()*10;

                x_DataQueue.poll();
                y_DataQueue.poll();
                z_DataQueue.poll();

                x_DataQueue.add(X);
                y_DataQueue.add(Y);
                z_DataQueue.add(Z);

                if(Counter%512 == 0) {
                    tmpX = x_DataQueue.toArray(new Float[0]);
                    tmpY = y_DataQueue.toArray(new Float[0]);
                    tmpZ = z_DataQueue.toArray(new Float[0]);

                    DataLineChartSetData(tmpX,tmpY,tmpZ);
                }


                try {
                    Thread.sleep(0,100000);
                } catch (final InterruptedException interruptException) {
                    runOnUiThread(() -> {
                        Toast.makeText(this, interruptException.toString(), Toast.LENGTH_SHORT).show();
                    });
                }
                //Counter = 0;
            }

        });

        GenerateDataThread.start();

    }

    private void Find_UI() {
        PauseButton = findViewById(R.id.PauseButton_ID);
        DataLineChart = findViewById(R.id.DataLineChart_ID);
    }
    private void SetPauseButton() {
        PauseButton.setOnClickListener( (v) -> {
            Pause = true;
            Toast.makeText(this, "PAUSED", Toast.LENGTH_SHORT).show();
        });
    }
    private void SetupDataLineChart() {
        /*
            Set up data chart
        */
        DataLineChart.getDescription().setEnabled(false);
        // enable touch gestures
        DataLineChart.setTouchEnabled(true);
        DataLineChart.setDragDecelerationFrictionCoef(0.9f);
        // enable scaling and dragging
        DataLineChart.setDragEnabled(true);
        DataLineChart.setScaleEnabled(true);
        DataLineChart.setDrawGridBackground(false);
        DataLineChart.setHighlightPerDragEnabled(true);
        // set an alternative background color
        DataLineChart.setBackgroundColor(Color.WHITE);
        DataLineChart.setViewPortOffsets(0f, 0f, 0f, 0f);

        XAxis DataXAxis = DataLineChart.getXAxis();
        DataXAxis.setPosition(XAxis.XAxisPosition.BOTTOM_INSIDE);
        DataXAxis.setTextSize(10f);
        DataXAxis.setTextColor(Color.WHITE);
        DataXAxis.setDrawAxisLine(true);
        DataXAxis.setDrawGridLines(true);
        DataXAxis.setTextColor(Color.rgb(255, 192, 56));
        DataXAxis.setCenterAxisLabels(false);
        DataXAxis.setGranularity(1f);

        YAxis DataLeftAxis = DataLineChart.getAxisLeft();
        DataLeftAxis.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        DataLeftAxis.setAxisMinimum(-5f);
        DataLeftAxis.setAxisMaximum(15f);
        DataLeftAxis.setDrawGridLines(true);
        DataLeftAxis.setGranularityEnabled(true);
        DataLeftAxis.setGranularity(0.1f);
        DataLeftAxis.setTextColor(Color.rgb(255, 192, 56));
        DataLineChart.getAxisRight().setDrawGridLines(false);

        DataXLineDataSet = new LineDataSet(null, "X");
        DataXLineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        DataXLineDataSet.setColor(Color.rgb(255, 51, 133));
        DataXLineDataSet.setValueTextColor(ColorTemplate.getHoloBlue());
        DataXLineDataSet.setLineWidth(0.5f);
        DataXLineDataSet.setDrawCircles(false);
        DataXLineDataSet.setDrawValues(false);
        DataXLineDataSet.setFillAlpha(65);
        DataXLineDataSet.setFillColor(ColorTemplate.getHoloBlue());
        DataXLineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
        DataXLineDataSet.setDrawCircleHole(false);

        DataYLineDataSet = new LineDataSet(null, "Y");
        DataYLineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        DataYLineDataSet.setColor(Color.rgb(31, 173, 31));
        DataYLineDataSet.setValueTextColor(ColorTemplate.getHoloBlue());
        DataYLineDataSet.setLineWidth(0.5f);
        DataYLineDataSet.setDrawCircles(false);
        DataYLineDataSet.setDrawValues(false);
        DataYLineDataSet.setFillAlpha(65);
        DataYLineDataSet.setFillColor(ColorTemplate.getHoloBlue());
        DataYLineDataSet.setHighLightColor(Color.rgb(244, 117, 117));
        DataYLineDataSet.setDrawCircleHole(false);

        DataZLineDataSet = new LineDataSet(null, "Z");
        DataZLineDataSet.setAxisDependency(YAxis.AxisDependency.LEFT);
        DataZLineDataSet.setColor(Color.rgb(153, 194, 255));
        DataZLineDataSet.setValueTextColor(ColorTemplate.getHoloBlue());
        DataZLineDataSet.setLineWidth(0.5f);
        DataZLineDataSet.setDrawCircles(false);
        DataZLineDataSet.setDrawValues(false);
        DataZLineDataSet.setFillAlpha(65);
        DataZLineDataSet.setFillColor(ColorTemplate.getHoloBlue());
        DataZLineDataSet.setHighLightColor(Color.rgb(153, 194, 255));
        DataZLineDataSet.setDrawCircleHole(false);

        // create a data object with the data sets
        ArrayList<ILineDataSet> DataDataSets = new ArrayList<>();
        DataDataSets.add(DataXLineDataSet);
        DataDataSets.add(DataYLineDataSet);
        DataDataSets.add(DataZLineDataSet);

        DataLineData = new LineData(DataDataSets);
        // set data
        DataLineChart.setData(DataLineData);
    }

    public void DataLineChartSetData(Float[] X_InputData,Float[] Y_InputData,Float[] Z_InputData) {
        DataXLineDataSet.clear();
        DataYLineDataSet.clear();
        DataZLineDataSet.clear();
        for(int i=0;i<DATA_LINE_CHART_WINDOW_SIZE;i++)
        {
            DataXLineDataSet.addEntry(new Entry(i,X_InputData[i]));
            DataYLineDataSet.addEntry(new Entry(i,Y_InputData[i]));
            DataZLineDataSet.addEntry(new Entry(i,Z_InputData[i]));
        }
        //DataLineChart.resetTracking();

        DataLineChart.resetTracking();


        DataLineData.notifyDataChanged(); // let the data know a dataSet changed
        DataLineChart.notifyDataSetChanged(); // let the chart know it's data changed
        DataLineChart.invalidate();
    }


}