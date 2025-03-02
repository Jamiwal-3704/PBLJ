// Easy question 
import java.util.*;

public class AutoBoxingSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // List to store Integer objects (autoboxing)
        List<Integer> numbers = new ArrayList<>();

        System.out.println("Enter numbers separated by spaces:");
        String input = scanner.nextLine();
        
        // Split input string and parse into Integers
        String[] strNumbers = input.split(" ");
        for (String str : strNumbers) {
            try {
                numbers.add(Integer.parseInt(str)); // Autoboxing (int -> Integer)
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input: " + str + " is not a number.");
            }
        }

        // Calculate sum using unboxing (Integer -> int)
        int sum = 0;
        for (Integer num : numbers) {
            sum += num; // Unboxing
        }

        System.out.println("✅ The sum of the numbers is: " + sum);
        scanner.close();
    }
}
