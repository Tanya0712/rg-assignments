import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class VerifyDatabase {
    private static final String URL = "jdbc:mysql://localhost:3306/company";
    private static final String USER = "root"; // Change this to your MySQL username
    private static final String PASSWORD = "@tanya07122002"; // Change this to your MySQL password
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Verify the database exists
            ResultSet rs = stmt.executeQuery("SHOW DATABASES LIKE 'company';");
            if (rs.next()) {
                System.out.println("Database 'company' exists.");
            } else {
                System.out.println("Database 'company' does not exist.");
            }

            // Verify the table exists
            rs = stmt.executeQuery("SHOW TABLES LIKE 'Employee';");
            if (rs.next()) {
                System.out.println("Table 'Employee' exists.");
            } else {
                System.out.println("Table 'Employee' does not exist.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
