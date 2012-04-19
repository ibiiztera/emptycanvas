package be.ibiiztera.md.pmatrix.pushmatrix;

public abstract class ObjetPositionnable
{
	protected MODHomothetie h;
	protected MODRotation r;
	protected MODTranslation t;
	
	public void positon(MODHomothetie h, MODRotation r, MODTranslation t)
	{
		this.h = h;
		this.r = r;
		this.t = t;
	}
	public abstract void positionne();
}