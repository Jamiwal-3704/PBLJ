package EXP_6;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

// Student class to store student details
class Student {
    private String name;
    private double marks;

    // Constructor
    public Student(String name, double marks) {
        this.name = name;
        this.marks = marks;
    }

    // Getter for marks
    public double getMarks() {
        return marks;
    }

    // Display student details
    public void display() {
        System.out.println("Name: " + name + " | Marks: " + marks + "%");
    }
}

// Main class
public class StudentFilterSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = new ArrayList<>();

        // Menu-based loop
        while (true) {
            System.out.println("\n🎓 Student Performance System");
            System.out.println("1. Add a Student");
            System.out.println("2. Display Students Scoring Above 75%");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Validate integer input
            if (!scanner.hasNextInt()) {
                System.out.println("❌ Invalid input! Please enter a valid option (1-3).");
                scanner.next(); // Clear invalid input
                continue;
            }

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a student
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();

                    System.out.print("Enter Marks (0-100): ");

                    // Validate marks input
                    if (!scanner.hasNextDouble()) {
                        System.out.println("❌ Invalid input! Marks must be a number between 0 and 100.");
                        scanner.next(); // Clear invalid input
                        continue;
                    }

                    double marks = scanner.nextDouble();
                    scanner.nextLine(); // Consume newline

                    if (marks < 0 || marks > 100) {
                        System.out.println("❌ Invalid marks! Enter a value between 0 and 100.");
                    } else {
                        students.add(new Student(name, marks));
                        System.out.println("✅ Student added successfully!");
                    }
                    break;

                case 2:
                    // Filter students scoring above 75% and sort by marks in descending order
                    List<Student> filteredStudents = students.stream()
                            .filter(student -> student.getMarks() > 75)
                            .sorted((s1, s2) -> Double.compare(s2.getMarks(), s1.getMarks()))
                            .collect(Collectors.toList());

                    if (filteredStudents.isEmpty()) {
                        System.out.println("⚠️ No students scored above 75%.");
                    } else {
                        System.out.println("\n🏆 Students Scoring Above 75%:");
                        filteredStudents.forEach(Student::display);
                    }
                    break;

                case 3:
                    // Exit
                    System.out.println("🚀 Exiting Student Performance System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("❌ Invalid choice! Please select a valid option.");
            }
        }
    }
}
