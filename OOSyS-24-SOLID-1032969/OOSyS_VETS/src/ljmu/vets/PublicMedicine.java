package ljmu.vets;

public class PublicMedicine extends SurgeryOnlyMedicine {
	private Double publicCost;

	public PublicMedicine(String name, Integer stock, Integer lowest, Double surgeryCost, Double publicCost) {
		super(name, stock, lowest, surgeryCost);

		this.publicCost = publicCost;
	}

	@Override
	public void setStock(Integer i) {
		this.stock = i;
	}

	@Override
	public Double getPublicCost() {
		return this.publicCost;
	}

	// ToDo : get / set Methods ?
}
