/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.md.pmatrix.test.pushmatrix.newtest;

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
}
