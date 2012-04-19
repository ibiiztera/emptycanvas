package be.ibiiztera.md.pmatrix.pushmatrix;

/***

PACKAGE be.ibiiztera.md.pmatrix.pushmatrix
OWNER DAHMEN MANUEL
 */
import java.awt.Color;
import java.util.ArrayList;
public class LumiereScene
{
    public class Lumiere
    {
	private double ratio = 1.0;
	private Color baseCouleur = Color.WHITE;
	private Point3D vecteur = new Point3D(0,0,1);
	public void ratio(double r)
	{
	    ratio = r;
	}
	public double ratio()
	{
	    return ratio;
	}
	public void vecteur(Point3D v)
	{
	    vecteur=v;
	}
	public Point3D vecteur()
	{
	    return vecteur;
	}
	public void couleur(Color c)
	{
	    baseCouleur = c;
	}
	public Color couleur()
	{
	    return baseCouleur;
	}
	public double facteurAngulaire(double a)
	{
	    return Math.exp(-a*a);
	}
    }

    private ArrayList<Lumiere> lumieres = new ArrayList<Lumiere>();
    public Color calculer(Point3D n, Color co)
    {
	double ratio = 0.0;
	double a = 0.0;
	double [] c  = new double[] {co.getRed(), co.getGreen(), co.getBlue()};
	for(int i=0; i<lumieres.size(); i++)
	    ratio += lumieres.get(i).ratio();
	for(int i=0; i<lumieres.size(); i++)
	    {
		Lumiere l  = lumieres.get(i);
		a = Math.acos(l.vecteur().prodScalaire(n));
		for(int comp = 0; comp<3; comp++)
		    {
			double compVal = 0;
			switch(comp)
			    {
			    case 0:
				compVal = l.couleur().getRed() - c[comp];
				break;
			    case 1:
				compVal = l.couleur().getGreen() - c[comp];
				break;
			    case 2:
				compVal = l.couleur().getBlue() - c[comp];
				break;
			    }
			c[comp] += 
			    compVal
			    * l.ratio()/ratio
			    * l.facteurAngulaire(a);
		    }
	    }
	for(int comp = 0; comp<3;comp++)
	    {
		if(c[comp]>255)
		    c[comp] = 255;
		if(c[comp]<0)
		    c[comp] = 0;

	    }
	return new Color((int)c[0], (int)c[1], (int)c[2]);
    }
}