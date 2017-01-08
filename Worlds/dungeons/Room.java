package dungeons;

import java.util.ArrayList;
import java.util.Hashtable;

public class Room 
{
	/**
	 * Contains the ID of another room - this room is used to connect to the new room
	 */
	private ArrayList<Integer>	connectionID;
	/**
	 * Contains a string with the description of the connection.
	 * things like <door> etc.
	 */
	private ArrayList<String>	connectionDescription;
	
	/**
	 * Contains an ID integer which refers to a connectionID and a connectionDescription
	 * It only displays visible connections
	 */
	private ArrayList<Integer>	visibleConnections;
	
	/** 
	 * Contains the events for the room ex.
	 * <p> reveal-x-: = reveals connection x from connectionID and connectionDescription.</p>
	 * <p> fight-x-= causes a fight to occur with x enemy from the enemylist - can be grouped</p>
	 * <p>newOption-x- reveals option x as a possible option.</p>
	 * Refer to eventDescription for a text reference. 
	 */
	private ArrayList<String> eventList;
	
	/**
	 * A string containing the description of an event within the room.
	 */
	ArrayList<String> eventDescription;
	
	//description text starts when the player enters the room
	ArrayList<String> description;
	
	/**
	 * A room contains our lists and nothing else
	 */
	
	public Room()
	{
		connectionID			=	new ArrayList<Integer>();
		connectionDescription	=	new ArrayList<String>();
		visibleConnections		=	new ArrayList<Integer>();
		eventList				=	new ArrayList<String>();
		eventDescription		=	new ArrayList<String>();
		description				=	new ArrayList<String>();
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