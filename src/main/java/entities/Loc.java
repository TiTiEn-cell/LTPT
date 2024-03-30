package entities;

public class Loc {
	private double x;
	private double y;
	
	
	
	public Loc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Loc(double x, double y) {
		super();
		this.x = x;
		this.y = y;
	}
	public double getX() {
		return x;
	}
	public void setX(double x) {
		this.x = x;
	}
	public double getY() {
		return y;
	}
	public void setY(double y) {
		this.y = y;
	}
	@Override
	public String toString() {
		return "Loc [x=" + x + ", y=" + y + "]";
	}
	
	
}
