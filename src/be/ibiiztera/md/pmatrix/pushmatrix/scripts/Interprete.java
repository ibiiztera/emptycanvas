package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

public interface Interprete {

    public void setRépertoire(String r);

    public Object interprete(String text, int pos) throws InterpreteException;

    public int getPosition();

    public InterpreteConstants constant();

    public void setConstant(InterpreteConstants c);
}
