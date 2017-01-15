package GameMain;

import java.io.IOException;

import gameServer.ChatProducer;
import gameServer.Listener;
import items.ArmorList;
import items.WeaponList;
import npc.EnemyList;
import playerPackage.AccountHandling;
import playerPackage.CharacterHandling;

public class ServerStart {
	private static int port = 8080;
	public static ChatProducer chat;
	
	public static void main(String argv[]) {
		intiateSeverData();		
		//listener waits for incoming connections, then hands them off to the sender.
		Listener listener = new Listener("mainListener", port);
		listener.start();
		chat 	= new ChatProducer();
		chat.start();
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
