package club.glamajestic.healthtrack;

import business.ClickSound;
import business.InitPieChart;
import business.Stats;
import business.StatsUtils;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;

import java.util.ArrayList;

public class StatsActivity extends Activity {

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
    private ProgressBar bars;
    private TextView barTitles;
    private TextView title;
    InitPieChart pie;
    ClickSound playSound;
    StatsUtils utils;


    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatsBus.init(mode);
        utils = new StatsUtils();
        utils.RemoveZeroTerms(StatsBus.getKeys(), utils.allToGrams(StatsBus.getValues(), StatsBus.Units()));
        yData = utils.y;
        xData = utils.x;
        setContentView(R.layout.stats);
        playSound = new ClickSound(this);
        final TabHost host = (TabHost) findViewById(R.id.tabHost);
        host.setup();
        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Pie Chart");
        spec.setContent(R.id.pieChartTab);
        spec.setIndicator("Pie Chart");
        host.addTab(spec);

        TextView x = (TextView) host.getTabWidget().getChildAt(0).findViewById(android.R.id.title);
        x.setTextSize(30);

        //Tab 2
        spec = host.newTabSpec("View Foods");
        spec.setContent(R.id.barChartTab);
        spec.setIndicator("View Foods");
        host.addTab(spec);




        x = (TextView) host.getTabWidget().getChildAt(1).findViewById(android.R.id.title);
        x.setTextSize(30);

        host.setOnTabChangedListener(new TabHost.OnTabChangeListener() {

            @Override
            public void onTabChanged(String arg0) {

                setTabColor(host);
            }
        });
        setTabColor(host);

        dayButton = (Button) findViewById(R.id.dayButton);
        weekButton = (Button) findViewById(R.id.weekButton);
        monthButton = (Button) findViewById(R.id.monthButton);

        dayButton.setAlpha(0.8f);

        stats = (FrameLayout) findViewById(R.id.chartFrame);
        pie = new InitPieChart(this, stats, chart, mode);


    }
    public void clearGoalsTab(){
        LinearLayout tab2 =(LinearLayout)findViewById(R.id.barChartTabLinear);
        tab2.removeAllViews();
    }

    public void onBackPressed() {
        //Intent gameMode = new Intent(this, StatsGuiActivity.class);
        //startActivity(gameMode);
        playSound.play();

        finish();
    }

    public void dayButton(View view) {
        mode = 0;
        StatsBus.init(mode);
        utils.RemoveZeroTerms(StatsBus.getKeys(), utils.allToGrams(StatsBus.getValues(), StatsBus.Units()));
        yData = utils.y;
        xData = utils.x;
        dayButton.setAlpha(0.8f);
        weekButton.setAlpha(0.4f);
        monthButton.setAlpha(0.4f);
        pie.addData(yData, xData, mode);
        playSound.play();
        clearGoalsTab();
    }

    public void weekButton(View view) {
        mode = 1;
        StatsBus.init(mode);
        utils.RemoveZeroTerms(StatsBus.getKeys(), utils.allToGrams(StatsBus.getValues(), StatsBus.Units()));
        yData = utils.y;
        xData = utils.x;
        dayButton.setAlpha(0.4f);
        weekButton.setAlpha(0.8f);
        monthButton.setAlpha(0.4f);
        pie.addData(yData, xData, mode);
        playSound.play();
        clearGoalsTab();
    }

    public void monthButton(View view) {
        mode = 2;
        StatsBus.init(mode);
        utils.RemoveZeroTerms(StatsBus.getKeys(), utils.allToGrams(StatsBus.getValues(), StatsBus.Units()));
        yData = utils.y;
        xData = utils.x;
        dayButton.setAlpha(0.4f);
        weekButton.setAlpha(0.4f);
        monthButton.setAlpha(0.8f);
        pie.addData(yData, xData, mode);
        playSound.play();
        clearGoalsTab();
    }
    public void setTabColor(TabHost tabhost) {

        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
            tabhost.getTabWidget().getChildAt(i).setBackgroundColor(Color.parseColor("#e28f13")); //unselected

        if(tabhost.getCurrentTab()==0)
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFBC59")); //1st tab selected
        else
            tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundColor(Color.parseColor("#FFFFBC59")); //2nd tab selected
    }
}
