package club.glamajestic.healthtrack;

import android.Manifest;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.github.mikephil.charting.charts.PieChart;

import business.ClickSound;
import business.InitPieChart;
import persistence.DatabaseDefinition;

public class MainActivity extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    private FrameLayout stats;
    private PieChart chart;
    private MediaPlayer backgroundMusic;
    ClickSound playSound;
    boolean soundEnabled;
    int charInitMode;
    InitPieChart pie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        PreferenceManager.setDefaultValues(this, R.xml.pref_general, false);
        PreferenceManager.setDefaultValues(this, R.xml.pref_notification, false);


        //request permissions to and read
        String[] permissions = {Manifest.permission.WRITE_EXTERNAL_STORAGE};
        ActivityCompat.requestPermissions(this,permissions,43);
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

        pie = new InitPieChart(this, stats, chart, charInitMode);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



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
        stats.removeAllViews();
        pie = new InitPieChart(this, stats, chart, charInitMode);
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

        if (id == R.id.nav_food) {
            Intent gameMode = new Intent(this, FoodEntry.class);
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
