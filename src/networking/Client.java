package networking;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import model.Problem;
import model.Solution;

public class Client {
	private String serverAddress;
	private int port;
	
	public Client() {
		this.serverAddress = "127.0.0.1";
		this.port = 8000;
	}
	/**
	 * initializes a connection with a server
	 * sends the server a problem that was defined by the user
	 * waits to receive a solution from the server
	 * @param problem
	 * @return return the solution that was sent by the server
	 */
	public Solution getSolution(Problem problem) {		
		Socket socket = null;
		ObjectInputStream in = null;
		ObjectOutputStream out = null;
		
		//System.out.println("Send new problem: " + problem.getDomainName());
		
		try {
			socket = new Socket(serverAddress, port);
			out = new ObjectOutputStream(socket.getOutputStream());
			in = new ObjectInputStream(socket.getInputStream());
						
			out.writeObject(problem);			
			
			Solution solution = (Solution)in.readObject();
			
			return solution;	
								
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} finally {
			try {
				out.close();
				in.close();
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}			
		}	
		return null;
	}
	
}
