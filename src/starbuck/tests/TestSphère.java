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

package starbuck.tests;

import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.generator.TRISphere;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Manuel DAHMEN
 * @date
 */
public class TestSphère extends TestObjet{

    @Override
    public void testScene() {
        TRISphere s;
        s = new TRISphere(Point3D.X.mult(3), 2);
        try {
            s.map(ImageIO.read(new File("C:\\Users\\Mary\\Documents\\dev\\emptycanvas\\gitclone\\emptycanvas\\src\\starbuck\\tests\\2456614033-blue-texture.jpg")), "C:\\Users\\Mary\\Documents\\dev\\emptycanvas\\gitclone\\emptycanvas\\src\\starbuck\\tests\\2456614033-blue-texture.jpg");
        } catch (IOException ex) {
            Logger.getLogger(TestSphère.class.getName()).log(Level.SEVERE, null, ex);
        }
        scene().add(s);
        s = new TRISphere(Point3D.X.mult(-3), 2);
        try {
            s.map(ImageIO.read(new File("C:\\Users\\Mary\\Documents\\dev\\emptycanvas\\gitclone\\emptycanvas\\src\\starbuck\\tests\\2456614033-blue-texture.jpg")), "C:\\Users\\Mary\\Documents\\dev\\emptycanvas\\gitclone\\emptycanvas\\src\\starbuck\\tests\\2456614033-blue-texture.jpg");
        } catch (IOException ex) {
            Logger.getLogger(TestSphère.class.getName()).log(Level.SEVERE, null, ex);
        }
        scene().add(s);
        s = new TRISphere(Point3D.Y.mult(3), 2);
        try {
            s.map(ImageIO.read(new File("C:\\Users\\Mary\\Documents\\dev\\emptycanvas\\gitclone\\emptycanvas\\src\\starbuck\\tests\\2456614033-blue-texture.jpg")), "C:\\Users\\Mary\\Documents\\dev\\emptycanvas\\gitclone\\emptycanvas\\src\\starbuck\\tests\\2456614033-blue-texture.jpg");
        } catch (IOException ex) {
            Logger.getLogger(TestSphère.class.getName()).log(Level.SEVERE, null, ex);
        }
        scene().add(s);
        s = new TRISphere(Point3D.Y.mult(-3), 2);
        try {
            s.map(ImageIO.read(new File("C:\\Users\\Mary\\Documents\\dev\\emptycanvas\\gitclone\\emptycanvas\\src\\starbuck\\tests\\2456614033-blue-texture.jpg")), "C:\\Users\\Mary\\Documents\\dev\\emptycanvas\\gitclone\\emptycanvas\\src\\starbuck\\tests\\2456614033-blue-texture.jpg");
        } catch (IOException ex) {
            Logger.getLogger(TestSphère.class.getName()).log(Level.SEVERE, null, ex);
        }
        scene().add(s);
        description = "4 spheres with textures";
    }
    public static void main(String[] args)
    {
        TestSphère ts = new TestSphère();
        ts.setPerspective(-10, -7);
        ts.run();
    }
}
