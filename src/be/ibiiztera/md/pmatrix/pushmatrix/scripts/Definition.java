
package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import java.util.ArrayList;

public interface Definition
    {
	public void addTypes(ArrayList<Pile.Type> types);
	public void addVariable(ArrayList<Pile.Variable> types);
	public void addOperateurs(ArrayList<Pile.Operateur> types);
    }
