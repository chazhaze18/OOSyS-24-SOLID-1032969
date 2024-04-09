package ljmu.vets;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;

public class Invoice implements Serializable {
	private Integer no;
	private LocalDateTime when;
	private Double amount;
	private List<Booking> bookings;
	private List<Payable> payables;

	public Invoice(Integer no, LocalDateTime when, Double amount, List<Booking> bookings, List<Payable> payables) {
		this.no = no;
		this.when = when;
		// this.amount = amount;
		this.amount = calculateAmount(bookings);

		this.bookings = bookings;
		this.payables = payables;
	}

	private Double calculateAmount(List<Booking> bookings) {
		Double tt = 0.0;

		for (Booking o : bookings) {			
			if (o.getPet() instanceof Cat c) {
				tt += o.getDuration() * 13.0;
			}
			else {
				if (o.getPet() instanceof Fish f) {
					tt += o.getDuration() * 17.0;
				}
			}
		}

		return Double.parseDouble(new DecimalFormat("#.##").format(tt));
	}

	public void saveAs(FileType fileType, String path) {
		switch (fileType) {
			case DOCX : {
				saveAsDocX(path);
				break;
			}
			case XPS : {
				saveAsXPS(path);
				break;
			}
			case PDF : {
				saveAsPDF(path);
				break;
			}
		}
	}
	
	// ToDo : Private !
	private void saveAsDocX(String path) {
		
	}

	// ToDo : Private !
	private void saveAsXPS(String path) {
		
	}

	// ToDo : Private !
	private void saveAsPDF(String path) {
		
	}

	// ToDo : get / set Methods ?
}
