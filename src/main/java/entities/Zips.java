package entities;

public class Zips {
	private String city;
	private String zip;
	private Loc loc;
	private int pop;
	private String state;
	
	
	
	public Zips() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Zips(String city, String zip, Loc loc, int pop, String state) {
		super();
		this.city = city;
		this.zip = zip;
		this.loc = loc;
		this.pop = pop;
		this.state = state;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public Loc getLoc() {
		return loc;
	}
	public void setLoc(Loc loc) {
		this.loc = loc;
	}
	public int getPop() {
		return pop;
	}
	public void setPop(int pop) {
		this.pop = pop;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Zips [city=" + city + ", zip=" + zip + ", loc=" + loc + ", pop=" + pop + ", state=" + state + "]";
	}
	
	
	
}
