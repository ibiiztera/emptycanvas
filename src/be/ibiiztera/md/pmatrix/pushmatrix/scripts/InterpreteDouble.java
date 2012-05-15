package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

public class InterpreteDouble implements Interprete{
    private String répertoire;
    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }

	@Override
	public Object interprete(String text, int pos) {
		Double d = Double.parseDouble(text);
		return d!=null ? d : new Throwable("Erreur d'analyse de nombre");
	}

	@Override
	public int getPosition() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public InterpreteConstants constant() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConstant(InterpreteConstants c) {
		// TODO Auto-generated method stub
		
	}

}
