package gameServer;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Listener extends Thread {
	private Thread listener;
	private String listenerName;
	private int port;
	int threadNumber = 0;
	ServerSocket servSock = null;
	String threadName = "Connection ";
	
	
	Listener(String _listenerName, int _port)
	{
		listenerName = _listenerName;
		port = _port;
		System.out.println("Created " + listenerName + "awaiting connections...");
	}
	
	public void run()
	{
		synchronized(this){
            this.listener = Thread.currentThread();
        }
		openServerSocket();
		try{
			//ServerSocket servSock = new ServerSocket(port);
			//Listens and accepts connections before handing over the connection to the Sender threads.
			while(true)
			{
				Socket sAccept = null;		//Allows a new connection to be established and handed off to a thread.
				sAccept = this.servSock.accept();
				
				Sender transmitter = new Sender(threadName + threadNumber, sAccept);
				threadNumber ++;
				System.out.println("A client has connected to the server. Creating thread" + threadName + threadNumber );
				transmitter.start();	//Allocates threads to clients.
			}
		} catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void start()
	{
		//There is no reason to have multiple listeners running, so a check is made.
		if(listener == null)
		{
			listener = new Thread(this, listenerName);
			listener.start();
		}
	}
	private void openServerSocket()
	{
		try {
            this.servSock = new ServerSocket(this.port);
            System.out.println("A serverSocket has been opened on port " + port + " succesfully! Now listening for clients.");
        } catch (IOException e) {
            System.out.println("Unable to open socket on port " + port + "\n" + e);
        }
	}
	
}