package ljmu.vets;

public class SupplyHub implements Notifiable {
	private String name;

	public SupplyHub(String n) {
		this.name = n;
	}

	@Override
	public void notifyy(String s) {
		System.out.println(this.name + " KNOWS ABOUT " + s);
	}
}
