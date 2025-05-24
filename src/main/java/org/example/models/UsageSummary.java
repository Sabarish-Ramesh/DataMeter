package org.example.models;

public class UsageSummary {
    private int home4g=0;
    private int home5g=0;
    private int roaming4g=0;
    private int roaming5g = 0;
    //aggregate the values
    public void aggregate(UsageRecord record) {
        if(record.isRoaming()){
            roaming4g += record.getData4G();
            roaming5g+=record.getData5G();
        }
        else{
            home4g += record.getData4G();
            home5g +=record.getData5G();
        }
    }
    public int getHome4g(){
        return home4g;
    }

    public int getHome5g() {
        return home5g;
    }

    public int getRoaming4g() {
        return roaming4g;
    }

    public int getRoaming5g() {
        return roaming5g;
    }
    public int total4G(){
        return home4g+roaming4g;
    }
    public int total5G(){
        return home5g+roaming5g;
    }
    public int getTotalUsage() {
        return total4G() + total5G(); // home + roaming
    }
}
