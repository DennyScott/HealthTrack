package club.glamajestic.healthtrack;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import persistence.DataFoods;
import persistence.DatabaseDefinition;

public class FoodEntry extends AppCompatActivity  implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    SQLiteDatabase db;
    DatabaseDefinition df;
    ListView multiListView;
    TextView tvFoodname, tvCalories, tvProteins, tvFats, tvSodium, tvChole, tvCarbs;
    SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_entry);
        searchView = (SearchView) findViewById(R.id.foodSearchView);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

        df = new DatabaseDefinition(this);
        df.createDatabase();

        multiListView = (ListView) findViewById(R.id.foodsSearchListView);


        tvFoodname = (TextView) findViewById(R.id.foodNameTextView);
        tvCalories= (TextView) findViewById(R.id.editCals);
        tvProteins = (TextView) findViewById(R.id.editProtein);
        tvFats = (TextView) findViewById(R.id.editFat);
        tvSodium= (TextView) findViewById(R.id.editSodium);
        tvChole = (TextView) findViewById(R.id.editCholesterol);
        tvCarbs = (TextView) findViewById(R.id.editCarbs);
    }

    private void doQuery(String foodname) {
        if (!foodname.equals("")) {
            String[] to =  {
                    DataFoods.COLNAME_FOODNAME,
            };
            String[] columns = {
                    DataFoods.COLNAME_ID,
                    DataFoods.COLNAME_FOODNAME,
                    DataFoods.COLNAME_CALORIES,
                    DataFoods.COLNAME_FATS,
                    DataFoods.COLNAME_PROTEINS,
                    DataFoods.COLNAME_CHOLESTROL,
                    DataFoods.COLNAME_SODIUM,
                    DataFoods.COLNAME_CARBOHYDRATES
            };
            int[] idsOfVIews = {
                    R.id.svFoodNameText
            };
            db = df.getReadableDatabase();
            final Cursor cursor = db.query(
                    DataFoods.TABLE_NAME_FOODS,
                    columns,
                    DataFoods.COLNAME_FOODNAME + " LIKE '" + foodname + "%'",
                    null,
                    null,
                    null,
                    null
            );
            SimpleCursorAdapter foods = null;
            try {
                foods = new SimpleCursorAdapter(this, R.layout.search_list_view, cursor, to, idsOfVIews, 0);
            }
            catch (RuntimeException e) {
                Log.e("ok",e.toString(),e);
            }
            if (foods == null) return;
            multiListView.setAdapter(foods);
            multiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Cursor cpos = (Cursor) multiListView.getItemAtPosition(position);

                    String foodname = cursor.getString(cursor.getColumnIndex(DataFoods.COLNAME_FOODNAME));
                    String calories = cursor.getString(cursor.getColumnIndex(DataFoods.COLNAME_CALORIES));
                    String fats = cursor.getString(cursor.getColumnIndex(DataFoods.COLNAME_FATS));
                    String proteins = cursor.getString(cursor.getColumnIndex(DataFoods.COLNAME_PROTEINS));
                    String cholestrol = cursor.getString(cursor.getColumnIndex(DataFoods.COLNAME_CHOLESTROL));
                    String sodium = cursor.getString(cursor.getColumnIndex(DataFoods.COLNAME_SODIUM));
                    String carbs = cursor.getString(cursor.getColumnIndex(DataFoods.COLNAME_CARBOHYDRATES));

                    tvFoodname.setText(foodname);
                    tvCalories.setText(calories);
                    tvFats.setText(fats);
                    tvProteins.setText(proteins);
                    tvChole.setText(cholestrol);
                    tvSodium.setText(sodium);
                    tvCarbs.setText(carbs);

                    searchView.setQuery("", true);
                }
            });
        }
    }
    /**
     * The user is attempting to close the SearchView.
     *
     * @return true if the listener wants to override the default behavior of clearing the
     * text field and dismissing it, false otherwise.
     */
    @Override
    public boolean onClose() {
        db.close();
        return false;
    }

    /**
     * Called when the user submits the query. This could be due to a key press on the
     * keyboard or due to pressing a submit button.
     * The listener can override the standard behavior by returning true
     * to indicate that it has handled the submit request. Otherwise return false to
     * let the SearchView handle the submission by launching any associated intent.
     *
     * @param query the query text that is to be submitted
     * @return true if the query has been handled by the listener, false to let the
     * SearchView perform the default action.
     */
    @Override
    public boolean onQueryTextSubmit(String query) {
        doQuery(query);
        return false;
    }

    /**
     * Called when the query text is changed by the user.
     *
     * @param newText the new content of the query text field.
     * @return false if the SearchView should perform the default action of showing any
     * suggestions if available, true if the action was handled by the listener.
     */
    @Override
    public boolean onQueryTextChange(String newText) {
        doQuery(newText);
        return false;
    }
}
