package org.example;

import org.example.calculate.CalculateCost;
import org.example.models.UsageRecord;
import org.example.parser.ParseData;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        //single input
        String line = "9000600600|InAir1234|0|13456|No";

        //parsing the string using '|' ,it returns an object for a single input
        UsageRecord record = ParseData.parserData(line);

        System.out.println(record);
        //cost calculation -feed created object

        double cost = CalculateCost.Calculate(record);
        System.out.printf( "%s | %s | %s | %s | %s | %.0f",
                record.getMobileno(),
                record.isRoaming()?0:record.getData4G(),
                record.isRoaming()?0:record.getData5G(),
                record.isRoaming()?record.getData4G():0,
                record.isRoaming()?record.getData5G():0,
                cost
        );
    }
}
