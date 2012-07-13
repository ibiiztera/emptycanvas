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

import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;
import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author manuel
 */
public class ConsoleMain {

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("USAGE ConsoleMain inputFile outputFile.png");
            System.exit(1);
        }
        String i = args[0];
        String o = args[1];
        ZBuffer b = new ZBufferImpl(1000, 1000);
        Scene s = new Scene();
        if (new File(i).exists()) {
            new Loader().loadIF(new File(i), s);
            b.scene(s);
            b.dessinerSilhouette3D();
            BufferedImage image = b.image();
            try {
                ImageIO.write((RenderedImage) image, "png", new File(o));
            } catch (IOException ex) {
                System.err.println("Erreur d'écriture de fichier\n" + ex.getMessage());
            }
        } else {
            System.err.println("Fichier d'entree inexistant");
        }
    }

    public static void display(int resx, int resy, String imageType, File i, File o) {
        ZBuffer b = new ZBufferImpl(resx, resy);
        Scene s = new Scene();
        if (i.exists() && !o.exists()) {
            new Loader().loadIF(i, s);
            b.scene(s);
            b.dessinerSilhouette3D();
            BufferedImage image = b.image();
            try {
                ImageIO.write((RenderedImage) image, imageType, o);
            } catch (IOException ex) {
                System.err.println("Erreur d'écriture de fichier\n" + ex.getMessage());
            }
        } else {
            System.err.println("Fichier d'entree inexistant ou fichier de sortie existant");
        }
    }
}
