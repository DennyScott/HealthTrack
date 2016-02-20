package business;

/**
 * Created by Deny Raaen on 20/02/2016.
 */
public class KeyValuePair implements Comparable<KeyValuePair> {
    float value;
    String key;

    KeyValuePair(String key, float value) {
        this.value = value;
        this.key = key;
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
