package dungeons;

import java.util.ArrayList;
import java.util.Hashtable;

import npc.Entity;

public class Room2 
{
	/**
	 * Contains the ID of another room - this room is used to connect to the new room
	 */
	private ArrayList<Integer>	connectionID 			=	new ArrayList<Integer>();
	/**
	 * Contains a string with the description of the connection.
	 * things like <door> etc.
	 */
	private ArrayList<String>	connectionDescription	=	new ArrayList<String>();
	/**
	 * Contains an ID integer which refers to a connectionID and a connectionDescription
	 * It only displays visible connections
	 */
	private ArrayList<Integer>	visibleConnections		=	new ArrayList<Integer>();
		
	/**
	 * A string containing the description of an event within the room.
	 */
	private ArrayList<String> eventDescription			=	new ArrayList<String>();
	
	//description text starts when the player enters the room
	ArrayList<String> description						=	new ArrayList<String>();
	
	public enum Event {
		FIGHT, TEXT, NEWOPTION;
	}
	//contains multiple instances of event
	public ArrayList<ArrayList> roomEventStacks	=	new ArrayList<ArrayList>();
	
	ArrayList<Integer> eventPointer	=	new ArrayList<Integer>();
	
	public void createNewEventStack()
	{
		roomEventStacks.add(new ArrayList<Event>());
	}
	
	
	//handles everything for an event - 
	//TODO LOOK INTO ENUM CONSTRUCTORS
	public void runEvent(ArrayList<Event> events)
	{
		for (Event event : events)
		{
			switch (event){
				case FIGHT:
					break;
				case TEXT: // prints the first line in eventDescription and then deletes it.
					System.out.println(eventDescription.get(0));
					eventDescription.remove(0);
					try {
						Thread.sleep(1500);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				default://nothing happens kek
			}
		}
	}


	/**
	 * A room contains our lists and nothing else
	 */
	public Room2(){}
	
	
	/**
	 * Adds a new text event to when the player enters the room.
	 * @param text
	 */
	public void addNewTextEvent(String text, ArrayList<Event> events)
	{
		eventDescription.add(text);
		events.add(Event.TEXT);
	}
	
	
	/**
	 * Adds a new connection for this room to another
	 * @param id the ID of the new room
	 * @param desc
	 */
	public void addConnection(int id, String desc)
	{
		connectionID.add(id);
		connectionDescription.add(desc);
	}
	
}