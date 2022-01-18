package hr.fer.obj;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;

public class Glavni {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		// The canvas
		GLCanvas glcanvas = new GLCanvas(capabilities);


		//ucitaj objekt
		String putObjekta = "C:\\RA\\Lab1\\panda.obj";
		String putPutanje = "C:\\RA\\Lab1\\putanja.obj";
		Objekt obj = Objekt.ucitajObjekt(putObjekta);
		Objekt putanja = Objekt.ucitajObjekt(putPutanje);

		Lab1Vj lab = new Lab1Vj(obj,putanja);


		glcanvas.addGLEventListener(lab);
		
		glcanvas.setSize(800,800);


		JFrame frame = new JFrame ("LAB");
		frame.getContentPane().add(glcanvas);
		frame.setSize(frame.getContentPane().getPreferredSize());
		frame.setVisible(true);
		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		FPSAnimator animator = new FPSAnimator(glcanvas,100,true);

		animator.start();


	}
}
