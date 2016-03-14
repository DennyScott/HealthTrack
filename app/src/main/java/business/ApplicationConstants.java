package business;


public interface ApplicationConstants {
    boolean USE_STUB_DATABASE = true;
    int APPLICATION_ERROR = -1;
    //...................CALCULATION CONSTANTS...................
    int BAD_CALCULATION = -1;
    double MAX_FLOAT_VARIATION = 0.1;
    double MIN_CONVERSION_VAL = 0;
    double MAX_CONVERSION_VAL = 1000000;

    //field constants
    double MIN_WEIGHT = 1;
    double MAX_WEIGHT = 10000;

    double MIN_WEEKS = 1;
    double MAX_WEEKS = 10000;

    double MIN_CALORIES = 1;
    double MAX_CALORIES = 10000;
    ////////////////////END CALCULATION CONSTANTS//////////////

}
