package no.hvl.dat110.messaging;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import no.hvl.dat110.TODO;


/**
 * implementing the connection abstraction linking the connection to the underlying 
 * TCP socket and associated input and output data streams that is to be used for sending 
 * and receiving message.
 */
public class Connection {

	private DataOutputStream outStream; // for writing bytes to the underlying TCP connection
	private DataInputStream inStream; // for reading bytes from the underlying TCP connection
	private Socket socket; // socket for the underlying TCP connection

	public Connection(Socket socket) {

		try {

			this.socket = socket;

			outStream = new DataOutputStream(socket.getOutputStream());

			inStream = new DataInputStream(socket.getInputStream());

		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}

	public void send(Message message) {

		// start TODO
		// encapsulate the data contained in the message and write to the output stream
		// Hint: use the encapsulate method on the message
		
		try {
			byte[] encapsulatedMessage = message.encapsulate();
			outStream.write(encapsulatedMessage);
		} catch (IOException e) {
			System.out.println("TCPServer: " + e.getMessage());
			e.printStackTrace();
		}
		// end TODO


	}

	public Message receive() {

		Message message;
		byte[] recvbuf;

		// TODO start
		// read a segment (128 bytes) from the input stream and decapsulate into message
		// Hint: create a new Message object and use the decapsulate method
		message = new Message();
		
		try {
			recvbuf = inStream.readNBytes(128);
			message.decapsulate(recvbuf);
		} catch (IOException e) {
			System.out.println("TCPServer: " + e.getMessage());
			e.printStackTrace();
		}
		
		// end TODO


		return message;

	}

	// close the connection by closing streams and the underlying socket
	public void close() {

		try {

			outStream.close();
			inStream.close();

			socket.close();
		} catch (IOException ex) {

			System.out.println("Connection: " + ex.getMessage());
			ex.printStackTrace();
		}
	}
}