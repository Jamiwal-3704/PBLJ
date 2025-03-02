// HARD PROBLEM 
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Employee class implementing Serializable
class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    String designation;
    double salary;

    // Constructor
    public Employee(int id, String name, String designation, double salary) {
        this.id = id;
        this.name = name;
        this.designation = designation;
        this.salary = salary;
    }

    // Display Employee details
    public void display() {
        System.out.println("\nEmployee ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Designation: " + designation);
        System.out.println("Salary: $" + salary);
    }
}

public class EmployeeManagementSystem {
    private static final String FILE_NAME = "employees_data.ser";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = loadEmployees(); // Load existing employees

        while (true) {
            // Display menu
            System.out.println("\n🏢 Employee Management System");
            System.out.println("1. Add an Employee");
            System.out.println("2. Display All Employees");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Employee
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Designation: ");
                    String designation = scanner.nextLine();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();

                    employees.add(new Employee(id, name, designation, salary));
                    saveEmployees(employees);
                    System.out.println("✅ Employee added successfully!");
                    break;

                case 2:
                    // Display All Employees
                    if (employees.isEmpty()) {
                        System.out.println("⚠️ No employees found!");
                    } else {
                        System.out.println("\n📋 Employee List:");
                        for (Employee employee : employees) {
                            employee.display();
                        }
                    }
                    break;

                case 3:
                    // Exit
                    System.out.println("🚀 Exiting Employee Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid choice! Please select a valid option.");
            }
        }
    }

    // Method to serialize and save the list of employees
    private static void saveEmployees(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(employees);
        } catch (IOException e) {
            System.out.println("❌ Error saving employees: " + e.getMessage());
        }
    }

    // Method to deserialize and load the list of employees
    private static List<Employee> loadEmployees() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Employee>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("❌ File not found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error loading employees: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
