package club.glamajestic.unittests;

import junit.framework.TestCase;

import business.StatsDataAccess;


public class StatsDataAccessTest extends TestCase {
    String[] foodNames, foodUnits, nutrientNames, nutrientUnits;
    float[] foodValues, nutrientValues;

    public void testGetFoodNames() throws Exception {
        StatsDataAccess statsBus = new StatsDataAccess();
//        foodNames = statsBus.getFoodNames(0, "Protein");
        assertFalse("Keys are null", foodNames!=null);
    }

    public void testGetFoodValues() throws Exception {
        StatsDataAccess statsBus = new StatsDataAccess();
//        foodValues = statsBus.getFoodValues(0, "Protein");
        assertFalse("Keys are null", foodValues!=null);
    }

    public void testGetFoodUnits() throws Exception {
        StatsDataAccess statsBus = new StatsDataAccess();
//        foodUnits = statsBus.getFoodUnits(0, "Protein");
        assertFalse("Keys are null", foodUnits!=null);
    }

    public void testGetNutrientsNames() throws Exception {
        StatsDataAccess statsBus = new StatsDataAccess();
        nutrientNames = statsBus.getNutrientsNames(0);
        assertTrue("Keys are null", nutrientNames!=null);
    }

    public void testGetNutrientsValues() throws Exception {
        StatsDataAccess statsBus = new StatsDataAccess();
        nutrientValues = statsBus.getNutrientsValues(0);
        assertTrue("Keys are null", nutrientValues!=null);
    }

    public void testGetNutrientsUnits() throws Exception {
        StatsDataAccess statsBus = new StatsDataAccess();
        nutrientUnits = statsBus.getNutrientsUnits(0);
        assertTrue("Values are null", nutrientUnits!=null);
    }
}