package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

import be.ibiiztera.md.pmatrix.pushmatrix.Representable;

public interface IInterprete {
	public IInterprete interprete(int TYPE);
	public IInterprete liste(int TYPE);
	public Representable resultat();
	public Class<Representable> typeResultat(int TYPE);
}
