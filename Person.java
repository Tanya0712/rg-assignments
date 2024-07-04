public class Person {
    // Private variables to restrict direct access
    private String name;
    private int age;

    // Constructor to initialize the variables
    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // Public getter method for name
    public String getName() {
        return name;
    }

    // Public setter method for name
    public void setName(String name) {
        this.name = name;
    }

    // Public getter method for age
    public int getAge() {
        return age;
    }

    // Public setter method for age
    public void setAge(int age) {
        if (age > 0) { // Validation to ensure age is positive
            this.age = age;
        } else {
            System.out.println("Age must be positive");
        }
    }

    // Method to display person's details
    public void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
    }
}
