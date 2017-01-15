package dungeons;

import java.util.ArrayList;
import java.util.Random;

import dungeon.Dungeon;
import dungeons.Room.Event;
import items.ArmorGeneration;
import items.Item;
import items.WeaponGeneration;
import npc.Entity;

public class World {
	static Random rand = new Random(); 

	
	public ArrayList<Dungeon> dungeonList = new ArrayList<Dungeon>();
	
	public World()
	{
		System.out.println("ye3s");
		initateWorld();
	}
	
	
	public boolean initateWorld()
	{
		Dungeon dung = new Dungeon("Test Town","A bussy little town.");
		dungeonList.add(dung);
		createTestTown(dung);
		return true;
	}
	
	/**
	 * Creates Test Town
	 * <P> Please note that events run till they require an input - branching events require
	 * different events for each path for that reason.
	 */
	//used to get rid of annoying yellow flags
	@SuppressWarnings({ "unchecked" })
	public void createTestTown(Dungeon dung)
	{
		dung.newRoom();
		Room 				room		=	dung.newRoom;
		Room				playerRoom	=	room;
		
		int[] enemies;
		
		room.newEvent();
		ArrayList<Event>	 event			=	room.eventStack.get(room.eventStack.size()-1);
		ArrayList<String>	 textEvent		=	room.eventTextStack.get(room.eventTextStack.size()-1);
		ArrayList<String>	 optionEvent	=	room.eventOptionStack.get(room.eventOptionStack.size()-1);
		ArrayList<Integer>	 optionIDevent	=	room.eventOptionIDStack.get(room.eventOptionIDStack.size()-1);
		ArrayList<String>	 exitEvent		=	room.eventExitStack.get(room.eventExitStack.size()-1);
		ArrayList<Integer>	 exitEventID	=	room.eventExitID.get(room.eventExitID.size()-1);
		ArrayList<Integer>	 exitEvents		=	room.eventExitEvent.get(room.eventExitEvent.size()-1);
		ArrayList<Entity[]>	 enemyEvents	=	room.eventEnemies.get(room.eventEnemies.size()-1);
		ArrayList<ArrayList> eventShops		=	room.eventShops.get(room.eventShops.size()-1);
		ArrayList<ArrayList> eventShopsText	=	room.eventShopsText.get(room.eventShopsText.size()-1);
		
		ArrayList<Item>		shopItems		=	new ArrayList<Item>();
		ArrayList<String>	shopText		=	new ArrayList<String>();
		
		
		//this is the order in which events occur.
		room.newTextEvent("Running event 0 in room 0.",event,textEvent);
		room.newOptionEvent("Run event 1", 1, optionEvent, optionIDevent);
		enemies = new int[1];
		enemies[0]	=	0;
		//room.newFight(enemies,event,enemyEvents);

		
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
		eventShops		=	room.eventShops.get(room.eventShops.size()-1);
		eventShopsText	=	room.eventShopsText.get(room.eventShopsText.size()-1);
		
		
		room.newTextEvent("Running event 1 in room 0.",event,textEvent);
		
		//let's just add some random items to the shop
		
		//clear arrayLists used for items beforehand!
		shopItems.clear();
		shopText.clear();
		
		//adding items to our shop
		shopItems.add(WeaponGeneration.createWeapon(rand.nextInt(2)));
		shopItems.add(WeaponGeneration.createWeapon(rand.nextInt(2)));
		shopItems.add(WeaponGeneration.createWeapon(rand.nextInt(2)));
		shopItems.add(WeaponGeneration.createWeapon(rand.nextInt(2)));
		shopItems.add(WeaponGeneration.createWeapon(rand.nextInt(2)));
		
		shopItems.add(ArmorGeneration.createArmor(rand.nextInt(2)));
		shopItems.add(ArmorGeneration.createArmor(rand.nextInt(2)));
		shopItems.add(ArmorGeneration.createArmor(rand.nextInt(2)));
		shopItems.add(ArmorGeneration.createArmor(rand.nextInt(2)));
		
		//first  = intro to shop
		//second = "you want to buy X?
		//third  = "it's a good item smth smth"
		//fourth = "you bought x"
		//fifth  = you don't have enough gold
		//sixth	 = invalid ID
		//seventh= leave shop
		
		shopText.add("Old man: Wha, what ar yu doin' in mah shop?");
		shopText.add("Old man: Oh, ya want to buy the ");
		shopText.add(" , that's a mighty fine item.");
		shopText.add("Old man: Aight, here ya go");
		shopText.add("Old man: Ya ain't got the money bud.");
		shopText.add("Old man: I'm pretty sure what'cha tryina buy ain't something I've got for sale.");
		shopText.add("Old man: Come back any time ya want to, if yu have the cash.");
		
		room.newShopEvent(event, eventShops, eventShopsText, shopItems, shopText);
		
		
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
		eventShops		= 	room.eventShops.get(room.eventShops.size()-1);
		eventShopsText	=	room.eventShopsText.get(room.eventShopsText.size()-1);
		
		
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
		eventShops		= 	room.eventShops.get(room.eventShops.size()-1);
		eventShopsText	=	room.eventShopsText.get(room.eventShopsText.size()-1);
		
		//this is the order in which events occur.
		room.newTextEvent("Running event 1 in room 1.",event,textEvent);
		room.newOptionEvent("Run event 0", 0, optionEvent, optionIDevent);
		room.newExit("Town Square",0,0,event,exitEvent,exitEventID,exitEvents);
		room.newTextEvent("Inside the room you notice there's a little hobbled figure.",event,textEvent);
		room.newTextEvent("Against all reason, you decide to move closer to it.",event,textEvent);		
	}

	/*
	public void runGame()
	{
		//starts in dungeon 0 room 0
		int dungID	=	0;
		int	roomID	=	0;
		Room playerRoom = dungeonList.get(dungID).rooms.get(0);
		String[]	dungeon;
		while(true)
		{
			while(true)
			{
				dungeon	=	playerRoom.runEvent(dungID,playerChar).split("@");
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
				//server operations
				
				//operation
			}
			//switch rooms
			playerRoom	=	dungeonList.get(dungID).rooms.get(Integer.parseInt(dungeon[1]));
			roomID	=	Integer.parseInt(dungeon[2]);
			// if dungID is equal to 1 or 0, check if room has been entered in the past.
			roomID	=	(playerRoom.hasEntered && (roomID == 1 || roomID == 0)) ? 1: 0;
		}
	}
	*/
	
}
