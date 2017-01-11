package Client;

import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;


public class Cl_Transmit extends Thread {
	String transmitterName;
	int portNumber;
	Thread transmitter;
	String server;
	String inbound;
	
	static ArrayBlockingQueue<String> inboundQueue = new ArrayBlockingQueue<String>(20);
	ArrayBlockingQueue<String> outbound = new ArrayBlockingQueue<String>(20);
	
	Cl_Transmit(String transmitterName, String server, int portNumber)
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
		}
	}
	
	public void run()
	{
		try{
		Socket sock = new Socket(server, portNumber);
		
		while(sock.isConnected())
		{
			BufferedReader buffRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			inbound = buffRead.readLine();
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
			if (inbound != null)
			{
				try {
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
	public void putToQueue(String toQueue)
	{
		try {
			outbound.put(toQueue);
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