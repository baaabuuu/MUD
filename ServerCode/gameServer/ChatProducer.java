package gameServer;

import java.util.concurrent.ArrayBlockingQueue;

public class ChatProducer extends Thread {
	
	private Thread chat;
	private String chatName;

	/**
	 * Globally accessible chat thing, where every message is sent to every client.
	 */
	ArrayBlockingQueue<String> chatPending;
	
	ChatProducer()
	{
		chatPending = new ArrayBlockingQueue<String>(100);
	}
	
	void run(Sender[] transmitters)
	{
		for(Sender transmitter : transmitters)
		{
			String[] elements = transmitter.takeCHA();
			for (String e : elements)
			{
				chatPending.add(e);
				transmitter.sendCHA(e);
			}
			
			
			try {
				transmitter.sendCHA(chatPending.take());
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
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