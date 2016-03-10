package club.glamajestic.healthtrack;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;
import business.CustomList;

/**
 * Created by Khaled on 3/9/2016.
 */
public class DisplayList extends Activity {
    // Array of strings...
    String[] keys =  {"Cholesterol", "Sodium", "Sugar", "Protein", "Fat", "Fiber", "Calcium", "Carbs"};
    float[] values = {5, 10, 15, 30, 40, 20, 10, 60};
    String[] units = {"grams","grams","grams","grams","grams","grams","grams","grams"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        CustomList adapter = new CustomList(this, keys, values,units);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setBackgroundColor(Color.parseColor("#ffffff"));
    }
}
