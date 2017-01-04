package items;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.google.gson.*;
//used to get the json files from package EnemyTypes
import enemyTypes.fileClass;

public class WeaponList
{
	//this array contains all the creep types.
	protected static ArrayList<WeaponType> weaponList;
	
	public ArrayList<WeaponType> getWeaponList()
	{
		return weaponList;
	}
	
	/**
	 * Used to generate weapon types and then adds them to an array
	 * @param filename, the file name from enemyTypes
	 * @throws IOException 
	 */
	public WeaponList() throws IOException
	{
		weaponList = new ArrayList<WeaponType>();
		createWeapons();
	}
	/**
	 * Adds the items to our itemList
	 * @param filename, name of file
	 * @throws IOException an IO exception in case of missing file - SHOULD NOT OCCUR EVER
	 */
	private static void addNewItem(String filename) throws IOException
	{
		Gson gson = new GsonBuilder().create();
		
		InputStreamReader reader = new InputStreamReader(Item.class.getResourceAsStream("/items/"+filename), "UTF-8");
		WeaponType weaponType = gson.fromJson(reader, WeaponType.class);
		
		//weaponType.getInfo();
		
		//appends an ID number to the list, esp useful when using different copies of same
		//weapon
		weaponType.setID(weaponList.size());
		weaponList.add(weaponType);
	}
	
	private void createWeapons() throws IOException
	{
		addNewItem("GoblinEngineeredRock.json");
		addNewItem("GoblinHammer.json");
		addNewItem("GoblinAxe.json");
		addNewItem("GoblinButterKnife.json");
	}
}