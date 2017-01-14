package gameServer;

/*
 * The constructor in this class creates a new thread which listens for new attempts to connect to the server.
 * Each thread constructed allows another client to simultaneously connect.
 */
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.*;

public class Sender extends Thread {
	
	private Thread connectionCheck;

	private String threadName;
	
	public String inbound;
	
	ArrayBlockingQueue<String> inboundQueue = new ArrayBlockingQueue<String>(20);
	ArrayBlockingQueue<String> outbound = new ArrayBlockingQueue<String>(20);
	ArrayBlockingQueue<String> outboundChat = new ArrayBlockingQueue<String>(20);
	
	private int timeToDie; // Decrements each loop where the server has no data
							// to send. Requires periodic messages sent to keep
							// socket alive.
	Socket sAccept;
	
	
	
	Sender(String name, Socket _sAccept) {
		threadName = name;
		System.out.println("Creating thread: " + name);
		sAccept = _sAccept;
	}
	
	public void run() {
		System.out.println("Thread " + Thread.currentThread().getName() + " created");	//Test if thread is starting at all.
		timeToDie = 300;		//Amount of times the thread will run through the true loop before closing socket.
		try {
			
			// New thread, job is to constantly check for new attempt to connect
			// to server.
			
			BufferedReader buffRead;
			
			while (sAccept.isConnected()) {

				
				buffRead = new BufferedReader(new InputStreamReader(sAccept.getInputStream()));
				

				inbound = buffRead.readLine(); // Save data received to public
												// variable to be processed
												// elsewhere.
				
				System.out.println(inbound);
				// code to send data to client if there is new data to be sent.
				if (inbound != null) {
					timeToDie = 300;
					try {
						inboundQueue.put(inbound);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if (outbound.size() != 0) {
					OutputStream out = sAccept.getOutputStream();
					OutputStreamWriter outW = new OutputStreamWriter(out);
					BufferedWriter outBW = new BufferedWriter(outW);
					try {
					if(outboundChat.size() != 0)
					{
						outBW.write(outboundChat.take());
					}
					
						outBW.write(outbound.take());
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					outBW.flush(); // Not much sense in streaming the data sent
									// considering only a string is sent. If
									// these strings somehow end up exceeding a
									// few Kb, needs rewriting.
					outbound = null;
				}

				
				if(timeToDie <= 0){
					//This is where player data should be saved, so nothing is lost if a player disconnects.
					sAccept.close();	//Cleanup. Just in case sockets do not close if the thread they are defined in dies.
				}
				
				timeToDie--;
			}
			
		} catch (IOException e) {
			System.out.println("A client has failed to connect. Error dump:\n" + e);
		}
	}

	public void start() {
		connectionCheck = new Thread(this, threadName);
		connectionCheck.start();
		}
	
	
	//Adds data to queue without adding tags. Should not be used, but is here just cause.
	void putToQueue(String toQueue)
	{
		try {
			outbound.put(toQueue);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Take data from queue
	String takeFromQueue()
	{
		String get = "";
		try {
			get = inboundQueue.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println(Thread.currentThread().getName() + "failed to get data from queue :(");
			get = "Failed to get data from queue.";
		}
		return get;
	}
	
	public String[] takeCHA()
	{
		ArrayList<String> ind = new ArrayList<String>();
		for (String string: inboundQueue)
		{
			if (string.substring(0,5).equals(":CHA:"))
			{
				ind.add(string.substring(5,string.length()));
				
				inboundQueue.remove(string);
			}
		}
		return ind.toArray(new String[ind.size()]);
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
	
	public void sendACT(String toQueue)
	{
		String sendACT = ":ACT:" + toQueue;
		try {
			outboundChat.put(sendACT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		 
	}
	
