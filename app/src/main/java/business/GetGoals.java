package business;

import java.util.ArrayList;

/**
 * TODO
 * Need to use values based on actual user goals and the foods they ate
 */
public class GetGoals {
    private static int DAY = 1;
    private static int DAYS_IN_WEEK = 7;
    private static int DAYS_IN_MONTH = 30;
    private static int DEFAULT_DAILY_CALORIES = 2000;

    public static ArrayList<GoalsType> loadGoalsFromDBDay(){
        return getGoals(DAY);
    }

    public static ArrayList<GoalsType> loadGoalsFromDBWeek(){
        return getGoals(DAYS_IN_WEEK);
    }

    public static ArrayList<GoalsType> loadGoalsFromDBMonth(){
        return getGoals(DAYS_IN_MONTH);
    }

    private static ArrayList<GoalsType> getGoals(int numDays) {
        ArrayList<GoalsType> goals = new ArrayList<GoalsType>();

        goals.add(calorieGoal(numDays));

        return goals;
    }

    private static GoalsType calorieGoal(int numDays) {
        GoalsType gt;
        CalcCaloriesPerDay ccpd;
        int finalCalories;

        String title;
        switch(numDays) {
            case 1:
                title = "Daily calories intake";
                break;
            case 7:
                title = "Weekly calories intake";
                break;
            case 30:
                title = "Monthly calories intake";
                break;
            default:
                title = "Invalid number of days";
                break;
        }

        ccpd = new CalcCaloriesPerDay();
        if(ccpd.calculate() <= 0) {
            finalCalories = DEFAULT_DAILY_CALORIES * numDays;
            title += ": Error calculating calorie intake, " +
                    "used default intake value";
        } else {
            finalCalories = (int) ccpd.calculate() * numDays;
        }

        gt = new GoalsType(finalCalories/numDays, finalCalories, title);

        return gt;
    }
}
