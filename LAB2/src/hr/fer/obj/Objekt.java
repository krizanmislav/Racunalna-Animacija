package hr.fer.obj;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

public class Objekt {

	String ime;
	LinkedList<Vrh> vrhovi = new LinkedList<Vrh>();
	LinkedList<Poligon> poligoni = new LinkedList<Poligon>();
	
	public Objekt() {
		// TODO Auto-generated constructor stub
	}
	
	
	public static Objekt ucitajObjekt(String put) {
		
		Objekt obj = new Objekt();
		
		List<String> lines = null;
		
		try {
			lines = Files.readAllLines(Paths.get(put));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		for(String s : lines) {
			//System.out.println(s);
			if(s.startsWith("v")) {
				String[] parts = s.split("\\s+");
				Vrh v = new Vrh(Double.parseDouble(parts[1]), 
						Double.parseDouble(parts[2]), 
						Double.parseDouble(parts[3]));
				obj.vrhovi.add(v);
				
			}else if(s.startsWith("f")) {
				String[] parts = s.split("\\s+");
				Poligon p = new Poligon();
				for(int i = 1; i < parts.length; i++) {
					p.poz.add(Integer.parseInt(parts[i]));
				}
				obj.poligoni.add(p);
				
			}else if(s.startsWith("g")) {
				obj.ime = s;
			}
		}
		
		return obj;
		
	}
	
	@Override
	public String toString() {
		return vrhovi.toString() + "\n" + poligoni.toString();
	}
	
}
