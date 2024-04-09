package ljmu.vets;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Surgery implements Serializable {
    private String surgery;
    private List<Pet> pets = new ArrayList<>();
    private BookingManager bookingManager = new BookingManager();

    public Surgery(String surgery) {
        this.surgery = surgery;
    }

    public void makePet(Pet pet) {
        this.pets.add(pet);
    }

    public Pet getPetByName(String name) {
        // Iterate through the pets list to find the pet by name
        for (Pet pet : pets) {
            if (pet.getName().equals(name)) {
                return pet;
            }
        }
        // If no pet with the specified name is found, return null
        return null;
    }

    public void makeBooking(Booking booking) {
        this.bookingManager.makeBooking(booking);
    }
}

