package hr.fer.obj;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import com.jogamp.opengl.GLException;
import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;




public class Util {

	public static double[][] multiplyMat(double[][] mat1, double[][] mat2){
		double[][] rez = new double[mat1.length][mat2[0].length];
		
		for(int i = 0; i < mat1.length; i++) {
			for(int j = 0; j < mat2[0].length; j++) {
				rez[i][j] = 0;
				for(int k = 0; k < mat2.length; k++) {
					rez[i][j] += mat1[i][k] * mat2[k][j];
				}
			}
		}
		
		return rez;
	}
	

}
