package playerPackage;


import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.MalformedJsonException;


public class AccountHandling {
	
	//never changed - only has things added to it - 
	//updated whenever the player adds new characters - though
	private static ArrayList<PlayerAccount> createdAccounts = new ArrayList<PlayerAccount>();
	//actively has accounts added and removed
	private static ArrayList<PlayerAccount> activeAccounts = new ArrayList<PlayerAccount>();
	
	public static void main(String[] args) throws UnsupportedEncodingException, MalformedJsonException
	{		
		createNewAccount("TestAccount","workingPassword","Blib@blob.dtu.dk");
		createNewAccount("a","non","Balib@blob.dtu.dk");
		createNewAccount("TestAbccount","workingPassword","hohn");
	}
	
	
	public static int createNewAccount(String username, String password, String email)
	{
		if (checkEmail(email))
		{
			if (checkUsername(username))
			{
				PlayerAccount	newPlayer 	= 	new PlayerAccount(username,password,email);
				newPlayer.setID(createdAccounts.size());
				createdAccounts.add(newPlayer);
				updateAccountDatabase();
			} 
			else
			{
				//username is already in use
				return 2;
			}
		}
		else
		{
			//email is already in use
			return 1;
		}
		//successfully created a new account
		return 0;
	}
	
	//called for active players
	//used when players add a new character or deletes an old one
	public static void updateCreatedAccounts()
	{
		for (int i = 0;i<activeAccounts.size();i++)
		{
			for (int a = 0; a<createdAccounts.size();a++)
			{
				if (activeAccounts.get(i).getID().equals(createdAccounts.get(a).getID()))
				{
					createdAccounts.set(a, activeAccounts.get(i));
					break;
				}
			}
		}
	}
	
	public static boolean loadDatabase()
	{
		InputStreamReader reader;
		try {
			reader = new InputStreamReader(AccountHandling.class.getResourceAsStream("/playerPackage/PlayerAccount.json"), "UTF-8");
			Gson gson = new GsonBuilder().create();
			PlayerAccount[] accounts = gson.fromJson(reader, PlayerAccount[].class);
			for(PlayerAccount saved: accounts)
			{
				createdAccounts.add(saved);
			}
			return true;
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	
	/**
	 * Updates the PlayerAccount.json file - 
	 * @return TRUE = has updated account database, FALSE = HAS NOT UPDATED ACCOUNT DATABASE
	 */
	public static boolean updateAccountDatabase() {		
		PrintWriter writer;
		try {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			String s = gson.toJson(createdAccounts);
			writer = new PrintWriter("Entities/playerPackage/PlayerAccount.json", "UTF-8");
			writer.println(s);
			writer.close();	
			return true;
		} catch (FileNotFoundException e)
		{
			System.out.println("COULD NOT WRITE!");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{
			System.out.println("COULD NOT WRITE!");
			e.printStackTrace();
		}
		return false;		
	}
	
	public AccountHandling() throws UnsupportedEncodingException{}
	
	/**
	 * adds a player to the active player list
	 * @param acc - a player account
	 */
	public void addToActive(PlayerAccount acc){
		activeAccounts.add(acc);
	}
	/**
	 * removes a player from the active player list
	 * @param acc - a player account
	 */
	public void removeToActive(PlayerAccount acc){
		activeAccounts.remove(acc);
	}

	private static boolean checkUsername(String name) {
		// TODO Auto-generated method stub
		name	=	name.toUpperCase();
		for (int i=0;i<createdAccounts.size();i++)
		{
			if (createdAccounts.get(i).getName().toUpperCase().equals(name))
				return false;
		}
		return true;
	}

	private static boolean checkEmail(String email) {
		email	=	email.toUpperCase();
		for (int i = 0; i < createdAccounts.size(); i++)
		{
			if (createdAccounts.get(i).getEmail().toUpperCase().equals(email))
				return false;
		}
		return true;
	}
}