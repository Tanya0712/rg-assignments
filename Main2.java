public class Main2 {
    public static void main(String[] args) {
        // Create an instance of Person
        Person person = new Person("John Doe", 25);

        // Access the private variables using getter methods
        System.out.println("Name: " + person.getName());
        System.out.println("Age: " + person.getAge());

        // Modify the private variables using setter methods
        person.setName("Jane Doe");
        person.setAge(30);

        // Attempt to set an invalid age
        person.setAge(-5); // This will print an error message

        // Display the updated details
        person.display();
    }
}
