
import java.util.Scanner;
import java.net.Socket;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.*;

public class c
{
    private Socket socket = null;
    private DataInputStream input = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;

    public c(String address, int port)
    {

        try
        {
            socket = new Socket(address, port);
            System.out.println("Connected");

            input  = new DataInputStream(System.in);

            out    = new DataOutputStream(socket.getOutputStream());

            in=new DataInputStream(
                new BufferedInputStream(socket.getInputStream()));
        }
        catch(UnknownHostException u)
        {
            System.out.println(u);
        }
        catch(IOException i)
        {
            System.out.println(i);
        }

        String line = "";
        Scanner sc=new Scanner(System.in);
	Stack<Double> ans  = new Stack<Double>();
	
            try
            {

                System.out.println("Enter an expression:");

                while(!line.equals("done"))
                {
                    line = sc.nextLine();
                    out.writeUTF(line);
		    double answer=0.0;
		    answer = Double.parseDouble(in.readUTF());
                    System.out.println("Answer is "+answer);
		    ans.push(answer);
                }

            }
            catch(IOException i)
            {
                System.out.println(i);
            }
	
 	
        try
        {
		System.out.println("Your all answers are");
		while (!ans.isEmpty()){
		System.out.println(ans.pop());
		}
            input.close();
            out.close();
            socket.close();
        }
        catch(IOException i)
        {
            System.out.println(i);
        }
    }

    public static void main(String args[])
    {
        c cli = new c("127.0.0.1", 5000);
    }
}
