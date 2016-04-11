package club.glamajestic.healthtrack;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import business.ApplicationConstants;

public class CustomList2 extends ArrayAdapter<String> implements ApplicationConstants {

    private final Activity context;
    private final String[] nutrient;
    private final float[] value;
    private final String[] units;

    public CustomList2(Activity context, String[] foods) {
        super(context, R.layout.main_list, foods);
        nutrient = new String[foods.length];
        value = new float[foods.length];
        units = new String[foods.length];
        for(int x = 0; x < foods.length; x++){
            String[] temp = foods[x].split("#");
            nutrient[x] = temp[0];
            try{
                value[x] = Float.parseFloat(temp[1]);
            }catch(Exception e){
                e.printStackTrace();
            }
            units[x] = temp[2];
        }
        this.context = context;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.main_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.mainList);

        TextView discr = (TextView) rowView.findViewById(R.id.subList);
        txtTitle.setText(nutrient[position]);
        discr.setText(value[position]+" "+ units[position]);
        return rowView;
    }
}