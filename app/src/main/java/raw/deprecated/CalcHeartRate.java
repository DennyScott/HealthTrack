//package raw.deprecated;
//
//
//
///*
//Heart rate information found at:
//http://www.heart.org/HEARTORG/HealthyLiving/PhysicalActivity/FitnessBasics/Target-Heart-Rates_UCM_434341_Article.jsp#.VuLNt5wrK9I
//
//Can calculate max heart rate, optimal heart rate for moderate activites, and optimal heart rate for high intensity (hard) activites
// */
//
//import business.ApplicationConstants;
//import raw.deprecated.UserDataAccess;
//
//public class CalcHeartRate implements Calculator, ApplicationConstants {
//    private static final int MAX_HR = 220;
//    private static final double MOD_LOWER_BOUND = 0.5;
//    private static final double MOD_UPPER_BOUND = 0.69;
//    private static final double HARD_LOWER_BOUND = 0.7;
//    private static final double HARD_UPPER_BOUND = 0.89;
//    private double lowerBound;
//    private double upperBound;
//    private int max;
//
//    private UserDataAccess user = new UserDataAccess();
//
//    public CalcHeartRate(double lowerBound, double upperBound) {
//        this.lowerBound = lowerBound;
//        this.upperBound = upperBound;
//        user = new UserDataAccess();
//    }
//
//    @Override
//    public double calculate() {
//        if (lowerBound < MOD_LOWER_BOUND || lowerBound > HARD_UPPER_BOUND ||
//                upperBound < MOD_LOWER_BOUND || upperBound > HARD_UPPER_BOUND ||
//                (upperBound - lowerBound < 0)) {
//            return BAD_CALCULATION;
//        }
//        setMax(getMAX_HR() - getUser().getAge());
//        return getMax();
//    }
//
//    public int getModLowerRate() {
//        setLowerBound(calculate() * getMOD_LOWER_BOUND());
//        return (int) Math.round(getLowerBound());
//    }
//
//    public int getModUpperRate() {
//        setUpperBound(calculate() * getMOD_UPPER_BOUND());
//        return (int) Math.round(getUpperBound());
//    }
//
//    public int getHardLowerRate() {
//        setLowerBound(calculate() * getHARD_LOWER_BOUND());
//        return (int) Math.round(getLowerBound());
//    }
//
//    public int getHardUpperRate() {
//        setUpperBound(calculate() * getHARD_UPPER_BOUND());
//        return (int) Math.round(getUpperBound());
//    }
//
//    public double getLowerBound() {
//        return lowerBound;
//    }
//
//    public void setLowerBound(double lowerBound) {
//        this.lowerBound = lowerBound;
//    }
//
//    public double getUpperBound() {
//        return upperBound;
//    }
//
//    public void setUpperBound(double upperBound) {
//        this.upperBound = upperBound;
//    }
//
//    public int getMax() {
//        return max;
//    }
//
//    public void setMax(int max) {
//        this.max = max;
//    }
//
//    public int getMAX_HR() {
//        return MAX_HR;
//    }
//
//    public double getMOD_LOWER_BOUND() {
//        return MOD_LOWER_BOUND;
//    }
//
//    public double getMOD_UPPER_BOUND() {
//        return MOD_UPPER_BOUND;
//    }
//
//    public double getHARD_LOWER_BOUND() {
//        return HARD_LOWER_BOUND;
//    }
//
//    public double getHARD_UPPER_BOUND() {
//        return HARD_UPPER_BOUND;
//    }
//
//    public UserDataAccess getUser() {
//        return user;
//    }
//
//    public void setUser(UserDataAccess user) {
//        this.user = user;
//    }
//}
