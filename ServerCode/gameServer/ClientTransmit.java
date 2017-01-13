package gameServer;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class ClientTransmit extends Thread {
	//initiate variables
	int portNumber;
	Thread transmitter;
	Thread gameData;
	String transmitterName;
	String server;
	String inbound;
	//queues used for data
	ArrayBlockingQueue<String> inboundQueue = new ArrayBlockingQueue<String>(20);
	ArrayBlockingQueue<String> outbound = new ArrayBlockingQueue<String>(20);
	
	ClientTransmit(String transmitterName, String server, int portNumber)
	{
		transmitterName = this.transmitterName;
		portNumber = this.portNumber;
		server = this.server;
		System.out.println("Thread created: " + transmitterName);
	}
	
	
	public void start()
	{
		if (transmitter != null)
		{
			transmitter = new Thread(this, transmitterName);
			transmitter.start();
			
			gameData	= new Thread(this, "gameMainThread");
			
		}
	}
	
	public void run()
	{
		try{
		Socket sock = new Socket(server, portNumber);
		
		while(sock.isConnected())
		{
			BufferedReader buffRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			if (outbound.size() != 0)
			{
				OutputStream out = sock.getOutputStream();
				OutputStreamWriter outW = new OutputStreamWriter(out);
				BufferedWriter outBW = new BufferedWriter(outW);
				try {
					outBW.write(outbound.take());
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				outBW.flush();
			}
			if (true)
			{
				try {
					System.out.println("IT GETS TO HERE");
					inboundQueue.put(inbound);
					
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				inbound = null;
			}
		}
		sock.close();
		
		} catch(IOException e){
			System.out.println(e);
		}
	}
	
	public void sendACT(String toQueue)
	{
		String sendACT = ":ACT:" + toQueue;
		try {
			outbound.put(sendACT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendINV(String toQueue)
	{
		String sendINV = ":INV:" + toQueue;
		try {
			outbound.put(sendINV);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendLAB(String toQueue)
	{
		String sendLAB = ":LAB:" + toQueue;
		try {
			outbound.put(sendLAB);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendCHA(String toQueue)
	{
		String sendCHA = ":CHA:" + toQueue;
		try {
			outbound.put(sendCHA);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	//Take data from queue
	public String takeFromQueue()
	{
		String get = "";
		try {
			if (inboundQueue.size() != 0){
				get = inboundQueue.take();
			}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println(Thread.currentThread().getName() + "failed to get data from queue");
				get = "Failed to get data from queue.";
			}
		return get;
	}
	
}