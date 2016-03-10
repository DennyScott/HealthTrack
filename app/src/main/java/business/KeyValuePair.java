package business;

/**
 * Write Java Docs (basic description for class, and description for each
 * complicated method).
 */
public class KeyValuePair implements Comparable<KeyValuePair> {
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
        boolean retVal = false;
        if (other == this && this.key.toLowerCase().equals(((KeyValuePair) other).key.toLowerCase())
                && this.value ==(((KeyValuePair)other).value)) retVal = true;
        return retVal;
    }

    @Override
    public int compareTo(KeyValuePair other) {
        int retVal = 0;
        if(other.value > this.value){
            retVal = 1;
        }
        else if(other.value < this.value){
            retVal = -1;
        }
        return retVal;
    }
}
