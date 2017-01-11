package dungeons;

import java.util.ArrayList;

import dungeon.Dungeon;
import dungeons.Room.Event;
import npc.Entity;
import playerPackage.CharacterHandling;
import shitTierPackage.ShitTierClass;

public class World {
	
	//TEMP DATA - REMEMBER TO EDIT
	public static Entity	playerChar;	
	
	public static ArrayList<Dungeon> dungeonList = new ArrayList<Dungeon>();
	
	public static void main(String[] args)
	{
		ShitTierClass.intiateSeverData();

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
		
		int[] enemies;
		
		room.newEvent();
		ArrayList<Event>	event			=	room.eventStack.get(room.eventStack.size()-1);
		ArrayList<String>	textEvent		=	room.eventTextStack.get(room.eventTextStack.size()-1);
		ArrayList<String>	optionEvent		=	room.eventOptionStack.get(room.eventOptionStack.size()-1);
		ArrayList<Integer>	optionIDevent	=	room.eventOptionIDStack.get(room.eventOptionIDStack.size()-1);
		ArrayList<String>	exitEvent		=	room.eventExitStack.get(room.eventExitStack.size()-1);
		ArrayList<Integer>	exitEventID		=	room.eventExitID.get(room.eventExitID.size()-1);
		ArrayList<Integer>	exitEvents		=	room.eventExitEvent.get(room.eventExitEvent.size()-1);
		ArrayList<Entity[]>	enemyEvents		=	room.eventEnemies.get(room.eventEnemies.size()-1);
		
		//this is the order in which events occur.
		room.newTextEvent("Running event 0 in room 0.",event,textEvent);
		room.newOptionEvent("Run event 1", 1, optionEvent, optionIDevent);
		enemies = new int[1];
		enemies[0]	=	0;
		room.newFight(enemies,event,enemyEvents);

		
		//adds the exit if event 0 launches
		//this exit is called market, makes the user go to room, and starts event 0.
		//if the player has entered the room before, event 1 is launched
		//please note that this is only true for 0 and 1, no other value is changed
		room.newExit("Market",1,0,event,exitEvent,exitEventID,exitEvents);
		
		
		room.newEvent();
		event			=	room.eventStack.get(room.eventStack.size()-1);
		textEvent		=	room.eventTextStack.get(room.eventTextStack.size()-1);
		optionEvent		=	room.eventOptionStack.get(room.eventOptionStack.size()-1);
		optionIDevent	=	room.eventOptionIDStack.get(room.eventOptionIDStack.size()-1);
		exitEvent		=	room.eventExitStack.get(room.eventExitStack.size()-1);
		exitEventID		=	room.eventExitID.get(room.eventExitID.size()-1);
		exitEvents		=	room.eventExitEvent.get(room.eventExitEvent.size()-1);
		enemyEvents		=	room.eventEnemies.get(room.eventEnemies.size()-1);
		
		room.newTextEvent("Running event 1 in room 0.",event,textEvent);
		
		
		
		room.newOptionEvent("Run event 0", 0, optionEvent, optionIDevent);
		room.newExit("Market",1,0,event,exitEvent,exitEventID,exitEvents);
		
		
		//room 1 market
		dung.newRoom();
		room		=	dung.newRoom;
		room.newEvent();
		event			=	room.eventStack.get(room.eventStack.size()-1);
		textEvent		=	room.eventTextStack.get(room.eventTextStack.size()-1);
		optionEvent		=	room.eventOptionStack.get(room.eventOptionStack.size()-1);
		optionIDevent	=	room.eventOptionIDStack.get(room.eventOptionIDStack.size()-1);
		exitEvent		=	room.eventExitStack.get(room.eventExitStack.size()-1);
		exitEventID		=	room.eventExitID.get(room.eventExitID.size()-1);
		exitEvents		=	room.eventExitEvent.get(room.eventExitEvent.size()-1);
		enemyEvents		=	room.eventEnemies.get(room.eventEnemies.size()-1);
		
		//this is the order in which events occur.
		room.newTextEvent("Running event 0 in room 1.",event,textEvent);
		room.newOptionEvent("Run event 1", 1, optionEvent, optionIDevent);
		room.newExit("Town Square",0,0,event,exitEvent,exitEventID,exitEvents);
		
		room.newEvent();
		event			=	room.eventStack.get(room.eventStack.size()-1);
		textEvent		=	room.eventTextStack.get(room.eventTextStack.size()-1);
		optionEvent		=	room.eventOptionStack.get(room.eventOptionStack.size()-1);
		optionIDevent	=	room.eventOptionIDStack.get(room.eventOptionIDStack.size()-1);
		exitEvent		=	room.eventExitStack.get(room.eventExitStack.size()-1);
		exitEventID		=	room.eventExitID.get(room.eventExitID.size()-1);
		exitEvents		=	room.eventExitEvent.get(room.eventExitEvent.size()-1);
		enemyEvents		=	room.eventEnemies.get(room.eventEnemies.size()-1);
		
		//this is the order in which events occur.
		room.newTextEvent("Running event 1 in room 1.",event,textEvent);
		room.newOptionEvent("Run event 0", 0, optionEvent, optionIDevent);
		room.newExit("Town Square",0,0,event,exitEvent,exitEventID,exitEvents);
		room.newTextEvent("Inside the room you notice there's a little hobbled figure.",event,textEvent);
		room.newTextEvent("Against all reason, you decide to move closer to it.",event,textEvent);
		
		
		playerChar	= CharacterHandling.characters.get(0);
		
		//call this when entering a new room
		int dungID	=	0;
		String[]	dungeon;
		while(true)
		{
			while(true)
			{
				dungeon	=	playerRoom.runEvent(dungID).split("@");
				playerRoom.hasEntered	=	true;
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
			//switch rooms
			playerRoom	=	dung.rooms.get(Integer.parseInt(dungeon[1]));
			dungID	=	Integer.parseInt(dungeon[2]);
			// if dungID is equal to 1 or 0, check if room has been entered in the past.
			dungID	=	(playerRoom.hasEntered && (dungID == 1 || dungID == 0)) ? 1: 0;
		}
		
	}
	
}
