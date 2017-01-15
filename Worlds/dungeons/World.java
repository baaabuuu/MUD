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
	public static void createTestTown(Dungeon dung)
	{
		Random rand = new Random(); 
		//Room 0
		dung.newRoom();
		Room 				room		=	dung.newRoom;
		Room				playerRoom	=	room;
		
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
		
		shopText.add("Shopkeeper: Wha, what ar yu doin' in mah shop?");
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
		room.newExit("Moonbright Square",1,0,event,exitEvent,exitEventID,exitEvents);

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
			room.newExit("Moonbright Square",1,0,event,exitEvent,exitEventID,exitEvents);
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
		room.newExit("Moonbright Square",1,0,event,exitEvent,exitEventID,exitEvents);
		
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
		room.newTextEvent("Old man: Dark times these days... Where are you from stranger?", event, textEvent);
		room.newOptionEvent("Hello old man, I'm from Darkshire.", 2, optionEvent, optionIDevent);

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
		room.newOptionEvent("Okay, thank you.", 4, optionEvent, optionIDevent);
		
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
		room.newOptionEvent("I hear you have a problem with werewolves around these parts?", 2, optionEvent, optionIDevent);
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
		room.newOptionEvent("Okay, then I will go and check him out.", 3, optionEvent, optionIDevent);
		room.newOptionEvent("Say nothing", 2, optionEvent, optionIDevent);
		
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
		room.newTextEvent("Steelchin: Nå, du er vist lidt den stille type? Hvis du vil hjælpe os med varulvene, så kan du tage ned i fangehullet. Vi har fået fanget en varulv, som måske kan fortælle os lidt om hvor de er kommet fra.",event,textEvent);
		room.newOptionEvent("Okay, thank you.", 3, optionEvent, optionIDevent);
		room.newOptionEvent("Say nothing", 3, optionEvent, optionIDevent);
		
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
		room.newExit("Market",1,0,event,exitEvent,exitEventID,exitEvents);
		room.newExit("Prison",3,0,event,exitEvent,exitEventID,exitEvents);

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
		room.newOptionEvent("I'm fine. Thank you.", 2, optionEvent, optionIDevent);

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
		room.newOptionEvent("Browse items", 3, optionEvent, optionIDevent);

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
		room.newOptionEvent("Sure.", 3, optionEvent, optionIDevent);

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
		room.newOptionEvent("Sounds great.", 2, optionEvent, optionIDevent);

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
		room.newOptionEvent("Where do you come from. What is it you want?", 2, optionEvent, optionIDevent);

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
						
		room.newTextEvent("There's nothing else here you can do. Unless you would like to go to jail?",event,textEvent);
		room.newExit("Go to jail",5,0,event,exitEvent,exitEventID,exitEvents);
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
		room.newTextEvent("Werewolf: I only know the goblins have a hidden lair under this town. The closest you can get is this prison i guess?",event,textEvent);
		room.newOptionEvent("Thank you creature. I will find the source of this.", 3, optionEvent, optionIDevent);

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
						
		room.newTextEvent("The werewolf walks into the darkness of the cell and is nowhere to be seen.",event,textEvent);
		room.newOptionEvent("Leave his cell.", 1, optionEvent, optionIDevent);

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

		room.newTextEvent("You enter the cell. The door shuts behind you and is now locked.",event,textEvent);
		room.newOptionEvent("(Cry for help)", 2, optionEvent, optionIDevent);
		room.newOptionEvent("I must find a way out.", 3, optionEvent, optionIDevent);

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

		room.newTextEvent("You're stuck in the jail cell again. What now?",event,textEvent);

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
			room.newOptionEvent("Thank you so much, m'lord.", 3, optionEvent, optionIDevent);
		} else {
			room.newTextEvent("No one hears your cries.",event,textEvent);
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

		room.newTextEvent("The cell is dark with a single candle on a wooden table, which is placed in the corner.",event,textEvent);
		room.newTextEvent("The floor, not made of anything other than the cold ground, the building was laid on.",event,textEvent);

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
		room.newTextEvent("The floor, not made of anything other than the cold ground, the building was laid on.",event,textEvent);

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

		if (rand.nextInt(100) > 70) {
			room.newTextEvent("Guard: What are you doing here?",event,textEvent);
			room.newTextEvent("Guard: Come on, I'll let you out.",event,textEvent);
			room.newOptionEvent("Thank you so much, m'lord.", 3, optionEvent, optionIDevent);
		} else {
			room.newTextEvent("The floor, not made of anything other than the cold ground, the building was laid on.",event,textEvent);
		}
		
		
	}
	
}
