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
	 */
	public static void createTestTown(Dungeon dung)
	{
		dung.newRoom();
		Room room	=	dung.newRoom;
		room.newEvent();
		ArrayList<Event>	event				=	room.eventStack.get(room.eventStack.size()-1);
		ArrayList<String>	textEvent			=	room.eventTextStack.get(room.eventTextStack.size()-1);
		ArrayList<String>	optionEvent		=	room.eventOptionStack.get(room.eventOptionStack.size()-1);
		ArrayList<Integer>	optionIDevent	=	room.eventOptionIDStack.get(room.eventOptionIDStack.size()-1);
		room.newTextEvent("Testing 1.",event,textEvent);
		room.newTextEvent("Testing 2.",event,textEvent);
		room.newTextEvent("Testing 3.",event,textEvent);
		room.newTextEvent("Testing 4.",event,textEvent);
		room.newTextEvent("Testing 5.",event,textEvent);
		room.newTextEvent("Testing 6.",event,textEvent);
		room.newOptionEvent("North", -1, optionEvent, optionIDevent);
		room.newOptionEvent("South", -1, optionEvent, optionIDevent);
		room.newOptionEvent("East", -1, optionEvent, optionIDevent);
		room.newOptionEvent("Plane", -1, optionEvent, optionIDevent);
		
		
		room.newEvent();
		event	=	room.eventStack.get(room.eventStack.size()-1);
		textEvent	=	room.eventTextStack.get(room.eventTextStack.size()-1);
		
		room.newTextEvent("fuck 1.",event,textEvent);
		room.newTextEvent("this 2.",event,textEvent);
		room.newTextEvent("shit 3.",event,textEvent);
		room.newTextEvent("up 4.",event,textEvent);
		room.newTextEvent("your 5.",event,textEvent);
		room.newTextEvent("ass 6.",event,textEvent);
		//call this when entering a new room
		room.runEvent(1);
		System.out.println("RUNNING EVENT STACK 2");
		room.runEvent(0);
	}
	
}
