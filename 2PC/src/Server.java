import java.io.*;
import java.net.*;
import java.util.*;

public class Server {
	
	boolean closed = false, all_confirm = false;
	List<ClientThread> t;
	List<String> data;
	
	Server() {
		t = new ArrayList<ClientThread>();
		data = new ArrayList<String>();
	}
	
	public static void main(String args[]) throws IOException {
		Socket clientSocket = null;
		ServerSocket serverSocket =  null;
		int PORT = 8989;
		Server server = new Server();
		try {
			serverSocket = new ServerSocket(PORT);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		while(!server.closed) {
			try {
				clientSocket = serverSocket.accept();
				ClientThread th =  new ClientThread(server,clientSocket);
				server.t.add(th);
				System.out.println("TOTAL CLIENTS: "+server.t.size());
				server.data.add("NOT_SENT");
				th.start();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}
		serverSocket.close();
	}
}
