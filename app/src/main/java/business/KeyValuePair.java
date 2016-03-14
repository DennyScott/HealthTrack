package business;


public class KeyValuePair implements Comparable<KeyValuePair>, ApplicationConstants  {
    float value;
    String key;
    String unit;

    KeyValuePair(String key, float value, String unit) {
        this.value = value;
        this.key = key;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object other) {
        if (!other.getClass().getName().equals(KeyValuePair.class.getName())) return false;
        boolean retVal = false;
        if (other == this && this.key.toLowerCase().equals(((KeyValuePair) other).key.toLowerCase())
                && Math.abs(this.value - (((KeyValuePair) other).value)) < MAX_FLOAT_VARIATION)
            retVal = true;
        return retVal;
    }

    @Override
    public int compareTo(KeyValuePair other) {
        int retVal = 0;
        if (Math.abs(other.value - this.value) < MAX_FLOAT_VARIATION) return retVal;
        if(other.value > this.value){
            retVal = 1;
        }
        else if(other.value < this.value){
            retVal = -1;
        }
        return retVal;
    }
}
