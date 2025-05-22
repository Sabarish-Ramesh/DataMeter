package org.example.calculate;

import org.example.models.UsageRecord;

public class CalculateCost {
    public static double Calculate(UsageRecord record){
        double cost =0;
        if(record.isRoaming()){
            cost+= record.getData4G()*0.011;
            cost+=record.getData5G()+0.022;
        }
        else{
            cost+=record.getData4G()*0.01;
            cost+=record.getData5G()*0.02;
        }
        return cost;
    }
}
