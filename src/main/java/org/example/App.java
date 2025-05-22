package org.example;

import org.example.calculate.CalculateCost;
import org.example.models.UsageRecord;
import org.example.parser.ParseData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        //single input
//        String line = "9000600600|InAir1234|0|13456|No";
        String filePath = "src/main/data/data_usage_demo.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            //creating the line
            String line;
            while((line = reader.readLine())!=null) {
                try {
                    //parsing the string using '|' ,it returns an object for a single input
                    UsageRecord record = ParseData.parserData(line);

//                    System.out.println(record);

                    //cost calculation -feed created object
                    double cost = CalculateCost.Calculate(record);
                    System.out.printf("%s | %s | %s | %s | %s | %.0f\n",
                            record.getMobileno(),
                            record.isRoaming() ? 0 : record.getData4G(),
                            record.isRoaming() ? 0 : record.getData5G(),
                            record.isRoaming() ? record.getData4G() : 0,
                            record.isRoaming() ? record.getData5G() : 0,
                            cost
                    );
                }
                catch (IllegalArgumentException e){
                    System.out.println("skip the invalid line: "+line);
                    System.out.println("Reason: " + e.getMessage());
                }
            }
        }
        //if there is error while reading the  input throw this error
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
