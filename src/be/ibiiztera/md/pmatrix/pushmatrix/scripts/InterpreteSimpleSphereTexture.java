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
/**
 *
 */
package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import java.awt.Color;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.Scene;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBuffer;
import be.ibiiztera.md.pmatrix.pushmatrix.ZBufferImpl;
import be.ibiiztera.md.pmatrix.pushmatrix.extras.SimpleSphereAvecTexture;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author MANUEL DAHMEN
 *
 * dev
 *
 * 21 oct. 2011
 *
 */
public class InterpreteSimpleSphereTexture implements Interprete {
    private String répertoire;

    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }
    private int pos;
    /*
     * (non-Javadoc) @see
     * be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#interprete(java.lang.String,
     * int)
     */

    @Override
    public Object interprete(String text, int pos) throws InterpreteException {
        try {
            Point3D c = null;
            double r = 1;
            Color col = Color.black;

            InterpretesBase ib;
            InterpretePoint3DBAK ip;
            InterpreteCouleur pc;
            ArrayList<Integer> patt = null;

            ib = new InterpretesBase();
            patt = new ArrayList<Integer>();
            patt.add(ib.BLANK);
            patt.add(ib.LEFTPARENTHESIS);
            patt.add(ib.BLANK);
            ib.compile(patt);
            ib.read(text, pos);
            pos = ib.getPosition();

            ip = new InterpretePoint3DBAK();
            c = (Point3D) ip.interprete(text, pos);
            pos = ip.getPosition();

            ib = new InterpretesBase();
            patt = new ArrayList<Integer>();
            patt.add(ib.BLANK);
            patt.add(ib.DECIMAL);
            patt.add(ib.BLANK);
            ib.compile(patt);


            ib.read(text, pos);
            pos = ib.getPosition();
            r = (Double) ib.get().get(1);

            pc = new InterpreteCouleur();
            col = (Color) pc.interprete(text, pos);
            pos = pc.getPosition();

            InterpreteNomFichier inf = new InterpreteNomFichier();
            File f = (File) inf.interprete(text, pos);
            if(f==null)
                throw new InterpreteException("Fichier non trouvé");
            pos = inf.getPosition();


            ib = new InterpretesBase();
            patt = new ArrayList<Integer>();
            patt.add(ib.BLANK);
            patt.add(ib.RIGHTPARENTHESIS);
            patt.add(ib.BLANK);
            ib.compile(patt);

            ib.read(text, pos);
            pos = ib.getPosition();


            this.pos = pos;
            SimpleSphereAvecTexture s = null;
            try {
                s = new SimpleSphereAvecTexture(c, r, col, ImageIO.read(f));
				s.fichier(f.getAbsolutePath());
            } catch (IOException ex) {
                Logger.getLogger(InterpreteSimpleSphereTexture.class.getName()).log(Level.SEVERE, null, ex);
            }
            return s;
        } catch (InterpreteException ex) {
            Logger.getLogger(InterpreteSimpleSphereTexture.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /*
     * (non-Javadoc) @see
     * be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#getPosition()
     */
    @Override
    public int getPosition() {
        return pos;
    }

    /*
     * (non-Javadoc) @see
     * be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#constant()
     */
    @Override
    public InterpreteConstants constant() {
        return null;
    }

    /*
     * (non-Javadoc) @see
     * be.ibiiztera.md.pmatrix.pushmatrix.scripts.Interprete#setConstant(be.ibiiztera.md.pmatrix.pushmatrix.scripts.InterpreteConstants)
     */
    @Override
    public void setConstant(InterpreteConstants c) {
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        InterpreteSimpleSphereTexture iss = new InterpreteSimpleSphereTexture();
        SimpleSphereAvecTexture ss = null;
        try {
            //ss = (SimpleSphereAvecTexture) iss.interprete("((0.0, 0.0, 0.0) 10.0 (255, 0, 0 ) \"c:\\Users\\Mary\\manu\\im1.jpg\")", 0);
            ss = (SimpleSphereAvecTexture) iss.interprete("((0.0, 0.0, 0.0) 10.0 (255, 0, 0 ) \"c:\\Users\\Mary\\manu\\dsc_9374.jpg\")", 0);
            //ss = (SimpleSphereAvecTexture) iss.interprete("((0.0, 0.0, 0.0) 10.0 (255, 0, 0 ) \"c:\\Users\\Mary\\manu\\simplesphere texture.png\")", 0);
            //ss = (SimpleSphereAvecTexture) iss.interprete("((0.0, 0.0, 0.0) 10.0 (255, 0, 0 ) \"c:\\Users\\Mary\\im1.jpg\")", 0);
        } catch (InterpreteException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        ZBuffer z = new ZBufferImpl(1000, 1000);
        Scene scene = new Scene();
        scene.add(ss);
        z.scene(scene);
        z.dessinerSilhouette3D();
        try {
            ImageIO.write((RenderedImage) z.image(), "png", new File(System.getProperty("user.home") + File.separator + "manu\\simplesphere texture 22.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
