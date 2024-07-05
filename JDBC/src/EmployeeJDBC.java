import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeJDBC {
    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/company";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "@tanya07122002";

    // SQL statements
    private static final String INSERT_SQL = "INSERT INTO Employee (name, department) VALUES (?, ?)";
    private static final String SELECT_ALL_SQL = "SELECT * FROM Employee";
    private static final String SELECT_BY_ID_SQL = "SELECT * FROM Employee WHERE id = ?";
    private static final String UPDATE_SQL = "UPDATE Employee SET name = ?, department = ? WHERE id = ?";
    private static final String DELETE_SQL = "DELETE FROM Employee WHERE id = ?";

    // Connection object
    private Connection connection;

    // Constructor
    public EmployeeJDBC() {
        try {
            // Establish the connection
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            System.out.println("Connected to the database!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Create operation
    public void create(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getDepartment());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating employee failed, no rows affected.");
            }

            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    employee.setId(generatedKeys.getInt(1));
                } else {
                    throw new SQLException("Creating employee failed, no ID obtained.");
                }
            }
            System.out.println("Employee created: " + employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Read operation - get all employees
    public List<Employee> readAll() {
        List<Employee> employees = new ArrayList<>();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(SELECT_ALL_SQL)) {
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String department = resultSet.getString("department");
                Employee employee = new Employee(id, name, department);
                employees.add(employee);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return employees;
    }

    // Read operation - get employee by id
    public Employee readById(int id) {
        try (PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID_SQL)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String name = resultSet.getString("name");
                    String department = resultSet.getString("department");
                    return new Employee(id, name, department);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update operation
    public void update(Employee employee) {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_SQL)) {
            statement.setString(1, employee.getName());
            statement.setString(2, employee.getDepartment());
            statement.setInt(3, employee.getId());

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Updating employee failed, no rows affected.");
            }
            System.out.println("Employee updated: " + employee);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete operation
    public void delete(int id) {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_SQL)) {
            statement.setInt(1, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Deleting employee failed, no rows affected.");
            }
            System.out.println("Employee deleted with id: " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Close connection
    public void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        EmployeeJDBC employeeJDBC = new EmployeeJDBC();

        // Create employees
        Employee emp1 = new Employee(1, "John Doe", "Engineering");
        Employee emp2 = new Employee(2, "Jane Smith", "Marketing");

        employeeJDBC.create(emp1);
        employeeJDBC.create(emp2);

        // Read all employees
        List<Employee> employees = employeeJDBC.readAll();
        System.out.println("All employees: " + employees);

        // Update employee
        emp2.setName("Jane Doe");
        emp2.setDepartment("Sales");
        employeeJDBC.update(emp2);

        // Read employee by id
        Employee empById = employeeJDBC.readById(2);
        System.out.println("Employee by id 2: " + empById);

        // Delete employee
        employeeJDBC.delete(1);

        // Read all employees after deletion
        employees = employeeJDBC.readAll();
        System.out.println("All employees after deletion: " + employees);

        // Close connection
        employeeJDBC.close();
    }
}
