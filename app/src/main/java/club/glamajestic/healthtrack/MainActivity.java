package club.glamajestic.healthtrack;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;
import com.github.mikephil.charting.charts.PieChart;

import business.ApplicationConstants;
import business.ClickSound;
import business.InitPieChart;
import persistence.CsvConverter;
import persistence.DatabaseDefinition;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private FrameLayout stats;
    private PieChart chart;
    private MediaPlayer backgroundMusic;
    ClickSound playSound;
    boolean soundEnabled;
    int charInitMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_stats);
        playSound = new ClickSound(this);
        boolean soundEnabled = false;
        if(soundEnabled) {
            backgroundMusic = MediaPlayer.create(this, R.raw.delta);
            backgroundMusic.setLooping( true );
            backgroundMusic.start();
        }
        stats = (FrameLayout) findViewById(R.id.chartMainScreen);
        charInitMode = 3;
        InitPieChart pie = new InitPieChart(this, stats, chart, charInitMode);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                playSound.play();
                startActivity(new Intent(MainActivity.this, Ate.class));
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onBackPressed() {
        playSound.play();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (this.isFinishing()){
            if(backgroundMusic != null) {
                backgroundMusic.stop();
            }
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
            if (backgroundMusic != null) {
                if (backgroundMusic.isPlaying()) {
                    backgroundMusic.pause();
                }
            }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(backgroundMusic != null){
            if(!backgroundMusic.isPlaying()){
                backgroundMusic.start();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_stats, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        playSound.play();

        int id = item.getItemId();

        if (id == R.id.action_settings) {

            Intent gameMode = new Intent(this, SettingsActivity.class);
            startActivity(gameMode);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        playSound.play();

        int id = item.getItemId();

        if (id == R.id.nav_goals) {
            Intent gameMode = new Intent(this, Goals.class);
            startActivity(gameMode);
            //finish();
        } else if (id == R.id.nav_food) {
            Intent gameMode = new Intent(this, Ate.class);
            startActivity(gameMode);
            //finish();
        } else if (id == R.id.nav_dstats) {
            Intent gameMode = new Intent(this, StatsActivity.class);
            startActivity(gameMode);
            //finish();
        } else if (id == R.id.nav_settings) {
            Intent gameMode = new Intent(this, SettingsActivity.class);
            startActivity(gameMode);
            //finish();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
