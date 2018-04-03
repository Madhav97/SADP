package arith;

public class Sub extends Operand{

Sub(Expression left,Expression right)
{
		super(left,right);
}
public double calculate()
{
	return left.calculate()-right.calculate();
}
}
