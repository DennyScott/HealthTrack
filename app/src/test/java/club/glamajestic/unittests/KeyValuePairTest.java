package business;

import junit.framework.TestCase;

import business.KeyValuePair;


public class KeyValuePairTest extends TestCase {

    public void testEquals() throws Exception {
        KeyValuePair testDuplicateClassesA = new KeyValuePair("Key",0.5f,"units");
        KeyValuePair testDuplicateClassesB = new KeyValuePair("Key",0.5f,"units");
        
        KeyValuePair testDissimilarClassesA = new KeyValuePair("Key",0.5f,"units");
        KeyValuePair testDissimilarClassesB = new Object();
        
        KeyValuePair testNonEqualDataKeyA = new KeyValuePair("KeyA",0.5f,"units");
        KeyValuePair testNonEqualDataKeyB = new KeyValuePair("KeyB",0.5f,"units");

        KeyValuePair testNonEqualDataFloatA = new KeyValuePair("Key",0.5f,"units");
        KeyValuePair testNonEqualDataFloatB = new KeyValuePair("Key",1.5f,"units");

        KeyValuePair testNonEqualDataUnitsA = new KeyValuePair("Key",0.5f,"unitsA");
        KeyValuePair testNonEqualDataUnitsB = new KeyValuePair("Key",0.5f,"unitsB");

        KeyValuePair testEqualDataNegativeA = new KeyValuePair("Key",-0.5f,"units");
        KeyValuePair testEqualDataNegativeB = new KeyValuePair("Key",-0.5f,"units");

        KeyValuePair testEqualDataZeroA = new KeyValuePair("Key",0.0f,"units");
        KeyValuePair testEqualDataZeroB = new KeyValuePair("Key",0.0f,"units");

        assertTrue("Identical constructed data is equal", testDuplicateClassesA.equals(testDuplicateClassesB));
        assertTrue("Different classes are not equal", !testDissimilarClassesA.equals(testDissimilarClassesB));
        assertTrue("Keys are not equal", !testNonEqualDataKeyA.equals(testNonequalDataKeyB));
        assertTrue("Data is not equal", !testNonEqualDataFloatA.equals(testNonEqualDataFloatB));
        assertTrue("Units are not equal", !testNonEqualDataUnitsA.equals(testNonEqualDataUnitsB));
        assertTrue("Negative values are still equal", testEqualDataNegativeA.equals(testEqualDataNegativeB));
        assertTrue("Zero values are still equal", testEqualDataZeroA.equals(testEqualDataZeroB));
    }

    public void testCompareTo() throws Exception {
        KeyValuePair testDuplicateClassesA = new KeyValuePair("Key",0.5f,"units");
        KeyValuePair testDuplicateClassesB = new KeyValuePair("Key",0.5f,"units");

        KeyValuePair testNonEqualDataKeyA = new KeyValuePair("KeyA",0.5f,"units");
        KeyValuePair testNonEqualDataKeyB = new KeyValuePair("KeyB",0.5f,"units");

        KeyValuePair testNonEqualDataFloatA = new KeyValuePair("Key",0.5f,"units");
        KeyValuePair testNonEqualDataFloatB = new KeyValuePair("Key",1.5f,"units");

        KeyValuePair testNonEqualDataUnitsA = new KeyValuePair("Key",0.5f,"unitsA");
        KeyValuePair testNonEqualDataUnitsB = new KeyValuePair("Key",0.5f,"unitsB");

        KeyValuePair testEqualDataNegativeA = new KeyValuePair("Key",-0.5f,"units");
        KeyValuePair testEqualDataNegativeB = new KeyValuePair("Key",-0.5f,"units");

        KeyValuePair testEqualDataZeroA = new KeyValuePair("Key",0.0f,"units");
        KeyValuePair testEqualDataZeroB = new KeyValuePair("Key",0.0f,"units");

        assertTrue("Identical constructed data is equal", testDuplicateClassesA.compareTo(testDuplicateClassesB) == 0);
        assertTrue("Keys are not equal", testNonEqualDataKeyA.compareTo(testNonequalDataKeyB) != 0 );
        assertTrue("Data is not equal", testNonEqualDataFloatA.compareTo(testNonEqualDataFloatB) != 0 );
        assertTrue("Units are not equal",  testNonEqualDataUnitsA.compareTo(testNonEqualDataUnitsB) != 0 );
        assertTrue("Negative values are still equal", testEqualDataNegativeA.compareTo(testEqualDataNegativeB) == 0 );
        assertTrue("Zero values are still equal", testEqualDataZeroA.compareTo(testEqualDataZeroB) == 0 );

        assertTrue("A is less than B", testNonEqualDataFloatA.compareTo(testNonEqualDataFloatB) == 1 );
        assertTrue("B is greater than A", testNonEqualDataFloatB.compareTo(testNonEqualDataFloatA) == -1 );
    }
}