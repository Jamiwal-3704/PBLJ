package EXP_4;
import java.util.*;

class TicketBookingSystem {
    public static final int TOTAL_SEATS = 10; // Total number of seats
    private final boolean[] seats = new boolean[TOTAL_SEATS]; // Seat availability
    private final Object lock = new Object(); // Lock for synchronization

    // Method to book a seat
    public void bookSeat(int seatNumber, String customerType) {
        synchronized (lock) { // Ensures only one thread books a seat at a time
            if (seatNumber < 1 || seatNumber > TOTAL_SEATS) {
                System.out.println(customerType + " ❌ Invalid seat number! Choose between 1 and " + TOTAL_SEATS);
                return;
            }
            if (seats[seatNumber - 1]) {
                System.out.println(customerType + " ❌ Seat " + seatNumber + " is already booked!");
            } else {
                seats[seatNumber - 1] = true;
                System.out.println(customerType + " ✅ Seat " + seatNumber + " booked successfully!");
            }
        }
    }
}

// Booking thread class
class BookingThread extends Thread {
    private final TicketBookingSystem system;
    private final int seatNumber;
    private final String customerType;

    public BookingThread(TicketBookingSystem system, int seatNumber, String customerType, int priority) {
        this.system = system;
        this.seatNumber = seatNumber;
        this.customerType = customerType;
        setPriority(priority); // Set thread priority (VIP gets high priority)
    }

    @Override
    public void run() {
        system.bookSeat(seatNumber, customerType);
    }
}

// Main class
public class TicketBooking {
    public static void main(String[] args) {
        TicketBookingSystem system = new TicketBookingSystem();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Display options
            System.out.println("\n🎟️ Ticket Booking System 🎟️");
            System.out.println("1. Book VIP Ticket (High Priority)");
            System.out.println("2. Book Regular Ticket");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            if (choice == 3) {
                System.out.println("🚀 Exiting system. Goodbye!");
                break;
            }

            System.out.print("Enter seat number (1-" + TicketBookingSystem.TOTAL_SEATS + "): ");
            int seatNumber = scanner.nextInt();

            if (choice == 1) {
                new BookingThread(system, seatNumber, "👑 VIP", Thread.MAX_PRIORITY).start();
            } else if (choice == 2) {
                new BookingThread(system, seatNumber, "🧑 Regular", Thread.NORM_PRIORITY).start();
            } else {
                System.out.println("❌ Invalid choice! Please select a valid option.");
            }
        }

        scanner.close();
    }
}
