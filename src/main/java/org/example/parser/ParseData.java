package org.example.parser;

import org.example.models.UsageRecord;

public class ParseData {
    public static UsageRecord parserData(String line){
        //split the line using split()
        String[] part = line.split("\\|");
//        System.out.println(part.length);
        //validation:string must have 5 parts
        if (part.length != 5) {
            throw new IllegalArgumentException("String must contain 5 parts: " + line);
        }

        String mobileno = part[0].trim();
        //validation:check the mobile numebr must have 10 digits
        if(!mobileno.matches("\\d{10}"))
        {
            throw new IllegalArgumentException("mobile number must be 10 digits: "+line);
        }
        //storing the splitted value
        String towername = part[1].trim();
        int data4G, data5G;
        try {
            data4G = Integer.parseInt(part[2].trim());
            data5G = Integer.parseInt(part[3].trim());
            //check if parsed is less than 0
            if (data4G < 0 || data5G < 0) {
                throw new IllegalArgumentException("Data values must be not negative");
            }
        }
        //else not a number
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid 4G or 5G data: must be a number");
        }
        //equalsIgnoreCase--it is more optimal
//        boolean isRoaming = part[4].trim().equalsIgnoreCase("yes");-->this may accept anything else "yes"

        String roaming = part[4].trim().toLowerCase();
        boolean isRoaming;
        if (roaming.equals("yes")) {
            isRoaming = true;
        } else if (roaming.equals("no")) {
            isRoaming = false;
        } else {
            throw new IllegalArgumentException("Roaming value must be 'Yes' or 'No'");
        }
        return new UsageRecord(mobileno,towername,data4G,data5G,isRoaming);
    }
}