package arith;


public class Execute{
	
public static void main(String args[])
{
 Expression e =
                new Div(
                       
                                new Div(new Constant(9.0), new Constant(6.0))
                        ,
                        new Adder(
                                new Constant(7.0),
                                new Sub(new Constant(2.0), new Constant(1.5))
                        )
                );
        System .out.println(e.calculate());

}	
	
}