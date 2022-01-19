package hr.fer.obj;

import java.util.Random;

public class Cestica {

	//pozicija, smjer,velicina, velicina brzine, duljina zivota, boja
	Vector3d pozicija;
	Vector3d smjer;
	double velicina = 1;
	
	double brzina = 1;
	double zivot = 10;
	double starost = 0;
	
	double r = 1.0, g = 1.0, b = 1.0;
	

	
	public Cestica(Vector3d glavnaPoz) {
		Random r = new Random();
		pozicija = new Vector3d(glavnaPoz.x + (r.nextDouble() * 2 - 1), 
				glavnaPoz.y+ (r.nextDouble() * 2 - 1),glavnaPoz.z+ (r.nextDouble() * 2)-1);
		smjer = new Vector3d(r.nextDouble()*2-1,r.nextDouble()*2-1, r.nextDouble()*2-1);
		smjer.normalize();
		
		zivot = r.nextDouble() * 5 + 1;
		
		brzina = r.nextDouble()*5;
		
		velicina = r.nextDouble() * 2;
		
	}

	public Cestica(Vector3d pozicija, Vector3d smjer, double velicina, double brzina, double zivot, int r, int g,
			int b) {
		super();
		this.pozicija = pozicija;
		this.smjer = smjer;
		this.velicina = velicina;
		this.brzina = brzina;
		this.zivot = zivot;
		this.r = r;
		this.g = g;
		this.b = b;
	}
	
	public void update(double timestep) {
		
		starost += timestep;
		//nova pozicija
		pozicija.add(Vector3d.duplicate(smjer).multiplyScalar(brzina).multiplyScalar(timestep));
		
		
		
		//nova boja ovisno o zivotu
		double postotakZivota = starost/zivot;
		g = 1 * (1-postotakZivota);
		b = 1 * (1-postotakZivota);
		
		//nova velicina ovisno o zivotu
		double postotakZivota2 = timestep/zivot;
		velicina = velicina * (1-postotakZivota2);
	}
	
	
	@Override
	public String toString() {
		return pozicija.toString();
	}
}
