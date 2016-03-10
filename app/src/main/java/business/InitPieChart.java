package business;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.widget.FrameLayout;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;
import java.util.ArrayList;

import club.glamajestic.healthtrack.DisplayList;
import club.glamajestic.healthtrack.StatsGui;

/**
 * Created by Khaled on 3/9/2016.
 */
public class InitPieChart {
    static private Stats StatsBus;
    Context ctx;
    FrameLayout f;
    PieChart pie;
    int mode;
    String[] putKeys;
    String[] putUnits;
    float[] putValues;
    float[] y;
    String[] x;
    public InitPieChart(final Context ctx, FrameLayout f, PieChart pie, final int charInitMode){
        StatsBus = new Stats();
        this.ctx = ctx;
        this.f = f;
        this.pie = pie;
        this.mode = charInitMode;
        initChart(y,x);
    }
    private void initChart(float[] y, String[] x){
        this.
        StatsBus.init(0);
        y = StatsBus.getValues();
        x = StatsBus.getKeys();

        pie = new PieChart(ctx);

        // add pie chart to main layout
        f.addView(pie);
        // configure pie chart
        pie.setUsePercentValues(true);
        pie.setDescription("");

        // enable hole and configure
        pie.setDrawHoleEnabled(true);
        pie.setHoleColorTransparent(true);
        pie.setHoleRadius(24);
        pie.setTransparentCircleRadius(27);
        pie.setCenterTextSize(25);
        pie.setDrawSliceText(false);
        pie.setDescription("");
        if(mode == 0 ||mode == 3 ) {
            pie.setCenterText("Day");
        }
        else if(mode == 1) {
            pie.setCenterText("Week");
        }
        else if(mode == 2) {
            pie.setCenterText("Month");
        }

        // enable rotation of the chart by touch
            pie.setRotationAngle(0);
            pie.setRotationEnabled(true);

        pie.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                if (mode == 3) {
                    openStats(ctx);
                } else {
                    if(StatsBus.getKeys()[e.getXIndex()].equals("Other")){
                        putKeys = StatsBus.getOtherKeys();
                        putUnits = StatsBus.getOtherUnits();
                        putValues = StatsBus.getOtherValues();
                    }
                    else{
                        putKeys = StatsDataAccess.getFoodNames(mode,StatsBus.getKeys()[e.getXIndex()]);
                        putUnits = StatsDataAccess.getFoodUnits(mode, StatsBus.getKeys()[e.getXIndex()]);
                        putValues = StatsDataAccess.getFoodValues(mode, StatsBus.getKeys()[e.getXIndex()]);
                    }
                    openList(ctx);
                }
            }

            @Override
            public void onNothingSelected() {

            }

        });
        addData(y,x,mode);

        // customize legends
        Legend l = pie.getLegend();
        l.setTextColor(Color.parseColor("#ffffff"));
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }
    /**
     * Fills the <code>PieChart</code> with data.
     *
     * @see PieChart
     */
    public void addData(float[] y, String[] x,int mode) {
        this.mode = mode;
        if(mode == 0 ||mode == 3 ) {
            pie.setCenterText("Day");
        }
        else if(mode == 1) {
            pie.setCenterText("Week");
        }
        else if(mode == 2) {
            pie.setCenterText("Month");
        }
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < y.length; i++) {
            yVals1.add(new Entry(y[i], i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < x.length; i++) {
            xVals.add(x[i]);
        }

        // create pie data set
        PieDataSet dataSet = new PieDataSet(yVals1, "Nutritional Elements");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

        // add many colors
        ArrayList<Integer> colors = new ArrayList<Integer>();

        for (int c : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.JOYFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.COLORFUL_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.LIBERTY_COLORS) {
            colors.add(c);
        }
        for (int c : ColorTemplate.PASTEL_COLORS) {
            colors.add(c);
        }

        colors.add(ColorTemplate.getHoloBlue());
        dataSet.setColors(colors);

        // instantiate pie data object now
        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.parseColor("#111111"));

        pie.setData(data);
        // undo all highlights
        pie.highlightValues(null);
        // update pie chart
        pie.invalidate();
    }
    private void openStats(Context ctx){
        Intent gameMode = new Intent(ctx, StatsGui.class);
        ctx.startActivity(gameMode);
    }
    private void openList(Context ctx){


        Intent gameMode = new Intent(ctx, DisplayList.class);
        gameMode.putExtra("keys",putKeys);
        gameMode.putExtra("units",putUnits);
        gameMode.putExtra("values",putValues);
        ctx.startActivity(gameMode);
    }
}
