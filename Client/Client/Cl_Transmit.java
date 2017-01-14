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
	
	ArrayBlockingQueue<String> inboundQueue = new ArrayBlockingQueue<String>(20);
	ArrayBlockingQueue<String> outbound = new ArrayBlockingQueue<String>(20);
	
	Cl_Transmit(String transmitterName, String server, int portNumber)
	{
		this.transmitterName = transmitterName;
		this.portNumber = portNumber;
		this.server = server;
		System.out.println("Thread created: " + transmitterName);
	}
	
	public void run(){
		System.out.println("Transmitter running!");
		try{
		Socket sock = new Socket(server, portNumber);
		System.out.println(sock.toString());
		BufferedReader buffRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
		OutputStream out = sock.getOutputStream();
		OutputStreamWriter outW = new OutputStreamWriter(out);
		BufferedWriter outBW = new BufferedWriter(outW);
		while(sock.isConnected())
		{
			if (!outbound.isEmpty())
			{
				try {
					String a = outbound.take();
					outBW.write(a);
					System.out.println(a);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				outBW.flush();
			}
			if (buffRead.ready())
			{
				try {
					inboundQueue.put(buffRead.readLine());
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
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
			outbound.put(toQueue + "\r\n");
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
			if (!inboundQueue.isEmpty()){
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