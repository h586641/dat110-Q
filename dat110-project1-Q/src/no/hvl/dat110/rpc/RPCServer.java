package no.hvl.dat110.rpc;

import java.nio.ByteBuffer;
import java.util.HashMap;

import no.hvl.dat110.TODO;
import no.hvl.dat110.messaging.Connection;
import no.hvl.dat110.messaging.Message;
import no.hvl.dat110.messaging.MessagingServer;

/**
 * Class implementing the client-side of the RPC layer using the client-side of the 
 * underlying messaging layer for communication.
 * 
 */
public class RPCServer {

	private MessagingServer msgserver;
	private Connection connection;

	// hashmap to register RPC methods which are required to implement RPCImpl

	private HashMap<Integer,RPCImpl> services;

	public RPCServer(int port) {

		this.msgserver = new MessagingServer(port);
		this.services = new HashMap<Integer,RPCImpl>();

		// the stop RPC methods is built into the server
		services.put((int)RPCCommon.RPIDSTOP,new RPCServerStopImpl());
	}

	public void run() {

		System.out.println("RPC SERVER RUN - Services: " + services.size());

		connection = msgserver.accept(); 

		System.out.println("RPC SERVER ACCEPTED");

		boolean stop = false;

		while (!stop) {

			int rpcid;

			// start TODO
			// TODO - receive message containing RPC request
			byte[] rpcrequest = connection.receive().getData();

			// TODO - find the identifier for the RPC methods to invoke
			rpcid = (int)rpcrequest[0];

			// TODO - lookup the method to be invoked
			RPCImpl rpcimpl = services.get(rpcid);

			// TODO - invoke the method
			byte[] rpcreply = rpcimpl.invoke(rpcrequest);
			
			// TODO - send back message containing RPC reply
			connection.send(new Message(rpcreply));			
			// end TODO

			if (rpcid == RPCCommon.RPIDSTOP) {
				stop = true;
			}
		}

	}

	public void register(int rpcid, RPCImpl impl) {
		services.put(rpcid, impl);
	}

	public void stop() {
		connection.close();
		msgserver.stop();

	}
}
