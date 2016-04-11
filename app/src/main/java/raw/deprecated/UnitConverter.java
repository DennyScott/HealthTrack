//package raw.deprecated;
//
//
//import business.ApplicationConstants;
//
//public class UnitConverter implements ApplicationConstants {
//    private static final double LB_TO_KG = 0.453592;
//    private static final double KG_TO_LB = 2.20462;
//    private static final double CM_TO_M = 0.01;
//    private static final double CM_TO_INCH = 0.393701;
//
//    public static double lbToKg(double lb) {
//        if (lb < MIN_CONVERSION_VAL || lb > MAX_CONVERSION_VAL)
//            return BAD_CALCULATION;
//        return lb * LB_TO_KG;
//    }
//
//    public static double kgToLb(double kg) {
//        if (kg < MIN_CONVERSION_VAL || kg > MAX_CONVERSION_VAL)
//            return BAD_CALCULATION;
//        return kg * KG_TO_LB;
//    }
//
//    public static double cmToM2(double cm) {
//        if (cm < MIN_CONVERSION_VAL || cm > MAX_CONVERSION_VAL)
//            return BAD_CALCULATION;
//        return (cm * CM_TO_M * cm * CM_TO_M);
//    }
//
//    public static double cmToInch(double cm) {
//        if (cm < MIN_CONVERSION_VAL || cm > MAX_CONVERSION_VAL)
//            return BAD_CALCULATION;
//        return cm * CM_TO_INCH;
//    }
//}
