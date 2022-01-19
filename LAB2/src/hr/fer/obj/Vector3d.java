package hr.fer.obj;

public class Vector3d {

	double x;
	double y;
	double z;
	
	
	
	
	public Vector3d(double x, double y, double z) {
		super();
		this.x = x;
		this.y = y;
		this.z = z;
	}


	//dodaj na vektora
	public Vector3d add(Vector3d vec) {
		this.x += vec.x;
		this.y += vec.y;
		this.z += vec.z;
		return this;
	}
	
	public Vector3d subtract(Vector3d vec) {
		this.x -= vec.x;
		this.y -= vec.y;
		this.z -= vec.z;
		return this;
	}
	
	public Vector3d multiplyScalar(double scal) {
		this.x *= scal;
		this.y *= scal;
		this.z *= scal;
		return this;
	}
	
	public double getNorm() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public Vector3d normalize() {
		double norm = getNorm();
		this.x /= norm;
		this.y /= norm;
		this.z /= norm;
		return this;
	}
	
	public static Vector3d duplicate(Vector3d v) {
		return new Vector3d(v.x, v.y, v.z);
	}
	
	@Override
	public String toString() {
		return "(" + x + ", " + y + ", " + z + ")";
	}
	
}
