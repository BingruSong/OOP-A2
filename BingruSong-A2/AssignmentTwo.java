import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class AssignmentTwo {
    public static void main(String[] args) {
        AssignmentTwo assignment = new AssignmentTwo();
        
        // Call each part method to demonstrate functionality
        assignment.partThree();
        assignment.partFourA();
        assignment.partFourB();
        assignment.partFive();
        assignment.partSix();
        assignment.partSeven();
    }
    
    public void partThree() {
        System.out.println("=== Part 3: Queue Management ===");
        
        // Create an employee for the ride
        Employee rideOperator = new Employee("John Smith", 25, "john.smith@themepark.com", "E001", "Ride Operator");
        
        // Create a ride
        Ride rollerCoaster = new Ride("Thunder Bolt", "Roller Coaster", rideOperator, 4);
        
        // Create visitors
        Visitor visitor1 = new Visitor("Alice Johnson", 20, "alice@email.com", "V001", "Adult");
        Visitor visitor2 = new Visitor("Bob Brown", 25, "bob@email.com", "V002", "Adult");
        Visitor visitor3 = new Visitor("Charlie Davis", 18, "charlie@email.com", "V003", "Student");
        Visitor visitor4 = new Visitor("Diana Wilson", 30, "diana@email.com", "V004", "VIP");
        Visitor visitor5 = new Visitor("Ethan Miller", 22, "ethan@email.com", "V005", "Adult");
        
        // Add visitors to queue
        System.out.println("\n--- Adding visitors to queue ---");
        rollerCoaster.addVisitorToQueue(visitor1);
        rollerCoaster.addVisitorToQueue(visitor2);
        rollerCoaster.addVisitorToQueue(visitor3);
        rollerCoaster.addVisitorToQueue(visitor4);
        rollerCoaster.addVisitorToQueue(visitor5);
        
        // Print current queue
        System.out.println("\n--- Current queue ---");
        rollerCoaster.printQueue();
        
        // Remove a visitor from queue
        System.out.println("\n--- Removing one visitor from queue ---");
        rollerCoaster.removeVisitorFromQueue();
        
        // Print queue after removal
        System.out.println("\n--- Queue after removal ---");
        rollerCoaster.printQueue();
        
        // Try to remove from empty queue (error case)
        System.out.println("\n--- Testing error case ---");
        Ride emptyRide = new Ride("Empty Ride", "Test", rideOperator, 2);
        emptyRide.removeVisitorFromQueue(); // Should show error message
    }
    
    public void partFourA() {
        System.out.println("\n=== Part 4A: Ride History ===");
        
        // Create employee and ride
        Employee operator = new Employee("Sarah Wilson", 28, "sarah@themepark.com", "E002", "Senior Operator");
        Ride waterRide = new Ride("Splash Mountain", "Water Ride", operator, 6);
        
        // Create visitors
        Visitor v1 = new Visitor("Michael Chen", 35, "michael@email.com", "V101", "VIP");
        Visitor v2 = new Visitor("Emma Thompson", 22, "emma@email.com", "V102", "Student");
        Visitor v3 = new Visitor("James Wilson", 40, "james@email.com", "V103", "Adult");
        Visitor v4 = new Visitor("Sophia Garcia", 19, "sophia@email.com", "V104", "Student");
        Visitor v5 = new Visitor("David Lee", 27, "david@email.com", "V105", "Adult");
        
        // Add visitors to ride history
        System.out.println("\n--- Adding visitors to ride history ---");
        waterRide.addVisitorToHistory(v1);
        waterRide.addVisitorToHistory(v2);
        waterRide.addVisitorToHistory(v3);
        waterRide.addVisitorToHistory(v4);
        waterRide.addVisitorToHistory(v5);
        
        // Try to add duplicate
        System.out.println("\n--- Testing duplicate addition ---");
        waterRide.addVisitorToHistory(v1);
        
        // Check if visitor is in history
        System.out.println("\n--- Checking visitor in history ---");
        waterRide.checkVisitorFromHistory(v2);
        waterRide.checkVisitorFromHistory(new Visitor("Unknown", 0, "unknown@email.com", "V999", "Adult"));
        
        // Print number of visitors
        System.out.println("\n--- Number of visitors ---");
        waterRide.numberOfVisitors();
        
        // Print ride history using Iterator
        System.out.println("\n--- Ride history ---");
        waterRide.printRideHistory();
    }
    
    public void partFourB() {
        System.out.println("\n=== Part 4B: Sorting Ride History ===");
        
        // Create employee and ride
        Employee operator = new Employee("Mike Johnson", 32, "mike@themepark.com", "E003", "Operator");
        Ride ferrisWheel = new Ride("Sky Wheel", "Ferris Wheel", operator, 8);
        
        // Create visitors in unsorted order
        Visitor v1 = new Visitor("Zack Anderson", 25, "zack@email.com", "V201", "Adult");
        Visitor v2 = new Visitor("Alice Cooper", 30, "alice@email.com", "V202", "VIP");
        Visitor v3 = new Visitor("Charlie Brown", 22, "charlie@email.com", "V203", "Student");
        Visitor v4 = new Visitor("Bob Marley", 28, "bob@email.com", "V204", "Adult");
        Visitor v5 = new Visitor("Anna Smith", 19, "anna@email.com", "V205", "Student");
        
        // Add visitors to ride history
        System.out.println("\n--- Adding visitors to ride history ---");
        ferrisWheel.addVisitorToHistory(v1);
        ferrisWheel.addVisitorToHistory(v2);
        ferrisWheel.addVisitorToHistory(v3);
        ferrisWheel.addVisitorToHistory(v4);
        ferrisWheel.addVisitorToHistory(v5);
        
        // Print unsorted history
        System.out.println("\n--- Unsorted ride history ---");
        ferrisWheel.printRideHistory();
        
        // Sort using Comparator
        System.out.println("\n--- Sorting ride history ---");
        VisitorComparator comparator = new VisitorComparator();
        ferrisWheel.sortRideHistory(comparator);
        
        // Print sorted history
        System.out.println("\n--- Sorted ride history ---");
        ferrisWheel.printRideHistory();
    }
    
    public void partFive() {
        System.out.println("\n=== Part 5: Run Ride Cycle ===");
        
        // Create employee and ride
        Employee operator = new Employee("Lisa Wang", 26, "lisa@themepark.com", "E004", "Operator");
        Ride rollerCoaster = new Ride("Dragon Coaster", "Roller Coaster", operator, 3);
        
        // Create visitors
        Visitor[] visitors = new Visitor[10];
        for (int i = 0; i < visitors.length; i++) {
            visitors[i] = new Visitor("Visitor" + (i+1), 20 + i, "visitor" + (i+1) + "@email.com", "V30" + i, "Adult");
        }
        
        // Add visitors to queue
        System.out.println("\n--- Adding visitors to queue ---");
        for (Visitor visitor : visitors) {
            rollerCoaster.addVisitorToQueue(visitor);
        }
        
        // Print queue before running cycle
        System.out.println("\n--- Queue before running cycle ---");
        rollerCoaster.printQueue();
        
        // Run one cycle
        System.out.println("\n--- Running one cycle ---");
        rollerCoaster.runOneCycle();
        
        // Print queue after running cycle
        System.out.println("\n--- Queue after running cycle ---");
        rollerCoaster.printQueue();
        
        // Print ride history
        System.out.println("\n--- Ride history after one cycle ---");
        rollerCoaster.printRideHistory();
        
        // Test error cases
        System.out.println("\n--- Testing error cases ---");
        Ride noOperatorRide = new Ride("Test Ride", "Test", null, 2);
        noOperatorRide.runOneCycle(); // Should show no operator error
        
        Ride noVisitorsRide = new Ride("Empty Ride", "Test", operator, 2);
        noVisitorsRide.runOneCycle(); // Should show no visitors error
    }
    
    public void partSix() {
        System.out.println("\n=== Part 6: Writing to File ===");
        
        // Create employee and ride
        Employee operator = new Employee("Tom Harris", 29, "tom@themepark.com", "E005", "Operator");
        Ride adventureRide = new Ride("Jungle Adventure", "Adventure Ride", operator, 4);
        
        // Create visitors and add to ride history
        Visitor v1 = new Visitor("Olivia Martinez", 24, "olivia@email.com", "V401", "VIP");
        Visitor v2 = new Visitor("Liam Johnson", 31, "liam@email.com", "V402", "Adult");
        Visitor v3 = new Visitor("Mia Williams", 17, "mia@email.com", "V403", "Student");
        Visitor v4 = new Visitor("Noah Brown", 26, "noah@email.com", "V404", "Adult");
        Visitor v5 = new Visitor("Isabella Garcia", 21, "isabella@email.com", "V405", "Student");
        
        System.out.println("--- Adding visitors to ride history ---");
        adventureRide.addVisitorToHistory(v1);
        adventureRide.addVisitorToHistory(v2);
        adventureRide.addVisitorToHistory(v3);
        adventureRide.addVisitorToHistory(v4);
        adventureRide.addVisitorToHistory(v5);
        
        // Run a few cycles to increase cycle count
        System.out.println("\n--- Running cycles to demonstrate data ---");
        for (int i = 0; i < 3; i++) {
            adventureRide.runOneCycle();
        }
        
        // Print current ride history
        System.out.println("\n--- Current ride history before export ---");
        adventureRide.printRideHistory();
        
        // Export ride history to file
        System.out.println("\n--- Exporting ride history to file ---");
        String filename = "jungle_adventure_history.txt";
        adventureRide.exportRideHistory(filename);
        
        // Test error case: export empty ride history
        System.out.println("\n--- Testing export with empty ride history ---");
        Ride emptyRide = new Ride("Empty Ride", "Test", operator, 2);
        emptyRide.exportRideHistory("empty_history.txt");
    }
    
    public void partSeven() {
        System.out.println("\n=== Part 7: Reading from File ===");
        
        // Create a new ride for import testing
        Employee operator = new Employee("Rachel Green", 27, "rachel@themepark.com", "E006", "Operator");
        Ride importedRide = new Ride("Magic Carpet", "Family Ride", operator, 5);
        
        // Print ride history before import (should be empty)
        System.out.println("--- Ride history before import ---");
        importedRide.printRideHistory();
        importedRide.numberOfVisitors();
        
        // Import ride history from file
        System.out.println("\n--- Importing ride history from file ---");
        String filename = "jungle_adventure_history.txt";
        importedRide.importRideHistory(filename);
        
        // Print ride history after import
        System.out.println("\n--- Ride history after import ---");
        importedRide.printRideHistory();
        importedRide.numberOfVisitors();
        
        // Test error cases
        System.out.println("\n--- Testing error cases ---");
        
        // Test with non-existent file
        System.out.println("--- Testing with non-existent file ---");
        importedRide.importRideHistory("non_existent_file.txt");
        
        // Test importing same file again (should show duplicates)
        System.out.println("\n--- Testing duplicate import ---");
        importedRide.importRideHistory(filename);
        
        // Test with corrupted/invalid file
        System.out.println("\n--- Testing with invalid file ---");
        try (PrintWriter writer = new PrintWriter(new FileWriter("corrupted_file.txt"))) {
            writer.println("Invalid,Data,Here");
            writer.println("John,not_a_number,john@email.com,V999,Adult");
            writer.println("Missing,Fields");
        } catch (IOException e) {
            System.out.println("Error creating test file: " + e.getMessage());
        }
        importedRide.importRideHistory("corrupted_file.txt");
    }
}