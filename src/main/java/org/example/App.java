package org.example;

import org.example.calculate.CalculateCost;
import org.example.models.UsageRecord;
import org.example.models.UsageSummary;
import org.example.parser.parseData;

import java.io.BufferedReader;
import java.io.File;
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
    public static void main( String[] args ) {
        //single input{
//        String line = "9000600600|InAir1234|0|13456|No";
//        String filePath = "src/main/data/january.txt";}

        //using File object for finding folder instead of file
        File Folder = new File("src/main/data");
        //this list all the files inside that folder
        File[] files = Folder.listFiles((dir, name) -> name.endsWith(".txt"));

        //check if folder having data files
        if (files == null || files.length == 0) {
            System.out.println("No data files found.");
            return;
        }
        //creating map to store the aggregate data with mobile number
        HashMap<String, UsageSummary> map = new HashMap<>();
        //loop each file
        for (File file : files) {
            System.out.println("Processing file: " + file.getName());
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                //creating the line
                String line;

                while ((line = reader.readLine()) != null) {
                    try {
                        //parsing the string using '|' ,it returns an object for a single input
                        UsageRecord record = parseData.parseData(line);

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
                    catch (IllegalArgumentException e) {
                        System.out.println("skip the invalid line: " + line);
                        System.out.println("Reason: " + e.getMessage() + "\n");
                    }
                }



            }
            //if there is error while reading the  input throw this error
            catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Mobile Number|4G|5G|4G Roaming|5G Roaming|Cost");
        //apply the threshold value 10000KB
        int threshold = 10000;
        //iterate the map and print the data
        for (Map.Entry<String, UsageSummary> data : map.entrySet()) {
            //get mobile number
            String mobile = data.getKey();
            //get values for the related mobile number
            UsageSummary summary = data.getValue();
            //calculate the cost
            double cost = CalculateCost.Calculate(summary);
            //check if exceed the threshold
            int TotalUsage = summary.getTotalUsage();
            boolean isExceed = TotalUsage > threshold;
            if (isExceed) {
                //utilisation is beyond certain limit(threshold) so adding 5% extra
                cost += cost * 0.05;
            }
            //if exceed the threshold print yes as last part else no
            String exceeded = isExceed ? "YES" : "NO";
            System.out.printf("%s   | %s | %s | %s | %s | %.0f | %s\n",
                    mobile,
                    summary.getHome4g(),
                    summary.getHome5g(),
                    summary.getRoaming4g(),
                    summary.getRoaming5g(),
                    cost,
                    exceeded

            );
        }

    }
}
