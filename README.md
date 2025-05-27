#  DataMeter

A Java application to read and aggregate mobile data usage across multiple monthly logs, calculate total cost with roaming charges and surcharges for exceeding usage thresholds.

---

##  Requirements
- Java 21
- Text-based input files in `.txt` format inside `src/main/data/`
- Files should be placed inside:
```
src/main/data/
```

---

## How to Run

#### Step 1: Clone the repository
```bash
git clone https://github.com/Sabarish-Ramesh/DataMeter.git
cd DataMeter
```

#### Step 2: Compile Java files
#### For Windows/Linux/macOS:
```bash
javac -d out src/main/java/org/example/*.java src/main/java/org/example/models/*.java src/main/java/org/example/parser/*.java src/main/java/org/example/calculate/*.java
```

#### Step 3: Run the application
```bash
java -cp out org.example.App
```

---


###  In IntelliJ IDEA
- Open the project folder
- Right-click `App.java` → `Run App.main()`

---

##  Input Format
```
MobileNumber|Tower|4GUsage|5GUsage|Roaming
```
Example:
```
9000600600|TowerA|1000|2000|No
9000600601|InAir125|1345|0|Yes
```


##  Output Format

```
Mobile Number|4G|5G|4G Roaming|5G Roaming|Cost
9000600600|0|13456|0|0|123
9000600601|0|0|1345|0|345
```
---

## Notes
- Automatically handles multiple input files
- Applies 5% surcharge if usage exceeds 10,000 KB (default threshold)
- Shows invalid input lines with clear error messages
