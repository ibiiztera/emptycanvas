/*

    Copyright (C) 2010-2012  DAHMEN, Manuel, Daniel

    This library is free software; you can redistribute it and/or
    modify it under the terms of the GNU Lesser General Public
    License as published by the Free Software Foundation; either
    version 2.1 of the License, or (at your option) any later version.

    This library is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
    Lesser General Public License for more details.

    You should have received a copy of the GNU Lesser General Public
    License along with this library; if not, write to the Free Software
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

*/
package be.ibiiztera.md.pmatrix.pushmatrix;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import javax.imageio.ImageIO;

/**
 * 
 * @author Manuel
 */
public class Animation {
	private int debut;
	private int fin;
	private int fps;

	protected int resX = 1200;
	protected int resY = 1200;

	private Scene scene;

	private int noImage;

	private BufferedImage image;

	private String repertoire;
	private String patternFichier;
	private int MAX_FRAMES = 24 * 3600;
	private int maxFrames = 3 * 60 * 25;
	private String nom;
	ZBuffer z;

	public Animation(Scene s) {
		this.scene = s;
		z = new ZBufferImpl(resX, resY);
	}

	public void endNoFrame(int end) {
		this.maxFrames = end;
	}

	public void repertoire(String rep, String patternFichier) {
		this.repertoire = rep;
		this.patternFichier = patternFichier;
	}

	public float dureeSec() {
		return 1.0f * (fin - debut) / fps;
	}

	public float debutSec() {
		return 1.0f * debut / fps;
	}

	public float finSec() {
		return 1.0f * fin / fps;
	}

	public int nbreImages() {
		return fin - debut;
	}

	public int fps() {
		return fps;
	}

	public void nom(String n) {
		this.nom = n;
		patternFichier = nom;
	}

	public String repertoire() {
		return repertoire;
	}

	public String repertoire(String rep) {
		{
			repertoire = System.getProperty("user.home") + File.separator
					+ "PMMATRIX.OUTPUT" + File.separator + "film-sequence"
					+ File.separator + rep;
		}
		return repertoire;
	}

	public boolean suivant() {
		noImage++;
		if (noImage > MAX_FRAMES || noImage > maxFrames)
			return false;
		else
			return true;
	}

	public int noImage() {
		return noImage;
	}
	protected void modifier()
	{
		
	}
	public void ecrireImage() throws Exception {
		modifier();
		z.scene(scene);
		z.dessinerSilhouette3D();

		NumberFormat nb = NumberFormat.getNumberInstance();
		nb.setMinimumIntegerDigits(6);
		nb.setMinimumFractionDigits(0);
		nb.setMaximumFractionDigits(0);
                new File(repertoire + File.separator + nom).mkdirs();

		File img = new File(repertoire + File.separator + nom + File.separator
				+ patternFichier + nb.format(noImage) + ".jpg");
		if (!img.exists())
			ImageIO.write((RenderedImage) z.image(), "jpg", img);
		else
			throw new Exception("FICHIER EXISTE: " + img.getAbsolutePath());
                System.gc();
	}

    public void lancer() {
        try {
            while (suivant()) {
                try {
                    modifier();
                    System.out.print(scene().toString());
                    System.out.print("+");
                    ecrireImage();
               } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // ENCODE
        /*
        Encoder e = new Encoder();
        EncodingAttributes ea = new EncodingAttributes();
        try {
            e.encode(new File(repertoire + File.separator + nom), new File(repertoire + File.separator + nom + ".avi"), ea);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InputFormatException ex) {
            Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
        } catch (EncoderException ex) {
            Logger.getLogger(Animation.class.getName()).log(Level.SEVERE, null, ex);
        }
        * */
        
    }

    public static void main(String[] args) {
		Scene s = new Scene();
		TRI t = new TRI(new Point3D(0, 0, 0), new Point3D(0, 10, 0),
				new Point3D(10, 0, 1), new Color(255,0,0));
		s.add(new TRIObject(t));
		Animation a = new Animation(s);
		a.repertoire("TRIANGLES");
		a.nom("TRIANGLES009");

		try {
			while (a.suivant()) {
				try {
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
	public void defaultGen(String nom) {

	}

	/**
	 * @return
	 */
	public Scene scene() {
		
		return scene;
	}
	/**
	 * @param s
	 */
	public void scene(Scene s) {
		scene = s;
	}

}
