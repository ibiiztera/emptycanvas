package be.ibiiztera.md.pmatrix.pushmatrix;
import java.awt.Color;
/**
 *
 * @author Atelier
 */
public class LumierePointSimple implements LumierePoint
{
    public static final Lumiere PARDEFAUT = new LumierePointSimple(Color.WHITE, Point3D.O0, 2.0);
    
    private Color couleur;
    private Point3D point;
    private double intensite;
    private float [] comp =new float[3];
    
    public LumierePointSimple(Color c, Point3D pl, double intensite){
        this.couleur = c;
        this.point = pl;
        this.intensite = intensite;
        couleur.getColorComponents(comp);
    }
    @Override
     public Color getCouleur(TColor base, Point3D p, Point3D n)
     {
         return mult((float)(Math.abs(n.norme1().prodScalaire(p.moins(point).norme1()))
                 *intensite), base.getCouleur());
     }
    float [] f = new float[3];
    private Color mult(float d, Color couleur) {
        couleur.getColorComponents(f);
        for(int i=0; i<3; i++)
        {
            f[i] = (float)( f[i] * comp[i] * intensite);
            if(f[i]>1f)
                f[i]=1f;
            if(f[i]<0f)
                f[i]=0f;
        }
        return new Color(f[0], f[1], f[2]);
    }
}
