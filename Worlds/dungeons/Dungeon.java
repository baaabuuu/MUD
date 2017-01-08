package dungeons;

import java.util.ArrayList;

public class Dungeon {
	
	//name and text description of the dungeon.
	String	dungeonName			=	"unnamed dungeon";	
	String	dungeonDescription	=	"undescried Dungeon";
	//our list of rooms
	ArrayList<Room> rooms;
	//player location, usually starts on 0 as the entry point.
	int playerLoc	=	0;
	//
	Room newRoom	=	null;
	
	
	/**
	 * Creates a new dungeon object which holds, playerlocation, and a list of rooms.
	 * @param name The name of the dungeon
	 * @param desc A description of the dungeon
	 */
	public Dungeon(String name, String desc){
		rooms				=	new ArrayList<Room>();
		dungeonName			=	name;
		dungeonDescription	=	desc;
	}
	
	/**
	 * Creates a new room object - sets newRoom Room to this room
	 */
	public void newRoom()
	{
		newRoom		=	new Room();
		rooms.add(newRoom);
	}
	
	
	
}
