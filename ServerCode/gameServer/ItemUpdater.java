package gameServer;

import playerPackage.Character;
import playerPackage.CharacterHandling;

public class ItemUpdater extends Thread {
	
	private Thread itemUpdater;
	private String threadName;
	
	public Character character;
	public Sender 	 transmitter;



	public ItemUpdater(String threadName) {
		this.threadName	=	threadName;
	}

	/**
	 * Globally accessible chat thing, where every message is sent to every client.
	 */
	
	public void run()
	{
		while(true)
		{
			if (transmitter.inboundItemData.size()>0)
			{
				try {
					String test = transmitter.inboundItemData.take();
					int s = Integer.parseInt(test.substring(5, test.length()));
					if (character.getInventory().size()-1 < s)
					{
						//item is in equipment?
						//NO DEQUIPPING
					}
					else
					{
						//item is in inventory?
						character.equipItem(character.getInventory().get(s));
						character.updateItems(transmitter);
						CharacterHandling.saveCharacters();
						transmitter.sendLAB(character.toDataStream());
					}					
				} catch (NumberFormatException | InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	public void start()
	{
		//There is no reason to have multiple listeners running, so a check is made.
		if(itemUpdater == null)
		{
			itemUpdater = new Thread(this, threadName);
			itemUpdater.start();
		}
	}

}