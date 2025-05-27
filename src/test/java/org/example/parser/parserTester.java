package org.example.parser;

import org.example.models.UsageRecord;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class parserTester {
    //checking parseData return obj value is equalto static data feeded
    @Test
    public void checkValidInput() {
        String line = "9000600600|TowerA|1000|2000|No";
        UsageRecord record = parseData.parseData(line);

        assertEquals("9000600600", record.getMobileno());
        assertEquals(1000, record.getData4G());
        assertEquals(2000, record.getData5G());
        assertFalse(record.isRoaming());

//        org.opentest4j.AssertionFailedError:
//        Expected :true
//        Actual   :false
//        assertTrue(record.isRoaming());

    }

    @Test
    //must have 5 parts
    public void testMissingFields() {
        String line = "9000600600|TowerA|100";
        //to catch result -> use Exception var_name
        Exception result = assertThrows(IllegalArgumentException.class, () -> {
            parseData.parseData(line);
        });
        System.out.println(result);
    }

    @Test
    //mobile number must be 10digits
    public void checkValidMobileno() {
        //Expected java.lang.IllegalArgumentException to be thrown, but nothing was thrown.
//        String line = "9000600600|InAir1234|0|13456|No";

        String line = "90006002600|InAir1234|0|13456|No";
        //to catch result -> use Exception var_name
        Exception result = assertThrows(IllegalArgumentException.class, () -> {
            parseData.parseData(line);
        });
        System.out.println(result);
    }

    @Test
    //4g and 5g must be notnegative
    public void checkNegativeDataUsage() {
        String line = "9000600600|TowerA|-100|200|No";
        Exception res = assertThrows(IllegalArgumentException.class, () -> {
            parseData.parseData(line);
        });
        System.out.println(res);
    }

    @Test
    //4g and 5g value must be a number
    public void checkNonNumericData() {
        String line = "9000600600|TowerA|abc|200|No";
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            parseData.parseData(line);
        });
        System.out.println(e);
    }

    @Test
    //roaming value must be yes or no
    public void testInvalidRoamingValue() {
        String line = "9000600600|TowerA|100|200|Maybe";
        Exception e  = assertThrows(IllegalArgumentException.class, () -> {
            parseData.parseData(line);
        });
        System.out.println(e);
    }




}
