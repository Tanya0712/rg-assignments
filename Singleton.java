public class Singleton {
    // Static variable to hold the single instance
    private static Singleton instance;

    // Private constructor to prevent instantiation
    private Singleton() {
            // Initialize any resources if needed
    }

    // Public static method to provide access to the instance
    public static Singleton getInstance() {
           if (instance == null) {
                    instance = new Singleton();
            }
            return instance;
    }

    // Example method to demonstrate functionality
    public void showMessage() {
            System.out.println("Hello from Singleton!");
    }
}
