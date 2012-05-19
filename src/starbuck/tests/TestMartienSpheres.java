package starbuck.tests;

import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;
import be.ibiiztera.md.pmatrix.test.pushmatrix.newtest.TestObjet;
import java.util.ResourceBundle;

/**
 *
 * @author Manuel DAHMEN
 */
public class TestMartienSpheres extends TestObjet
{
    public static void main(String [] args)
    {
        TestMartienSpheres to = new TestMartienSpheres();
        to.setPerspective(-100, -20);
        to.run();
        
    }
    @Override
    public void testScene()
    {
        ResourceBundle rb = ResourceBundle.getBundle("starbuck/tests/Spheres");
        String mite = rb.getString("sphere1");
        new Loader().loadIF(mite, scene);
        description = "Primtive model. Green face made up with spheres";
    }
            
    


}
