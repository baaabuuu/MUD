package playerPackage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.MalformedJsonException;



public class Accounts {
	private static ArrayList<PlayerAccount> createdAccounts = new ArrayList<PlayerAccount>();
	private static ArrayList<PlayerAccount> activeAccounts = new ArrayList<PlayerAccount>();
	
	
	public Accounts() throws UnsupportedEncodingException{}

	
	public static void main(String[] args) throws UnsupportedEncodingException, MalformedJsonException
	{
		
		Gson gson = new GsonBuilder().create();
		InputStreamReader reader = new InputStreamReader(Accounts.class.getResourceAsStream("/playerPackage/PlayerAccount.json"), "UTF-8");
		PlayerAccount[] accounts = gson.fromJson(reader, PlayerAccount[].class);
		
		for(PlayerAccount account: accounts)
		{
			//adds it to the list.
			createdAccounts.add(account);
		}
		
		
		createNewAccount("TestAccount","workingPassword","Blib@blob.dtu.dk");
		createNewAccount("a","non","Balib@blob.dtu.dk");
		createNewAccount("TestAbccount","workingPassword","hohn");
	}
	
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
	
	/**
	 * adds a player to the active player list
	 * @param acc - a player account
	 */
	public static void addToCreated(PlayerAccount acc){
		createdAccounts.add(acc);
	}

	
	public static boolean createNewAccount(String name, String pass, String email)
	{
		//needs to have checks for if the email exists - which is going to be from MetaData.java
		//needs a check for username as well. in order to check they aren't duplicates
		if (checkEmail(email))
		{	
			if (checkUser(name))
			{
				PlayerAccount	newPlayer 	= 	new PlayerAccount(name,pass,email);	
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				// 1. Java object to JSON, and save into a file
				
				try(FileWriter writer = new FileWriter("Entities/playerPackage/PlayerAccount.json", true);
					    BufferedWriter bufferWriter = new BufferedWriter(writer);
					    PrintWriter out = new PrintWriter(bufferWriter))
				{
						
						try (BufferedReader br = new BufferedReader(new FileReader("Entities/playerPackage/PlayerAccount.json")))
						{
						    String line;
						    ArrayList<String> words = new ArrayList<String>();
						    while ((line = br.readLine()) != null) {
						       words.add(line);
						    }
						    words.remove(words.size()-1);
						    String tempWord	=	words.get(words.size()-1);
						    tempWord = tempWord + ",";
						    words.remove(words.size()-1);
						    words.add(tempWord);
						    try(FileWriter writer2 = new FileWriter("Entities/playerPackage/PlayerAccount.json", false);
								    BufferedWriter bufferWriter2 = new BufferedWriter(writer2);
								    PrintWriter out2 = new PrintWriter(bufferWriter2))
							{
							    for (String word : words) {
									out2.println(word);
								} // our for statement
							    //adds the newPlayer to the PlayerAccount.json file
							    addToCreated(newPlayer);
							    out2.println(gson.toJson(newPlayer));
							    out2.println("]");
							    out2.close();
							    return true;
							}
						} catch (FileNotFoundException e)
						{
							System.out.println("Could not find the player Data Files!");
							
						}
				} catch (IOException e) {
					System.out.print("could not find the playerDatafiles");
					    //exception handling left as an exercise for the reader
				}
			}
			else
			{
				System.out.println("That username is already in use.");
			}
		}
		else
		{
			System.out.println("That email is already in use.");
		}
		return false;
	}
	

	private static boolean checkUser(String name) {
		// TODO Auto-generated method stub
		name	=	name.toUpperCase();
		for (int i=0;i<createdAccounts.size();i++)
		{
			if (createdAccounts.get(i).accountName.toUpperCase().equals(name))
				return false;
		}
		return true;
	}

	private static boolean checkEmail(String email) {
		email	=	email.toUpperCase();
		for (int i = 0; i < createdAccounts.size(); i++)
		{
			if (createdAccounts.get(i).email.toUpperCase().equals(email))
				return false;
		}
		return true;
	}
}
