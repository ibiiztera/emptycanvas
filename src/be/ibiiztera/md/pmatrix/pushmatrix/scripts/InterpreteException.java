package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

@SuppressWarnings("serial")
public class InterpreteException extends Exception {

	public InterpreteException(String string) {
		super(string);
	}
	public InterpreteException(Exception ex) {
		super(ex);
	}

	/**
	 * @param string
	 * @param e
	 */
	public InterpreteException(String string, Exception e) {
		super(string, e);
	}
}
