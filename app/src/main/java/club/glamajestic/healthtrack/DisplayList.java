package club.glamajestic.healthtrack;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;


public class DisplayList extends Activity {
    String[] keys =  {"Nothing to show"};
    String[] foods = null;
    float[] values = {0};
    String[] units = {""};
    CustomList adapter;
    CustomList2 adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        Bundle extras = getIntent().getExtras();
        if(foods != null) {
            adapter2 = new CustomList2(this, foods);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter2);
            listView.setBackgroundColor(Color.parseColor("#ffffff"));
        }
        else {
            foods = extras.getStringArray("foods");
            units = extras.getStringArray("units");
            values = extras.getFloatArray("values");

            if (keys == null) {
                keys = new String[]{"Nothing to show"};
            }
            if (values == null) {
                values = new float[]{0};
            }
            if (units == null) {
                units = new String[]{""};
            }
            adapter = new CustomList(this, keys, values,units);
            ListView listView = (ListView) findViewById(R.id.listView);
            listView.setAdapter(adapter);
            listView.setBackgroundColor(Color.parseColor("#ffffff"));
        }



    }
}
