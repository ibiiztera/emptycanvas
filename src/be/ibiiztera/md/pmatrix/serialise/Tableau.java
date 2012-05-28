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
