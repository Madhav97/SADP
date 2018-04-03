package arith;
public class Div extends Operand{

Div(Expression left,Expression right)
{
		super(left,right);
}
public double calculate()
{
	return left.calculate()/right.calculate();
}	
}
