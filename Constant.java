package arith;

public class Constant implements Expression{
double value;
Constant(double value)
{
	this .value=value;
}
public double calculate()
{
	return value;
}	
	
}

