import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import ljmu.vets.*;

public class Sys {
	private final String PATH = "M:\\data\\OOSyS\\";
	private final Scanner S = new Scanner(System.in);

	private List<Surgery> surgeries = new ArrayList<Surgery>();
	private Surgery surgery;
	 private DataStorage dataStorage; // Add a field for data storage

	    public Sys(DataStorage dataStorage) {
	        this.dataStorage = dataStorage;
	        deSerialize(); // DeSerialize data using the provided data storage
	    }
	// ToDo : Necessary ?
	public Sys() {
		deSerialize();

		/* NOTE : Debugging !
		surgeries.add(new Surgery("SurgeryA"));
		surgeries.add(new Surgery("SurgeryB"));

		Pet pet1 = new Cat("GlynH", LocalDate.of(2020, 11, 11));
		surgeries.get(0).makePet(pet1);

		Booking booking1 = new Booking("SurgeryA-REF1", pet1, LocalDateTime.of(2022, 9, 9, 9, 33), 30);
		surgeries.get(0).makeBooking(booking1);

		Booking booking2 = new Booking("SurgeryA-REF2", pet1, LocalDateTime.of(2023, 4, 4, 9, 22), 60);
		surgeries.get(0).makeBooking(booking2);

		Booking booking3 = new Booking("SurgeryA-REF3", pet1, LocalDateTime.of(2025, 11, 11, 11, 00), 90);
		surgeries.get(0).makeBooking(booking3);
		*/
	}

	public void entryMenu() {
	    DataStorage dataStorage = new SerializableStorage(); // or GsonStorage
	    Sys sys = new Sys(dataStorage); // Pass the data storage implementation to Sys constructor

	    String choice = "";

	    do {
	        System.out.println("-- ENTRY MENU --");
	        System.out.println("1 : [L]ogOn");
	        System.out.println("Q : Quit");
	        System.out.print("Pick : ");

	        choice = S.nextLine().toUpperCase();

	        switch (choice) {
	            case "1":
	            case "L": {
	                logOn();
	                break;
	            }
	        }

	    } while (!choice.equals("Q"));

	    serialize();

	    System.out.println("Bye Bye !");
	    System.exit(0);
	}
	

	private void deSerialize() {
		ObjectInputStream ois;

		try {
			ois = new ObjectInputStream(new FileInputStream(PATH + "surgeries.ser"));

			// NOTE : Erasure Warning !
			this.surgeries = (List<Surgery>)ois.readObject();

			// ToDo : Finally ?
			ois.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void serialize() {
		ObjectOutputStream oos;

		try {
			oos = new ObjectOutputStream(new FileOutputStream(PATH + "surgeries.ser"));
			oos.writeObject(this.surgeries);

			// ToDo : Finally ?
			oos.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	private void logOn() {
		// ToDo : Actually loggingOn :) ?
		this.surgery = this.surgeries.get(0);

		surgeryMenu();
	}

	private void surgeryMenu() {
		String choice = "";

		do {
			System.out.println("-- " + surgery.toString() + "'s SURGERY MENU --");
			System.out.println("1 : makePet");
			System.out.println("2 : getPetByName");
			System.out.println("3 : makeBooking");
			System.out.println("4 : getPetsNextBooking");
			System.out.println("5 : ToDo");
			System.out.println("Q : Quit");
			System.out.print("Pick : ");

			choice = S.nextLine().toUpperCase();

			switch (choice) {
				case "1" : {
					makePet();
					break;
				}
				case "2" : {
					getPetByName();
					break;
				}
				case "3" : {
					makeBooking();
					break;
				}
				case "4" : {
					getPetsNextBooking();
					break;
				}
				case "5" : {
					// ToDo : ?
					break;
				}
			}

		} while (!choice.equals("Q"));

		// NOTE : Logging Out !
		surgery = null;
	}

	private void makePet() {
		System.out.print("Pet's Name : ");
		String name = S.nextLine();

		System.out.print("Pet's RegDate [i.e. 03 Oct 23] : ");
		LocalDate regDate = LocalDate.parse(S.nextLine(), DateTimeFormatter.ofPattern("dd MMM yy"));

		// ToDo : Always Fresh Fish !
		surgery.makePet(new Fish(name, regDate, WaterType.FRESH));
	}

	private void getPetByName() {
		System.out.print("Pet's Name : ");
		String name = S.nextLine();

		// ToDo : Validate ?
		System.out.println(surgery.getPetByName(name).toString());
	}

	private void makeBooking() {
		// ToDo : Auto-Generate ?
		System.out.print("Booking's Ref : ");
		String ref = S.nextLine();

		System.out.print("Pet's Name : ");
		String name = S.nextLine();

		// ToDo : Validate ?
		Pet pet = surgery.getPetByName(name);

		System.out.print("Booking's DateTime [i.e. 03 Oct 23 09:30] : ");
		LocalDateTime when = LocalDateTime.parse(S.nextLine(), DateTimeFormatter.ofPattern("dd MMM yy HH:mm"));

		System.out.print("Booking's Duration [i.e. MINS] : ");
		Integer duration = Integer.parseInt(S.nextLine());

		surgery.makeBooking(new Booking(ref, pet, when, duration));
	}

	private void getPetsNextBooking() {
		System.out.print("Pet's Name : ");
		String name = S.nextLine();

		// ToDo : Validate ?
		Pet pet = surgery.getPetByName(name);

		System.out.println(pet.getNextBooking().getWhen().format(DateTimeFormatter.ofPattern("dd MMM yy HH:mm")));
	}
}
