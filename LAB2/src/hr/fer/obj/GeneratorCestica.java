package hr.fer.obj;

import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureCoords;

public class GeneratorCestica {

	int maxCestice = 200;
	List<Cestica> cestice = new LinkedList<Cestica>();
	
	double vrijeme = 0.05;
	Vector3d orijentacija = new Vector3d(0, 0, 1);
	
	Vector3d glavnaPozicija = new Vector3d(0, 0, 0);
	
	public GeneratorCestica() {
		inicijalizacijaCestice();
	}
	
	private void inicijalizacijaCestice() {
		for(int i = 0; i < maxCestice; i++) {
			Cestica c = new Cestica(glavnaPozicija);
			cestice.add(c);
		}
		
	}
	
	private void podesiCestice() {
		for(int i = 0; i < cestice.size(); i++) {
			Cestica c = cestice.get(i);
			
			//smanjiti zivot
			c.update(vrijeme);
			
			if(c.starost > c.zivot) {
				cestice.remove(c);
				Cestica nova = new Cestica(glavnaPozicija);
				cestice.add(nova);
			}

		}
		
	}
	
	public void nacrtaj(GLAutoDrawable drawable, Texture texture, Vector3d camera) {
		podesiCestice();
		
		GL2 gl = drawable.getGL().getGL2();
		//gl.glBlendFunc(GL.GL_SRC_ALPHA, GL.GL_ONE);
		
		
		//pronadi ciljnu orjentaciju
		Vector3d ciljna = Vector3d.duplicate(orijentacija).subtract(camera);
		ciljna.normalize();
		
		//pocetna
		napraviRotaciju(ciljna,orijentacija,gl);
		
		
		//gl.glPointSize(5);
		double scalef = 0.05;
		
		gl.glScaled(scalef, scalef, scalef);
		
		texture.enable(gl);
		texture.bind(gl);
		
		
		gl.glBegin(GL2.GL_QUADS);
		for(Cestica c : cestice) {
			if(c.zivot > 0) {
				Vector3d v = c.pozicija;

				gl.glColor4d(c.r, c.g, c.b, 0.4);
				
				//poligon s teksturom
				TextureCoords cords = texture.getImageTexCoords();
				gl.glVertex2d(v.x,v.y);
				gl.glTexCoord2f(cords.left(), cords.top());
				
				gl.glVertex2d(v.x,v.y + c.velicina);
				gl.glTexCoord2f(cords.right(), cords.top());
				
				gl.glVertex2d(v.x + c.velicina,v.y + c.velicina);
				gl.glTexCoord2f(cords.right(), cords.bottom());
				
				gl.glVertex2d(v.x + c.velicina,v.y);
				gl.glTexCoord2f(cords.left(), cords.bottom());
			}
		}
		gl.glEnd();
		texture.disable(gl);
	}
	
	
	public void napraviRotaciju(Vector3d e, Vector3d s, GL2 gl) {
		Vector3d os = new Vector3d(s.y* e.z - e.y*s.z,
						-(s.x*e.z - e.x*s.z),
						s.x*e.y - s.y*e.x);
		
		os.normalize();
		
		double normE = e.getNorm();
		double normS = s.getNorm();
		
		double scalProd = s.x * e.x + s.y * e.y + s.z * e.z;
		
		double fi = Math.acos(scalProd/(normE * normS)) * (180/Math.PI);
		
		if(fi != 0) {
			gl.glRotated(fi, os.x, os.y, os.z);
		}
		
		

		
	}
	

}
