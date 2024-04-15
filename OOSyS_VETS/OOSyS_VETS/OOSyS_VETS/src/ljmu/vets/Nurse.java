package ljmu.vets;

public class Nurse implements Notifiable {
	private String name;

	public Nurse(String n) {
		this.name = n;
	}

	@Override
	public void notifyy(String s) {
		System.out.println(this.name + " KNOWS ABOUT " + s);
	}
}
