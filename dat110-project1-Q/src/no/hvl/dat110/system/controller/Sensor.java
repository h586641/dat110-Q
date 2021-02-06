package no.hvl.dat110.system.controller;

import no.hvl.dat110.rpc.*;

public class Sensor extends RPCStub {

	private byte RPCID = 1;
	
	public int read() {
		
		int temp;
		
		// start TODO
		// implement marshalling, call and unmarshalling for read RPC method
		byte[] rpcrequest = RPCUtils.marshallVoid(RPCID);
		
		byte[] rpcreply = rpcclient.call(rpcrequest);
		
		temp = RPCUtils.unmarshallInteger(rpcreply);
		// end TODO
		
		return temp;
	}
	
}
