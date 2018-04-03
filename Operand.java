package arith;

public abstract class Operand implements Expression{
protected Expression left,right;
Operand(Expression left,Expression right)
{
	this .left=left;
	this.right=right;
}
	
}

