package be.ibiiztera.md.pmatrix.pushmatrix;

public class ColorMatrix<E> implements Representable{
	private String id;
	private Point3D [] nodes;
	private My2DarrayColor colors;
	private E [] elements;
	private Point3D [] interpolate;
	private int dimx;
	private int dimy;
	public ColorMatrix(int dimx, int dimy) {
		super();
		this.dimx = dimx;
		this.dimy = dimy;
	}
	public Point3D[] getNodes() {
		return nodes;
	}
	public void setNodes(Point3D[] nodes) {
		this.nodes = nodes;
	}
	public My2DarrayColor getColors() {
		return colors;
	}
	public void setColors(My2DarrayColor colors) {
		this.colors = colors;
	}
	public E[] getElements() {
		return elements;
	}
	public void setElements(E[] elements) {
		this.elements = elements;
	}
	public Point3D[] getInterpolate() {
		return interpolate;
	}
	public void setInterpolate(Point3D[] interpolate) {
		this.interpolate = interpolate;
	}
	public int getDimx() {
		return dimx;
	}
	public void setDimx(int dimx) {
		this.dimx = dimx;
	}
	public int getDimy() {
		return dimy;
	}
	public void setDimy(int dimy) {
		this.dimy = dimy;
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

    public Representable place(MODObjet aThis) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
	
}
