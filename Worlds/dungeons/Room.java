package dungeons;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Scanner;

import dungeons.Room2.Event;
import npc.Entity;

public class Room 
{
	
	public int currEvent =	0;
	
	public boolean hasEntered = false;
	
	public enum Event {
		FIGHT,
		TEXT;
		
	}
		
	/**
	 * Contains a list of all events:
	 * <p>0 = first time entering a room.
	 * <p>1	= when entering the room afterwards.
	 * <p>Other IDs have different functions and can be refered to in different ways.
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventStack			=	new ArrayList<ArrayList>();
	
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventTextStack		=	new ArrayList<ArrayList>();
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventOptionStack	=	new ArrayList<ArrayList>();
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventOptionIDStack	=	new ArrayList<ArrayList>();
	
	public void newTextEvent(String text, ArrayList<Event> events, ArrayList<String> textList)
	{		
		events.add(Event.TEXT);
		textList.add(text);
	}
	
	public void newOptionEvent(String text, int id, ArrayList<String> textList, ArrayList<Integer> id2)
	{
		textList.add(text);
		id2.add(id);
		
	}
	
	/**
	 * Adds a new event to our event stack.
	 */
	public void newEvent()
	{
		eventStack.add(new ArrayList<Event>());
		eventTextStack.add(new ArrayList<String>());
		eventOptionStack.add(new ArrayList<String>());
		eventOptionIDStack.add(new ArrayList<Integer>());
		
	}

	public void runEvent(int id)
	{
		ArrayList<Event>	events		=	eventStack.get(id);
		ArrayList<String>	text		=	eventTextStack.get(id);
		ArrayList<String>	options		= 	eventOptionStack.get(id);
		ArrayList<Integer>	nextEvent	=	eventOptionIDStack.get(id);
		options.add("Exit");
		nextEvent.add(-1);
		
		int 				textCounter	=	0;
		
		for (Event event : events)
		{
			switch (event){					
				case FIGHT:
					break;
				case TEXT: // prints the first line in eventDescription and then deletes it.
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					System.out.println(text.get(textCounter));
					try {
						Thread.sleep(750);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					textCounter++;
					break;
				default://nothing happens kek
			}
		}
		//Things can occur here
		//run game logic
		Scanner input = new Scanner(System.in);
		System.out.println("Select which option you'd like to take:");	
		for (String option : options)
		{
			System.out.println(" --- <" + option +">" );
		}
		String newInput;
		boolean correctValue;
		while(true)
		{
			
			newInput	=	input.nextLine().toLowerCase();
			if (newInput.startsWith("<"))
				newInput	=	newInput.substring(1, newInput.length());
			if (newInput.endsWith(">"))
				newInput	=	newInput.substring(0, newInput.length()-1);
				
			correctValue	=	true;
			for (String option : options)
			{
				System.out.println(option.toLowerCase() + " = " + newInput.toLowerCase());
				if (option.toLowerCase().equals(newInput.toLowerCase()))
				{	correctValue	=	true;
					break;
				}
			}
			if (correctValue)
			{
				break;
			} else
			{
				System.out.println("WRONG INPUT, TRY AGAIN");
			}
			
		}
	}	
	
	
	
	
}