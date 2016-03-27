package persistence;

public class DataExternalFoods extends DataFoods{
/*
        //retrieve the data in the assumed order:
        //        external foods
        //          id
        //          food_name
        //          calories
        //          proteins
        //          carbohydrdates
        //          fats
 */
    public DataExternalFoods(int id, String food_name, int calories, int proteins, int carbohydrates, int fats) {
        this.setmId(id);
        this.setmFoodDescription(food_name);

    }
}
