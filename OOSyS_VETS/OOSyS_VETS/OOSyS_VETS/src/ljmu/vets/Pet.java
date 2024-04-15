package ljmu.vets;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public abstract class Pet implements Serializable {
	protected String name;
	protected LocalDate regDate;

	// ToDo : Private ?
	protected List<Booking> bookings = new ArrayList<Booking>();

	public Pet(String name, LocalDate regDate) {
		this.name = name;
		this.regDate = regDate;
	}

	public void makeBooking(Booking when) {
		this.bookings.add(when);
	}

	public Booking getNextBooking() {
		return this.bookings.stream()
			.filter(o -> o.getWhen().isAfter(LocalDateTime.now()))
				.sorted(Comparator.comparing(o -> o.getWhen()))
					.findFirst().get();
	}

	public String getName() {
		return this.name;
	}

	public LocalDate getRegDate() {
		return this.regDate;
	}

	// ToDo : get / set Methods ?
}
