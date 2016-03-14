package club.glamajestic.healthtrack;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.ListView;


public class DisplayList extends Activity {
    String[] keys =  {"Nothing to show"};
    float[] values = {0};
    String[] units = {""};
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            keys = extras.getStringArray("keys");
            units = extras.getStringArray("units");
            values = extras.getFloatArray("values");
        }
        if(keys == null){
            keys = new String[] {"Nothing to show"};
        }
        if(values == null){
            values = new float[] {0};
        }
        if(units == null){
            units = new String[] {""};
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        CustomList adapter = new CustomList(this, keys, values,units);

        ListView listView = (ListView) findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setBackgroundColor(Color.parseColor("#ffffff"));
    }
}
