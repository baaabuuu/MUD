


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Scanner;

import GameMain.BCrypt;

import items.ArmorList;

import items.WeaponList;
import metaData.MetaData;
import npc.EnemyList;
import playerPackage.AccountHandling;
import playerPackage.CharacterHandling;
import playerPackage.PlayerAccount;
import playerPackage.Character;

public class testClass
{
	
	public static void main(String[] args) throws UnsupportedEncodingException
	{
		//load required data first
		//this needs to be done on server startup
		//load up our databases
		intiateSeverData();
		String gameName = MetaData.gameName;
		String gameVersion	=	MetaData.gameVersion;
		
		//we'll be using a scanner for input in the game demo
		Scanner input = new Scanner(System.in);
		
		//playerLogin and intro!
		System.out.println("Welcome to " + gameName + " v" + gameVersion);
        
		System.out.println("Please login by first typing your username and then your password on the next line.");
		PlayerAccount user = login(input);
		System.out.println("Thank you for logging in " + user.getName() + ".");
		
		//select character
		Character userChar = selectCharacter(user,input);
		userChar.getInfo();
		AccountHandling.updateAccountDatabase();
			

        input.close();
	}	
	
	/**
	 * Select a character for the player to use.
	 * When the player selects a new character - which is an option - 
	 * @param user
	 * @param input
	 * @return
	 */
	public static Character selectCharacter(PlayerAccount user, Scanner input) {
		// TODO Auto-generated method stub
		int[] 	charID 		=	new int[6];
		String[] charName	=	new	String[6];
		Character[] chars 	=	new Character[6];
		int id;
		String 	command2;
		String	deleteCommand	=	"n";
		for (int i	=	0;	i<6	;i++)
		{
			try{
				charID[i]	=	Integer.parseInt(user.getCharacterIDS()[i]);
				chars[i]	=	CharacterHandling.characters.get(charID[i]);
				charName[i]	=	chars[i].getName() + "class:  " + chars[i].getCharClass() + " level: " + chars[i].getLevel();
				
			} catch (NumberFormatException e)
			{
				charID[i]	=	-1;
				charName[i]	=	"NEW CHARACTER";
			}
		}
		Character character = null;
		while(true)
		{
			System.out.println("Please select which character you'd like to play as:");
			for (int i	=	0;	i<5	;i++)
			{
				System.out.println("    ["+(1+i)	+	"]    ---    "+charName[i] + ".");
			}
							
			id = input.nextInt();
			input.nextLine();
			System.out.println(id);
			if (id>0 && id <6)
			{
				if (!charName[id-1].equals("NEW CHARACTER"))
				{
					System.out.println("Do you wish to play as: " + charName[id-1] + ".");
				}
				else
				{
					System.out.println("Do you wish to create a new character?");
				}
				System.out.println("Please type: <Y> for yes and <N> for no.");
				command2	=	input.nextLine().toLowerCase();
				if (command2.equals("<y>") || command2.equals("y"))
				{
					//character selected
					break;
				}
				else
				if (command2.equals("<n>") || command2.equals("n"))
				{
					//character NOT selected - player has an option
					//to delete the character if it exists
					if (!charName[id-1].equals("NEW CHARACTER"))
					{
						System.out.println("Do you wish to delete: " + charName[id-1]);
						System.out.println("Please type: <Y> for yes and <N> for no.");
						deleteCommand	=	input.nextLine().toLowerCase();
						if (deleteCommand.equals("y") || deleteCommand.equals("<y>"))
						{
							System.out.println("ARE YOU SURE!");
							System.out.println("Please type: <Y> for yes and <N> for no.");
							deleteCommand	=	input.nextLine().toLowerCase();
							if (deleteCommand.equals("y") || deleteCommand.equals("<y>"))
							{
								//TODO delete character function
							}
								deleteCommand	=	"n";
						}
					}
				}
			}
			else
			{
				System.out.println("Please type in a whole number between 1 and 5 only.");
			}
		}
		//Create new character
		if (charName[id-1].equals("NEW CHARACTER"))
		{
			String name;
			String accept;
			String charClass;
			while (true)
			{
				System.out.println("Please type the wished character name:");
				name	=	input.nextLine();
				System.out.println("Will your character be named: " + name);
				System.out.println("Please type: <Y> for yes and <N> for no.");
				accept	=	input.nextLine().toLowerCase();
				if (accept.equals("y") || accept.equals("<y>"))
					break;				
			}
			accept	=	"n";
			while (true)
			{
				System.out.println("Please select one of the follow classes by writing <className>");
				for (int i=0;i<CharacterHandling.characterClasses.length;i++)
				{
					System.out.println("    --- " + CharacterHandling.characterClasses[i]);
				}
				charClass	=	input.nextLine().toLowerCase();
				if (charClass.startsWith("<") && charClass.endsWith(">"))
				{
					charClass = charClass.substring(1,charClass.length()-1);
				}
				charClass = charClass.substring(0, 1).toUpperCase() + charClass.substring(1).toLowerCase();
				//debug
				System.out.println(charClass);
				//debug
				if (Arrays.asList(CharacterHandling.characterClasses).contains(charClass))
				{
					System.out.println("Are you certain you wish to play as a " + charClass + "?");
					System.out.println("Please type: <Y> for yes and <N> for no.");
					accept	=	input.nextLine().toLowerCase();
					if (accept.equals("y") || accept.equals("<y>"))
						break;	
				}
							
			}
			character = CharacterHandling.createNewCharacter(charClass, name, Integer.parseInt(user.getID()));
			user.addCharacter(id-1, character.getCharacterID());
		}
		return character;
	}



	//this data has to be sent to the user! - as in the password and username
	//We do not want the user to send unencryptedStrings
	//Likewise BCrypt should be added to the client library - hashpasswords are alright to send.
	public static PlayerAccount login(Scanner input)
	{
		String username;
		String password;
		PlayerAccount user = null;
		while(true)
		{
	        username = input.nextLine();
	        user = AccountHandling.loginUser(username);
	        if (!user.equals(null))
	        {
		        password	= 	input.nextLine();
		        // TODO Password encryption here - we need to encrypt it!
		        
		        if (BCrypt.checkpw(password,user.getPassword()))
		        {
		        	AccountHandling.login(user);
		        	break;
		        }
		        else
		        {
		        	System.out.println("INVALID PASSWORD!");
		        }
	        }
	        else
	        {
	        	System.out.println("INVALID USERNAME!");
	        }
		}
		return user;
		
	}
	
	
	
	//sets up every system the server uses for data.
	public static void intiateSeverData()
	{
		if (AccountHandling.loadDatabase())
		{
			System.out.println("STEP 1: ACCOUNT LOADING SUCCESFULL");
		} else
		{
			System.out.println("STEP 1: FAILED SHUTTING DOWN");
			System.exit(0);
		}
		if (CharacterHandling.loadCharacters())
		{
			System.out.println("STEP 2: CHARACTER LOADING SUCCESFULL");
		} else
		{
			System.out.println("STEP 2: FAILED SHUTTING DOWN");
			System.exit(0);
		}
		try {
			EnemyList.createMobs();
			System.out.println("STEP 3: NPC DATA LOADING SUCCESFULL");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("STEP 3: FAILED SHUTTING DOWN");
			System.exit(0);
		}
		try {
			ArmorList.createArmors();
			System.out.println("STEP 4: ARMOR DATA LOADING SUCCESFULL");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("STEP 4: FAILED SHUTTING DOWN");
			System.exit(0);
		}
		try {
			WeaponList.createWeapons();
			System.out.println("STEP 5: WEAPON DATA LOADING SUCCESFULL");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("STEP 5: FAILED SHUTTING DOWN");
			System.exit(0);
		}
		System.out.println("DATA HAS SUCCESFULLY LOADED - SERVER IS READY TO DEPLOY");

	}
	
	
}