package ljmu.vets;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class Pet implements Serializable {
    protected String name;
    protected LocalDate regDate;
    protected List<Booking> bookings; // Add the bookings list

    public Pet(String name, LocalDate regDate) {
        this.name = name;
        this.regDate = regDate;
        this.bookings = new ArrayList<>(); // Initialize the bookings list
    }

    // Other methods of the Pet class...
}
