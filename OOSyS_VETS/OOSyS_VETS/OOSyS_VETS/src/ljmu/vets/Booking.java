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
