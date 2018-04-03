package arith;

public class Mult extends Operand{

Mult(Expression left,Expression right)
{
		super(left,right);
}
public double calculate()
{
	return left.calculate()*right.calculate();
}

}