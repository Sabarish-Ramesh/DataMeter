package org.example.models;

public class UsageRecord {
    private String mobileno;
    private String towername;
    private int data4G;
    private int data5G;
    private boolean isRoaming;

    public UsageRecord(String mobileno, String towername, int data4G, int data5G, boolean isRoaming) {
        this.mobileno = mobileno;
        this.towername = towername;
        this.data4G = data4G;
        this.data5G = data5G;
        this.isRoaming = isRoaming;
    }

    public String getMobileno() {
        return mobileno;
    }
    public int getData4G(){
        return data4G;
    }
    public int getData5G(){
        return data5G;
    }
    public boolean isRoaming(){
        return isRoaming;
    }
}
