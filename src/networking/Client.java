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
			System.out.println("Found solution: " + solution.getProblemDescription());
			
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
