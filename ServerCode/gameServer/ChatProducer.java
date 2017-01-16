package gameServer;

import java.util.ArrayList;

public class ChatProducer extends Thread {
	
	private Thread chat;
	private String chatName = "chatServer";

	/**
	 * Globally accessible chat thing, where every message is sent to every client.
	 */
	public ArrayList<String>	chatPending = new ArrayList<String>();
	public ArrayList<Sender>	transmitters	=	new ArrayList<Sender>();
	
	private void transmitToOthers(Sender transmitter1)
	{
		String msg	=	transmitter1.takeCHA();
		System.out.println(msg);
		if (msg.substring(0,5).equals(":CHA:"))
		{
			msg	=	msg.substring(5,msg.length());
			for(Sender transmitter : transmitters) //add every chat element
			{	
				if (!transmitter.equals(transmitter1))
					transmitter.sendCHA(msg);
			}
		}
	}
	
	
	public void run()
	{
		while(true)
		{
			for(Sender transmitter : transmitters) //add every chat element
			{
				if (transmitter.hasCHAT())
				{
					transmitToOthers(transmitter);
				}
			}
			//we sleep the thread shortly - just so it does not take tooo much processing power
			//now it updates every 0.1 second.
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void start()
	{
		//There is no reason to have multiple listeners running, so a check is made.
				if(chat == null)
				{
					chat = new Thread(this, chatName);
					chat.start();
				}
	}

}