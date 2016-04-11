//package raw.deprecated;
//
//import android.os.Bundle;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
//import android.support.v4.app.DialogFragment;
//import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
//import android.view.View;
//
//import club.glamajestic.healthtrack.ChooseNutrientsDialog;
//import club.glamajestic.healthtrack.R;
//import persistence.DatabaseDefinition;
//
//public class MealEntry extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        DatabaseDefinition db = new DatabaseDefinition(this);
//        setContentView(R.layout.activity_meal_entry);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);
//
//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//                DialogFragment df = new ChooseNutrientsDialog();
//                df.show(getSupportFragmentManager(), "poop");
//            }
//        });
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//    }
//}
