package club.glamajestic.healthtrack;

import business.ClickSound;
import business.InitPieChart;
import business.Stats;
import business.StatsUtils;
import persistence.DataTransactionalHistory;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TextView;

import com.github.mikephil.charting.charts.PieChart;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

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
    ListView multiListView;
    String Today,LastWeek,LastMonth;


    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        StatsBus.init(mode);
        utils = new StatsUtils();
        float offset = utils.getOffSet(StatsBus.getOtherValues(),StatsBus.getOtherUnits() );

        utils.RemoveZeroTerms(StatsBus.getKeys(), utils.allToGrams(StatsBus.getValues(), StatsBus.Units()),offset);
        yData = utils.y;
        xData = utils.x;
        setContentView(R.layout.stats);
        playSound = new ClickSound(this);
        final TabHost host = (TabHost) findViewById(R.id.tabHost);
        multiListView = (ListView) findViewById(R.id.statsFoodsSearchListView);
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

        DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
        Calendar cal = Calendar.getInstance();
        Today = dateFormat.format(cal.getTime());
        cal.add(Calendar.DATE, -7);
        LastWeek = dateFormat.format(cal.getTime());
        cal.add(Calendar.DATE, -23);
        LastMonth = dateFormat.format(cal.getTime());

        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, DataTransactionalHistory.getAllFoodNames(Today,Today));
        multiListView.setAdapter(listAdapter);

        dayButton = (Button) findViewById(R.id.dayButton);
        weekButton = (Button) findViewById(R.id.weekButton);
        monthButton = (Button) findViewById(R.id.monthButton);

        dayButton.setAlpha(0.8f);

        stats = (FrameLayout) findViewById(R.id.chartFrame);
        pie = new InitPieChart(this, stats, chart, mode);


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
        float offset = utils.getOffSet(StatsBus.getOtherValues(),StatsBus.getOtherUnits() );
        utils.RemoveZeroTerms(StatsBus.getKeys(), utils.allToGrams(StatsBus.getValues(), StatsBus.Units()),offset);
        yData = utils.y;
        xData = utils.x;
        dayButton.setAlpha(0.8f);
        weekButton.setAlpha(0.4f);
        monthButton.setAlpha(0.4f);
        pie.addData(yData, xData, mode);
        playSound.play();
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, DataTransactionalHistory.getAllFoodNames(Today,Today));
        multiListView.setAdapter(listAdapter);
    }

    public void weekButton(View view) {
        mode = 1;
        StatsBus.init(mode);
        float offset = utils.getOffSet(StatsBus.getOtherValues(),StatsBus.getOtherUnits() );
        utils.RemoveZeroTerms(StatsBus.getKeys(), utils.allToGrams(StatsBus.getValues(), StatsBus.Units()),offset);
        yData = utils.y;
        xData = utils.x;
        dayButton.setAlpha(0.4f);
        weekButton.setAlpha(0.8f);
        monthButton.setAlpha(0.4f);
        pie.addData(yData, xData, mode);
        playSound.play();
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, DataTransactionalHistory.getAllFoodNames(LastWeek,Today));
        multiListView.setAdapter(listAdapter);
    }

    public void monthButton(View view) {
        mode = 2;
        StatsBus.init(mode);
        float offset = utils.getOffSet(StatsBus.getOtherValues(),StatsBus.getOtherUnits() );
        utils.RemoveZeroTerms(StatsBus.getKeys(), utils.allToGrams(StatsBus.getValues(), StatsBus.Units()),offset);
        yData = utils.y;
        xData = utils.x;
        dayButton.setAlpha(0.4f);
        weekButton.setAlpha(0.4f);
        monthButton.setAlpha(0.8f);
        pie.addData(yData, xData, mode);
        playSound.play();
        ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, DataTransactionalHistory.getAllFoodNames(LastMonth,Today));
        multiListView.setAdapter(listAdapter);
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
