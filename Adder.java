package arith;

public class Adder extends Operand{

Adder(Expression left,Expression right)
{
		super(left,right);
}
public double calculate()
{
	return left.calculate()+right.calculate();
}
}
