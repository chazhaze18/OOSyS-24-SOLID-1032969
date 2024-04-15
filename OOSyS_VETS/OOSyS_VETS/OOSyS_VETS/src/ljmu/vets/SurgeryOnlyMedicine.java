package ljmu.vets;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SurgeryOnlyMedicine implements Payable, Serializable {
	protected String name;
	protected Integer stock, lowest;
	protected Double surgeryCost;
	protected List<Notifiable> people = new ArrayList<Notifiable>();

	public SurgeryOnlyMedicine(String name, Integer stock, Integer lowest, Double surgeryCost) {
		this.name = name;
		this.stock = stock;
		this.lowest = lowest;
		this.surgeryCost = surgeryCost;
	}

	public void setStock(Integer i) {
		this.stock = i;

		if (i < lowest) {
			notifyPeople();
		}
	}

	public void addNotifiable(Notifiable n) {
		this.people.add(n);
	}

	private void notifyPeople() {
		for	(Notifiable n : people) {
			n.notifyy(this.stock + " LEFT OF " + this.name);
		}
	}	

	@Override
	public Double getSurgeryCost() {
		return this.surgeryCost;
	}

	@Override
	public Double getPublicCost() throws Exception {
		throw new Exception("ToDo");
	}

	// ToDo : get / set Methods ?
}
