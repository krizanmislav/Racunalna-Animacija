package hr.fer.obj;

public class Vrh {

	Double x;
	Double y;
	Double z;
	public Vrh(Double x, Double y, Double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	//za vektore
	public void normaliziraj() {
		double norm = Math.sqrt(x*x + y*y + z*z);
		this.x = x/norm;
		this.y = y/norm;
		this.z = z/norm;
	}
	
	@Override
	public String toString() {
		return "(" + this.x + " ," + this.y + " ," + this.z + ")";
	}
	
}
