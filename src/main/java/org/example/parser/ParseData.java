package org.example.parser;

import org.example.models.UsageRecord;

public class ParseData {
    public static UsageRecord parserData(String line){
        //split the line using split()
        String[] part = line.split("\\|");
//        System.out.println(part.length);
        //validation:string must have 5 parts
        if (part.length != 5) {
            throw new IllegalArgumentException("Invalid input line: " + line);
        }

        String mobileno = part[0].trim();
        //validation:check the mobile numebr must have 10 digits
        if(!mobileno.matches("\\d{10}"))
        {
            throw new IllegalArgumentException("mobile number must be 10 digits: "+line);
        }
        //storing the splitted value
        String towername = part[1].trim();
        int data4G = Integer.parseInt(part[2].trim());
        int data5G = Integer.parseInt(part[3].trim());
        //equalsIgnoreCase--it is more optimal
        boolean isRoaming = part[4].trim().equalsIgnoreCase("yes");
        return new UsageRecord(mobileno,towername,data4G,data5G,isRoaming);
    }
}