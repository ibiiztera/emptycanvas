package be.ibiiztera.md.pmatrix.pushmatrix;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

import be.ibiiztera.md.pmatrix.pushmatrix.PolygonObjet;
import be.ibiiztera.md.pmatrix.pushmatrix.Representable;

public class PolygonObjet implements Representable {
	private String id;
	private ArrayList<Polygon3D> polygons;
	public PolygonObjet(ArrayList<Polygon3D> triangles) {
		polygons = triangles;
	}
	public Polygon3D remove(int arg0) {
		return polygons.remove(arg0);
	}

	public ArrayList<Polygon3D> getPoints() {
		return polygons;
	}

	public void setPoints(ArrayList<Polygon3D> polygons) {
		this.polygons = polygons;
	}

	public boolean add(Polygon3D arg0) {
		return polygons.add(arg0);
	}

	public void add(int arg0, Polygon3D arg1) {
		polygons.add(arg0, arg1);
	}

	public Polygon3D get(int arg0) {
		return polygons.get(arg0);
	}

	public boolean isEmpty() {
		return polygons.isEmpty();
	}

	public Iterator<Polygon3D> iterator() {
		return polygons.iterator();
	}

	public boolean remove(Object arg0) {
		return polygons.remove(arg0);
	}

	public int size() {
		return polygons.size();
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

	
	public void add(PolygonObjet point3d, Color c) {
	}
	public String toString()
	{
		String s = "tri \n(\n\n";
		Iterator<Polygon3D> tris =  iterator();
		while(tris.hasNext())
		{
			s+="\n"+tris.next().toString()+"\n";
		}
		return s +"\n)\n";
	}
	public ArrayList<Polygon3D> iterable() {
		return polygons;
	}

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
