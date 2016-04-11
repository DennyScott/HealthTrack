//package raw.deprecated;
//
//import java.io.Serializable;
//
//import business.ApplicationConstants;
//
//
//class Potassium extends Nutrient implements Serializable, ApplicationConstants {
//    private int amount;
//    private String name;
//    private GoalsAccess goals;
//
//    public Potassium()
//    {
//        name = "Potassium";
//        goals = new GoalsAccess();
//        amount = 0;
//    }
//
//    public int getGoalAmount() {
//        return goals.getTargetPotassium();
//    }
//
//    public int getDailyAmount() {
//        amount = goals.getTargetPotassium();
//        if (goals.getTargetWeeks()>0) {
//            amount = amount / (goals.getTargetWeeks()*7);
//        }
//        return amount;
//    }
//
//    public String getName() {
//        return name;
//    }
//}
