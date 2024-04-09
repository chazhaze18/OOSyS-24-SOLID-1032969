package ljmu.vets;
import java.util.ArrayList;
import java.util.List;

public class BookingManager {
    private List<Booking> bookings = new ArrayList<>();

    public void makeBooking(Booking booking) {
        this.bookings.add(booking);
        // Optionally: Handle two-way linking with Pet
    }

    // Add methods for managing bookings as needed
}