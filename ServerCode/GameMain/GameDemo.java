package GameMain;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

import items.ArmorList;
import items.WeaponList;
import metaData.MetaData;
import npc.EnemyList;
import playerPackage.AccountHandling;
import playerPackage.CharacterHandling;
import playerPackage.PlayerAccount;

public class GameDemo
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
		System.out.println("Thank you for logging in succesffully:");
		
		
		//selectCharacter();
		
		
		

        input.close();
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
