package ljmu.vets;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Booking implements Serializable {
	private String ref;
	private Pet pet;
	private LocalDateTime when;
	private Integer duration;

	public Booking(String ref, Pet pet, LocalDateTime when, Integer duration) {
		this.ref = ref;
		this.pet = pet;
		this.when = when;
		this.duration = duration;
	}
	
	public Booking getNextBooking() {
	    Booking nextBooking = null;
	    LocalDateTime now = LocalDateTime.now();

	    // Iterate through the list of bookings to find the next booking after the current time
	    for (Booking booking : bookings) {
	        LocalDateTime bookingTime = booking.getWhen();
	        // Check if the booking time is after the current time and update nextBooking accordingly
	        if (bookingTime.isAfter(now) && (nextBooking == null || bookingTime.isBefore(nextBooking.getWhen()))) {
	            nextBooking = booking;
	        }
	    }

	    return nextBooking;
	}


	@Override
	public String toString() {
		return this.getClass().getSimpleName() + " >> " +
				this.ref + " " +
				this.pet.toString() + " " +
				this.when.format(DateTimeFormatter.ofPattern("dd MMM yy HH:mm"));
	}

	public String getRef() {
		return this.ref;
	}

	public Pet getPet() {
		return this.pet;
	}

	public LocalDateTime getWhen() {
		return this.when;
	}

	public Integer getDuration() {
		return this.duration;
	}

	// ToDo : get / set Methods ?
}
