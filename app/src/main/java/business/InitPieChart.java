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
import club.glamajestic.healthtrack.StatsActivity;


//Testing of this class
public class InitPieChart implements  ApplicationConstants {
    private static Stats STATS_BUS;
    Context ctx;
    FrameLayout f;
    PieChart pie;
    int mode;
    String[] putKeys;
    String[] putUnits;
    float[] putValues;
    String[] putString;
    float[] y;
    String[] x;
    ClickSound playSound;
    StatsDataAccess dataAccess = new StatsDataAccess();
    public InitPieChart(final Context ctx, FrameLayout f, PieChart pie, final int charInitMode){
        STATS_BUS = new Stats();
        this.ctx = ctx;
        this.f = f;
        this.pie = pie;
        this.mode = charInitMode;
        initChart(y,x);
        playSound = new ClickSound(ctx);

    }
    private void initChart(float[] y, String[] x){
        STATS_BUS.init(0);
        y = STATS_BUS.getValues();
        x = STATS_BUS.getKeys();

        pie = new PieChart(ctx);

        f.addView(pie);

        pie.setUsePercentValues(true);
        pie.setDescription("");

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


        pie.setRotationAngle(0);
        pie.setRotationEnabled(true);

        pie.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                playSound.play();
                if (mode == 3) {
                    openStats(ctx);
                } else {
                    if(STATS_BUS.getKeys()[e.getXIndex()].equals("Other")){
                        putKeys = STATS_BUS.getOtherKeys();
                        putUnits = STATS_BUS.getOtherUnits();
                        putValues = STATS_BUS.getOtherValues();
                        putString = null;
                    }
                    else {
                        System.out.println("Getting: " + STATS_BUS.getKeys()[e.getXIndex()]);
                        putString = dataAccess.getFoods(mode, STATS_BUS.getKeys()[e.getXIndex()]);
                        for (int x = 0; x < putString.length; x++){
                            System.out.println("Got: " + putString[x]);
                        }
                    }
                    openList(ctx);
                }
            }

            @Override
            public void onNothingSelected() {

            }

        });
        addData(y,x,mode);

        Legend l = pie.getLegend();
        l.setTextColor(Color.parseColor("#ffffff"));
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    public void addData(float[] y, String[] x,int mode) {
        this.mode = mode;
        if (mode == 0 || mode == 3) {
            pie.setCenterText("Day");
        } else if (mode == 1) {
            pie.setCenterText("Week");
        } else if (mode == 2) {
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

        createPieDataSet(yVals1, xVals);
    }

    public void createPieDataSet(ArrayList<Entry> yVals1, ArrayList<String> xVals) {
        PieDataSet dataSet = new PieDataSet(yVals1, "Nutritional Elements");
        dataSet.setSliceSpace(3);
        dataSet.setSelectionShift(5);

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

        PieData data = new PieData(xVals, dataSet);
        data.setValueFormatter(new PercentFormatter());
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.parseColor("#111111"));

        pie.setData(data);

        pie.highlightValues(null);

        pie.invalidate();
    }

    private void openStats(Context ctx){
        Intent gameMode = new Intent(ctx, StatsActivity.class);
        ctx.startActivity(gameMode);
    }

    private void openList(Context ctx){
        Intent gameMode = new Intent(ctx, DisplayList.class);
        gameMode.putExtra("keys",putKeys);
        gameMode.putExtra("units",putUnits);
        gameMode.putExtra("values",putValues);
        gameMode.putExtra("foods",putString);
        ctx.startActivity(gameMode);
    }
}
