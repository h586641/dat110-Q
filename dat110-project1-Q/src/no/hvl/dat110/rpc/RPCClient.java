package no.hvl.dat110.rpc;

import java.io.IOException;
import java.net.UnknownHostException;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.*;

/**
 * Class implementing the client-side of the RPC layer using the client-side of the underlying
 * messaging layer for communication.
 *
 */
public class RPCClient {

	private MessagingClient msgclient;
	private Connection connection;
	
	public RPCClient(String server, int port) {
	
		msgclient = new MessagingClient(server,port);
	}
	
	public void register(RPCStub remote) {
		remote.register(this);
	}
	
	public void connect() {
		
		// start TODO: connect using the underlying messaging layer connection
			try {
				connection = msgclient.connect();
			} catch (IOException ex) {
				System.out.println("TCP client: " + ex.getMessage());
				ex.printStackTrace();
			}
	    // end TODO
			
	}
	
	public void disconnect() {
		
		// start TODO: disconnect/close the underlying messaging connection
		connection.close();
		// end TODO
		
	}
	
	public byte[] call(byte[] rpcrequest) {
		
		byte[] rpcreply;
		
		/* start TODO: 
		Make a remote call on the RPC server by sending the RPC request message
		and receive an RPC reply message
		
		rpcrequest is the marshalled rpcrequest from the client-stub
		rpctreply is the rpcreply to be unmarshalled by the client-stub
		*/
		
		connection.send(new Message(rpcrequest));
		rpcreply = connection.receive().getData();
		// end TODO
		
		return rpcreply;
		
	}

}
