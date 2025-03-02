package EXP_6;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

// Employee class
class Employee {
    String name;
    int age;
    double salary;

    // Constructor
    public Employee(String name, int age, double salary) {
        this.name = name;
        this.age = age;
        this.salary = salary;
    }

    // Display Employee details
    public void display() {
        System.out.println("\nName: " + name);
        System.out.println("Age: " + age);
        System.out.println("Salary: $" + salary);
    }
}

public class EmployeeSortingLambda {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        // Menu-driven loop
        while (true) {
            System.out.println("\n🏢 Employee Sorting System");
            System.out.println("1. Add an Employee");
            System.out.println("2. Sort by Name");
            System.out.println("3. Sort by Age");
            System.out.println("4. Sort by Salary");
            System.out.println("5. Display All Employees");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Employee
                    System.out.print("Enter Employee Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Age: ");
                    int age = scanner.nextInt();
                    System.out.print("Enter Salary: ");
                    double salary = scanner.nextDouble();

                    employees.add(new Employee(name, age, salary));
                    System.out.println("✅ Employee added successfully!");
                    break;

                case 2:
                    // Sort by Name
                    employees.sort(Comparator.comparing(e -> e.name));
                    System.out.println("✅ Employees sorted by Name!");
                    break;

                case 3:
                    // Sort by Age
                    employees.sort(Comparator.comparingInt(e -> e.age));
                    System.out.println("✅ Employees sorted by Age!");
                    break;

                case 4:
                    // Sort by Salary
                    employees.sort(Comparator.comparingDouble(e -> e.salary));
                    System.out.println("✅ Employees sorted by Salary!");
                    break;

                case 5:
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

                case 6:
                    // Exit
                    System.out.println("🚀 Exiting Employee Sorting System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid choice! Please select a valid option.");
            }
        }
    }
}
