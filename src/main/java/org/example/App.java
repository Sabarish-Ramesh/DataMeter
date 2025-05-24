package org.example;

import org.example.calculate.CalculateCost;
import org.example.models.UsageRecord;
import org.example.models.UsageSummary;
import org.example.parser.ParseData;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Hello world!
 *
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        //single input
//        String line = "9000600600|InAir1234|0|13456|No";
        String filePath = "src/main/data/january.txt";
        try(BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            //creating the line
            String line;
            //creating map
            HashMap<String, UsageSummary> map = new HashMap<>();
            while((line = reader.readLine())!=null) {
                try {
                    //parsing the string using '|' ,it returns an object for a single input
                    UsageRecord record = ParseData.parserData(line);

                    //get mobileno and aggregate it using UsageSummary
                    String mobileno = record.getMobileno();
                    //add a new mobile number
                    map.putIfAbsent(mobileno, new UsageSummary());
                    //aggregate it the exist mobile number
                    map.get(mobileno).aggregate(record);
//                    {
                    //cost calculation -feed created object
//                    double cost = CalculateCost.Calculate(record);
//                    System.out.printf("%s | %s | %s | %s | %s | %.0f\n",
//                            record.getMobileno(),
//                            record.isRoaming() ? 0 : record.getData4G(),
//                            record.isRoaming() ? 0 : record.getData5G(),
//                            record.isRoaming() ? record.getData4G() : 0,
//                            record.isRoaming() ? record.getData5G() : 0,
//                            cost
//                    );
//                }
                }
                catch (IllegalArgumentException e){
                    System.out.println("skip the invalid line: "+line);
                    System.out.println("Reason: " + e.getMessage()+"\n");
                }
            }
            System.out.println("Mobile Number|4G|5G|4G Roaming|5G Roaming|Cost");
            //iterate the map and print the data
            for(Map.Entry<String,UsageSummary> data:map.entrySet())
            {
                //get mobile number
                String mobile = data.getKey();
                //get values for the related mobile number
                UsageSummary summary = data.getValue();
                double cost = CalculateCost.Calculate(summary);
                System.out.printf("%s   | %s | %s | %s | %s | %.0f\n",
                        mobile,
                        summary.getHome4g(),
                        summary.getHome5g(),
                        summary.getRoaming4g(),
                        summary.getRoaming5g(),
                        cost
                );
            }
        }
        //if there is error while reading the  input throw this error
        catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
}
