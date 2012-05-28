package be.ibiiztera.md.pmatrix.serialise;

import java.io.IOException;
import java.io.OutputStream;



/**
 *
 * @author Manuel DAHMEN
 */
public class TypePrimitif implements Serialisable{
	public TypePrimitif()
	{
		valeur = null;
	}
    static Serialisable getInstance(Object object) {
        TypePrimitif tp = new TypePrimitif();
        tp.valeur(object);
        return tp;
    }
    public void valeur(Object o)
    {
        this.valeur = o;
    }
    public Object valeur()
    {
        return this.valeur;
    }
    @Override
    public String serialise() throws Exception{
        return serialise(getCode(), valeur);
    }

    @Override
    public void serialise(OutputStream out) throws IOException, TypeNotFoundException {
        out.write(serialise(getCode(), valeur).getBytes());
    }

    @Override
    public String type() {
        return ""+getCode();
    }

    @Override
    public int taille() {
        return getCode() == STRING ? ((String)valeur).length() : 1;
    }

    @Override
    public Serialisable lecture(String s) {
        int type  = Integer.parseInt(s);
        switch(type)
        {
            case BOOLEAN:
                valeur = (Boolean) s ? "true" : "false";
                break;
            case INTEGER:
                resultat = ;
                break;
            case SHORT:
                resultat = ((Integer) value).toString();
                break;
            case LONG:
                resultat = ((Long) value).toString();
                break;
            case FLOAT:
                resultat = ((Float) value).toString();
                break;
            case DOUBLE:
                resultat = ((Double) value).toString();
                break;
            case CHARACTER:
                resultat = ((Character) value).toString();
                break;
            case STRING:
                resultat = ""+((String) value).length()+((String) value);
                break;
            default:
                throw new TypeNotFoundException(type);

        }
        return "atome ("+taille() + " " + type()+" ) : " + resultat.length() + resultat;
    }
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private static class TypeNotFoundException extends Exception {

        public TypeNotFoundException(int type) {
        }
    }
    public final int BOOLEAN = 1;
    public final int INTEGER = 2;
    public final int SHORT = 3;
    public final int LONG = 4;
    public final int FLOAT = 5;
    public final int DOUBLE = 10;
    public final int CHARACTER = 11;
    public final int STRING = 12;
    protected Object valeur = null;
    
    public int getCode()
    {
        if(valeur==null)
            return 0;
        if(valeur instanceof Boolean)
            return BOOLEAN;
        if(valeur instanceof Integer)
            return INTEGER;
        if(valeur instanceof Short)
            return SHORT;
        if(valeur instanceof Long)
            return LONG;
        if(valeur instanceof Float)
            return FLOAT;
        if(valeur instanceof Double)
            return DOUBLE;
        if(valeur instanceof Character)
            return CHARACTER;
        if(valeur instanceof String)
            return STRING;
        return 0;
    }
    
    public String serialise(int type, Object value) throws TypeNotFoundException
    {
        String resultat = "";
        switch(type)
        {
            case BOOLEAN:
                resultat = (Boolean) value ? "true" : "false";
                break;
            case INTEGER:
                resultat = ((Integer) value).toString();
                break;
            case SHORT:
                resultat = ((Integer) value).toString();
                break;
            case LONG:
                resultat = ((Long) value).toString();
                break;
            case FLOAT:
                resultat = ((Float) value).toString();
                break;
            case DOUBLE:
                resultat = ((Double) value).toString();
                break;
            case CHARACTER:
                resultat = ((Character) value).toString();
                break;
            case STRING:
                resultat = ""+((String) value).length()+((String) value);
                break;
            default:
                throw new TypeNotFoundException(type);

        }
        return "atome ("+taille() + " " + type()+" ) : " + resultat.length() + resultat;
    }
}
