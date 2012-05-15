package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

public class InterpreteString implements Interprete {
    private String répertoire;

        @Override
    public void setRépertoire(String r) {
        this.répertoire = r;
    }
private int pos;

	@Override
	public Object interprete(String text, int pos) throws InterpreteException {
		String res = "";
		if (Character.isLetter(text.charAt(pos))) {
			res += text.charAt(pos);
			pos++;
			while (pos < text.length() && Character.isLetterOrDigit(text.charAt(pos))) {
				res += text.charAt(pos);
				pos++;
			}
		}
		this.pos = pos;
		return res;
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
