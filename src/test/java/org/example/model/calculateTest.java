package org.example.model;

import org.example.calculate.CalculateCost;
import org.example.models.UsageRecord;
import org.example.models.UsageSummary;
import org.junit.jupiter.api.Test;

public class calculateTest {

    @Test
    public void cost_test() {
        UsageSummary summary;

        // Case 1: Home user only
        // Home: 4G = 1000 KB, 5G = 500 KB
        // Expected  = 1000×0.01 + 500×0.02 = 10 + 10 = 20
        summary = new UsageSummary();
        summary.aggregate(new UsageRecord("9000000001", "TowerA", 1000, 500, false));
        System.out.println("Case 1 (Home only) → Cost: " + CalculateCost.Calculate(summary));

        // Case 2: Roaming user only
        // Roaming: 4G = 800 KB, 5G = 1200 KB
        // Expected  = 800×0.011 + 1200×0.023 = 8.8 + 27.6 = 36.4 → Rounded = 36
        summary = new UsageSummary();
        summary.aggregate(new UsageRecord("9000000002", "TowerB", 800, 1200, true));
        System.out.println("Case 2 (Roaming only) → Cost: " + CalculateCost.Calculate(summary));

        // Case 3: Mixed usage
        // Home: 4G = 500, 5G = 1000 → 5 + 20 = 25
        // Roaming: 4G = 400, 5G = 500 → 4.4 + 11.5 = 15.9
        // Total = 25 + 15.9 = ₹40.9 → Rounded = 41
        summary = new UsageSummary();
        summary.aggregate(new UsageRecord("9000000003", "TowerC", 500, 1000, false));
        summary.aggregate(new UsageRecord("9000000003", "TowerD", 400, 500, true));
        System.out.println("Case 3 (Mixed usage) → Cost: " + CalculateCost.Calculate(summary));

        // Case 4: All zeros
        // Home + Roaming = 0
        // Total  = 0
        summary = new UsageSummary();
        summary.aggregate(new UsageRecord("9000000004", "TowerZ", 0, 0, true));
        summary.aggregate(new UsageRecord("9000000004", "TowerZ", 0, 0, false));
        System.out.println("Case 4 (Zero usage) → Cost: " + CalculateCost.Calculate(summary));

        // Case 5: Large usage
        // Home: 4G = 5000, 5G = 10000 → 50 + 200 = 250
        // Roaming: 4G = 2000, 5G = 3000 → 22 + 69 = 91
        // Total = 250 + 91 = 341
        summary = new UsageSummary();
        summary.aggregate(new UsageRecord("9000000005", "TowerX", 5000, 10000, false));
        summary.aggregate(new UsageRecord("9000000005", "TowerY", 2000, 3000, true));
        System.out.println("Case 5 (Large usage) → Cost: " + CalculateCost.Calculate(summary));
    }
}
