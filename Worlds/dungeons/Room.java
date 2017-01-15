package dungeons;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

import gameServer.Sender;
import items.Item;
import npc.EnemyGeneration;
import npc.Entity;
import playerPackage.Character;

public class Room 
{
	
	public int currEvent =	0;
	
	

	
	public boolean hasEntered = false;
	
	public enum Event {
		FIGHT,
		EXIT,
		SHOP,
		TEXT;
	}
	
	public Shop shop		=	new Shop();
		
	/**
	 * Contains a list of all events:
	 * <p>0 = first time entering a room.
	 * <p>1	= when entering the room afterwards.
	 * <p>Other IDs have different functions and can be refered to in different ways.
	 */	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventStack			=	new ArrayList<ArrayList>();
	
	public ArrayList<String>	exitOption			=	new ArrayList<String>();
	
	public ArrayList<Integer>	exitID				=	new ArrayList<Integer>();
	
	public ArrayList<Integer>	exitEvent			=	new ArrayList<Integer>();
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventExitStack		=	new ArrayList<ArrayList>();
		
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventExitID			=	new ArrayList<ArrayList>();
	
	/**
	 * Contains which events launches upon entering the room - 0 is default
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventExitEvent		=	new ArrayList<ArrayList>();
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventEnemies		=	new ArrayList<ArrayList>();
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventTextStack		=	new ArrayList<ArrayList>();
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventOptionStack	=	new ArrayList<ArrayList>();
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventOptionIDStack	=	new ArrayList<ArrayList>();
	
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventShops			=	new ArrayList<ArrayList>();
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventShopsText		=	new ArrayList<ArrayList>();	
	
	//requires an arraylist of shops
	//an arraylist of texts for that shop
	
	@SuppressWarnings("rawtypes")
	public void newShopEvent(ArrayList<Event> events,
							 ArrayList<ArrayList> shops,
							 ArrayList<ArrayList> texts,
						 	 ArrayList<Item> items,
						     ArrayList<String> text)
	{	
		shops.add(items);
		texts.add(text);
		events.add(Event.SHOP);

	}
	
	
	public void newTextEvent(String text, ArrayList<Event> events, ArrayList<String> textList)
	{		
		events.add(Event.TEXT);
		textList.add(text);
	}
	
	public void newFight(int[] enemyID, ArrayList<Event> events, ArrayList<Entity[]> eventEnemies)
	{		
		events.add(Event.FIGHT);
		Entity[]	enemies	=	new Entity[enemyID.length];
		for (int i = 0;i<enemyID.length;i++)
		{
			try {
				enemies[i]	=	EnemyGeneration.createCreep(enemyID[i]);
			} catch (IOException e) {
				System.out.println("Could not create enemy NPC");
			}
		}
		eventEnemies.add(enemies);

	}
	
	public void newOptionEvent(String text, int id, ArrayList<String> textList, ArrayList<Integer> id2)
	{
		textList.add(text);
		id2.add(id);
	}
	
	public void newExit(String text, int id, int newEventID, ArrayList<Event> events, ArrayList<String> textList, ArrayList<Integer> id2, ArrayList<Integer> newEvent)
	{
		events.add(Event.EXIT);
		textList.add(text);
		id2.add(id);
		newEvent.add(newEventID);
	}
	
	/**
	 * Adds a new event to our event stack.
	 * <p>When adding new event types remember to add them here as well
	 */
	@SuppressWarnings("rawtypes")
	public void newEvent()
	{
		eventStack.add(new ArrayList<Event>());
		eventTextStack.add(new ArrayList<String>());
		eventOptionStack.add(new ArrayList<String>());
		eventOptionIDStack.add(new ArrayList<Integer>());
		eventExitStack.add(new ArrayList<String>());
		eventExitID.add(new ArrayList<Integer>());
		eventExitEvent.add(new ArrayList<Integer>());
		eventEnemies.add(new ArrayList<Entity[]>());
		//might need more work here
		eventShops.add(new ArrayList<ArrayList>());
		eventShopsText.add(new ArrayList<ArrayList>());
	}

	
	String data;
	ArrayBlockingQueue<String>	inbound;
	ArrayBlockingQueue<String>	outbound;
	
	
	
	@SuppressWarnings({"unchecked","rawtypes" })
	public String runEvent(int id, 
						Character player,
						Sender transmitter) throws InterruptedException
	{
		//for data transfering
		String data;
		while(inbound == null)
		{
			System.out.println(transmitter.toString());
			try{
				inbound		=	transmitter.inboundQueue;
			}
			catch (NullPointerException e)
			{
			}
		
		}
		ArrayBlockingQueue<String>	inbound		=	transmitter.inboundQueue;
		
		//event room data
		ArrayList<Event>		events			=	eventStack.get(id);
		ArrayList<String>		text			=	eventTextStack.get(id);
		ArrayList<String>		options			= 	eventOptionStack.get(id);
		ArrayList<Integer>		nextEvent		=	eventOptionIDStack.get(id);
		ArrayList<String>		exits			=	eventExitStack.get(id);
		ArrayList<Integer>		exitIDs			=	eventExitID.get(id);
		ArrayList<Integer>		exitEvents		=	eventExitEvent.get(id);
		ArrayList<Entity[]>		enemies			=	eventEnemies.get(id);
		ArrayList<ArrayList>	shops			=	eventShops.get(id);
		ArrayList<ArrayList> 	shopText		=	eventShopsText.get(id);
		
		//ints for checking - as we neeed to know which have been running
		int 				textCounter		=	0;
		int 				exitCounter		=	0;
		int					battleCounter	=	0;
		int					result			=	0;
		int					shopCounter		=	0;
		if (!options.contains("Exit"))
		{
			options.add("Exit");
			nextEvent.add(-1);
		}
		
		for (Event event : events)
		{
			switch (event){
				case SHOP:
					Shop.newShop(shops.get(shopCounter),shopText.get(shopCounter), transmitter, player);
					shopCounter++;
					break;
				case EXIT: //adds a new exit event - note they are permanent for this room
					
					//duplicate detection!
					
					if (!exitOption.contains(exits.get(exitCounter)))
					{
						exitOption.add(exits.get(exitCounter));
						exitID.add(exitIDs.get(exitCounter));
						exitEvent.add(exitEvents.get(exitCounter));
						exitCounter++;
					}
					break;
				case FIGHT:
					//battlecode!
					result	= new Battle().fight(enemies.get(battleCounter),player,transmitter);
					//TODO handling for when a character dies?
					if (result == 0)
					{
						
					}
					//CONSIDER A NEW EVENT IN ROOM 0 DUNGEON 0
					//TODO
					battleCounter++;
					break;
				case TEXT: // prints the first line in eventDescription and then deletes it.
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(text.get(textCounter));
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					textCounter++;
					break;
				default://nothing happens kek
			}
		}
		//Things can occur here
		//run game logic
		String newInput;
		String answer;
		boolean correctValue;
		int		newEvent	=	-1;
		while(true)
		{
			transmitter.sendACT("Select which option you'd like to take:");	
			for (String option : options)
			{
				transmitter.sendACT(" --- <" + option +">" );
			}
			
			data		 =	inbound.take();
			newInput	 =	data.substring(5,data.length()).toLowerCase();
			
			if (newInput.startsWith("<"))
				newInput	=	newInput.substring(1, newInput.length());
			if (newInput.endsWith(">"))
				newInput	=	newInput.substring(0, newInput.length()-1);
			if (data.substring(0,5).equals(":ACT"));
			{
				correctValue	=	false;
				for (String option : options)
				{
					if (option.toLowerCase().equals(newInput.toLowerCase()))
					{	correctValue	=	true;
						break;
					}
				}
				
				for (int i = 0; i<exitOption.size();i++)
				{
					if (options.get(i).toLowerCase().equals(newInput))
					{
						correctValue	=	true;
						newEvent		=	nextEvent.get(i);
					}
				}
				
				
				if (correctValue)
				{
					if (newInput.equals("exit"))
					{
						transmitter.sendACT("Where would you like to exit through:");
						for (String option : exitOption)
						{
							transmitter.sendACT(" --- <" + option +">" );
						}
						while (true)
						{
							data		 =	inbound.take();
							answer		 =	data.substring(5,data.length()).toLowerCase();
							if (answer.startsWith("<"))
								answer	=	answer.substring(1, answer.length());
							if (newInput.endsWith(">"))
								answer	=	answer.substring(0, answer.length()-1);
							if (!answer.equals("none"))
							{
								for (int i = 0; i<exitOption.size();i++)
								{
									
									if (answer.equals(exitOption.get(i).toLowerCase()))
									{
										return "exit@" + exitID.get(i) + "@" + exitEvent.get(i);
									}
								}
							}
							else //if answer == "none" - Yes this is not valid java syntax
							{
								//in case answer is equal to false exit - tell the 
								//system to rerun select option screen
								correctValue	=	false;
								break;
							}	
						}	
					}
					if (correctValue)
						break;
					
				} else
				{
					System.out.println("WRONG INPUT, TRY AGAIN");
				}
			}
			//returns newEvent@ID where ID is the new event to run.
			
		}
		
		//in case of wrong msg sent from client - it breaks.
		return "newEvent@"+newEvent;
	}	
	public Room()
	{
		exitOption.add("None");
		exitID.add(-1);
		exitEvent.add(0);
	}
	
	
	
}