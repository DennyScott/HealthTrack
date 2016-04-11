package club.glamajestic.healthtrack;

import android.annotation.TargetApi;
import android.content.ContentValues;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleCursorAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import business.StatsDataAccess;
import persistence.DataFoods;
import persistence.DataTransactionalHistory;
import persistence.DatabaseDefinition;

public class FoodEntry extends AppCompatActivity implements SearchView.OnQueryTextListener, SearchView.OnCloseListener {
    ArrayList<Integer> alphabeticalNutrientNameTextIds;
    ArrayList<Integer> alphabeticalNutrientValueTextIds;
    SQLiteDatabase db;
    DatabaseDefinition df;
    ListView multiListView;
    SearchView searchView;
    EditText textbox;
    Cursor cpos = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_entry);
        searchView = (SearchView) findViewById(R.id.foodSearchView);
        searchView.setOnQueryTextListener(this);
        searchView.setOnCloseListener(this);

        df = new DatabaseDefinition(this);
        df.createDatabase();
        textbox = (EditText) findViewById(R.id.portionsText);
        multiListView = (ListView) findViewById(R.id.foodsSearchListView);
        //create the id set for holding the views
        alphabeticalNutrientNameTextIds = new ArrayList<>();
        alphabeticalNutrientValueTextIds = new ArrayList<>();

    }

    private void doQuery(String foodname) {
        if (!foodname.equals("")) {
            String[] to = {
                    DataFoods.COLNAME_FOODNAME,
            };
            //get all the columns and parse using cursor
            String[] columns = null; //{
//                    DataFoods.COLNAME_ID,
//                    DataFoods.COLNAME_FOODNAME,
//                    DataFoods.COLNAME_CALORIES,
//                    DataFoods.COLNAME_FATS,
//                    DataFoods.COLNAME_PROTEINS,
//                    DataFoods.COLNAME_CHOLESTROL,
//                    DataFoods.COLNAME_SODIUM,
//                    DataFoods.COLNAME_CARBOHYDRATES
//            };
            int[] idsOfViews = {
                    R.id.svFoodNameText //the View within the list view where the food name will go
            };
            db = df.getReadableDatabase();
            final Cursor cursor = db.query(
                    DataFoods.TABLE_NAME_FOODS,
                    columns,
                    DataFoods.COLNAME_FOODNAME + " LIKE '%" + foodname + "%'",      //do pattern match on CONTAINING the string so %string%
                    null,
                    null,
                    null,
                    DataFoods.COLNAME_FOODNAME                  //find strings containing this pattern but order by this
            );
            SimpleCursorAdapter foods = null;
            try {
                foods = new SimpleCursorAdapter(this, R.layout.search_list_view, cursor, to, idsOfViews, 0);
            } catch (RuntimeException e) {
                Log.e("ok", e.toString(), e);
            }
            if (foods == null) return;
            multiListView.setAdapter(foods);
            multiListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    cpos = (Cursor) multiListView.getItemAtPosition(position);
                    populateUsingCursor(cpos);

                    searchView.setQuery("", true);
                }
            });
        }
    }

    /**
     * Takes whatever the user clicked on and creates a
     *
     * @param cpos
     */
    @TargetApi(Build.VERSION_CODES.M)
    private void populateUsingCursor(Cursor cpos) {
        //using all the nutrient column names, generate a list of strings and corresponding values
        ArrayList<String> nutrients = DataFoods.getAllNutrients();
        String[] nutrientColumnNames = DataFoods.getAllNutrientColumnNames();

        ListView layout = (ListView) findViewById(R.id.foodEntryScrollableLayout);
        ArrayAdapter<String> adapter;
        ArrayList<String> nutrientAndValArray= new ArrayList<>();

        String nutrientName;
        String nutrientValueAndUnits;
        for (int i = 0; i < nutrients.size(); i++) {

            nutrientName = nutrients.get(i);
            nutrientValueAndUnits = cpos.getString(cpos.getColumnIndex(nutrientColumnNames[i] + "Value")) +
                    cpos.getString(cpos.getColumnIndex(nutrientColumnNames[i] + "Unit"));
            if (nutrientValueAndUnits.equals("")) nutrientValueAndUnits = "0mg";
            nutrientAndValArray.add(nutrientName + ": " + nutrientValueAndUnits);

        }
        if (layout.getAdapter() != null) ((ArrayAdapter)layout.getAdapter()).clear();
            layout.setAdapter(null);
            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1,android.R.id.text1,nutrientAndValArray);
            layout.setAdapter(adapter);
            adapter.notifyDataSetChanged();
    }

    /**
     * The user is attempting to close the SearchView.
     *
     * @return true if the listener wants to override the default behavior of clearing the
     * text field and dismissing it, false otherwise.
     */
    @Override
    public boolean onClose() {

        return false;
    }
    public void onSave(View view){
        if(textbox.getText().toString().equals("")){
            Output.toastMessage(this,"Please enter portion first!",0);
        }
        else{
            StatsDataAccess sda = new StatsDataAccess();
            SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);
            int fatLimit = Integer.parseInt(sharedPref.getString("user_fat_limit", ""));
            int carbLimit = Integer.parseInt(sharedPref.getString("user_carb_limit", ""));
            int proteinLimit =Integer.parseInt(sharedPref.getString("user_protein_limit", ""));
            if(cpos != null){
                SQLiteDatabase db = DatabaseDefinition.currentDatabase.getReadableDatabase();
                ContentValues cv = new ContentValues();

                //        transactional history (eaten)
                //          id
                //          food_name
                //          eaten_date
                //          portion_size
                //          foodTableId
                DateFormat dateFormat = new SimpleDateFormat("yyyyMMdd");
                Calendar cal = Calendar.getInstance();
                String today = dateFormat.format(cal.getTime());

                cv.put(DataTransactionalHistory.COLNAME_FOODNAME,cpos.getString(cpos.getColumnIndex(DataFoods.COLNAME_FOODNAME)));
                cv.put(DataTransactionalHistory.COLNAME_FOODTABLE_ID,cpos.getInt(cpos.getColumnIndex(DataFoods.COLNAME_ID)));
                cv.put(DataTransactionalHistory.COLNAME_PORTIONSIZE,Float.parseFloat(textbox.getText().toString()));
                cv.put(DataTransactionalHistory.COLNAME_EATEN_DATE, today);
                db.insert(DataTransactionalHistory.TABLE_NAME, null, cv);

                HTNotifications hn = new HTNotifications("Food successfully added to your log. Congratulations!");
                hn.throwNotification(this);

                String[] names = sda.getNutrientsNames(0);
                int fatIndex = -1;
                int carbIndex = -1;
                int proteinIndex = -1;
                float[] values = sda.getNutrientsValues(0);
                int i=0;
                while((fatIndex==-1||carbIndex==-1||proteinIndex==-1)&&i<names.length-1){
                    if(names[i].equalsIgnoreCase("fat total lipids")){
                        fatIndex = i;
                    }else if(names[i].equalsIgnoreCase("carbohydrate, total (by difference)")){
                        fatIndex = i;
                    }else if(names[i].equalsIgnoreCase("protein")){
                        fatIndex = i;
                    }
                    i++;
                }
                if(fatIndex!=-1&&values[fatIndex]>fatLimit){
                    HTNotifications fatNot = new HTNotifications("Fat", fatLimit);
                    fatNot.throwNotification(this);
                }
                if(carbIndex!=-1&&values[carbIndex]>carbLimit){
                    HTNotifications carbNot = new HTNotifications("Carbohydrate", carbLimit);
                    carbNot.throwNotification(this);
                }
                if(proteinIndex!=-1&&values[proteinIndex]>proteinLimit){
                    HTNotifications proteinNot = new HTNotifications("Protein", proteinLimit);
                    proteinNot.throwNotification(this);
                }
                finish();
            } else {
                Output.toastMessage(this,"Please select a food",0);
            }

        }
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
