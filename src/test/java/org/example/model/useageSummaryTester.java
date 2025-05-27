package org.example.model;

import org.example.models.UsageRecord;
import org.example.models.UsageSummary;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class useageSummaryTester {

    @Test
    //check homeUse working by set roaming as "false"
    public void testHomeUseOnly() {
        UsageSummary summary = new UsageSummary();
        UsageRecord home1 = new UsageRecord("9000000001", "TowerA", 1000, 2000, false);
        UsageRecord home2 = new UsageRecord("9000000001", "TowerB", 500, 1000, false);

        summary.aggregate(home1);
        summary.aggregate(home2);
        //4g->aggregate->1000+500=1500
        assertEquals(1500, summary.getHome4g());
        //5g->aggregate->2000+1000=3000
        assertEquals(3000, summary.getHome5g());
        assertEquals(0, summary.getRoaming4g());
        assertEquals(0, summary.getRoaming5g());
    }

    @Test
    //check roaming aggregate working by set roaming as "true"
    public void testRoamingUseOnly() {
        UsageSummary summary = new UsageSummary();
        UsageRecord roam1 = new UsageRecord("9000000001", "TowerC", 300, 600, true);
        UsageRecord roam2 = new UsageRecord("9000000001","daa",10,20,true);

        summary.aggregate(roam1);
        summary.aggregate(roam2);
        assertEquals(0, summary.getHome4g());
        assertEquals(0, summary.getHome5g());
        //roaming4g = 300
        assertEquals(310, summary.getRoaming4g());
        assertEquals(620, summary.getRoaming5g());
    }

    @Test
    //check mixeduse working by set roaming as "false" and "true"
    public void checkMixedUse() {
        UsageSummary summary = new UsageSummary();
        summary.aggregate(new UsageRecord("9000000001", "TowerA", 1000, 2000, false)); // home
        summary.aggregate(new UsageRecord("9000000001", "TowerB", 300, 400, true));   // roaming

        assertEquals(1000, summary.getHome4g());
        assertEquals(2000, summary.getHome5g());
        assertEquals(300, summary.getRoaming4g());
        assertEquals(400, summary.getRoaming5g());
    }

    @Test
    ////check total working by set roaming as "false" and "true" and "aggregateall"
    public void testTotalUseCalculation() {
        UsageSummary summary = new UsageSummary();
        summary.aggregate(new UsageRecord("9000000001", "TowerA", 2000, 3000, false));
        summary.aggregate(new UsageRecord("9000000001", "TowerB", 1000, 500, true));

        int total = summary.getTotalUsage(); // 2000+3000+1000+500 = 6500
        assertEquals(6500, total);
    }
}
