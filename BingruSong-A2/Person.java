public abstract class Person {
    private String name;
    private int age;
    private String email;
    
    // Default constructor
    public Person() {
        this.name = "Unknown";
        this.age = 0;
        this.email = "unknown@example.com";
    }
    
    // Parameterized constructor
    public Person(String name, int age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }
    
    // Getters and setters
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    @Override
    public String toString() {
        return "Name: " + name + ", Age: " + age + ", Email: " + email;
    }
}