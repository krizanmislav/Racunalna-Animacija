package hr.fer.obj;

import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLException;
import com.jogamp.opengl.GLProfile;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.util.FPSAnimator;
import com.jogamp.opengl.util.texture.Texture;

public class Glavni {


	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		GLProfile profile = GLProfile.get(GLProfile.GL2);
		GLCapabilities capabilities = new GLCapabilities(profile);

		// The canvas
		GLCanvas glcanvas = new GLCanvas(capabilities);


		//ucitaj objekt
		String putObjekta = "C:\\RA\\Lab1\\f16.obj";
		String putPutanje = "C:\\RA\\Lab1\\putanja.obj";
		
		//snow,explosion, balloon
		String putDoTexture = "C:\\RA\\Lab2\\snow.bmp";
		
		
		Objekt obj = Objekt.ucitajObjekt(putObjekta);
		Objekt putanja = Objekt.ucitajObjekt(putPutanje);

		//Lab1Vj lab = new Lab1Vj(obj,putanja);
		Lab2Vj lab = new Lab2Vj(obj,putDoTexture);

		glcanvas.addGLEventListener(lab);
		glcanvas.addKeyListener(lab);
		
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
