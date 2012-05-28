package be.ibiiztera.md.pmatrix.serialise;

import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Manuel DAHMEN
 */
public class Table  implements Serialisable{
    private String[] colonnes;
    private List<Map<String,Serialisable>> lignes;

	public Table()
	{
	}


    public void colonnes(String [] colonnes)
    {
        this.colonnes = colonnes;
    }
    public void ajouterLigne(Map<String, Serialisable> ligne)
    {
        lignes.add(ligne);
    }
    @Override
    public String serialise() throws Exception {
        String resultat = "table("+ taille() +"): ";
        Iterator<Map<String, Serialisable>> it = lignes.iterator();
        while(it.hasNext())
        {
            Map<String, Serialisable> map = it.next();
            resultat += "ligne ( "+map.entrySet().size()+" ) : ";
            Iterator<Entry<String, Serialisable>> it2 = map.entrySet().iterator();
            while(it2.hasNext())
            {
                Entry<String, Serialisable> next = it2.next();
                resultat += "nom champ : " + next.getKey();
                resultat += "valeur champ : " + next.getValue().serialise();
            }
            
        }
        return resultat + "";
    }

    @Override
    public void serialise(OutputStream out) throws Exception {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public int taille() {
        return lignes.size();
    }

    @Override
    public String type() {
        return "table";
    }

    @Override
    public Serialisable lecture(String s) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
}
