package tuto7;

class MultiplyExpression extends NonTerminalExpression {
	  public int evaluate(Context c) {
	    return getLeftNode().evaluate(c) * getRightNode().evaluate(c);
	  }

	  public MultiplyExpression(Expression l, Expression r) {
	    super(l, r);
	  }

	}// MultiplyExpression


