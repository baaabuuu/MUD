package dungeons;

import java.util.ArrayList;
import java.util.Random;

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
	public static void createTestTown(Dungeon dung)
	{
		Random rand = new Random(); 
		//Room 0
		dung.newRoom();
		Room 				room		=	dung.newRoom;
		
		int[] enemies;
		
		//Event 0m room 0
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
		
		//Adding shop items and text when you first start the game.
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
		
		shopText.add("Shopkeeper: Looking for something specific?");
		shopText.add("Shopkeeper: Oh, ya want to buy the ");
		shopText.add(" , that's a mighty fine item.");
		shopText.add("Shopkeeper: Aight, here ya go");
		shopText.add("Shopkeeper: Ya ain't got the money bud.");
		shopText.add("Shopkeeper: I'm pretty sure what'cha tryina buy ain't something I've got for sale.");
		shopText.add("Shopkeeper: Come back any time ya want to, if yu have the cash.");
		
		//this is the order in which events occur.
		
		room.newTextEvent("After a long journey, you arrive at the gates to a small town. Moonbright, it's called.",event,textEvent);
		room.newTextEvent("The area is surrounded by huge woods, and the trees dim the light of the village, even though it is, not late.",event,textEvent);
		room.newTextEvent("You approach the gate, which is guarded by two men.",event,textEvent);
		room.newTextEvent("Guard: Halt! What are you doing here? The town is under lockdown, and noone may enter.",event,textEvent);
		room.newTextEvent("Guard: Unless you are on official buisness you better leave.",event,textEvent);

		room.newOptionEvent("Persuade", 2, optionEvent, optionIDevent);
		room.newOptionEvent("Bribe", 3, optionEvent, optionIDevent);
		
		//Event 1, room 0
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

		room.newTextEvent("The woods look peacefull. Dark, but you don't notice anything strange.", event, textEvent);
		room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);

		//Event 2, room 0
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

		if (rand.nextInt(100)+1 > 50) {
			room.newTextEvent("Oh sorry. I did not know you had buisness here. Let me unlock the gate.", event, textEvent);
			room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);
		} else {
			room.newTextEvent("Guard: Hahaha! You're not gonna trick me stranger.", event, textEvent);
			room.newOptionEvent("Bribe", 3, optionEvent, optionIDevent);
		}
		
		//Event 3, room 0
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

		room.newTextEvent("Guard: That's better. Now hurry on in, before I change my mind.", event, textEvent);
		room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);
		
		//room 1 Moonbright Square
		dung.newRoom();
		room		=	dung.newRoom;

		//Event 0, room 1
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

		room.newTextEvent("Old man Cain: Greetings traveller.", event, textEvent);
		room.newTextEvent("Old man Cain: Dark times these days... Where are you from stranger?", event, textEvent);
		room.newOptionEvent("Hello old man Im from Darkshire", 2, optionEvent, optionIDevent);
		room.newOptionEvent("No time old man", 4, optionEvent, optionIDevent);

		//Event 1, room 1
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

		room.newTextEvent("Old man: Stay safe traveller...", event, textEvent);
		room.newExit("Gate",0,0,event,exitEvent,exitEventID,exitEvents);
		room.newExit("Town hall",2,0,event,exitEvent,exitEventID,exitEvents);
		room.newExit("Market",3,0,event,exitEvent,exitEventID,exitEvents);
		room.newExit("Inn",4,0,event,exitEvent,exitEventID,exitEvents);
		

		//Event 2, room 1
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

		room.newTextEvent("Old man: That's a long way from here. Sad time you would come here though...", event, textEvent);
		room.newOptionEvent("Why is that so?", 3, optionEvent, optionIDevent);
		
		//Event 3, room 1
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

		room.newTextEvent("Old man: Werewolves are preying in the surrounding woods, no one knows why they've come, but the city council is trying to figure out where they're coming from.", event, textEvent);
		room.newOptionEvent("Okay thank you", 4, optionEvent, optionIDevent);
		
		//Event 4, room 1
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

		room.newExit("Gate",0,0,event,exitEvent,exitEventID,exitEvents);
		room.newExit("Town hall",2,0,event,exitEvent,exitEventID,exitEvents);
		room.newExit("Market",3,0,event,exitEvent,exitEventID,exitEvents);
		room.newExit("Inn",4,0,event,exitEvent,exitEventID,exitEvents);
		
		//Room 2 town hall
		dung.newRoom();
		room		=	dung.newRoom;
		//Event 0,room 2
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
		
		room.newTextEvent("You enter a large building. It looks like the town hall.",event,textEvent);
		room.newTextEvent("A large room is at the center of the building.",event,textEvent);
		room.newTextEvent("A man apporaches, he gives off the distinct impression that he is a man who knows Java. This sensation is rather odd, as you are a fantasy guy who has never heard of the word Java before, but you feel it would make for a good coffee name.",event,textEvent);
		room.newTextEvent("Steelchin: Goddag min herre, hvordan kan jeg hjælpe dig i denne mørke tid?",event,textEvent);
		room.newTextEvent("The nobleman speaks to you in a strange, intriguing, well mannered, perfect, language, which you somewhow can interpret. ",event,textEvent);
		room.newOptionEvent("I hear you have a werewolf problem?", 2, optionEvent, optionIDevent);
		room.newOptionEvent("Say nothing", 3, optionEvent, optionIDevent);
		
		//Event 1, room 2
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
		if (rand.nextInt(100)+1 > 80) {
			room.newTextEvent("Steelchin: Jeg frygter for vores by...",event,textEvent);
			room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);
			room.newExit("Prison",5,0,event,exitEvent,exitEventID,exitEvents);
		} else if ((rand.nextInt(100)+1 > 60)) {
			room.newTextEvent("Steelchin: Andet jeg kan hjælpe dem med?",event,textEvent);
			room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);
			room.newExit("Prison",5,0,event,exitEvent,exitEventID,exitEvents);
		} else if ((rand.nextInt(100)+1 > 40)) {
			room.newTextEvent("Steelchin: Yes, yes, yes!",event,textEvent);
			room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);
			room.newExit("Prison",5,0,event,exitEvent,exitEventID,exitEvents);
		} else if ((rand.nextInt(100)+1 > 20)) {
			room.newTextEvent("Steelchin: Mat 1 kommer... Løb!",event,textEvent);
			room.newOptionEvent("What..?", 4, optionEvent, optionIDevent);
		} else if ((rand.nextInt(100)+1 > 5)) {
			room.newTextEvent("Velkommen tilbage.",event,textEvent);
			room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);
			room.newExit("Prison",5,0,event,exitEvent,exitEventID,exitEvents);
		}

		//Event 2, room 2
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
		room.newTextEvent("Steelchin: Ja flere varulve er blevet set heromkring. En af vores beboere er også blevet dræbt af en varulv, men vi ved ikke hvorfor disse magiske væsener pludselig er kommet her.",event,textEvent);
		room.newTextEvent("Dog har vi fået fanget en varulv, som måske ville kunne lade os forstå, hvorfor de er kommet til vores land. Du kan prøve og gå ned i fangekælderen og få noget information om ham.",event,textEvent);
		room.newOptionEvent("Okay I will check him out", 4, optionEvent, optionIDevent);
		room.newOptionEvent("Say nothing", 4, optionEvent, optionIDevent);
		
		//Event 3, room 2
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
		room.newTextEvent("Steelchin: Nå, du er vist lidt den stille type? Hvis du vil hjælpe os med varulvene, så kan du tage ned i fangehullet. Vi har fået fanget en varulv, som måske kan fortælle os lidt om hvor de er kommet fra.",event,textEvent);
		room.newOptionEvent("Okay, thank you.", 4, optionEvent, optionIDevent);
		room.newOptionEvent("Say nothing", 4, optionEvent, optionIDevent);
		
		//Event 4, room 2
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
		room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);
		room.newExit("Prison",5,0,event,exitEvent,exitEventID,exitEvents);
		
		//Room 3 Market
		dung.newRoom();
		room		=	dung.newRoom;
		//Event 0, room 3
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
		
		room.newTextEvent("You enter the local shop.",event,textEvent);
		room.newTextEvent("Shopkeeper: Hello tha! How you doing this fine evening??!",event,textEvent);
		room.newOptionEvent("Im fine thank you", 2, optionEvent, optionIDevent);

		//Event 1, room 3
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
		
		room.newTextEvent("Shopkeeper: Welcome back!",event,textEvent);
		room.newOptionEvent("Browse", 3, optionEvent, optionIDevent);

		//Event 2, room 3
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
		
		room.newTextEvent("Shopkeeper: Care to check out my fine wares?!",event,textEvent);
		room.newOptionEvent("Sure", 3, optionEvent, optionIDevent);

		//Event 3, room 3
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

		room.newShopEvent(event, eventShops, eventShopsText, shopItems, shopText);
		room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);

		//Room 4 Inn
		dung.newRoom();
		room		=	dung.newRoom;
		//Event 0, room 4
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
		
		room.newTextEvent("You visit the local inn.",event,textEvent);
		room.newTextEvent("The inn is cozy with a hearthfire burning strong in the chimney.",event,textEvent);
		room.newTextEvent("Innkeeper: Welcome to my humble inn! If you need something to eat or drink, I'll help you. Maybe a warm bed to rest your feet?",event,textEvent);
		room.newOptionEvent("Sounds good", 2, optionEvent, optionIDevent);

		//Event 1, room 4
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
		
		room.newTextEvent("Innkeeper: Welcome back!",event,textEvent);
		room.newOptionEvent("Get a drink", 3, optionEvent, optionIDevent);
		room.newOptionEvent("Get some food", 4, optionEvent, optionIDevent);
		room.newOptionEvent("Rest in a bed", 5, optionEvent, optionIDevent);
		room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);

		//Event 2, room 4
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
		
		room.newOptionEvent("Get a drink", 3, optionEvent, optionIDevent);
		room.newOptionEvent("Get some food", 4, optionEvent, optionIDevent);
		room.newOptionEvent("Rest in a bed", 5, optionEvent, optionIDevent);
		room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);

		//Event 3, room 4
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
		
		room.newTextEvent("The local mead is refreshing.",event,textEvent);
		room.newOptionEvent("Get some food", 4, optionEvent, optionIDevent);
		room.newOptionEvent("Rest in a bed", 5, optionEvent, optionIDevent);
		room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);

		//Event 4, room 4
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
		
		room.newTextEvent("The food was good and you feel well fed.",event,textEvent);
		room.newOptionEvent("Get a drink", 3, optionEvent, optionIDevent);
		room.newOptionEvent("Rest in a bed", 5, optionEvent, optionIDevent);
		room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);

		//Event 5, room 4
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
		
		room.newTextEvent("You slept for several hours and feel well rested.",event,textEvent);
		room.newOptionEvent("Get a drink", 3, optionEvent, optionIDevent);
		room.newOptionEvent("Get some food", 4, optionEvent, optionIDevent);
		room.newExit("Square",1,0,event,exitEvent,exitEventID,exitEvents);

		//Room 5 prison
		dung.newRoom();
		room		=	dung.newRoom;
		//Event 0, room 5
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

		room.newTextEvent("You climb down the stairs to the prison. It's a dank celler you've gotten yourself into.",event,textEvent);
		room.newTextEvent("At the end of the stairs you see a light from a cell. You move closer.",event,textEvent);
		room.newTextEvent("Werewolf: So, someone has come finally. Please help me, I don't want to stay here for the rest of my life!",event,textEvent);
		room.newOptionEvent("Where are you from beast?", 2, optionEvent, optionIDevent);

		//Event 1, room 5
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
						
		room.newTextEvent("There's nothing else here you can do. Another cell is open though.",event,textEvent);
		room.newExit("Inspect cell",6,0,event,exitEvent,exitEventID,exitEvents);
		room.newExit("Town hall",3,0,event,exitEvent,exitEventID,exitEvents);
		
		//Event 2, room 5
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
						
		room.newTextEvent("Werewolf: The goblins summoned us. Most of us went ravage, but I did not succumb to rage...",event,textEvent);
		room.newTextEvent("Werewolf: The only thing I know about the green men, is their lair lies under these very stones.",event,textEvent);
		room.newTextEvent("Werewolf: I don't know where the entrance is though. Can't remember for some reason.",event,textEvent);
		room.newOptionEvent("I will find the source of this", 3, optionEvent, optionIDevent);

		//Event 3, room 5
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
						
		room.newTextEvent("The werewolf disappears into the shadows of the cell.",event,textEvent);
		room.newOptionEvent("Where should I start?", 1, optionEvent, optionIDevent);

		//Room 6 prison cell
		dung.newRoom();
		room		=	dung.newRoom;
		//Event 0, room 6
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

		room.newTextEvent("You enter the cell to inspect it, but ass soon as you face away from the door, it magically clashes shut.",event,textEvent);
		room.newTextEvent("You pull and push the door, but it's locked. There must be a way out. Someone might hear you if you yell?",event,textEvent);
		room.newOptionEvent("HELP!", 2, optionEvent, optionIDevent);
		room.newOptionEvent("Look around", 4, optionEvent, optionIDevent);

		//Event 1, room 6
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

		room.newTextEvent("You've entered the jail cell again. The door is not locked now though.",event,textEvent);
		room.newExit("Hidden door",7,0,event,exitEvent,exitEventID,exitEvents);
		room.newExit("Prison",5,0,event,exitEvent,exitEventID,exitEvents);

		//Event 2, room 6
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

		if (rand.nextInt(100)+1 > 70) {
			room.newTextEvent("Someone heard you yell for help.",event,textEvent);
			room.newTextEvent("Moonbright Guard: What are you doing here?",event,textEvent);
			room.newTextEvent("Moonbright Guard: Come on, I'll let you out.",event,textEvent);
			room.newOptionEvent("Thank you so much m'lord", 3, optionEvent, optionIDevent);
		} else {
			room.newTextEvent("It seems like no one is coming.",event,textEvent);
			room.newOptionEvent("Inspect cell", 4, optionEvent, optionIDevent);
		}
		
		//Event 3, room 6
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

		room.newTextEvent("The door is unlocked, and you can leave if you want. You could look around if you want to?",event,textEvent);
		room.newOptionEvent("Inspect cell", 4, optionEvent, optionIDevent);
		room.newExit("Prison",5,0,event,exitEvent,exitEventID,exitEvents);

		//Event 4, room 6
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

		room.newTextEvent("The cell is dark with a single candle on a wooden table, which is placed in the corner.",event,textEvent);
		room.newTextEvent("The floor, not made of anything other than the cold ground, the building was laid on. The walls are made of bricks.",event,textEvent);
		room.newTextEvent("A single square window with bars, is at the top of the wall, opposite of the door.",event,textEvent);
		room.newTextEvent("On one of the walls, prison shackles are hanging from the ceiling and wall. Must have been horrible to be locked in those.",event,textEvent);
		room.newOptionEvent("Inspect table", 5, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect window", 6, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect wall", 7, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect chains", 8, optionEvent, optionIDevent);

		//Event 5, room 6
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

		room.newTextEvent("The table looks older than the cell itself. You look around it and under, but there's nothing special here.",event,textEvent);
		room.newOptionEvent("Inspect window", 6, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect wall", 7, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect chains", 8, optionEvent, optionIDevent);

		//Event 6, room 6
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

		room.newTextEvent("You move to the window. You can feel the cold coming through it. Must have been horrible to stay here in winter.",event,textEvent);
		room.newTextEvent("You reach for it, but it is too high up for you to reach. Probably couldn't climb out of it anyway.",event,textEvent);
		room.newOptionEvent("Inspect table", 5, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect wall", 7, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect chains", 8, optionEvent, optionIDevent);

		//Event 7, room 6
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

		room.newTextEvent("The bricks look very old. But some of them almost look out of place? You touch them but nothing happens.",event,textEvent);
		room.newTextEvent("You try grabbing one of them to pull on it, but the stones are too wet to get a firm grip on. Nothing really you can do.",event,textEvent);
		room.newOptionEvent("Inspect table", 5, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect window", 6, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect chains", 8, optionEvent, optionIDevent);

		//Event 8, room 6
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

		room.newTextEvent("The chains are rusted with giant red rust spots. Or are they blood? Hard to tell, but you can almost feel the cold metal around your wrists and ankles.",event,textEvent);
		room.newTextEvent("You notice some of the chains connect the wall and ceiling. Wonder what those have been used for.",event,textEvent);
		room.newOptionEvent("Inspect table", 5, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect window", 6, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect wall", 7, optionEvent, optionIDevent);
		room.newOptionEvent("Push chains", 9, optionEvent, optionIDevent);
		room.newOptionEvent("Pull chains", 13, optionEvent, optionIDevent);

		//Event 9, room 6
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

		room.newTextEvent("Nothing happens. What would you expect happened from pushing some stupid chains?",event,textEvent);
		room.newOptionEvent("Inspect table", 5, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect window", 6, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect wall", 7, optionEvent, optionIDevent);
		room.newOptionEvent("Push chains", 10, optionEvent, optionIDevent);
		room.newOptionEvent("Pull chains", 13, optionEvent, optionIDevent);

		//Event 10, room 6
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

		room.newTextEvent("I already told you nothing happens? Now do something else.",event,textEvent);
		room.newOptionEvent("Inspect table", 5, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect window", 6, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect wall", 7, optionEvent, optionIDevent);
		room.newOptionEvent("Push chains", 11, optionEvent, optionIDevent);
		room.newOptionEvent("Pull chains", 13, optionEvent, optionIDevent);

		//Event 11, room 6
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

		room.newTextEvent("Hello? Are you blind? Nothing happens, just stop.",event,textEvent);
		room.newOptionEvent("Inspect table", 5, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect window", 6, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect wall", 7, optionEvent, optionIDevent);
		room.newOptionEvent("Push chains", 12, optionEvent, optionIDevent);
		room.newOptionEvent("Pull chains", 14, optionEvent, optionIDevent);

		//Event 12, room 6
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

		room.newTextEvent("If you're that damp, fine. I'll remove the damn option for you. Now choose something else.",event,textEvent);
		room.newOptionEvent("Inspect table", 5, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect window", 6, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect wall", 7, optionEvent, optionIDevent);
		room.newOptionEvent("Pull chains", 14, optionEvent, optionIDevent);

		//Event 13, room 6
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

		room.newTextEvent("A large cracking sound is coming from the wall. Some of the bricks fall from the wall, and one of them reveals a hidden lever.",event,textEvent);
		room.newOptionEvent("Pull lever", 16, optionEvent, optionIDevent);

		//Event 14, room 6
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

		room.newTextEvent("A large cracking sound is coming from the wall. Some of the bricks fall from the wall, and one of them reveals a hidden lever.",event,textEvent);
		room.newOptionEvent("Pull lever", 16, optionEvent, optionIDevent);
		room.newOptionEvent("Push lever", 15, optionEvent, optionIDevent);

		//Event 15, room 6
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

		room.newTextEvent("Oh no, not this pushing again. I'll just remove the option immediately.",event,textEvent);
		room.newOptionEvent("Pull lever", 16, optionEvent, optionIDevent);

		//Event 16, room 6
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

		room.newTextEvent("The lever is hard to pull, but when you got it, a hidden pathway was revealed behind the wall.",event,textEvent);
		room.newExit("Inspect path",7,0,event,exitEvent,exitEventID,exitEvents);

		//Room 7 Hidden entrance
		dung.newRoom();
		room		=	dung.newRoom;
		//Event 0, room 7
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

		room.newTextEvent("You enter the path. The surrounding walls look identical to the ones in the cell. It's lit from what looks like magical lamps. You should keep going.",event,textEvent);
		room.newExit("Keep going",8,0,event,exitEvent,exitEventID,exitEvents);

		//Room 8 Ancient hall
		dung.newRoom();
		room		=	dung.newRoom;
		//Event 0, room 8
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

		room.newTextEvent("The path opens up to a giant hall. The architecture here is different though. Lots of marble everywhere, and the magical lamps are haning from the ceiling.",event,textEvent);
		room.newOptionEvent("Walk further", 1, optionEvent, optionIDevent);

		//Event 1, room 8
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

		room.newTextEvent("You slowly walk towards the middle of the room. But out of nowhere a werewolf jumps at you!",event,textEvent);
		enemies = new int[1];
		enemies[0]	=	2;
		room.newFight(enemies, event, enemyEvents);
		room.newTextEvent("You have defeated the werewolf. It seems this is the right way to the goblins lair.",event,textEvent);
		room.newTextEvent("The hall has 4 exits you can take, with one being where you came from.",event,textEvent);
		room.newOptionEvent("Inspect right exit", 2, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect middle exit", 3, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect left exit", 4, optionEvent, optionIDevent);

		//Event 2, room 8 right exit
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

		room.newTextEvent("This way opens up to a smaller room. You sense something magical from the room.",event,textEvent);
		room.newExit("Walk further",9,0,event,exitEvent,exitEventID,exitEvents);

		//Event 3, room 8 middle exit
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

		room.newTextEvent("The room is protected by a magical barrier. I should check out the other rooms.",event,textEvent);
		room.newOptionEvent("Inspect right exit", 2, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect left exit", 4, optionEvent, optionIDevent);

		//Event 4, room 8 left exit
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

		room.newTextEvent("The room sends out a vibe of magic from it. I should check it out..",event,textEvent);
		room.newExit("Walk further",9,0,event,exitEvent,exitEventID,exitEvents);

		//Event 5, room 8 already been in one room
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

		room.newTextEvent("The orb begins to glow, and a pedestal near the middle lights up. You put the orb on the pedestal and the magical barrier seems to weaken.",event,textEvent);
		room.newOptionEvent("Inspect right exit", 2, optionEvent, optionIDevent);
		room.newOptionEvent("Inspect left exit", 4, optionEvent, optionIDevent);

		//Event 6, room 8 been in both rooms
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

		room.newTextEvent("You place the other orb on it's pedestal. The barrier disappears and you can now move forward.",event,textEvent);
		room.newExit("Enter",10,0,event,exitEvent,exitEventID,exitEvents);

		//Room 9 hall right & left exit
		dung.newRoom();
		room		=	dung.newRoom;
		//Event 0, room 9 first time
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

		room.newTextEvent("As soon as you enter the room, werewolves and a goblin attack you!",event,textEvent);
		enemies = new int[3];
		enemies[0]	=	2;
		enemies[1]	=	3;
		enemies[2]	=	0;
		room.newFight(enemies, event, enemyEvents);
		room.newTextEvent("The enemies drop dead. You inspect the area and find a magical orb on the goblin.",event,textEvent);
		room.newExit("Hall",8,5,event,exitEvent,exitEventID,exitEvents);

		//Event 1, room 9 second time left
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

		room.newTextEvent("The room is darker than the other. You see a pack of werewolves and attack them.",event,textEvent);
		enemies = new int[3];
		enemies[0]	=	2;
		enemies[1]	=	2;
		enemies[2]	=	4;
		room.newFight(enemies, event, enemyEvents);
		room.newTextEvent("The werewolves are dead. In the corner you see the other orb. You take it with you.",event,textEvent);
		room.newExit("Hall",8,6,event,exitEvent,exitEventID,exitEvents);

		//Room 10 summoning hall
		dung.newRoom();
		room		=	dung.newRoom;
		//Event 0, room 10
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

		room.newTextEvent("You enter the room and see a portal and 3 goblins summoning the werewolves.",event,textEvent);
		enemies = new int[3];
		enemies[0]	=	1;
		enemies[1]	=	1;
		enemies[2]	=	1;
		room.newFight(enemies, event, enemyEvents);
		room.newTextEvent("Dying Goblin: You idiot... You can not control them... Without us, the werewolves will follow no master.",event,textEvent);
		room.newTextEvent("Dying Goblin: We were the ones keeping them in control. Now they will ravage the land, and it's your fault...",event,textEvent);
		room.newTextEvent("The goblin dies, and the portal closes. Now they will no longer summon the beasts.",event,textEvent);
		room.newTextEvent("But before you can do anything, the room begins to collapse.",event,textEvent);
		room.newOptionEvent("Run for it", 1, optionEvent, optionIDevent);

		//Event 1, room 10
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

		room.newTextEvent("You see a small cave exit and run towards it. You should be able to exit through it.",event,textEvent);
		room.newExit("Run",11,0,event,exitEvent,exitEventID,exitEvents);

		//Room 11 endgame inn
		dung.newRoom();
		room		=	dung.newRoom;
		//Event 0, room 11 first time
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

		room.newTextEvent("You collapsed on the ground, but wake up in the inn.",event,textEvent);
		room.newTextEvent("You quickly spot old man Cain.",event,textEvent);
		room.newTextEvent("Old man Cain: How are you holding up there?",event,textEvent);
		room.newOptionEvent("What happened?", 1, optionEvent, optionIDevent);

		//Event 1, room 11
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

		room.newTextEvent("Old man Cain: I found you on the ground outside. Don't know where you came from though.",event,textEvent);
		room.newTextEvent("I defeated the goblins and closed the portal. The room collapsed and I ran out through some cave. Last I remember.",event,textEvent);
		room.newOptionEvent("Sit up", 2, optionEvent, optionIDevent);

		//Event 1, room 11
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

		room.newTextEvent("Old man Cain: I haven't seen the werewolves when I was outside, did they disappear?",event,textEvent);
		room.newTextEvent("No, they're still out there, but the goblins no longer control them.",event,textEvent);
		room.newTextEvent("Old man Cain: Great news! Well, I will be leaving. See you around later, friend.",event,textEvent);
		room.newTextEvent("Cain leaves. You stand up and can't forget what the goblin said about the werewolves. Probably not true, or what?",event,textEvent);
		room.newExit("Exit the inn",1,0,event,exitEvent,exitEventID,exitEvents);
	}
}