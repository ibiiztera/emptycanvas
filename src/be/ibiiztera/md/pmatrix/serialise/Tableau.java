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
package be.ibiiztera.md.pmatrix.serialise;

import java.io.OutputStream;

/**
 *
 * @author Manuel DAHMEN
 */
public class Tableau  implements Serialisable{
    private final Serialisable[] tableau;

    Tableau(Serialisable[] colonnes) {
        this.tableau = colonnes;
    }
    public Tableau()
    {
        tableau = null;
    }
    Tableau(Object[] colonnes) {
        tableau = new Serialisable[colonnes.length];
        for(int i = 0; i < colonnes.length; i++)
            tableau[i] = TypePrimitif.getInstance(colonnes[i]);
    }

    @Override
    public String serialise() throws Exception {
        String resultat = "tableau ("+tableau.length+") : ";
        for(int i = 0; i< tableau.length; i++)
            resultat += tableau[i].serialise();
        return resultat;
            
    }

    @Override
    public void serialise(OutputStream out) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String type() {
        return "tableau";
    }

    @Override
    public int taille() {
        return tableau.length;
    }

    @Override
    public Serialisable lecture(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
