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
		EXIT,
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
	
	public ArrayList<String>	exitOption			=	new ArrayList<String>();
	
	public ArrayList<Integer>	exitID				=	new ArrayList<Integer>();
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventTextStack		=	new ArrayList<ArrayList>();
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventOptionStack	=	new ArrayList<ArrayList>();
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventOptionIDStack	=	new ArrayList<ArrayList>();
	
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventExitStack	=	new ArrayList<ArrayList>();
	
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList> eventExitID		=	new ArrayList<ArrayList>();
	
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
	
	public void newExit(String text, int id, ArrayList<Event> events, ArrayList<String> textList, ArrayList<Integer> id2)
	{
		events.add(Event.EXIT);
		textList.add(text);
		id2.add(id);
	}
	
	/**
	 * Adds a new event to our event stack.
	 * <p>When adding new event types remember to add them here as well
	 */
	public void newEvent()
	{
		eventStack.add(new ArrayList<Event>());
		eventTextStack.add(new ArrayList<String>());
		eventOptionStack.add(new ArrayList<String>());
		eventOptionIDStack.add(new ArrayList<Integer>());
		eventExitStack.add(new ArrayList<String>());
		eventExitID.add(new ArrayList<Integer>());
		
	}

	@SuppressWarnings({"unchecked" })
	public String runEvent(int id)
	{
		ArrayList<Event>	events		=	eventStack.get(id);
		ArrayList<String>	text		=	eventTextStack.get(id);
		ArrayList<String>	options		= 	eventOptionStack.get(id);
		ArrayList<Integer>	nextEvent	=	eventOptionIDStack.get(id);
		ArrayList<String>	exits		=	eventExitStack.get(id);
		ArrayList<Integer>	exitIDs		=	eventExitID.get(id);
		
		int 				textCounter	=	0;
		int 				exitCounter	=	0;
		if (!options.contains("Exit"))
		{
			options.add("Exit");
			nextEvent.add(-1);
		}
		
		for (Event event : events)
		{
			switch (event){
				case EXIT:
					exitOption.add(exits.get(exitCounter));
					exitID.add(exitIDs.get(exitCounter));
					exitCounter++;
					break;
				case FIGHT:
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
		Scanner input = new Scanner(System.in);
		String newInput;
		String answer;
		boolean correctValue;
		boolean correctValue2;
		int		newEvent	=	-1;
		while(true)
		{
			System.out.println("Select which option you'd like to take:");	
			for (String option : options)
			{
				System.out.println(" --- <" + option +">" );
			}
			newInput	=	input.nextLine().toLowerCase();
			if (newInput.startsWith("<"))
				newInput	=	newInput.substring(1, newInput.length());
			if (newInput.endsWith(">"))
				newInput	=	newInput.substring(0, newInput.length()-1);
				
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
					System.out.println("Where would you like to exit through:");	
					for (String option : exitOption)
					{
						System.out.println(" --- <" + option +">" );
					}
					while (true)
					{
						answer = input.nextLine().toLowerCase();
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
									return "exit@" + exitID.get(i);
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
		return "newEvent@"+newEvent;
	}	
	public Room()
	{
		exitOption.add("None");
		exitID.add(-1);
	}
	
	
	
}