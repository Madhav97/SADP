package view;

import java.util.Scanner;


public class CommandLine {
	 public String expression;
	 public boolean isExpressionReady=false;
     
     public void setView(String result) {
    	 if(result.equals(""));
    	 else
    	 System.out.println(result);
     }
}
