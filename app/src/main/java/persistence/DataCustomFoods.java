package persistence;

public class DataCustomFoods extends DataFoods {
    public DataCustomFoods(int id, String food_name, int calories, int proteins, int carbohydrates, int fats) {
        super();
        this.setmId(id);
        this.setmFoodDescription(food_name);
    }

    /*
            //retreieve the data in the assumed order:
        //        custom foods
        //              id
        //              food_name
        //              calories
        //              proteins
        //              carbohydrdates
        //              fats
     */

}
