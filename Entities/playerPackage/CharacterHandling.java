package playerPackage;

import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.stream.MalformedJsonException;

import items.Armor;
import items.Item;
import items.Weapon;


public class CharacterHandling {
	//contains the save objects
	public static ArrayList<CharacterType> createdCharacters = new ArrayList<CharacterType>();
	//contains characters
	public static ArrayList<Character> characters = new ArrayList<Character>();
	//add classes to this array, so that other parts of the game can referer to it
	//Note, they also need to be added to createNewCharacter
	public static String[] characterClasses	=	{"Warrior"};
	
	
	//player characters demoing
	public static void main(String[] args) throws UnsupportedEncodingException, MalformedJsonException
	{
		loadCharacters();
		createNewCharacter("Warrior","TempWarrior1",1);
		createNewCharacter("Warrior","TempWarrior6",10);
		createNewCharacter("Warrior","TempWarrior3",12);
		createNewCharacter("Warrior","TempWarrior4",1);
		createNewCharacter("Warrior","TempWarrior5",1);
		saveCharacters();
	}
	
	
	public CharacterHandling() throws UnsupportedEncodingException{}	
	
	public static Character createNewCharacter(String charClass,String name, int playerID)
	{
		Character character = null;
		if (charClass.equals("Warrior"))
		{
			//create a warrior
			character = new WarriorClass(name, 1, 1, playerID);
		}
		try {
			if (!character.equals(null))
			{
				 character.setID(createdCharacters.size());
				 CharacterType saveObject = initalizeSaveObject(character);
				 createdCharacters.add(saveObject);
				 characters.add(character);
				 return character;
			}
			else
			{
				System.out.println("COULD NOT CREATE CHARACTER");
			}
		} catch (NullPointerException e)
		{
			System.out.println("Could not create a character");
		}
		
		return null;
	}
	
	/**
	 * takes every character in characters converts them to save objects and then saves the game
	 * @return
	 */
	public static boolean loadCharacters()
	{
		InputStreamReader reader;
		Character chara;
		try {
			characters.clear();
			reader = new InputStreamReader(AccountHandling.class.getResourceAsStream("/playerPackage/Characters.json"), "UTF-8");
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			CharacterType[] savedCharacters = gson.fromJson(reader, CharacterType[].class);
			for(CharacterType saved: savedCharacters)
			{
				chara =	convertToCharacter(saved);
				if (!characters.contains(chara))
					characters.add(convertToCharacter(saved));
			}
			return true;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
			return false;
		}
	}
		
	public static Character convertToCharacter(CharacterType saved) {
		//load values
		String name				=	saved.name;
		String charClass 		= 	saved.charClass;
		int playerID			=	Integer.parseInt(saved.playerID);
		int level				=	Integer.parseInt(saved.level);
		int exps					=	Integer.parseInt(saved.exp);
		//reads values
		
		//set values
		Character loadedChar	=	createNewCharacter(charClass,name,playerID);
		loadedChar.exp			=	exps;
		
		
		loadedChar.setLevel(level);
		loadedChar.equipment	=	loadItems(saved.equipment);
		loadedChar.inventory	=	loadItems(saved.inventory);

		return loadedChar;
	}

	private static ArrayList<Item> loadItems(String[] items) {
		ArrayList<Item> loadedItems = new ArrayList<Item>();
		for (String item: items)
		{
			String[] newItem = item.split("@");
			if (newItem[0].equals("armor"))
			{
				//load values
				String name 		=	newItem[1];
				String slot			=	newItem[2];
				int rarity			=	Integer.parseInt(newItem[3]);
				int level			=	Integer.parseInt(newItem[4]);
				int cost			=	Integer.parseInt(newItem[5]);
				String	desc		=	newItem[6];
				String	mod			=	newItem[7];
				int mDurability		=	Integer.parseInt(newItem[8]);
				int cDurability		=	Integer.parseInt(newItem[9]);
				int	evaMod			=	Integer.parseInt(newItem[10]);
				int reduc			=	Integer.parseInt(newItem[11]);
				int spellReduc		=	Integer.parseInt(newItem[12]);
				String armClass		=	newItem[13];
				int[]	additionalStats;
				if (item.length()>=15)
				{
					additionalStats	=	new int[item.length()-13];
					for (int i = 14; i<item.length();i++)
					{
						additionalStats[i-14]=Integer.parseInt(newItem[i]);
					}
				}
				else
				{
					additionalStats = new int[1];
				}
				loadedItems.add(new Armor(name, slot, rarity, level, cost, desc, cDurability,
						mDurability, mod, evaMod, reduc, armClass, additionalStats, spellReduc));
			}
			else if (newItem[0].equals("weapon"))
			{
				//load values
				String name 		=	newItem[1];
				String slot			=	newItem[2];
				int rarity			=	Integer.parseInt(newItem[3]);
				int level			=	Integer.parseInt(newItem[4]);
				int cost			=	Integer.parseInt(newItem[5]);
				String	desc		=	newItem[6];
				String	mod			=	newItem[7];
				int mDurability		=	Integer.parseInt(newItem[8]);
				int cDurability		=	Integer.parseInt(newItem[9]);
				int	accMod			=	Integer.parseInt(newItem[10]);
				int critMod			=	Integer.parseInt(newItem[11]);
				int[]	dmg			=	{Integer.parseInt(newItem[12]),
										Integer.parseInt(newItem[13])};
				String attackDesc	=	newItem[14];
				String wepClass		=	newItem[15];
				int[]	additionalStats;
				if (item.length()>=15)
				{
					additionalStats	=	new int[item.length()-13];
					for (int i = 14; i<item.length();i++)
					{
						additionalStats[i-14]=Integer.parseInt(newItem[i]);
					}
				}
				else
				{
					additionalStats = new int[1];
				}
				loadedItems.add(new Weapon(name, slot, rarity, level, cost, desc, mod, 
						accMod, critMod, dmg, attackDesc,wepClass,mDurability, cDurability,
						additionalStats));
			}
			
		}
		return loadedItems;
	}

	public static boolean updateCharacterSaves()
	{
		ArrayList<CharacterType> tempList = new ArrayList<CharacterType>();
		for (int i = 0;i<characters.size();i++)
		{
			for (int a = 0; a<createdCharacters.size();a++)
			{
				if (characters.get(i).getCharacterID().equals(createdCharacters.get(a).characterID))
				{
					tempList.add(initalizeSaveObject(characters.get(i)));
					createdCharacters.remove(a);
					break;
				}
			}
		}
		for (CharacterType character: tempList)
		{
			createdCharacters.add(character);
		}
		return true;
	}
	
	public static void saveCharacters() {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		updateCharacterSaves();
		String s = gson.toJson(createdCharacters);
		PrintWriter writer;
		try {
			writer = new PrintWriter("Entities/playerPackage/Characters.json", "UTF-8");
			writer.println(s);
			writer.close();	
		} catch (FileNotFoundException e)
		{
			System.out.println("COULD NOT WRITE!");
			e.printStackTrace();
		} catch (UnsupportedEncodingException e)
		{
			System.out.println("COULD NOT WRITE!");
			e.printStackTrace();
		}
	}

	/**
	 * This function takes a Character and returns a Character type in its place - a savable object.
	 * The Character Type is then used as a save "object"
	 * @param character
	 * @return
	 */
	private static CharacterType initalizeSaveObject(Character character) {
		CharacterType saveObject = new CharacterType();
		saveObject.name=character.getName();
		saveObject.charClass=character.getCharClass();
		saveObject.level		=	String.valueOf(character.getLevel());
		saveObject.exp			=	String.valueOf(character.getExp());
		saveObject.stats		=	new String[character.getStats().size()];
		saveObject.characterID	=	String.valueOf(character.characterID);
		//wat
		saveObject.playerID		=	String.valueOf(character.getPlayerID());
		//wat
		for (int i=0;i<character.getStats().size();i++)
		{
			saveObject.stats[i]=String.valueOf(character.getStat(i));
		}
		saveObject.inventory	=	new String[character.getInventory().size()];
		for (int i=0;i<character.getInventory().size();i++)
		{
			saveObject.inventory[i]	=	String.valueOf(character.getInventory().get(i).toSaveString());
		}
		saveObject.equipment	=	new String[character.getEquipment().size()];
		for (int i=0;i<character.getEquipment().size();i++)
		{
			saveObject.equipment[i]	=	String.valueOf(character.getEquipment().get(i).toSaveString());
		}
		return saveObject;
	}
	
	
	/**
	 * adds our createdCharater list
	 * @throws UnsupportedEncodingException
	 */
	public void addSavedCharacters() throws UnsupportedEncodingException
	{
		Gson gson = new GsonBuilder().create();
		InputStreamReader reader = new InputStreamReader(AccountHandling.class.getResourceAsStream("playerPackage/Characters.json"), "UTF-8");
		CharacterType[] characterTypes = gson.fromJson(reader, CharacterType[].class);
		for(CharacterType character: characterTypes)
		{
			addToCreated(character);
		}
	}
	
	/**
	 * adds a characterType to the active player list
	 * @param acc - a player account
	 */
	public static void addToCreated(CharacterType character){
		createdCharacters.add(character);
	}
}