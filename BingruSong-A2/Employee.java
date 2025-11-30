public class Employee extends Person {
    private String employeeId;
    private String position;
    
    // Default constructor
    public Employee() {
        super();
        this.employeeId = "E000";
        this.position = "Unassigned";
    }
    
    // Parameterized constructor
    public Employee(String name, int age, String email, String employeeId, String position) {
        super(name, age, email);
        this.employeeId = employeeId;
        this.position = position;
    }
    
    // Getters and setters
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getPosition() {
        return position;
    }
    
    public void setPosition(String position) {
        this.position = position;
    }
    
    @Override
    public String toString() {
        return super.toString() + ", Employee ID: " + employeeId + ", Position: " + position;
    }
}