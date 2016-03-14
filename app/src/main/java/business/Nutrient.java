package business;


public class Nutrient implements  ApplicationConstants  {
    private int NutrientID;
    private String NutrientSymbol;
    private String NutrientUnit;
    private String NutrientName;

    public int getNutrientID() {
        return NutrientID;
    }

    public void setNutrientID(int nutrientID) {
        NutrientID = nutrientID;
    }

    public String getNutrientSymbol() {
        return NutrientSymbol;
    }

    public void setNutrientSymbol(String nutrientSymbol) {
        NutrientSymbol = nutrientSymbol;
    }

    public String getNutrientUnit() {
        return NutrientUnit;
    }

    public void setNutrientUnit(String nutrientUnit) {
        NutrientUnit = nutrientUnit;
    }

    public String getNutrientName() {
        return NutrientName;
    }

    public void setNutrientName(String nutrientName) {
        NutrientName = nutrientName;
    }
}
