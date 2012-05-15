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
        to.setPerspective(-3);
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
