/*

 Copyright (C) 2010-2012  DAHMEN, Manuel, Daniel

 This library is free software; you can redistribute it and/or
 modify it under the terms of the GNU Lesser General Public
 License as published by the Free Software Foundation; either
 version 2.1 of the License, or (at your option) any later version.

 This library is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 Lesser General Public License for more details.

 You should have received a copy of the GNU Lesser General Public
 License along with this library; if not, write to the Free Software
 Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA

 */
package be.ibiiztera.md.pmatrix.pushmatrix;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import javax.naming.OperationNotSupportedException;

@SuppressWarnings("serial")
public class Scene implements Representable, Serializable {

    public static final String VERSION = "1.2";
    private String id;
    private ArrayList<Representable> objets = new ArrayList<Representable>();
    private ArrayList<Animation> animations = new ArrayList<Animation>();
    private ArrayList<TColor> colors = new ArrayList<TColor>();
    private SceneCadre cadre = new SceneCadre();
    //private Camera camera;
    private Camera cameraActive;
    private ArrayList<Camera> cameras = new ArrayList<Camera>();
	
	private String DESCRIPTION;
    private ArrayList<Lumiere> lumieres = new ArrayList<Lumiere>();
    private Lumiere lumiereActive;
    private Representable dernierAjout;

    public Representable getDernierAjout() {
        return dernierAjout;
    }
	
    public Iterator<Representable> iterator() {
        return objets.iterator();
    }

    public boolean add(Representable add) {
        this.dernierAjout = add;
        return objets.add(add);
    }

    public boolean remove(Representable rem) {
        return objets.remove(rem);
    }

    public Representable get(int index) {
        return objets.get(index);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = ID.GEN(this);
    }

    @Override
    public String id() {
        return id;
    }

    public int size() {
        return objets.size();
    }

    public void dumpDATA() {
        this.setDESCRIPTION(toString());
    }

    @Override
    public String toString() {
        String str = "scene \n(\n\n";


        Iterator<Representable> it = iterator();
        while (it.hasNext()) {
            Representable r = it.next();
            if (r instanceof Point3D) {
                str += ((Point3D) r).toLongString();
            } else {
                str += r.toString();
            }
        }
	str += "cameras (\n\t";
        if(cameras.isEmpty())
        {
            str += "\n\t"+cameraActive().toString()+"\n";
        }
        Iterator<Camera> itC = cameras.iterator();
        while (itC.hasNext()) {
            str += "\n\t"+itC.next().toString()+"\n";
        }
	str += "\n)";
        
        str += "\n\n)\n";
        return str;
    }

    public String getDESCRIPTION() {
        return DESCRIPTION;
    }

    public void setDESCRIPTION(String dESCRIPTION) {
        DESCRIPTION = dESCRIPTION;
    }

    public Object[] liste() {
        Iterator<Representable> ir = iterator();

        Object[] liste = new Object[size()];
        int i = 0;
        while (ir.hasNext()) {
            liste[i] = ir.next().toString();
            liste[i] = ((String) liste[i]).length() >= 100 ? ((String) liste[i]).substring(0, 100) : liste[i];
            i++;
        }
        return liste;
    }

    /**
     * @return
     *
     */
    public boolean updateTime() {
        return false;
    }

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public Scene() {
    }

    public SceneCadre getCadre() {
        return cadre;
    }

    public void setCadre(SceneCadre cadre) {
        this.cadre = cadre;
    }

    public ArrayList<TColor> texture() {
        return colors;
    }

    public void texture(ArrayList<TColor> c) {
        colors.addAll(c);
    }

    public void texture(TColor c) {
        colors.add(c);
    }
	
        @Deprecated
	public Camera camera()
	{
		return cameraActive();
	}
        @Deprecated
	public void camera(Camera c)
	{
		cameraActive = c;
	}
    public ArrayList<Camera> cameras()
    {
        return cameras;
    }
    public Camera cameraActive()
    {
        if(cameraActive!=null)
            return cameraActive;
        else if(cameras.size()>0)
            return cameras.get(0);
        return Camera.PARDEFAULT;
    }
    public Lumiere lumiereActive(){
        if(lumiereActive!=null)
            return lumiereActive;
        else if(lumieres.size()>0)
            return lumieres.get(0);
        return LumierePointSimple.PARDEFAUT;
    }
    public void cameraActive(Camera c)
    {
        this.cameraActive = c;
    }

    public void updateFromText(Representable selectedComponent, String text) {
        //throw new UnsupportedOperationException("Not yet implemented");
    }

    public void clear() {
        objets.clear();
        texture().clear();
    }

    public void cameras(ArrayList<Camera> cs) {
        this.cameras = cs;
    }

    public void lumieres(ArrayList<Lumiere> lumieres) {
        this.lumieres = lumieres;
    }
    public ArrayList<Lumiere> lumieres(){
        return lumieres;
    }

    public void rotationPolygone(Representable da, Point3D axeBase, Point3D axeDirection, 
            int numRotations) {
        if(da instanceof Point3D)
        {
            for(int i = 0; i<numRotations; i++)
                ((Point3D)da).rotatePoint(new Axe(axeBase, axeDirection), 
                        2.0*Math.PI*i/numRotations);
        }
        else if(da instanceof Polygone)
        {
            for(int p= 0; p<((Polygone)da).getPoints().size(); p++)
            for(int i = 0; i<numRotations; i++)
                ((Polygone)da).getPoints().get(p).
                        rotatePoint(new Axe(axeBase, axeDirection), 
                        2.0*Math.PI*i/numRotations);
        }
        else if(da instanceof SegmentDroite)
        {
            Point3D p = ((SegmentDroite)da).getOrigine();
            for(int i = 0; i<numRotations; i++)
                p.
                        rotatePoint(new Axe(axeBase, axeDirection), 
                        2.0*Math.PI*i/numRotations);
            p = ((SegmentDroite)da).getExtremite();
            for(int i = 0; i<numRotations; i++)
                p.
                        rotatePoint(new Axe(axeBase, axeDirection), 
                        2.0*Math.PI*i/numRotations);
        }
        else
        {
            throw new UnsupportedOperationException
                    ("Non supporté: rotationPolygone" + 
                    da.getClass().getSimpleName());
        }
    }
	public void flushImports()
        {
            dernierAjout = null;
        }
}
