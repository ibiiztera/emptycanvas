package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

public class InterpreteIdentifier implements Interprete {
    private String répertoire;
    @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }

	private int pos;

	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		int i = pos;
		int start = pos;
		if (Character.isLetter(text.charAt(i))) {
			i++;
			while (i < text.length()
					&& Character.isLetterOrDigit(text.charAt(i)))
				i++;
			this.pos = i;
			return text.substring(start, this.pos);//new Identifier(text.substring(pos, i), null, Double.class);
		}
		return "";
	}

	@Override
	public int getPosition() {
		return pos;
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
