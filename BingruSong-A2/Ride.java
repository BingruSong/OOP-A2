import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class Ride implements RideInterface {
    private String rideName;
    private String rideType;
    private Employee operator;
    private int maxRider;
    private int numOfCycles;
    
    private Queue<Visitor> waitingLine;
    private LinkedList<Visitor> rideHistory;
    
    public Ride() {
        this.rideName = "Unknown Ride";
        this.rideType = "General";
        this.operator = null;
        this.maxRider = 2;
        this.numOfCycles = 0;
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
    }
    
    public Ride(String rideName, String rideType, Employee operator, int maxRider) {
        this.rideName = rideName;
        this.rideType = rideType;
        this.operator = operator;
        this.maxRider = maxRider;
        this.numOfCycles = 0;
        this.waitingLine = new LinkedList<>();
        this.rideHistory = new LinkedList<>();
    }
    
    // Getters and setters
    public String getRideName() { return rideName; }
    public void setRideName(String rideName) { this.rideName = rideName; }
    public String getRideType() { return rideType; }
    public void setRideType(String rideType) { this.rideType = rideType; }
    public Employee getOperator() { return operator; }
    public void setOperator(Employee operator) { this.operator = operator; }
    public int getMaxRider() { return maxRider; }
    public void setMaxRider(int maxRider) { this.maxRider = maxRider; }
    public int getNumOfCycles() { return numOfCycles; }
    public void setNumOfCycles(int numOfCycles) { this.numOfCycles = numOfCycles; }
    
    // Part 3: Queue Methods
    @Override
    public void addVisitorToQueue(Visitor visitor) {
        if (visitor != null) {
            waitingLine.add(visitor);
            System.out.println("Success: " + visitor.getName() + " added to the queue for " + rideName);
        } else {
            System.out.println("Error: Cannot add null visitor to queue");
        }
    }
    
    @Override
    public void removeVisitorFromQueue() {
        if (!waitingLine.isEmpty()) {
            Visitor removedVisitor = waitingLine.poll();
            System.out.println("Success: " + removedVisitor.getName() + " removed from the queue");
        } else {
            System.out.println("Error: Queue is empty, cannot remove visitor");
        }
    }
    
    @Override
    public void printQueue() {
        if (waitingLine.isEmpty()) {
            System.out.println("Queue for " + rideName + " is empty");
        } else {
            System.out.println("Current queue for " + rideName + ":");
            int position = 1;
            for (Visitor visitor : waitingLine) {
                System.out.println(position + ". " + visitor.getName() + " (ID: " + visitor.getVisitorId() + ")");
                position++;
            }
        }
    }
    
    // Part 4A: Ride History Implementation
    @Override
    public void addVisitorToHistory(Visitor visitor) {
        if (visitor != null) {
            if (!rideHistory.contains(visitor)) {
                rideHistory.add(visitor);
                System.out.println("Success: " + visitor.getName() + " added to ride history for " + rideName);
            } else {
                System.out.println("Info: " + visitor.getName() + " is already in ride history");
            }
        } else {
            System.out.println("Error: Cannot add null visitor to ride history");
        }
    }
    
    @Override
    public boolean checkVisitorFromHistory(Visitor visitor) {
        if (visitor != null) {
            boolean found = rideHistory.contains(visitor);
            System.out.println("Check result: " + visitor.getName() + " is " + (found ? "" : "not ") + "in ride history");
            return found;
        } else {
            System.out.println("Error: Cannot check null visitor");
            return false;
        }
    }
    
    @Override
    public int numberOfVisitors() {
        int count = rideHistory.size();
        System.out.println("Number of visitors in ride history: " + count);
        return count;
    }
    
    @Override
    public void printRideHistory() {
        if (rideHistory.isEmpty()) {
            System.out.println("Ride history for " + rideName + " is empty");
        } else {
            System.out.println("Ride history for " + rideName + " (using Iterator):");
            Iterator<Visitor> iterator = rideHistory.iterator();
            int position = 1;
            while (iterator.hasNext()) {
                Visitor visitor = iterator.next();
                System.out.println(position + ". " + visitor.toString());
                position++;
            }
        }
    }
    
    // Part 4B: Sorting Method
    public void sortRideHistory(Comparator<Visitor> comparator) {
        if (!rideHistory.isEmpty()) {
            Collections.sort(rideHistory, comparator);
            System.out.println("Ride history sorted successfully");
        } else {
            System.out.println("Cannot sort empty ride history");
        }
    }
    
    // Part 5: Run Ride Cycle
    @Override
    public void runOneCycle() {
        // Check if operator is assigned
        if (operator == null) {
            System.out.println("Error: Cannot run ride - no operator assigned");
            return;
        }
        
        // Check if there are visitors in queue
        if (waitingLine.isEmpty()) {
            System.out.println("Error: Cannot run ride - no visitors in queue");
            return;
        }
        
        System.out.println("Running ride cycle for " + rideName + "...");
        System.out.println("Operator: " + operator.getName());
        System.out.println("Max riders per cycle: " + maxRider);
        
        int ridersThisCycle = 0;
        
        // Remove visitors from queue and add to history
        while (ridersThisCycle < maxRider && !waitingLine.isEmpty()) {
            Visitor rider = waitingLine.poll();
            addVisitorToHistory(rider);
            ridersThisCycle++;
        }
        
        numOfCycles++;
        System.out.println("Ride cycle completed. " + ridersThisCycle + " visitors took the ride.");
        System.out.println("Total cycles run: " + numOfCycles);
    }
    
    // Part 6: Export to file
     public void exportRideHistory(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Write header with ride information
            writer.println("Ride Name: " + rideName);
            writer.println("Ride Type: " + rideType);
            writer.println("Total Visitors: " + rideHistory.size());
            writer.println("Total Cycles: " + numOfCycles);
            writer.println("--- Visitor Data ---");
            
            // Write each visitor's data in CSV format
            for (Visitor visitor : rideHistory) {
                String line = String.format("%s,%d,%s,%s,%s",
                    visitor.getName(),
                    visitor.getAge(),
                    visitor.getEmail(),
                    visitor.getVisitorId(),
                    visitor.getTicketType());
                writer.println(line);
            }
            
            System.out.println("Success: Ride history exported to " + filename);
            System.out.println("Total visitors exported: " + rideHistory.size());
            
        } catch (IOException e) {
            System.out.println("Error: Failed to export ride history to " + filename);
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    // Part 7: Import from file
    public void importRideHistory(String filename) {
        try {
            // Check if file exists
            if (!Files.exists(Paths.get(filename))) {
                System.out.println("Error: File " + filename + " does not exist");
                return;
            }
            
            int importedCount = 0;
            int lineNumber = 0;
            
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                boolean isVisitorData = false;
                
                while ((line = reader.readLine()) != null) {
                    lineNumber++;
                    
                    // Skip empty lines and header information
                    if (line.trim().isEmpty() || line.startsWith("Ride Name:") || 
                        line.startsWith("Ride Type:") || line.startsWith("Total Visitors:") ||
                        line.startsWith("Total Cycles:") || line.equals("--- Visitor Data ---")) {
                        if (line.equals("--- Visitor Data ---")) {
                            isVisitorData = true;
                        }
                        continue;
                    }
                    
                    // Only process visitor data after the header
                    if (isVisitorData) {
                        try {
                            // Split CSV line
                            String[] parts = line.split(",");
                            if (parts.length == 5) {
                                String name = parts[0].trim();
                                int age = Integer.parseInt(parts[1].trim());
                                String email = parts[2].trim();
                                String visitorId = parts[3].trim();
                                String ticketType = parts[4].trim();
                                
                                // Create new visitor and add to history
                                Visitor visitor = new Visitor(name, age, email, visitorId, ticketType);
                                
                                // Check if visitor already exists to avoid duplicates
                                if (!rideHistory.contains(visitor)) {
                                    rideHistory.add(visitor);
                                    importedCount++;
                                    System.out.println("Imported: " + name);
                                } else {
                                    System.out.println("Skipped duplicate: " + name);
                                }
                            } else {
                                System.out.println("Warning: Invalid data format on line " + lineNumber);
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Warning: Invalid age format on line " + lineNumber);
                        } catch (Exception e) {
                            System.out.println("Warning: Error processing line " + lineNumber + ": " + e.getMessage());
                        }
                    }
                }
            }
            
            System.out.println("Success: Imported " + importedCount + " visitors from " + filename);
            
        } catch (IOException e) {
            System.out.println("Error: Failed to import ride history from " + filename);
            System.out.println("Exception: " + e.getMessage());
        }
    }
    
    // Helper method to clear ride history (for testing)
    public void clearRideHistory() {
        rideHistory.clear();
        System.out.println("Ride history cleared");
    }
}