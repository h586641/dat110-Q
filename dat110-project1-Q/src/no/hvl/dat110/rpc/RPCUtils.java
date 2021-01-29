package no.hvl.dat110.rpc;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.Arrays;

import no.hvl.dat110.TODO;

public class RPCUtils {

	// Utility methods for marshalling and unmarshalling of parameters and return values
	// in RPC request and RPC responses
	// data bytearrays and return byte arrays is according to the 
	// RPC message syntax [rpcid,parameter/return value]
	
	public static byte[] marshallString(byte rpcid, String str) {

		byte[] encoded;

		// start TODO: marshall RPC identifier and string into byte array
		// konvertere str til byte
		byte[] strByte = str.getBytes();
		encoded = new byte[strByte.length + 1];
		
		encoded[0] = rpcid;
		for (int i = 1; i < encoded.length; i++) {
			encoded[i] = strByte[i-1];
		}
		// end TODO

		return encoded;
	}

	public static String unmarshallString(byte[] data) {

		String decoded;

		// start TODO: unmarshall String contained in data into decoded
		byte[] strByte = new byte[data.length - 1];
		for (int i = 0; i < strByte.length; i++) {
			strByte[i] = data[i+1];
		}
		decoded = new String(strByte);
		// end TODO
		
		return decoded;
	}

	public static byte[] marshallVoid(byte rpcid) {

		byte[] encoded;

		// start TODO: marshall RPC identifier in case of void type
		encoded = new byte[1];
		encoded[0] = rpcid;
		// end TODO

		return encoded;

	}

	public static void unmarshallVoid(byte[] data) {

		// TODO: unmarshall void type
		// gjøres ingenting
	}

	public static byte[] marshallBoolean(byte rpcid, boolean b) {

		byte[] encoded = new byte[2];

		encoded[0] = rpcid;

		if (b) {
			encoded[1] = 1;
		} else {
			encoded[1] = 0;
		}

		return encoded;
	}

	public static boolean unmarshallBoolean(byte[] data) {

		return (data[1] > 0);

	}

	public static byte[] marshallInteger(byte rpcid, int x) {

		byte[] encoded;

		// start TODO: marshall RPC identifier and string into byte array
		
		byte[] intByte = intToByteArray(x);
		encoded = new byte[intByte.length + 1];
		encoded[0] = rpcid;
		for (int i = 0; i < intByte.length; i++) {
			encoded[i+1] = intByte[i];
		}
		// end TODO

		return encoded;
	}

	public static int unmarshallInteger(byte[] data) {

		int decoded;

		// start TODO: unmarshall integer contained in data
		decoded = dataToInt(data);
		// end TODO
		
		return decoded;
	}
	
	/*
	 * Konverterer 32-bits heltall til en tabell av bytes
	 */
	public static byte[] intToByteArray(int heltall) {
		return ByteBuffer.allocate(4).putInt(heltall).array();

	}
	
	/**	
	 * Konverterer en tabell av bytes til 32-bits heltall
	 */
	public static int dataToInt(byte[] byteArray) {
		 
		return ByteBuffer.wrap(byteArray).getInt(1);

	}
}
