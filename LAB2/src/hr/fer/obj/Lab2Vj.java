package hr.fer.obj;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL2;
import com.jogamp.opengl.GLAutoDrawable;
import com.jogamp.opengl.GLEventListener;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.glu.GLU;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

public class Lab2Vj implements GLEventListener, KeyListener{
	private Objekt obj;
	
	private Vector3d camera = new Vector3d(0, 0, 0);
	private Vector3d cameraRotate = new Vector3d(0,0,0);
	private Vector3d ociste = new Vector3d(0, 0, 5);
	
	
	GeneratorCestica gener = new GeneratorCestica();

	String putdoTexture;
	Texture texture;
	
	public Lab2Vj(Objekt obj,String putdoTexture) {
		this.obj = obj;
		this.putdoTexture = putdoTexture;

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
		GL2 gl = drawable.getGL().getGL2();

		gl.glShadeModel(GL2.GL_SMOOTH);
		gl.glClearColor(0f,0f,0f,0f);
		gl.glClearDepth(1.0f);
		gl.glEnable(GL2.GL_DEPTH_TEST);
		gl.glDepthFunc(GL2.GL_LEQUAL);
		gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

		
		gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT); 
		gl.glLoadIdentity();

		//gl.glTranslated(-camera.x * scaler,-camera.y * scaler, camera.z * scaler);
		
		//ociste
		GLU glu = GLU.createGLU(gl);
		glu.gluLookAt(camera.x,camera.y,camera.z,ociste.x, ociste.y, ociste.z, 0, 1, 0);
		

		
		displayObject(gl);
		
		//pomicati objekt cestica
		gener.nacrtaj(drawable,texture,Vector3d.duplicate(camera).subtract(ociste));

		gl.glFlush();
	}

	
	
	@Override
	public void dispose(GLAutoDrawable drawable) {
		// TODO Auto-generated method stub
		
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
		gl.glEnable(GL2.GL_BLEND);
		gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE_MINUS_SRC_ALPHA);
		
		try {
			//this.texture = Util.loadTexture(putdoTexture);
			this.texture = TextureIO.newTexture(Paths.get(putdoTexture).toFile(), false);
		} catch (GLException | IOException e) {

			e.printStackTrace();
		}
		
		System.out.println();
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

		gl.glMatrixMode(GL2.GL_MODELVIEW);
		gl.glLoadIdentity();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_A) {
			camera.x+= 0.1;
		}
		if(e.getKeyCode() == KeyEvent.VK_D) {
			camera.x-= 0.1;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_S) {
			camera.y+= 0.1;
		}
		if(e.getKeyCode() == KeyEvent.VK_W) {
			camera.y-= 0.1;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_I) {
			ociste.x -= 0.1;
		}
		if(e.getKeyCode() == KeyEvent.VK_K) {
			ociste.x += 0.1;
		}
		if(e.getKeyCode() == KeyEvent.VK_J) {
			ociste.y -= 0.1;
		}
		if(e.getKeyCode() == KeyEvent.VK_L) {
			ociste.y += 0.1;
		}
		
		
		if(e.getKeyCode() == KeyEvent.VK_1) {
			gener.glavnaPozicija.x += 0.1;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_2) {
			gener.glavnaPozicija.x -= 0.1;
		}
		if(e.getKeyCode() == KeyEvent.VK_3) {
			gener.glavnaPozicija.y += 0.1;
		}
		
		if(e.getKeyCode() == KeyEvent.VK_4) {
			gener.glavnaPozicija.y -= 0.1;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
