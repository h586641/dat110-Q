package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;


/**
 * Implement client-side stub of display
 */
public class Display extends RPCStub {

	private byte RPCID = 1;

	
	// See the RPCServerStopStub.javafor inspiration
	public void write(String message) {

		// start TODO
		// implement marshalling, call and unmarshalling for write RPC method

		byte[] rpcrequest = RPCUtils.marshallString(RPCID, message);
		
		byte[] rpcreply = rpcclient.call(rpcrequest);
		
		RPCUtils.unmarshallString(rpcreply);
		
		// end TODO
	}
}
