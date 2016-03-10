package business;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import club.glamajestic.healthtrack.R;

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private final String[] web;
    private final float[] sub;
    private final String[] units;

    public CustomList(Activity context, String[] web, float[] sub, String[] units) {
        super(context, R.layout.main_list, web);
        this.context = context;
        this.web = web;
        this.sub = sub;
        this.units = units;
    }
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.main_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.mainList);

        TextView discr = (TextView) rowView.findViewById(R.id.subList);
        txtTitle.setText(web[position]);
        discr.setText(sub[position]+" "+ units[position]);
        return rowView;
    }
}