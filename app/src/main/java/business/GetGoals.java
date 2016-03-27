package business;

import java.util.ArrayList;

/**
 * Created by Khaled on 3/26/2016.
 */
public class GetGoals {
    // to be done
    // get from actual DB
    // User sets monthly, the rest should just be interpoplates,
    // as in week does the same thing as month but divides by 4
    public static ArrayList<GoalsType> loadGoalsFromDBDay(){
        ArrayList<GoalsType> goals = new ArrayList<GoalsType>();
        goals.add(new GoalsType(70,200, "Daily protein intake"));
        goals.add(new GoalsType(25,80, "Daily fat intake"));
        goals.add(new GoalsType(30,600, "Daily carbs intake"));
        goals.add(new GoalsType(45,60, "Daily sugar intake"));
        goals.add(new GoalsType(2,8, "Daily iron intake"));
        return goals;

    }
    public static ArrayList<GoalsType> loadGoalsFromDBWeek(){
        ArrayList<GoalsType> goals = new ArrayList<GoalsType>();
        goals.add(new GoalsType(70,1400, "Weekly protein intake"));
        goals.add(new GoalsType(25,560, "Weekly fat intake"));
        goals.add(new GoalsType(30,4200, "Weekly carbs intake"));
        goals.add(new GoalsType(45,420, "Weekly sugar intake"));
        goals.add(new GoalsType(2,56, "Weekly iron intake"));
        return goals;

    }
    public static ArrayList<GoalsType> loadGoalsFromDBMonth(){
        ArrayList<GoalsType> goals = new ArrayList<GoalsType>();
        goals.add(new GoalsType(70,5600, "Monthly protein intake"));
        goals.add(new GoalsType(25,2240, "Monthly fat intake"));
        goals.add(new GoalsType(30,16800, "Monthly carbs intake"));
        goals.add(new GoalsType(45,1680, "Monthly sugar intake"));
        goals.add(new GoalsType(2,224, "Monthly iron intake"));
        return goals;

    }
}
