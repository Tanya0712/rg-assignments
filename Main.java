public class Main {
    public static void main(String[] args) {
        // Get the single instance of Singleton
        Singleton singleton = Singleton.getInstance();

        // Call the method on the singleton instance
        singleton.showMessage();
        }
}
