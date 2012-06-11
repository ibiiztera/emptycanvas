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

import be.ibiiztera.md.pmatrix.pushmatrix.Camera;
import be.ibiiztera.md.pmatrix.pushmatrix.Point3D;
import be.ibiiztera.md.pmatrix.pushmatrix.extras.CollineModèle1;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;

/**
 *
 * @author Atelier
 */
public class TestColline extends TestObjet
{
    public static void main(String [] args)
    {
        TestColline to = new TestColline();
        for(int i=0; i<1000;i++)
        {
            to.setFilename("image_"+i);
            to.publishResult(false);
            to.camera(new Camera(new Point3D(0,0,-i*10), new Point3D(0,0,0), new Point3D(0,0,-i*10+5)));
            to.run();
        }
    }
    @Override
    public void testScene()
    {
         scene().add(new CollineModèle1(1000));
         description("Rorschah-like object");
    }
}
