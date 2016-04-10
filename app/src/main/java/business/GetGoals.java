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

        GoalsAccess nutrientGoals = new GoalsAccess();
        if (nutrientGoals.getTargetWeight()>0)
            goals.add(nutrientGoal(numDays, 8));
        if (nutrientGoals.getTargetFat()>0)
            goals.add(nutrientGoal(numDays, 0));
        if (nutrientGoals.getTargetCarbs()>0)
            goals.add(nutrientGoal(numDays, 1));
        if (nutrientGoals.getTargetProtein()>0)
            goals.add(nutrientGoal(numDays, 2));
        if (nutrientGoals.getTargetSodium()>0)
            goals.add(nutrientGoal(numDays, 3));
        if (nutrientGoals.getTargetPotassium()>0)
            goals.add(nutrientGoal(numDays, 4));
        if (nutrientGoals.getTargetFiber()>0)
            goals.add(nutrientGoal(numDays, 5));
        if (nutrientGoals.getTargetIron()>0)
            goals.add(nutrientGoal(numDays, 6));
        if (nutrientGoals.getTargetVitamin()>0)
            goals.add(nutrientGoal(numDays, 7));

        return goals;
    }

    private static GoalsType nutrientGoal(int numDays, int nutrient) {
        GoalsType gt;
        int nutrientPerDay;
        int finalNutrient;

        GoalsAccess goals = new GoalsAccess();

        String title;
        switch(numDays) {
            case 1:
                title = "Daily ";
                break;
            case 7:
                title = "Weekly ";
                break;
            case 30:
                title = "Monthly ";
                break;
            default:
                title = "Invalid number of days";
                break;
        }

        switch(nutrient) {
            case 0: title += "fat intake";
                nutrientPerDay = goals.getTargetFat();
                break;
            case 1: title += "carbohydrate intake";
                nutrientPerDay = goals.getTargetCarbs();
                break;
            case 2: title += "protein intake";
                nutrientPerDay = goals.getTargetProtein();
                break;
            case 3: title += "sodium intake";
                nutrientPerDay = goals.getTargetSodium();
                break;
            case 4: title += "potassium intake";
                nutrientPerDay = goals.getTargetPotassium();
                break;
            case 5: title += "fiber intake";
                nutrientPerDay = goals.getTargetFiber();
                break;
            case 6: title += "iron intake";
                nutrientPerDay = goals.getTargetIron();
                break;
            case 7: title += "vitamin intake";
                nutrientPerDay = goals.getTargetVitamin();
                break;
            case 8: title += "calorie intake";
                CalcCaloriesPerDay ccpd = new CalcCaloriesPerDay();
                nutrientPerDay = (int) ccpd.calculate();
                break;
            default: nutrientPerDay = 0;
                break;
        }

        if(nutrientPerDay <= 0 | goals.getTargetWeeks()<=0) {
            nutrientPerDay = DEFAULT_DAILY_CALORIES * numDays;
            title += ": Error calculating intake, " +
                    "used default intake value";
        } else if (nutrient <=7) {
            nutrientPerDay = nutrientPerDay / (goals.getTargetWeeks()*7);
        }

        finalNutrient = nutrientPerDay*numDays;
        gt = new GoalsType(nutrientPerDay, finalNutrient, title);

        return gt;
    }
}
