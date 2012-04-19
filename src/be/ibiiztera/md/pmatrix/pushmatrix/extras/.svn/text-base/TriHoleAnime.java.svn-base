/**
 * 
 */
package be.ibiiztera.md.pmatrix.pushmatrix.extras;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import be.ibiiztera.md.pmatrix.pushmatrix.Animation;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIObject;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 27 déc. 2011
 *
 */
public class TriHoleAnime extends Animation {
	TriHole th ;
	/**
	 * @param s
	 */
	public TriHoleAnime(Scene s) {
		super(s);
	}
	public static void main(String[] args) {
		Scene s = new Scene();
		ArrayList<Point3D> p = new ArrayList<Point3D>();
		p.add(new Point3D(0,0,0));
		p.add(new Point3D(1,0,0));
		p.add(new Point3D(0,1,0));
		p.add(new Point3D(0,0,1));
		
		TriHole th = new TriHole(p);
		s.add(th);
		TriHoleAnime a = new TriHoleAnime(s);
		a.repertoire("TRIHOLE_RANDOM");
		a.nom("THR003");

		try {
			while (a.suivant()) {
				try {
					a.modifier(th);
					a.ecrireImage();
					System.out.print(a.scene().toString());
					System.out.print("+");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	private Point3D pcurrent = new Point3D(0,0,0);
	/**
	 * @param th2
	 */
	private void modifier(TriHole th2) {
		Random r = new Random();
		float fx = (r.nextFloat()-0.5f)*10;
		float fy = (r.nextFloat()-0.5f)*10;
		float fz = (r.nextFloat()-0.5f)*10;
		pcurrent = pcurrent.plus(new Point3D(fx, fy, fz));
		th2.add(pcurrent);
	}

}
