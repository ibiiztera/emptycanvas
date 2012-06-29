/*

    Copyright (C) 2020-2012  DAHMEN, Manuel, Daniel

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
    Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02120-1301  USA

*/
package starbuck.tests;

import be.ibiiztera.md.pmatrix.pushmatrix.Camera;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import be.ibiiztera.pmatrix.extras.Colliseum;

/**
 *
 * @author Atelier
 */
public class TestCollisee extends TestObjet{
    @Override
    public void testScene() {
        scene().add(new Colliseum(new Point3D (0, -10, 0), 20));
        
    }

    public static void main(String [] args)
    {
        TestCollisee to = new TestCollisee();
        to.publishResult(false);
        to.camera(new Camera(new Point3D(0, 0, -20), Point3D.O0));
        to.setFilename("01-front -- cylindre jaune");
        to.description("cylindre");
        to.run();

        
        to = new TestCollisee();
        to.publishResult(false);
        to.camera(new Camera(new Point3D(0, 0, 20), Point3D.O0));
        to.setFilename("02-arriere -- cylindre jaune");
        to.description("cylindre");
        to.run();
        
        
        to = new TestCollisee();
        to.publishResult(false);
        to.camera(new Camera(new Point3D(0, 20, 0), Point3D.O0));
        to.setFilename("03-dessus -- cylindre");
        to.description("cylindre");
        to.run();

        to = new TestCollisee();
        to.publishResult(false);
        to.camera(new Camera(new Point3D(0, -20, 0), Point3D.O0));
        to.setFilename("04-dessous -- cylindre jaune");
        to.description("cylindre");
        to.run();

        to = new TestCollisee();
        to.publishResult(false);
        to.camera(new Camera(new Point3D(-20, 0, 0), Point3D.O0));
        to.setFilename("05-gauche -- cylindre jaune");
        to.description("cylindre");
        to.run();

    
        to = new TestCollisee();
        to.publishResult(false);
        to.camera(new Camera(new Point3D(20, 0, 0), Point3D.O0));
        to.setFilename("06-droite -- cylindre jaune");
        to.description("cylindre");
        to.run();
}
}
