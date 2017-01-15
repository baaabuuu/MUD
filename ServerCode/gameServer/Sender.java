package gameServer;

/*
 * The constructor in this class creates a new thread which listens for new attempts to connect to the server.
 * Each thread constructed allows another client to simultaneously connect.
 */
//FORCE PUSH
import java.net.Socket;
import java.util.concurrent.ArrayBlockingQueue;
import java.io.*;

public class Sender extends Thread {
	
	private Thread connectionCheck;

	private String threadName;
	public String inbound;
	
	public ArrayBlockingQueue<String> inboundQueue = new ArrayBlockingQueue<String>(20);
	public ArrayBlockingQueue<String> outbound = new ArrayBlockingQueue<String>(20);
	//chat
	public ArrayBlockingQueue<String> inboundChat = new ArrayBlockingQueue<String>(20);
	
	Socket sAccept;
	
	
	
	Sender(String name, Socket _sAccept) {
		threadName = name;
		System.out.println("Creating thread: " + name);
		sAccept = _sAccept;
	}
	
	public void run() {
		String input;
		System.out.println("Thread " + Thread.currentThread().getName() + " created");	//Test if thread is starting at all.
		try {
			
			// New thread, job is to constantly check for new attempt to connect
			// to server.
			
			BufferedReader buffRead;
								
			
			while (sAccept.isConnected()) {	
				buffRead = new BufferedReader(new InputStreamReader(sAccept.getInputStream()));
				// code to send data to client if there is new data to be sent.
				if (buffRead.ready()) {
					try {
						input	=	buffRead.readLine();
						if(input.substring(0,5).equals(":CHA:"))
						{
							inboundChat.put(input);
						}
						else {
							inboundQueue.put(input);
						}
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				
				
				if (!outbound.isEmpty())
				{
					OutputStream out = sAccept.getOutputStream();
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
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				

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
	
	public boolean hasCHAT()
	{
		return (inboundChat.size()>0);
	}
	
	
	public String takeCHA()
	{
		try {
			return inboundChat.take();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "failed to fetch";
		}
	}
	
	public void sendCHA(String msg)
	{
		String sendCHA = ":CHA:" + msg + "\r\n";
		try {
			outbound.put(sendCHA);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendITE(String msg)
	{
		String sendITE = ":ITE:" + msg + "\r\n";
		try {
			outbound.put(sendITE);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendLAB(String toQueue)
	{
		String sendLAB = ":LAB:" + toQueue + "\r\n";
		try {
			outbound.put(sendLAB);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendINV(String toQueue)
	{
		String sendINV = ":INV:" + toQueue + "\r\n";
		try {
			outbound.put(sendINV);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendACT(String toQueue)
	{
		String sendACT = ":ACT:" + toQueue + "\r\n";
		try {
			outbound.put(sendACT);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sendDAT(String toQueue)
	{
		String sendLAB = ":DAT:" + toQueue + "\r\n";
		try {
			outbound.put(sendLAB);
			System.out.println(sendLAB);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		 
	}
	