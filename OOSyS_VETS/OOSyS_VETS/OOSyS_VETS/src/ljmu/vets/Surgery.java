package ljmu.vets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Surgery implements Serializable {
    private static final long serialVersionUID = 1L; // Add this line
    private String surgery;
    private List<Pet> pets = new ArrayList<Pet>();
    private List<Booking> bookings = Collections.synchronizedList(new ArrayList<Booking>());

    public Surgery(String surgery) {
        this.surgery = surgery;
    }

    public String getName() {
        return this.surgery;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName() + " >> " + this.surgery;
    }

    public void makePet(Pet pet) {
        this.pets.add(pet);
    }

    public Pet getPetByName(String name) {
        for (Pet p : this.pets) {
            if (p.getName().equals(name)) {
                return p;
            }
        }
        return null;
    }

    public void makeBooking(Booking booking) {
        this.bookings.add(booking);
        booking.getPet().makeBooking(booking);
    }
}


