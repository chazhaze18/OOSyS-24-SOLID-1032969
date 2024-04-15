package ljmu.vets;

import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

public class SysTest {

    @Test
    public void testSerialisationDeserialisation() {
        // Create a test list of surgeries
        List<Surgery> surgeries = List.of(new Surgery("SurgeryA"), new Surgery("SurgeryB"));
        Sys sys = new Sys();
        
        // Serialise the surgeries
        sys.serialiseSurgeries(surgeries);
        
        // Deserialise the surgeries
        List<Surgery> deserialisedSurgeries = sys.deserialiseSurgeries();
        
        // Compare the original surgeries with the deserialised ones
        assertEquals(surgeries.size(), deserialisedSurgeries.size());
        for (int i = 0; i < surgeries.size(); i++) {
            assertEquals(surgeries.get(i).getName(), deserialisedSurgeries.get(i).getName());
        }
    }

    @Test
    public void testLogOnWithValidCredentials() {
        // Set up input stream to simulate user input
        String input = "1\nQ\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Redirect standard output to capture printed messages
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        // Create a test list of surgeries
        List<Surgery> surgeries = List.of(new Surgery("SurgeryA"), new Surgery("SurgeryB"));
        Sys sys = new Sys();
        sys.setSurgeries(surgeries);
        
        // Call the entry menu method
        sys.entryMenu();
        
        // Verify that the correct message is printed
        assertTrue(outContent.toString().contains("Bye Bye"));
    }

    @Test
    public void testMakePetWithValidData() {
        // Set up input stream to simulate user input
        String input = "GlynH\n03 Oct 23\nQ\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Redirect standard output to capture printed messages
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        // Create a test surgery and add it to the list
        Surgery surgery = new Surgery("TestSurgery");
        List<Surgery> surgeries = Collections.singletonList(surgery);
        Sys sys = new Sys();
        sys.setSurgeries(surgeries);
        
        // Call the make pet method
        sys.makePet();
        
        // Verify that the correct message is printed
        assertTrue(outContent.toString().contains("Pet successfully added"));
    }

    @Test
    public void testGetPetByNameWithExistingName() {
        // Create a test surgery with a pet
        Surgery surgery = new Surgery("TestSurgery");
        Pet pet = new Cat("GlynH", LocalDate.of(2020, 11, 11), null);
        surgery.makePet(pet);
        List<Surgery> surgeries = Collections.singletonList(surgery);
        Sys sys = new Sys();
        sys.setSurgeries(surgeries);
        
        // Call the get pet by name method
        Pet retrievedPet = sys.getPetByName("GlynH");
        
        // Verify that the correct pet is retrieved
        assertNotNull(retrievedPet);
        assertEquals("GlynH", retrievedPet.getName());
    }

    @Test
    public void testGetPetByNameWithNonExistingName() {
        // Create a test surgery with a pet
        Surgery surgery = new Surgery("TestSurgery");
        Pet pet = new Cat("GlynH", LocalDate.of(2020, 11, 11), null);
        surgery.makePet(pet);
        List<Surgery> surgeries = Collections.singletonList(surgery);
        Sys sys = new Sys();
        sys.setSurgeries(surgeries);
        
        // Call the get pet by name method with a non-existing name
        Pet retrievedPet = sys.getPetByName("NonExistingName");
        
        // Verify that null is returned
        assertNull(retrievedPet);
    }

    @Test
    public void testMakeBookingWithValidData() {
        // Set up input stream to simulate user input
        String input = "SurgeryA-REF1\nGlynH\n03 Oct 23 09:30\n30\nQ\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        
        // Redirect standard output to capture printed messages
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        // Create a test surgery with a pet
        Surgery surgery = new Surgery("TestSurgery");
        Pet pet = new Cat("GlynH", LocalDate.of(2020, 11, 11), null);
        surgery.makePet(pet);
        List<Surgery> surgeries = Collections.singletonList(surgery);
        Sys sys = new Sys();
        sys.setSurgeries(surgeries);
        
        // Call the make booking method
        sys.makeBooking();
        
        // Verify that the correct message is printed
        assertTrue(outContent.toString().contains("Booking successfully made"));
    }

    @Test
    public void testGetPetsNextBooking() {
        // Create a test surgery with a pet and a booking
        Surgery surgery = new Surgery("TestSurgery");
        Pet pet = new Cat("GlynH", LocalDate.of(2020, 11, 11), null);
        surgery.makePet(pet);
        LocalDateTime bookingTime = LocalDateTime.of(2023, 10, 10, 10, 30);
        Booking booking = new Booking("SurgeryA-REF1", pet, bookingTime, 30);
        surgery.makeBooking(booking);
        List<Surgery> surgeries = Collections.singletonList(surgery);
        Sys sys = new Sys();
        sys.setSurgeries(surgeries);
        
        // Redirect standard output to capture printed messages
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        // Call the get pets next booking method
        sys.getPetsNextBooking();
        
        // Verify that the correct message is printed
        assertTrue(outContent.toString().contains(bookingTime.format(DateTimeFormatter.ofPattern("dd MMM yy HH:mm"))));
    }
}

