package no.hvl.dat110.system.display;

import no.hvl.dat110.rpc.RPCImpl;
import no.hvl.dat110.rpc.RPCUtils;

public class DisplayImpl implements RPCImpl {

	public void write(String message) {
		System.out.println("DISPLAY:" + message);
	}
	
	public byte[] invoke(byte[] request) {
		byte[] reply;
		byte rpcid;
		
		// start TODO: 
		// implement unmarshalling, call, and marshall for write RPC method
		// look at how this is done int he SensorImpl for the read method
		String tempStr = RPCUtils.unmarshallString(request);
		write(tempStr);
		
		rpcid = request[0];
		reply = RPCUtils.marshallString(rpcid,tempStr);
		// end TODO
		
		return reply;
	}
}
