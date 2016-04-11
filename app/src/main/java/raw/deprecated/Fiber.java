//package raw.deprecated;
//
//import java.io.Serializable;
//
//import business.ApplicationConstants;
//
//
//class Fiber extends Nutrient implements Serializable, ApplicationConstants {
//    private int amount;
//    private String name;
//    private GoalsAccess goals;
//
//    public Fiber()
//    {
//        name = "Fiber";
//        goals = new GoalsAccess();
//        amount = 0;
//    }
//
//    public int getGoalAmount() {
//        return goals.getTargetFiber();
//    }
//
//    public int getDailyAmount() {
//        amount = goals.getTargetFiber();
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
