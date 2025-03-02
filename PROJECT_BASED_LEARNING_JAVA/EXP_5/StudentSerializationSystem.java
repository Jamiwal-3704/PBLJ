import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Student class implementing Serializable
class Student implements Serializable {
    private static final long serialVersionUID = 1L;
    int id;
    String name;
    double gpa;

    // Constructor
    public Student(int id, String name, double gpa) {
        this.id = id;
        this.name = name;
        this.gpa = gpa;
    }

    // Display Student details
    public void display() {
        System.out.println("\nStudent Details:");
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("GPA: " + gpa);
    }
}

public class StudentSerializationSystem {
    private static final String FILE_NAME = "students_data.ser";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Student> students = loadStudents(); // Load existing students from file

        while (true) {
            // Display menu
            System.out.println("\n📚 Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Display All Students");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add Student
                    System.out.print("Enter Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // Consume newline
                    System.out.print("Enter Student Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Student GPA: ");
                    double gpa = scanner.nextDouble();

                    students.add(new Student(id, name, gpa));
                    saveStudents(students);
                    System.out.println("✅ Student added successfully!");
                    break;

                case 2:
                    // Display All Students
                    if (students.isEmpty()) {
                        System.out.println("⚠️ No students found!");
                    } else {
                        System.out.println("\n📋 Student List:");
                        for (Student student : students) {
                            student.display();
                        }
                    }
                    break;

                case 3:
                    // Exit
                    System.out.println("🚀 Exiting Student Management System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid choice! Please select a valid option.");
            }
        }
    }

    // Method to serialize and save the list of students
    private static void saveStudents(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println("❌ Error during saving students: " + e.getMessage());
        }
    }

    // Method to deserialize and load the list of students
    private static List<Student> loadStudents() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<Student>) ois.readObject();
        } catch (FileNotFoundException e) {
            System.out.println("❌ File not found, starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("❌ Error loading students: " + e.getMessage());
        }
        return new ArrayList<>();
    }
}
