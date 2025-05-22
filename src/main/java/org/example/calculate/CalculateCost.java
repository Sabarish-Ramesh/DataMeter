package org.example.calculate;

import org.example.models.UsageRecord;

public class CalculateCost {
    public static double Calculate(UsageRecord record){

        double cost =0;
        //if isRoaming is true then apply extra charge
        if(record.isRoaming()){
            cost+= record.getData4G()*0.011;
            cost+=record.getData5G()+0.022;
        }
        //else normal price is applied
        else{
            cost+=record.getData4G()*0.01;
            cost+=record.getData5G()*0.02;
        }
        return cost;
    }
}
