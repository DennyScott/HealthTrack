package business;

import java.sql.Date;
import java.sql.Time;

/**
 * Created by scott on 28/02/16.
 * Represents a single entry in the food journal
 */
public class JournalEntry implements ApplicationConstants {
    Date date;
    Time time;
    Food food;
    int servings;

    public JournalEntry (Date dateEaten, Time timeEaten, Food foodEaten, int servingEaten){
        this.date=dateEaten;
        this.time=timeEaten;
        this.food=foodEaten;
        this.servings=servingEaten;
    }
}
