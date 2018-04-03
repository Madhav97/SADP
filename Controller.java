package controller;
import java.util.Stack;
import java.awt.event.*;
import javax.swing.*;
import model.CalculatorModel;
import view.*;

public class Controller{
   private CalculatorModel calcModel;
   private JFrameView jFrameView;
   private CommandLine cmdView;
   //one more view...
   //set all views and user can enter expression in any one of them
   public Controller(CalculatorModel calcModel,JFrameView jFrameView,CommandLine cmdView) 
   {
	   this.jFrameView = jFrameView;
	   this.calcModel = calcModel;
	   this.cmdView = cmdView;
	   jFrameView.bresult.addActionListener(new ActionListener(){
		   public void actionPerformed(ActionEvent e){
			   calcModel.setExpr(jFrameView.getExpression());
			   calcModel.eval();
			   updateViews();
		   	}
	   });   
   }
   
   public void updateViews()
   {
	 String result = this.calcModel.getResult();
	 this.jFrameView.setView(result);
	 this.cmdView.setView(result);
   }

}
