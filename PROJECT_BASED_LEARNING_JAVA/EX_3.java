// Create an application to calculate interest for FDs, RDs based on certain conditions using inheritance.

import java.util.Scanner;

class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message);
    }
}

// Abstract class Account
abstract class Account {
    double amount;
    int age;

    public Account(double amount, int age) throws InvalidInputException {
        if (amount <= 0 || age <= 0) {
            throw new InvalidInputException("Amount and age must be positive values.");
        }
        this.amount = amount;
        this.age = age;
    }

    abstract double calculateInterest();
}

class SavingsAccount extends Account {
    public SavingsAccount(double amount, int age) throws InvalidInputException {
        super(amount, age);
    }

    double calculateInterest() {
        double rate = (age >= 60) ? 5.0 : 4.0; // 5% for senior citizens, 4% for general
        return (amount * rate) / 100; // Return interest amount
    }
}

class FixedDepositAccount extends Account {
    int days;

    public FixedDepositAccount(double amount, int age, int days) throws InvalidInputException {
        super(amount, age);
        if (days <= 0) {
            throw new InvalidInputException("Number of days must be positive.");
        }
        this.days = days;
    }

    @Override
    double calculateInterest() {
        double rate;
        if (amount > 10000000) {
            rate = (age >= 60) ? 7.5 : 6.5;
        } else {
            rate = (age >= 60) ? 6.0 : 5.0;
        }
        return (amount * rate * days) / (100 * 365); // Interest for given duration
    }
}

// Recurring Deposit (RD) Account Class
class RecurringDepositAccount extends Account {
    int months;

    public RecurringDepositAccount(double amount, int age, int months) throws InvalidInputException {
        super(amount, age);
        if (months <= 0) {
            throw new InvalidInputException("Number of months must be positive.");
        }
        this.months = months;
    }

    double calculateInterest() {
        double rate = (age >= 60) ? 6.0 : 5.5; // Senior citizens get higher rates
        return (amount * rate * months) / (100 * 12); // Return interest amount
    }
}

public class EX_3 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Select Account Type: \n1. Savings \n2. Fixed Deposit (FD) \n3. Recurring Deposit (RD)");
            int choice = scanner.nextInt();

            System.out.print("Enter amount: ₹");
            double amount = scanner.nextDouble();
            System.out.print("Enter age of account holder: ");
            int age = scanner.nextInt();

            switch (choice) {
                case 1:
                    SavingsAccount sa = new SavingsAccount(amount, age);
                    System.out.println("Interest Earned: ₹" + sa.calculateInterest());
                    break;
                case 2:
                    System.out.print("Enter number of days: ");
                    int days = scanner.nextInt();
                    FixedDepositAccount fd = new FixedDepositAccount(amount, age, days);
                    System.out.println("Interest Earned: ₹" + fd.calculateInterest());
                    break;
                case 3:
                    System.out.print("Enter number of months: ");
                    int months = scanner.nextInt();
                    RecurringDepositAccount rd = new RecurringDepositAccount(amount, age, months);
                    System.out.println("Interest Earned: ₹" + rd.calculateInterest());
                    break;
                default:
                    System.out.println("Invalid choice! Please select a valid account type.");
            }
        } catch (InvalidInputException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            scanner.close(); // Close the scanner to avoid memory leaks
        }
    }
}
