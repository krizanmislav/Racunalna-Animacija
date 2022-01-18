package hr.fer.obj;

import javax.swing.JFrame;

import com.jogamp.opengl.util.FPSAnimator;

import java.awt.Frame;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.math.Matrix4;
import com.jogamp.opengl.util.FPSAnimator;


public class Lab1Vj implements GLEventListener{

	private Objekt obj;
	
	
	//putanja bspline
	private Objekt putanja;
	
	private Vrh pocetnaOs = new Vrh(0.0, 0.0, 1.0);
	private Vrh trenutnaOs = pocetnaOs;
	
	
	private Vrh ociste = new Vrh(0.0, 0.0, 0.0);
	
	private GLU glu = new GLU();

	
	public Lab1Vj(Objekt obj, Objekt putanja) {
		this.obj = obj;
		this.putanja = putanja;
	}
	
	double t = 0;
	int iter = 0;
	
	public Vrh izracunajBSplint(double t, Vrh v1, Vrh v2, Vrh v3, Vrh v4) {
		double[][] tMat = {{t*t*t, t*t, t, 1.0}};
		double[][] bMat = {{-1/6.0, 3/6.0, -3/6.0, 1/6.0},
							{3/6.0, -6/6.0, 3/6.0, 0/6.0},
							{-3/6.0, 0/6.0, 3/6.0, 0/6.0},
							{1/6.0, 4/6.0, 1/6.0, 0/6.0}};
		
		double[][] rMat = {{v1.x, v1.y, v1.z},
							{v2.x, v2.y, v2.z},
							{v3.x, v3.y, v3.z},	
							{v4.x, v4.y, v4.z}};
		
		double[][] rez1 = Util.multiplyMat(tMat, bMat);
		double[][] rez2 = Util.multiplyMat(rez1, rMat);
		
		
		Vrh v = new Vrh(rez2[0][0], rez2[0][1], rez2[0][2]);
		return v;
	}
	
	public Vrh izracunajTangentu(double t, Vrh v1, Vrh v2, Vrh v3, Vrh v4) {
		double[][] tMat = {{t*t, t, 1.0}};
		double[][] bMat = {{-1/2.0, 3/2.0, -3/2.0, 1/2.0},
							{2/2.0, -4/2.0, 2/2.0, 0/2.0},
							{-1/2.0, 0/2.0, 1/2.0, 0/2.0}};
		
		double[][] rMat = {{v1.x, v1.y, v1.z},
							{v2.x, v2.y, v2.z},
							{v3.x, v3.y, v3.z},	
							{v4.x, v4.y, v4.z}};
		
		double[][] rez1 = Util.multiplyMat(tMat, bMat);
		double[][] rez2 = Util.multiplyMat(rez1, rMat);
		
		
		Vrh v = new Vrh(rez2[0][0], rez2[0][1], rez2[0][2]);
		v.normaliziraj();
		
		
		return v;
	}
	
	public void napraviRotaciju(Vrh e, Vrh s, GL2 gl) {
		Vrh os = new Vrh(s.y* e.z - e.y*s.z,
						-(s.x*e.z - e.x*s.z),
						s.x*e.y - s.y*e.x);
		
		os.normaliziraj();
		
		double normE = Math.sqrt(e.x*e.x + e.y * e.y + e.z*e.z);
		double normS = Math.sqrt(s.x*s.x + s.y * s.y + s.z*s.z);
		
		double scalProd = s.x * e.x + s.y * e.y + s.z * e.z;
		
		double fi = Math.acos(scalProd/(normE * normS)) * (180/Math.PI);
		gl.glRotated(fi, os.x, os.y, os.z);
		
		//trenutnaOs = os;
	}
	
	
	public void displayObject(GL2 gl) {
		if(obj != null) {
			gl.glBegin(GL2.GL_TRIANGLES); 

			for(Poligon p : obj.poligoni) {
				for(Integer i : p.poz) {
					//jer se vrhovi pisu od 1 (ne od 0)
					Vrh v = obj.vrhovi.get(i-1);
					
					if(i % 3 == 1) {
						gl.glColor3f(1.0f,0.0f,0.0f);
					}else if(i % 3 == 2) {
						gl.glColor3f(0.0f,1.0f,0.0f);
					}else if(i % 3 == 0) {
						gl.glColor3f(0.0f,0.0f,1.0f);
					}
					
					//gl.glColor3d(v.x,v.y,v.z);
					gl.glVertex3d(v.x,v.y,v.z);
				}
			}
			
			gl.glEnd();

		}
		

	}
	
	@Override
	public void display(GLAutoDrawable drawable) {

		
		///GLU glu = new GLU();
		//glu.gluLookAt(ociste.x, ociste.y, -20f, 0, 0, 0, 0, 0, 0);
		
		
		//restart
		if(t >= 1.0) {
			iter++;
			t = 0;
			if((iter+3) >= putanja.vrhovi.size()) {
				iter = 0;
			}
		}

		
		GL2 gl = drawable.getGL().getGL2();

		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glClearColor(0f,0f,0f,0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); 
		gl.glLoadIdentity();
		

		gl.glTranslated(10,-10.0, -100.f);
		gl.glRotated(-45, 1, 1, 1);
		
		gl.glLineWidth(5);
		gl.glBegin(GL2.GL_LINE_STRIP);
		//nacrtaj putanju
		for(int tempIter = 0; (tempIter + 3) < putanja.vrhovi.size(); tempIter++) {
			for(double tempT = 0; tempT < 1.0; tempT+=0.05) {
				Vrh p1 = izracunajBSplint(tempT, putanja.vrhovi.get(tempIter),
						putanja.vrhovi.get(tempIter + 1), putanja.vrhovi.get(tempIter + 2), putanja.vrhovi.get(tempIter + 3));
				
				gl.glColor3f(1.0f,0.0f,0.0f);
				gl.glVertex3d(p1.x,p1.y,p1.z);
				
			}
		}
		gl.glEnd();
		
		
		//ovisno o t radi translaciju
		Vrh p1 = izracunajBSplint(t, putanja.vrhovi.get(iter),
				putanja.vrhovi.get(iter + 1), putanja.vrhovi.get(iter + 2), putanja.vrhovi.get(iter + 3));
		
		Vrh e = izracunajTangentu(t, putanja.vrhovi.get(iter),
				putanja.vrhovi.get(iter + 1), putanja.vrhovi.get(iter + 2), putanja.vrhovi.get(iter + 3));
		
		
	
		
		//nacrtaj tangentu
		gl.glLineWidth(5);
		gl.glBegin(GL2.GL_LINE_STRIP);
			gl.glColor3f(0.0f,0.0f,1.0f);
			//gl.glVertex3d(0,0,0);
			gl.glVertex3d(p1.x - 6* e.x,p1.y - 6* e.y,p1.z - 6* e.z);
			gl.glVertex3d(p1.x,p1.y,p1.z);
			gl.glVertex3d(p1.x + 6* e.x,p1.y + 6* e.y,p1.z + 6* e.z);
		gl.glEnd();
		

		
		gl.glTranslated(p1.x, p1.y, p1.z);
		//gl.glTranslated(0.0,0.0, -50.f);
		
		napraviRotaciju(e, trenutnaOs, gl);

		
		gl.glScalef(10.50f,10.25f,10.50f); 
		
		
		displayObject(gl);

		gl.glFlush();
		
		t += 0.05;
		
		
		
	}

	@Override
	public void dispose(GLAutoDrawable drawable) {
	}

	@Override
	public void init(GLAutoDrawable drawable) {

		GL2 gl = drawable.getGL().getGL2();

		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glClearColor(0f, 0f, 0f, 0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

	}

	@Override
	public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {

		// TODO Auto-generated method stub
		GL2 gl = drawable.getGL().getGL2();
		if(height <= 0) 
			height = 1;

		float h = (float)width / (float)height;
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL2.GL_PROJECTION);
		gl.glLoadIdentity();

		glu.gluPerspective(45.0f, h, 0.1,100.0);
		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}



}