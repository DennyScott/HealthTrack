package club.glamajestic.healthtrack;

import business.Stats;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class StatsGui extends Activity {

    // 0 = day, 1 = week, 2 = month
    // day by default
    private int mode = 0;

    private FrameLayout stats;
    private Stats StatsBus = new Stats();
    private PieChart chart;
    private Button dayButton;
    private Button weekButton;
    private Button monthButton;
    private float[] yData;
    private String[] xData;

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatsBus.init(mode);
        yData = StatsBus.getValues();
        xData = StatsBus.getKeys();
        setContentView(R.layout.stats);

        dayButton = (Button) findViewById(R.id.dayButton);
        weekButton = (Button) findViewById(R.id.weekButton);
        monthButton = (Button) findViewById(R.id.monthButton);

        dayButton.setAlpha(0.8f);

        stats = (FrameLayout) findViewById(R.id.chartFrame);
        chart = new PieChart(this);
        // add pie chart to main layout
        stats.addView(chart);
        // configure pie chart
        chart.setUsePercentValues(true);
        chart.setDescription("Daily Nutritional Intake");

        // enable hole and configure
        chart.setDrawHoleEnabled(true);
        chart.setHoleColorTransparent(true);
        chart.setHoleRadius(7);
        chart.setTransparentCircleRadius(10);

        // enable rotation of the chart by touch
        chart.setRotationAngle(0);
        chart.setRotationEnabled(true);

        // set a chart value selected listener
        chart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, int dataSetIndex, Highlight h) {
                // display when value is selected
                if (e == null)
                    return;
                if (e.getXIndex() == yData.length - 1) {
                    Output.toastMessage(StatsGui.this, "Other Clicked", Output.SHORT_TOAST);
                } else {
                    Output.toastMessage(StatsGui.this, xData[e.getXIndex()] + " Clicked", Output.SHORT_TOAST);
                }

                // insert what happens on click
            }

            @Override
            public void onNothingSelected() {

            }
        });

        addData();

        // customize legends
        Legend l = chart.getLegend();
        l.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);
        l.setXEntrySpace(7);
        l.setYEntrySpace(5);
    }

    /**
     * Fills the <code>PieChart</code> with data.
     *
     * @see PieChart
     */
    private void addData() {
        ArrayList<Entry> yVals1 = new ArrayList<Entry>();

        for (int i = 0; i < yData.length; i++) {
            yVals1.add(new Entry(yData[i], i));
        }

        ArrayList<String> xVals = new ArrayList<String>();

        for (int i = 0; i < xData.length; i++) {
            xVals.add(xData[i]);
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
        data.setValueTextSize(11f);
        data.setValueTextColor(Color.GRAY);

        chart.setData(data);
        // undo all highlights
        chart.highlightValues(null);
        // update pie chart
        chart.invalidate();
    }

    /**
     * Calls <code>finish()</code> to close this <code>Activity</code>, returning to previous
     * <code>Activity</code> on the stack.
     */
    public void onBackPressed() {
        Output.toastMessage(this, "Returning to previous screen.", Output.LONG_TOAST);
        finish();
    }

    /**
     * Creates a new <code>Intent</code> and starts <code>SettingsActivity</code>.
     *
     * @param view Unused.
     */
    public void settingsButton(View view) {
        Intent gameMode = new Intent(this, SettingsActivity.class);
        startActivity(gameMode);
    }

    /**
     * When the day button is clicked, the <code>PieChart</code> is updated to show the data
     * for the user's day.
     *
     * @param view View that has been clicked.
     * @see PieChart
     */
    public void dayButton(View view) {
        mode = 0;
        StatsBus.init(mode);
        yData = StatsBus.getValues();
        xData = StatsBus.getKeys();
        dayButton.setAlpha(0.8f);
        weekButton.setAlpha(0.4f);
        monthButton.setAlpha(0.4f);
        chart.setDescription("Daily Nutritional Intake");
        addData();
    }

    /**
     * When the week button is clicked, the <code>PieChart</code> is updated to show the data
     * for the user's week.
     *
     * @param view View that has been clicked.
     * @see PieChart
     */
    public void weekButton(View view) {
        mode = 1;
        StatsBus.init(mode);
        yData = StatsBus.getValues();
        xData = StatsBus.getKeys();
        dayButton.setAlpha(0.4f);
        weekButton.setAlpha(0.8f);
        monthButton.setAlpha(0.4f);
        chart.setDescription("Weekly Nutritional Intake");
        addData();
    }

    /**
     * When the month button is clicked, the <code>PieChart</code> is updated to show the data
     * for the user's month.
     *
     * @param view View that has been clicked.
     * @see PieChart
     */
    public void monthButton(View view) {
        mode = 2;
        StatsBus.init(mode);
        yData = StatsBus.getValues();
        xData = StatsBus.getKeys();
        dayButton.setAlpha(0.4f);
        weekButton.setAlpha(0.4f);
        monthButton.setAlpha(0.8f);
        chart.setDescription("Monthly Nutritional Intake");
        addData();
    }
}