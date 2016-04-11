//package raw.deprecated;
//
//
//import business.ApplicationConstants;
//import raw.deprecated.UserDataAccess;
//
///*
//Information from:
//http://www.calculator.net/calorie-calculator.html
//
//Rough estimate of how many calories to consume per day to maintain weight.
//Varies depending on physical activity level (due to loss of calories).
// */
//public class CalcWeightSustain implements Calculator, ApplicationConstants {
//    private static final double EXTRA_ACTIVE = 1.9;
//    private static final double VERY_ACTIVE = 1.725;
//    private static final double MOD_ACTIVE = 1.55;
//    private static final double LIGHT_ACTIVE = 1.375;
//    private static final double VERY_LIGHT_ACTIVE = 1.2; // default AKA little to no exercise
//    private static final double NOT_ACTIVE = 1; // CalcBMR value
//    private double calories;
//
//    public CalcWeightSustain() {
//
//    }
//
//    public CalcWeightSustain(double calories) {
//        this.calories = calories;
//    }
//
//    @Override
//    public double calculate() {
//        if (calories==0) {
//            CalcBMR calcBMR = new CalcBMR();
//            calories = calcBMR.calculate();
//        }
//
//        if (Double.MAX_VALUE / VERY_LIGHT_ACTIVE < calories ||
//                calories < MIN_CALORIES || calories > MAX_CALORIES)
//            return ApplicationConstants.BAD_CALCULATION;
//
//        UserDataAccess user = new UserDataAccess();
//        int activeLevel = user.getActiveLevel();
//        double activeMultiplier = NOT_ACTIVE;
//
//        switch (activeLevel) {
//            case 1: activeMultiplier = VERY_LIGHT_ACTIVE;
//                break;
//            case 2: activeMultiplier = LIGHT_ACTIVE;
//                break;
//            case 3: activeMultiplier = MOD_ACTIVE;
//                break;
//            case 4: activeMultiplier = VERY_ACTIVE;
//                break;
//            case 5: activeMultiplier = EXTRA_ACTIVE;
//                break;
//            default: break;
//        }
//
//        calories = calories * activeMultiplier;
//
//        return calories;
//    }
//
//}
