package be.ibiiztera.md.pmatrix.serialise;

/**
 *
 * @author Manuel DAHMEN
 */
public class Lecture {
    private final String string;
    public Lecture(String s)
    {
        this.string = s;
    }
    public Serialisable lire()
    {
        Serialisable res = null;
        String type = string.substring(0, string.indexOf(" ("));
        if(type.equals("tableau"))
            return new Tableau().lecture(string);
        if(type.equals("table"))
            return new Table().lecture(string);
        if(type.equals("atome"))
            return new TypePrimitif().lecture(string);
        return null;
    }
}
