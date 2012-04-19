package be.ibiiztera.md.pmatrix.pushmatrix;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;

public class PObjet implements Representable{
	private String id;
	private ArrayList<Point3D> points;
	public PObjet()
	{
		points = new ArrayList<Point3D>();
	}
	public Point3D remove(int arg0) {
		return points.remove(arg0);
	}

	public ArrayList<Point3D> getPoints() {
		return points;
	}

	public void setPoints(ArrayList<Point3D> points) {
		this.points = points;
	}

	public boolean add(Point3D arg0) {
		return points.add(arg0);
	}

	public void add(int arg0, Point3D arg1) {
		points.add(arg0, arg1);
	}

	public Point3D get(int arg0) {
		return points.get(arg0);
	}

	public boolean isEmpty() {
		return points.isEmpty();
	}

	public Iterator<Point3D> iterator() {
		return points.iterator();
	}

	public boolean remove(Object arg0) {
		return points.remove(arg0);
	}

	public int size() {
		return points.size();
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

	
	public void add(Point3D point3d, Color c) {
	}
	public String toString()
	{
		String s = "tri \n(\n\n";
		Iterator<Point3D> tris =  iterator();
		while(tris.hasNext())
		{
			s+="\n"+tris.next().toString()+"\n";
		}
		return s +"\n)\n";
	}
	public Iterable<Point3D> iterable() {
		return points;
	}

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}