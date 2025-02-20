package EXP_4;

import java.util.ArrayList;
import java.util.Scanner;

// Employee class to store details
class Employee {
    int id;
    String name;
    double salary;

    // Constructor
    public Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    // Display employee details
    public void displayEmployee() {
        System.out.println("ID: " + id + ", Name: " + name + ", Salary: ₹" + salary);
    }
}

public class EmployeeManager {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>(); // ArrayList to store employees
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Menu options
            System.out.println("\nEmployee Management System");
            System.out.println("1. Add Employee");
            System.out.println("2. Update Employee");
            System.out.println("3. Remove Employee");
            System.out.println("4. Search Employee");
            System.out.println("5. Display All Employees");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    // Add Employee
                    System.out.print("Enter Employee ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Employee Salary: ₹");
                    double salary = scanner.nextDouble();
                    employees.add(new Employee(id, name, salary));
                    System.out.println("✅ Employee added successfully!");
                    break;

                case 2:
                    // Update Employee
                    System.out.print("Enter Employee ID to update: ");
                    int updateId = scanner.nextInt();
                    boolean foundUpdate = false;
                    for (Employee emp : employees) {
                        if (emp.id == updateId) {
                            scanner.nextLine(); // Consume newline
                            System.out.print("Enter new Name: ");
                            emp.name = scanner.nextLine();
                            System.out.print("Enter new Salary: ₹");
                            emp.salary = scanner.nextDouble();
                            System.out.println("✅ Employee updated successfully!");
                            foundUpdate = true;
                            break;
                        }
                    }
                    if (!foundUpdate) {
                        System.out.println("❌ Employee not found!");
                    }
                    break;

                case 3:
                    // Remove Employee
                    System.out.print("Enter Employee ID to remove: ");
                    int removeId = scanner.nextInt();
                    boolean foundRemove = false;
                    for (Employee emp : employees) {
                        if (emp.id == removeId) {
                            employees.remove(emp);
                            System.out.println("✅ Employee removed successfully!");
                            foundRemove = true;
                            break;
                        }
                    }
                    if (!foundRemove) {
                        System.out.println("❌ Employee not found!");
                    }
                    break;

                case 4:
                    // Search Employee
                    System.out.print("Enter Employee ID to search: ");
                    int searchId = scanner.nextInt();
                    boolean foundSearch = false;
                    for (Employee emp : employees) {
                        if (emp.id == searchId) {
                            System.out.println("🔍 Employee Found:");
                            emp.displayEmployee();
                            foundSearch = true;
                            break;
                        }
                    }
                    if (!foundSearch) {
                        System.out.println("❌ Employee not found!");
                    }
                    break;

                case 5:
                    // Display All Employees
                    System.out.println("📋 Employee List:");
                    if (employees.isEmpty()) {
                        System.out.println("⚠️ No employees found!");
                    } else {
                        for (Employee emp : employees) {
                            emp.displayEmployee();
                        }
                    }
                    break;

                case 6:
                    // Exit Program
                    System.out.println("🚀 Exiting Employee Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid choice! Please select a valid option.");
            }
        }
    }
}
