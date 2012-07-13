package be.ibiiztera.md.pmatrix.pushmatrix.scripts;


import java.util.ArrayList;

/***
   AUTHOR DAHMEN MANUEL
   DATE 18 AVRIL 2012
 */
public class Pile implements Interprete, Definition
{
    private ArrayList<Type> types;
    private ArrayList<Type> variables;
    private ArrayList<Operateur> operateurs;
    public class Type
    {
	public String nom;
	public Class type;
    }
    public class Operateur
    {
	public String id;
	public int nbrOperandes;
	public ArrayList<Integer> types;
    } 
    public class Variable
    {
	public Type type;
	public Object value;
    }
    public interface Definition
    {
	public void addTypes(ArrayList<Type> types);
	public void addVariable(ArrayList<Type> types);
	public void addOperateurs(ArrayList<Type> types);
    }

    public void loadOperateurs()
    {
	types = new ArrayList<Type>();
	Type t = new Type();
	t.nom = "Double";
	t.type = java.lang.Double;
	types.add(t);
	t = new Type();
	t.nom = variable;
	t.type = java.lang.Double;
    }
    public interface Loader
    {
	public void loadDefVarCst(Definition d);
    } 


    public void  addTypes(ArrayList<Type> types)
    {
	this.types.addAll(types);
    }
    public void addVariable(ArrayList<Variable> v)
    {
	this.variables.addAll(v);
    }
    public void addOperateurs(ArrayList<Operateur> o)
    {
	this.operateurs.addAll(o);
    }


    public void loadDefVarCst(Loader l)
    {
	l.load(this);
    }
    private int pos;
    
    public int getPosition()
    {
	return pos;
    }
    public Object interprete(String text, int pos)
	throws InterpreteException
    {
	loadOperateurs();
	InterpretesBase ib = new InterpretesBase();
	ArrayList<Integer> pattern = new ArrayList<Integer>();
	pattern.add(ib.BLANK);
	pattern.add(ib.LEFTPARENTHESIS);
	pattern.add(ib.BLANK);
        ib.compile(pattern);
	ib.read(text, pos);
	pos = this.pos;

	boolean cont = true;
	while(cont)
	    {
		try
		    {
			InterpretesBase ib = new InterpretesBase();
			ArrayList<Integer> pattern = new ArrayList<Integer>();
			pattern.add(ib.BLANK);
			pattern.add(ib.LEFTPARENTHESIS);
			pattern.add(ib.BLANK);
			ib.compile(pattern);
			ib.read(text, pos);
			pos = this.pos;
			cont = false;
		    }
		catch(InterpreteException ex)
		    {
			cont = true;
		    }
		
		Double d = interpreteDouble(text, pos);
		pos = this.pos;
	    }
    }
    
    public Double interpreteDouble(String text, int pos)
	throws InterpreteException
    {
	Double ret = 0;
	InterpretesBase ib = new InterpretesBase();
	ArrayList<Integer> pattern = new ArrayList<Integer>();
	pattern.add(ib.BLANK);
	pattern.add(ib.DECIMAL);
	pattern.add(ib.BLANK);
	ib.compile(pattern);
 	ret = (Double) ib.read(text, pos).get(1);
	this.pos = pos;
	return ret;
    }
    public String interpreteOperateur(String text, int pos)
	throws InterpreteException
    {
	String ret = "";
	InterpretesBase ib = new InterpretesBase();
	ArrayList<Integer> pattern = new ArrayList<Integer>();
	pattern.add(ib.BLANK);
	pattern.add(ib.STRING);
	pattern.add(ib.BLANK);
	ib.compile(pattern);
	ret = (String) ib.read(text, pos).get(1);
	this.pos = pos;
	return ret;
    }
    public String  interpreteIdentifier(String text, int pos)
	throws InterpreteException
    {
	String ret = "";
	InterpretesBase ib = new InterpretesBase();
	ArrayList<Integer> pattern = new ArrayList<Integer>();
	pattern.add(ib.BLANK);
	pattern.add(ib.STRING);
	pattern.add(ib.BLANK);
	ib.compile(pattern);
	ib.read(text, pos).get(1);
	this.pos = pos;
	return ret;
    }
}