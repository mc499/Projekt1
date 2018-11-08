package MeinServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MeinServer {
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MeinServer server = new MeinServer(8000);
		server.startListening();
	}
	
	private int port;
	
	public MeinServer(int port) {
		//Hier startet man den Server
		this.port = port;
	}
	
	
	public void startListening() {
		
		new Thread(new Runnable() {
		
			public void run() {
			
				
				while (true) {
					try {
						//Debugg Ausgabe
					System.out.println("(Server) Server starten...");
					ServerSocket serverSocket = new ServerSocket(port);
					System.out.println("(Server) Warten auf Verbindung...");
					Socket remoteClientSocket = serverSocket.accept();
					System.out.println("(Server) Client verbunden: "+remoteClientSocket.getRemoteSocketAddress());
					
					Scanner s = new Scanner(new BufferedReader(new InputStreamReader(remoteClientSocket.getInputStream())));
					if (s.hasNextLine()) {
						System.out.println("(Server) Message from Client: "+ s.nextLine());
					}
					
					// Verbindung schliessen
					s.close();
					remoteClientSocket.close();
					serverSocket.close();
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
					
			}
		}).start();
	}
	
	


}
