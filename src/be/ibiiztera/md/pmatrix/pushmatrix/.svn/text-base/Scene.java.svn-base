package be.ibiiztera.md.pmatrix.pushmatrix;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

@SuppressWarnings("serial")
public class Scene implements Representable, Serializable{
	private String id;
	private ArrayList<Representable> objets = new ArrayList<Representable>();
	private ArrayList<Animation> animations = new ArrayList<Animation>();
	private SceneCadre cadre = new SceneCadre();
	private String DESCRIPTION;

	public Iterator<Representable> iterator() {
		return objets.iterator();
	}

	public boolean add(Representable arg0) {
		return objets.add(arg0);
	}

	public boolean remove(Representable arg0) {
		return objets.remove(arg0);
	}

	public Representable get(int arg0) {
		return objets.get(arg0);
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
	public void dumpDATA()
	{
		this.setDESCRIPTION(toString());
	}
	
    @Override
	public String toString()
	{
		String str= "scene \n(\n\n";
		
		
		Iterator<Representable> it = iterator();
		while(it.hasNext())
		{
			Representable r = it.next();
			if(r instanceof Point3D)
			{
				str+=((Point3D)r).toLongString();
			}
			else
			{
				str+=r.toString();
			}
		}
		str +="\n\n)\n";
		return str;
	}

	

	public String getDESCRIPTION() {
		return DESCRIPTION;
	}

	public void setDESCRIPTION(String dESCRIPTION) {
		DESCRIPTION = dESCRIPTION;
	}

        
        public Object [] liste()
        {
            Iterator<Representable> ir = iterator();
            
            Object [] liste  = new Object[size()];
            int i = 0;
            while(ir.hasNext())
            {
                liste[i] = ir.next().toString();
                liste[i] = ((String)liste[i]).length()>=100 ? ((String)liste[i]).substring(0,100):liste[i];
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

    
}
