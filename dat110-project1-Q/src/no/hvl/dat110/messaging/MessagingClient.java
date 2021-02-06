package no.hvl.dat110.messaging;

import java.io.IOException;

import java.net.Socket;
import java.net.UnknownHostException;

/**
 * implementing the methods for the client-side of the messaging service and responsible 
 * for creating the underlying TCP socket on the client-side.
 */

public class MessagingClient {

	private String server;
	private int port;

	public MessagingClient(String server, int port) {
		this.server = server;
		this.port = port;
	}

	// connect to messaging server
	public Connection connect()  throws UnknownHostException, IOException { 

		Socket clientSocket;
		Connection connection = null;

		// start TODO
		// create TCP socket for client and connection
		// create connection object
		try {
			clientSocket = new Socket(server, port);
			connection = new Connection(clientSocket);
			
		} catch (IOException e) {
			System.out.println("TCP client: " + e.getMessage());
			e.printStackTrace();
		}
		// end TODO

		return connection;
	}
}
