/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.md.pmatrix.test.pushmatrix.newtest;

import be.ibiiztera.md.pmatrix.pushmatrix.scripts.ExtensionFichierIncorrecteException;
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;
import be.ibiiztera.md.pmatrix.pushmatrix.scripts.VersionNonSupportéeException;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Manuel DAHMEN
 */
public class TestCollection {
    private ArrayList<TestObjet> tests = new ArrayList<TestObjet>();
    
    public void testCollection()
    {
        Iterator<TestObjet> it = tests.iterator();
        while(it.hasNext())
            it.next().run();
    }
    
    public void add(TestObjet to)
    {
        tests.add(to);
    }
    public void add(File fichier)
    {
        try {
            TestObjet to = new TestObjet();
                to.scene(new Loader().load(fichier, to.scene()));
            add(to);
        } catch (VersionNonSupportéeException ex) {
            Logger.getLogger(TestCollection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ExtensionFichierIncorrecteException ex) {
            Logger.getLogger(TestCollection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void add(File [] fichiers)
    {
        for(int i=0; i<fichiers.length; i++)
        {
            try {
                TestObjet to = new TestObjet();
                to.scene(new Loader().load(fichiers[i], to.scene()));
                add(to);
            } catch (VersionNonSupportéeException ex) {
                Logger.getLogger(TestCollection.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ExtensionFichierIncorrecteException ex) {
                Logger.getLogger(TestCollection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void run()
    {
        Iterator<TestObjet> it = tests.iterator();
        while(it.hasNext())
            it.next().run();
    }
}
