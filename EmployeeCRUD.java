import java.util.ArrayList;
import java.util.List;

public class EmployeeCRUD {
    private List<Employee> employees;

    public EmployeeCRUD() {
        employees = new ArrayList<>();
    }

    // Create operation
    public void create(Employee employee) {
        employees.add(employee);
        System.out.println("Employee added: " + employee);
    }

    // Read operation
    public List<Employee> read() {
        return new ArrayList<>(employees);
    }

    // Update operation
    public boolean update(int id, Employee newEmployee) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employee.setName(newEmployee.getName());
                employee.setDepartment(newEmployee.getDepartment());
                System.out.println("Employee updated: " + employee);
                return true;
            }
        }
        System.out.println("Employee not found with id: " + id);
        return false;
    }

    // Delete operation
    public boolean delete(int id) {
        for (Employee employee : employees) {
            if (employee.getId() == id) {
                employees.remove(employee);
                System.out.println("Employee removed: " + employee);
                return true;
            }
        }
        System.out.println("Employee not found with id: " + id);
        return false;
    }

    public static void main(String[] args) {
        EmployeeCRUD employeeCRUD = new EmployeeCRUD();

        // Create employees
        Employee emp1 = new Employee(1, "John Doe", "Engineering");
        Employee emp2 = new Employee(2, "Jane Smith", "Marketing");

        employeeCRUD.create(emp1);
        employeeCRUD.create(emp2);

        // Read employees
        System.out.println("Employees: " + employeeCRUD.read());

        // Update employee
        Employee empUpdate = new Employee(2, "Jane Doe", "Sales");
        employeeCRUD.update(2, empUpdate);

        // Read employees
        System.out.println("Employees after update: " + employeeCRUD.read());

        // Delete employee
        employeeCRUD.delete(1);

        // Read employees
        System.out.println("Employees after deletion: " + employeeCRUD.read());
    }
}