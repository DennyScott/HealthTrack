package business;

import java.util.ArrayList;

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

        Fat fat = new Fat();
        Carbohydrate carbs = new Carbohydrate();
        Protein protein = new Protein();
        Sodium sodium = new Sodium();
        Potassium potassium = new Potassium();
        Fiber fiber = new Fiber();
        Iron iron = new Iron();
        Vitamin vitamin = new Vitamin();
        Nutrient calories = new Nutrient();

        if (calories.getGoalAmount()>0)
            goals.add(nutrientGoal(numDays, calories));
        if (fat.getGoalAmount()>0)
            goals.add(nutrientGoal(numDays, fat));
        if (carbs.getGoalAmount()>0)
            goals.add(nutrientGoal(numDays, carbs));
        if (protein.getGoalAmount()>0)
            goals.add(nutrientGoal(numDays, protein));
        if (sodium.getGoalAmount()>0)
            goals.add(nutrientGoal(numDays, sodium));
        if (potassium.getGoalAmount()>0)
            goals.add(nutrientGoal(numDays, potassium));
        if (fiber.getGoalAmount()>0)
            goals.add(nutrientGoal(numDays, fiber));
        if (iron.getGoalAmount()>0)
            goals.add(nutrientGoal(numDays, iron));
        if (vitamin.getGoalAmount()>0)
            goals.add(nutrientGoal(numDays, vitamin));

        return goals;
    }

    private static GoalsType nutrientGoal(int numDays, Nutrient nutrient) {
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

        title += nutrient.getName() + " Intake";
        nutrientPerDay = nutrient.getDailyAmount();

        if(nutrientPerDay <= 0 | goals.getTargetWeeks()<=0) {
            nutrientPerDay = DEFAULT_DAILY_CALORIES * numDays;
            title += ": Error calculating intake, " +
                    "used default intake value";
        }

        finalNutrient = nutrientPerDay*numDays;

        gt = new GoalsType(nutrientPerDay, finalNutrient, title);

        return gt;
    }
}
