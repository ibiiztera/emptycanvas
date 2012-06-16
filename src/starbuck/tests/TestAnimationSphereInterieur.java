/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starbuck.tests;

import be.ibiiztera.md.pmatrix.pushmatrix.Camera;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.TRI;
import be.ibiiztera.md.pmatrix.pushmatrix.generator.TRISphere;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Atelier
 */
public class TestAnimationSphereInterieur extends TestObjet {

    private static Point3D coordSphere(double a, double b, double radius) {
        return new Point3D(Math.cos(a) * Math.cos(b) * radius,
                0 + Math.cos(a) * Math.sin(b) * radius,
                0 + Math.sin(a) * radius);

    }
    private double a;
    private double b;
    private Point3D pos;

    @Override
    public void init() {
        try {
            super.init();

            TRISphere ts = new TRISphere(new Point3D(0, 0, 0), 100);

            ts.map(ImageIO.read(getClass().getResource("Coucherdesoleil.jpg")), "Coucherdesoleil.jpg");
            scene().add(ts);
        } catch (IOException ex) {
            Logger.getLogger(TestAnimationSphereInterieur.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void testScene() {
        a = 0;
        b = 0;
        pos = coordSphere(a, b, 10);

        a += (Math.random() - 0.5) * Math.PI / 36;
        b += (Math.random() - 0.5) * Math.PI / 36 / 2;
        if (a > Math.PI / 2) {
            a = Math.PI / 2;
        }
        if (a < -Math.PI / 2) {
            a = -Math.PI / 2;
        }
        if (b > Math.PI) {
            a = Math.PI;
        }
        if (b < -Math.PI) {
            a = -Math.PI;
        }
        camera(new Camera(pos, new Point3D(0, 0, 0), null));
        pos = coordSphere(a, b, 10);
        description("Textured sphere seen from inside");

    }

    public static void main(String[] args) {
        for (int i = 0; i < 2000; i++) {
            TestAnimationSphereInterieur to = new TestAnimationSphereInterieur();
            to.setResx(640);
            to.setResy(480);
            to.loop(true);
            to.setFilename("image_" + (i + 10000));
            to.publishResult(false);
            to.run();


        }
    }
}
