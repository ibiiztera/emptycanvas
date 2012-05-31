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
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package starbuck.tests;

import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import java.util.ResourceBundle;

/**
 *
 * @author Manuel DAHMEN
 */
public class TestMite extends TestObjet{
    public static void main(String [] args)
    {
        TestMite to = new TestMite();
        to.setPerspective(-100, -20);
        to.run();
        
    }
    @Override
    public void testScene()
    {
        ResourceBundle rb = ResourceBundle.getBundle("starbuck/tests/Mite");
        String mite = rb.getString("mite");
        new Loader().loadIF(mite, scene);
        description = "Primtive model. triangle mesh";
    }
            
    
}
