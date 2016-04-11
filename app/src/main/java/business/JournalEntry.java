package business;

import java.sql.Date;
import java.sql.Time;

import persistence.DataFoods;

/**
 * Created by scott on 28/02/16.
 * Represents a single entry in the food journal
 */
public class JournalEntry implements ApplicationConstants {

    Date date;
    Time time;
    DataFoods food;
    int servings;

    public JournalEntry(Date dateEaten, int portionSize, int foodTableId) {
        this.date = dateEaten;
        this.servings = portionSize;
        this.food = new DataFoods(foodTableId);
    }
}
