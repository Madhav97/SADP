import java.net.*;
import java.io.*;
import java.text.*;
import java.util.*;
 
class calc 
{
    Double calculate(String exp){

        Stack<Integer> optmp  = new Stack<Integer>();
        Stack<Double> valtmp = new Stack<Double>();
		Stack<Integer> op  = new Stack<Integer>();
        Stack<Double> val = new Stack<Double>();

		String input = exp;
        
        input = "0" + input;
        input = input.replaceAll("-","+-");

        String num = "";

        for (int i = 0;i < input.length();i++)
        {
            char ch = input.charAt(i);
            if (ch == '-')
                num = "-" + num;
            else if (ch != '+' &&  ch != '*' && ch != '/')
               num = num + ch;
            else
            {
                val.push(Double.parseDouble(num));
                op.push((int)ch);
                num = "";
            }
        }

        val.push(Double.parseDouble(num));

        char operators[] = {'/','*','+'};

        for (int i = 0; i < 3; i++)
        {
            boolean it = false;
            while (!op.isEmpty())
            {
                int optr = op.pop();
                double v1 = val.pop();
                double v2 = val.pop();
                if (optr == operators[i])
                {
     
                    if (i == 0)
                    {
                        valtmp.push(v2 / v1);
                        it = true;
                        break;
                    }
                    else if (i == 1)
                    {
                        valtmp.push(v2 * v1);
                        it = true;
                        break;
                    }
                    else if (i == 2)
                    {
                        valtmp.push(v2 + v1);
                        it = true;
                        break;
                    }                                        
                }
                else
                {
                    valtmp.push(v1);
                    val.push(v2);
                    optmp.push(optr);
                }                
            }    
                       
            while (!valtmp.isEmpty())
                val.push(valtmp.pop());
            while (!optmp.isEmpty())
                op.push(optmp.pop());

            if (it)
                i--;                            
        }; 
	return val.pop();        
    } 
    
}

public class sa
{
    
    private Socket socket = null;
    private ServerSocket server = null;
    private DataInputStream in = null;
    private DataOutputStream output = null;
    
    public sa(int port)
    {
        try
        {
			server = new ServerSocket(port);
			while(true){
		
				socket=null;
				System.out.println("Server started");
				System.out.println("Waiting for a client ...");
 
				socket = server.accept();
				System.out.println("Client accepted");
 
				// takes input from the client socket
				in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
				output= new DataOutputStream(socket.getOutputStream());
				System.out.println("Assigning new thread for this client");
 
                // create a new thread object
                Thread t = new ClientHandler(socket, in, output);
 
                // Invoking the start() method
                t.start();
			}
		}catch (Exception e){
			e.printStackTrace();
        } 
    }
 
    public static void main(String args[])
    {
        sa server = new sa(5000);
    }
}


class ClientHandler extends Thread 
{
    
    final DataInputStream dis;
    final DataOutputStream dos;
    final Socket s;
     
 
    // Constructor
    public ClientHandler(Socket s, DataInputStream dis, DataOutputStream dos) 
    {
        this.s = s;
        this.dis = dis;
        this.dos = dos;
		
    }
 
    @Override
    public void run() 
    {
       
		String line = "";
       		 calc cal = new calc();   
            // reads message from client until "Over" is sent
           
                try
                {
                    line = dis.readUTF();
			double answer=0.0;
                   while(!line.equals("done")){
			answer= cal.calculate(line);
                        dos.writeUTF(""+answer);
 			line = dis.readUTF();	

			}
                }
                catch(IOException i)
                {
                    System.out.println(i);
                }


        	try
        {
		

            System.out.println("Closing connection");
 
            // close connection
            s.close();
            dis.close();
			// closing resources
            this.dis.close();
            this.dos.close();
             
        }catch(IOException e){
            e.printStackTrace();
        }
  
         
        
    }
	
}