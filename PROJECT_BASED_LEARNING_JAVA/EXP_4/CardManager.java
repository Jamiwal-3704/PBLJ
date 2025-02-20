package EXP_4;
import java.util.*;

class Card {
    private String symbol;
    private int number;

    public Card(String symbol, int number) {
        this.symbol = symbol;
        this.number = number;
    }

    public String getSymbol() {
        return symbol;
    }

    public int getNumber() {
        return number;
    }

    public void displayCard() {
        System.out.println("Card Symbol: " + symbol + ", Number: " + number);
    }
}

public class CardManager {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Card> cardCollection = new ArrayList<>();

        while (true) {
            // Display menu options
            System.out.println("\n🎴 Card Collection System 🎴");
            System.out.println("1. Add Card");
            System.out.println("2. Search Cards by Symbol");
            System.out.println("3. Display All Cards");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Add a new card
                    System.out.print("Enter Card Symbol: ");
                    String symbol = scanner.nextLine();

                    int number;
                    while (true) {
                        System.out.print("Enter Card Number (1-10): ");
                        number = scanner.nextInt();
                        if (number >= 1 && number <= 10) {
                            break;
                        } else {
                            System.out.println("❌ Invalid number! Please enter a number between 1 and 10.");
                        }
                    }

                    cardCollection.add(new Card(symbol, number));
                    System.out.println("✅ Card added successfully!");
                    break;

                case 2:
                    // Search for cards by symbol
                    System.out.print("Enter Symbol to Search: ");
                    String searchSymbol = scanner.nextLine();
                    boolean found = false;

                    System.out.println("\n🔍 Cards with Symbol: " + searchSymbol);
                    for (Card card : cardCollection) {
                        if (card.getSymbol().equalsIgnoreCase(searchSymbol)) {
                            card.displayCard();
                            found = true;
                        }
                    }

                    if (!found) {
                        System.out.println("❌ No cards found with the symbol: " + searchSymbol);
                    }
                    break;

                case 3:
                    // Display all cards
                    if (cardCollection.isEmpty()) {
                        System.out.println("⚠️ No cards in the collection.");
                    } else {
                        System.out.println("\n📋 All Stored Cards:");
                        for (Card card : cardCollection) {
                            card.displayCard();
                        }
                    }
                    break;

                case 4:
                    // Exit the program
                    System.out.println("🚀 Exiting Card Collection System. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("❌ Invalid choice! Please select a valid option.");
            }
        }
    }
}
