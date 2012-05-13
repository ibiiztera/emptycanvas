/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.md.pmatrix.test.pushmatrix.newtest;

import be.ibiiztera.md.pmatrix.pushmatrix.scripts.Loader;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;

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
        TestObjet to = new TestObjet();
        new Loader().loadIF(fichier, to.scene());
        add(to);
    }
    public void add(File [] fichiers)
    {
        for(int i=0; i<fichiers.length; i++)
        {
            TestObjet to = new TestObjet();
            new Loader().loadIF(fichiers[i], to.scene());
            add(to);
        }
    }
}
