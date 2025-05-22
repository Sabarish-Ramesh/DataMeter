package org.example.calculate;

import org.example.models.UsageRecord;
import org.example.models.UsageSummary;

public class CalculateCost {
//    {
//    public static double Calculate(UsageRecord record){
//        double cost =0;
//        //if isRoaming is true then apply extra charge
//        if(record.isRoaming()){
//            cost+= record.getData4G()*0.011;
//            cost+=record.getData5G()+0.022;
//        }
//        //else normal price is applied
//        else{
//            cost+=record.getData4G()*0.01;
//            cost+=record.getData5G()*0.02;
//        }
//        return cost;
//    }
//    }

    public static double Calculate(UsageSummary summary){
        double cost = 0;
        cost += summary.getHome4g() * 0.01;
        cost += summary.getHome5g() * 0.02;
        cost += summary.getRoaming4g() * 0.011;//+10%
        cost += summary.getRoaming5g() * 0.023;//+15%
        return Math.round(cost);
    }
}
