import java.io.*;
import java.net.*;

public class Client implements Runnable {
	
	static Socket clientSocket = null;
	static PrintStream os = null;
	static DataInputStream is = null;
	static BufferedReader br = null;
	static boolean closed = false;
	
	public static void main(String args[]) {
		int PORT = 8989;
		String HOST = "localhost";
		String str = "";
		try {
			clientSocket = new Socket(HOST,PORT);
			br = new BufferedReader(new InputStreamReader(System.in));
			os = new PrintStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
			
			if (clientSocket != null && os != null && is != null) {
				try {
					new Thread(new Client()).start();
					while(!closed) {
						str = br.readLine();
						if(str.contains("/")) {
							if(str.contains("/0"))
								os.println("ABORT");
							else
								os.println("COMMIT");
						}
						else
							os.println(str);
					}
					os.close();
					is.close();
					clientSocket.close();
				}
				catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings("deprecation")
	@Override
	public void run() {
		// TODO Auto-generated method stub
		String response;
		try {
			while((response = is.readLine()) != null) {
				System.out.println("\n"+response);
				if(response.equalsIgnoreCase("GLOBAL_COMMIT") || response.equalsIgnoreCase("GLOBAL_ABORT"))
					break;
			}
			closed = true;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
}
