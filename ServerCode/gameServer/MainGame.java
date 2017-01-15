package gameServer;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;

import dungeons.Room;
import dungeons.World;
import items.Armor;
import items.ArmorGeneration;
import items.Item;
import items.WeaponGeneration;
import playerPackage.AccountHandling;
import playerPackage.Character;
import playerPackage.CharacterHandling;
import playerPackage.PlayerAccount;

public class MainGame extends Thread {
	
	Thread game;
	Sender transmitter;
	String data;
	ArrayBlockingQueue<String>	inbound;
	ArrayBlockingQueue<String>	outbound;
	static Random rand = new Random(); 
	
	
	public void run()
	{	
		PlayerAccount user	=	null;
		//login -- we need to ensure that we have a transmitter inbound queue
		while(inbound == null)
		{
			try{
				inbound		=	transmitter.inboundQueue;
			}
			catch (NullPointerException e)
			{
			}
		
		}
		//login -- we need to ensure that we have a transmitter outbound queue
		while(outbound == null)
		{
			try{
				outbound		=	transmitter.outbound;
			}
			catch (NullPointerException e)
			{
			}
			
		}

		boolean login	=	false;
		
		while(!login)
		{
			//check if there's anything sent to it - we need to do comparisons
			if (!inbound.isEmpty())
			{
				try {

					//Data should be :DAT: - login data followed by a username
					data = inbound.take();
					if (data.substring(0,5).equals(":DAT:"))
					{
						//login username
						data = data.substring(5,data.length());
						user = AccountHandling.loginUser(data);
						System.out.println(user.getPassword());
				        if (user != null)
				        {
				        	transmitter.sendDAT(user.getPassword());
				        	if (inbound.take().equals(":DAT:YES"))
				        			break;
				        }		
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
					System.out.println("COULD NOT TAKE LOGIN DATA");
				}
				
			}
		}
		System.out.println("SUCCESFULLY CONNTECTED TO CLIENT - INTIATING CHARACTERS");
		//LOGIN SUCCESFULL
		//load characters
		Character player = null;
		try {
			player = selectCharacter(user);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		//load player character
		
		transmitter.sendLAB(player.toDataStream());
		player.updateItems(transmitter);
		
		
		//save character update
		CharacterHandling.saveCharacters();
		AccountHandling.updateAccountDatabase();
		//save character update
		
		
		player.getInfo();
		
		
		//starts in dungeon 0 room 0
		// new world crashes?
		
		World gameWorld = new World();
		System.out.println(gameWorld);
		System.out.println(" game world " + gameWorld.dungeonList.size());
		// new world crashes?
		int dungID	=	0;
		int	roomID	=	0;
		Room playerRoom = gameWorld.dungeonList.get(dungID).rooms.get(0);
		String[]	dungeon = null;
		while(true)
		{
			while(true)
			{
				
				//Run event for the room
				//reason for split is due to the fact that it returns a value/
				// and also plays the content of the room
				try {
					dungeon	=	playerRoom.runEvent(dungID, player,transmitter).split("@");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ArrayIndexOutOfBoundsException e)
				{
					e.printStackTrace();
				}
				playerRoom.hasEntered	=	true;
				if (dungeon[0].equals("exit"))
				{
					break;
				}
				else if (dungeon[0].equals("newEvent"))
				{
					dungID	=	Integer.parseInt(dungeon[1]);
				}
			}
			//switch rooms
			playerRoom	=	gameWorld.dungeonList.get(dungID).rooms.get(Integer.parseInt(dungeon[1]));
			roomID	=	Integer.parseInt(dungeon[2]);
			// if dungID is equal to 1 or 0, check if room has been entered in the past.
			roomID	=	(playerRoom.hasEntered && (roomID == 1 || roomID == 0)) ? 1: 0;
		}
	}
	
	public Character selectCharacter(PlayerAccount user) throws InterruptedException {		
		int[] 	charID 		=	new int[6];
		String[] charName	=	new	String[6];
		Character[] chars 	=	new Character[6];
		int id = 0;
		String s = "";
		for (Character chara: CharacterHandling.characters)
		{
			System.out.println(chara.getName());
		}
		for (int i	=	0;	i<6	;i++)
		{
			try{
				charID[i]	=	Integer.parseInt(user.getCharacterIDS()[i]);
				chars[i]	=	CharacterHandling.characters.get(charID[i]);
				charName[i]	=	chars[i].getName() + " class:  " + chars[i].getCharClass() + " level: " + chars[i].getLevel();
				
			} catch (NumberFormatException e)
			{
				charID[i]	=	-1;
				charName[i]	=	"NEW CHARACTER";
			}
		}
		Character character = null;
		while(true)
		{
			transmitter.sendACT("Please select which character you'd like to play as:");
			transmitter.sendACT("You select your character by writing [1-6].");
			for (int i	=	0;	i<6	;i++)
			{
				transmitter.sendACT("    ["+(1+i)	+	"]    ---    "+charName[i] + ".");

			}
			s = inbound.take();
			try {
				id = Integer.parseInt(s.substring(5,s.length()));
			} catch (NumberFormatException e) {
				e.printStackTrace();
			}
			
			if (id>0 && id <7 && s.substring(0,5).equals(":ACT:"))
			{
				if (!charName[id-1].equals("NEW CHARACTER"))
				{
					transmitter.sendACT("Do you wish to play as: " + charName[id-1] + ".");
				}
				else
				{
					transmitter.sendACT("Do you wish to create a new character?");
				}
				transmitter.sendACT("Please type: <Y> for yes and <N> for no.");
				s	=	inbound.take();
				data	=	s.substring(5,s.length()).toLowerCase();
				
				if ((data.equals("<y>") || data.equals("y")) && s.substring(0,5).equals(":ACT:"))
				{
					character	=	chars[id-1];
					//character selected
					break;
				}
				else
				if ((data.equals("<n>") || data.equals("n")) && s.substring(0,5).equals(":ACT:"))
				{
					//character NOT selected - player has an option
					//to delete the character if it exists
					if (!charName[id-1].equals("NEW CHARACTER"))
					{
						transmitter.sendACT("Do you wish to delete: " + charName[id-1]);
						transmitter.sendACT("Please type: <Y> for yes and <N> for no.");
						s	=	inbound.take();
						data	=	s.substring(5,s.length()).toLowerCase();
	
						if ((data.equals("<y>") || data.equals("y")) && s.substring(0,5).equals(":ACT:"))
						{
							transmitter.sendACT("ARE YOU SURE!");
							transmitter.sendACT("Please type: <Y> for yes and <N> for no.");
							
							s	=	inbound.take();
							data	=	s.substring(5,s.length()).toLowerCase();
							
							if ((data.equals("<y>") || data.equals("y")) && s.substring(0,5).equals(":ACT:"))
							{
								//TODO delete character function - not needed for alpha test
							}
						}
					}
				}
			}
			else
			{
				transmitter.sendACT("Please type in a whole number between 1 and 6 only.");
			}
		}
		
		//Create new character
		if (charName[id-1].equals("NEW CHARACTER"))
		{
			String name;
			String charClass;
			while (true)
			{
				transmitter.sendACT("Please type the desired character name:");
				
				s		 =	inbound.take();
				name	 =	s.substring(5,s.length());
				if (s.substring(0,5).equals(":ACT:"))
				{
					transmitter.sendACT("Will your character be named: " + name);
					transmitter.sendACT("Please type: <Y> for yes and <N> for no.");
					
					s	=	inbound.take();
					data	=	s.substring(5,s.length()).toLowerCase();
					
					if ((data.equals("<y>") || data.equals("y")) && s.substring(0,5).equals(":ACT:"))
						break;
				}
			}
			while (true)
			{
				transmitter.sendACT("Please select one of the follow classes by writing <className>");
				for (int i=0;i<CharacterHandling.characterClasses.length;i++)
				{
					transmitter.sendACT("    --- " + CharacterHandling.characterClasses[i]);
				}
				
				s			=	inbound.take();
				charClass	=	s.substring(5,s.length()).toLowerCase();
				
				if (charClass.startsWith("<") && charClass.endsWith(">"))
				{
					charClass = charClass.substring(1,charClass.length()-1);
				}
				charClass = charClass.substring(0, 1).toUpperCase() + charClass.substring(1).toLowerCase();
				
				if (Arrays.asList(CharacterHandling.characterClasses).contains(charClass) && s.substring(0,5).equals(":ACT:"))
				{
					transmitter.sendACT("Are you certain you wish to play as a " + charClass + "?");
					transmitter.sendACT("Please type: <Y> for yes and <N> for no.");
					s	=	inbound.take();
					data	=	s.substring(5,s.length()).toLowerCase();
					
					if ((data.equals("<y>") || data.equals("y")) && s.substring(0,5).equals(":ACT:"))
						break;	
				}
							
			}
			character = CharacterHandling.createNewCharacter(charClass, name, Integer.parseInt(user.getID()));
			user.addCharacter(id-1, character.getCharacterID());
		}
		return character;
	}
	
	
	
	
	
	
	public void start()
	{
		if (game == null)
		{
			game = new Thread(this, "gameName");
			game.start();
		}
	}

	public void createSender(String threadName, Socket sAccept, int threadNumber)
	{
		transmitter = new Sender(threadName, sAccept);
		System.out.println("A client has connected to the server. Creating thread" + threadName);
		transmitter.start();	//Allocates threads to clients.
		
	}

}
