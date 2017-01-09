package dungeons;

import java.util.ArrayList;

import dungeons.Room.Event;

public class World {
	public static ArrayList<Dungeon> dungeonList = new ArrayList<Dungeon>();
	
	
	public static void main(String[] args)
	{
		Dungeon dung = new Dungeon("Test Town","A bussy little town.");
		createTestTown(dung);
	}
	
	/**
	 * Creates Test Town
	 * <P> Please note that events run till they require an input - branching events require
	 * different events for each path for that reason.
	 */
	//used to get rid of annoying yellow flags
	@SuppressWarnings("unchecked")
	public static void createTestTown(Dungeon dung)
	{
		dung.newRoom();
		Room 				room		=	dung.newRoom;
		Room				playerRoom	=	room;
		room.newEvent();
		ArrayList<Event>	event			=	room.eventStack.get(room.eventStack.size()-1);
		ArrayList<String>	textEvent		=	room.eventTextStack.get(room.eventTextStack.size()-1);
		ArrayList<String>	optionEvent		=	room.eventOptionStack.get(room.eventOptionStack.size()-1);
		ArrayList<Integer>	optionIDevent	=	room.eventOptionIDStack.get(room.eventOptionIDStack.size()-1);
		ArrayList<String>	exitEvent		=	room.eventExitStack.get(room.eventExitStack.size()-1);
		ArrayList<Integer>	exitEventID		=	room.eventExitID.get(room.eventExitID.size()-1);
		
		//this is the order in which events occur.
		room.newTextEvent("Running event 0.",event,textEvent);
		room.newOptionEvent("Run event 1", 1, optionEvent, optionIDevent);

		
		//adds the exit if event 0 launches
		room.newExit("Market",1,event,exitEvent,exitEventID);
		
		
		room.newEvent();
		event			=	room.eventStack.get(room.eventStack.size()-1);
		textEvent		=	room.eventTextStack.get(room.eventTextStack.size()-1);
		optionEvent		=	room.eventOptionStack.get(room.eventOptionStack.size()-1);
		optionIDevent	=	room.eventOptionIDStack.get(room.eventOptionIDStack.size()-1);
		exitEvent		=	room.eventExitStack.get(room.eventExitStack.size()-1);
		exitEventID		=	room.eventExitID.get(room.eventExitID.size()-1);
		
		room.newTextEvent("Running event 1.",event,textEvent);
		room.newOptionEvent("Run event 0", 0, optionEvent, optionIDevent);
		//call this when entering a new room
		int dungID	=	0;
		String[]	dungeon;
		while(true)
		{
			dungeon	=	playerRoom.runEvent(dungID).split("@");
			if (dungeon[0].equals("exit"))
			{
				//switch room
				break;
			}
			else if (dungeon[0].equals("newEvent"))
			{
				dungID	=	Integer.parseInt(dungeon[1]);
			}
		}
		
	}
	
}
