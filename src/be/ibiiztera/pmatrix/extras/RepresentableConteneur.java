/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package be.ibiiztera.pmatrix.extras;

import be.ibiiztera.md.pmatrix.pushmatrix.MODObjet;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;
import be.ibiiztera.md.pmatrix.pushmatrix.TRIObject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Atelier
 */
public class RepresentableConteneur implements Representable
{
    private List<Representable> re = new ArrayList<Representable>();
    
    
    public RepresentableConteneur(Representable [] r) {
        re.addAll(Arrays.asList(r));
    }

    RepresentableConteneur() {
        
    }
    public void add(Representable r)
    {
        re.add(r);
    }
    public List<Representable> getListRepresentable()
    {
        return re;
    }
    public Iterator<Representable> iterator()
    {
        return re.iterator();
    }
    @Override
    public String id() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setId(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
