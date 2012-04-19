package be.ibiiztera.md.pmatrix.pushmatrix.scripts;

public interface Run {
	public void runCode(Code code);
	public void executeInstruction(Instruction instruction);
	public void evaluateExpression(Expression expression);
	public void setVariableValue(Variable variable, Value value);
	public void getVariableValue(Variable variable, Value value);
	public void loopSubCode(SubCode subcode);
	
}
