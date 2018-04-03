package calculator;

import controller.*;
import model.*;
import view.CommandLine;
import view.JFrameView;

public class calculator {
  public static void main(String[] args)
  {
	  CalculatorModel m = new CalculatorModel();
	  JFrameView v1 = new JFrameView();
	  CommandLine v2 = new CommandLine();
	  Controller c = new Controller(m, v1, v2);
  }
}
