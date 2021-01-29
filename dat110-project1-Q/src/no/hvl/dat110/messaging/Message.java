package no.hvl.dat110.messaging;

import java.util.Arrays;

import no.hvl.dat110.TODO;

public class Message {
	
	private final int MAX_PAYLOAD_SIZE = 127;
	private final int MESSAGE_SIZE = 128;
	
	private byte[] payload;

	public Message(byte[] payload) {

		// start TODO: check for length within boundary
		if (payload.length > MAX_PAYLOAD_SIZE) {
			payload = resizePayload(payload);
			System.out.println("Payload er for stor. Kun de første 127 bytes er behandlet.");
		}
		// Annen mulighet for å behandle payload data.
		// throw new IllegalArgumentException("Payload kan ikke være større enn 127 bytes");
		// end TODO
		this.payload = payload;
	}

	public Message() {
		super();
	}

	public byte[] getData() {
		return this.payload; 
	}

	public byte[] encapsulate() {
		
		byte[] encoded = null;
		
		// start TODO
		// encapulate/encode the payload of this message in the
		// encoded byte array according to message format
		
		int payloadDataLength = 0;

		encoded = new byte[MESSAGE_SIZE];
		encoded[0] = (byte)payload.length;
		System.out.println(encoded[0]);
		for (int i = 0; i < payload.length; i++) {
			encoded[i+1] = payload[i];
		}
		// end TODO
		
		return encoded;
		
	}

	public void decapsulate(byte[] received) {

		// start TODO
		// decapsulate the data contained in the received byte array and store it 
		// in the payload of this message
		byte[] payload = new byte[received[0]];
		
		for (int i = 0; i < payload.length; i++) {
			payload[i] = received[i+1];
		}	
		this.payload = payload;
		// end TODO
	}
	

	/**
	 * Hjelpemetode som returnere en payload som har riktig størrelse.
	 * Dersom den opprinnelige payload har flere bytes, blir den forkortet og de øvrige byte blir kastet.
	 * Dersom den opprinnelige payload har færre bytes, blir den fylt med ekstra padding (null byte); 
	 */
	private byte[] resizePayload(byte[] payload) {
		byte[] kopi = new byte[MAX_PAYLOAD_SIZE];
		for (int i = 0; i < MAX_PAYLOAD_SIZE; i++) {
			kopi[i] = payload[i];
		}
		return kopi;
	}
}
