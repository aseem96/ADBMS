import java.io.DataInputStream;
import java.io.PrintStream;
import java.net.Socket;

public class ClientThread extends Thread {
	
	DataInputStream is = null;
	String line;
	String destClient = "";
	String name;
	PrintStream os = null;
	Socket clientSocket = null;
	String clientIdentity;
	Server server;
	
	public ClientThread(Server server,Socket clientSocket) {
		this.clientSocket = clientSocket;
		this.server = server;
	}
	
	@SuppressWarnings("deprecation")
	public void run() {
		try {
			is = new DataInputStream(clientSocket.getInputStream());
			os = new PrintStream(clientSocket.getOutputStream());
			os.println("ENTER NAME: ");
			name = is.readLine();
			clientIdentity = name;
			os.println("WELCOME "+name+" TO 2PC CLIENT APPLICATION...");
			os.println("VOTE REQUEST... ENTER A/B:");
			for(int i = 0;i < server.t.size();i++) {
				if(server.t.get(i) != this)
					server.t.get(i).os.println("A NEW CLIENT "+name+" ENTERED THE APPLICATION");
			}
			while(true) {
				line = is.readLine();
				if(line.equalsIgnoreCase("ABORT")) {
					System.out.println("FROM CLIENT "+clientIdentity+": ABORT");
					System.out.println("ABORTED...");
					for(int i = 0;i < server.t.size();i++) {
						server.t.get(i).os.println("GLOBAL_ABORT");
						server.t.get(i).os.close();
						server.t.get(i).is.close();
						System.exit(0);
					}
					break;
				}
				if(line.equalsIgnoreCase("COMMIT")) {
					System.out.println("FROM CLIENT "+clientIdentity+": COMMIT");
					if(server.t.contains(this)) {
						server.data.set(server.t.indexOf(this), "COMMIT");
						for(int j = 0;j < server.data.size();j++) {
							if(!server.data.get(j).equalsIgnoreCase("NOT_SENT")) {
								server.all_confirm = true;
								continue;
							}
							else {
								server.all_confirm = false;
								System.out.println("WAITING FOR OTHER CLIENTS...");
								break;
							}
						}
						if(server.all_confirm) {
							System.out.println("COMMITED...");
							for(int i = 0;i < server.t.size();i++) {
								server.t.get(i).os.println("GLOBAL_COMMIT");
								server.t.get(i).os.close();
								server.t.get(i).is.close();
								System.exit(0);
							}
							break;
						}
					}
				}
			}
			server.closed = true;
			clientSocket.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}
