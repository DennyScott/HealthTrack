package business;


public class UnitConverter implements  ApplicationConstants  {
    private final double LB_TO_KG = 0.453592;
    private final double KG_TO_LB = 2.20462;
    private final double CM_TO_M = 0.01;
    private final double CM_TO_INCH = 0.393701;

    public double lbToKg(double lb) {
        return lb * LB_TO_KG;
    }

    public double kgToLb(double kg) {
        return kg * KG_TO_LB;
    }

    public double cmToM2(double cm) {
        return (cm * CM_TO_M * cm * CM_TO_M);
    }

    public double cmToInch(double cm) {
        return cm * CM_TO_INCH;
    }
}
