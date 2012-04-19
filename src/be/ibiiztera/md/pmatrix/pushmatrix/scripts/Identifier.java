package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

public class Identifier {
	private String name;
	private Object value;
	private Class<?> theClazz;
	public Identifier(String name, Object value, Class<?> theClazz) {
		super();
		this.name = name;
		this.value = value;
		this.theClazz = theClazz;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
	public Class<?> getTheClazz() {
		return theClazz;
	}
	public void setTheClazz(Class<?> theClazz) {
		this.theClazz = theClazz;
	}
	
}
