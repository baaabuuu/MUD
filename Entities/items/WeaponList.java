package items;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.google.gson.*;

//used to get the json files from package EnemyTypes
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
		for (WeaponType wep: weaponList)
		{
			wep.getInfo();
		}
	}
	/**
	 * Adds the items to our itemList
	 * @param filename, name of file
	 * @throws IOException an IO exception in case of missing file - SHOULD NOT OCCUR EVER
	 */
	private static void addNewItem(String filename) throws IOException
	{
		Gson gson = new GsonBuilder().create();
		
		InputStreamReader reader = new InputStreamReader(WeaponList.class.getResourceAsStream("/items/"+filename), "UTF-8");
		WeaponType[] weaponType = gson.fromJson(reader, WeaponType[].class);
		for(WeaponType weapon: weaponType)
		{
			//appends the ID to the item
			weapon.setID(weaponList.size());
			//adds it to the list.
			weaponList.add(weapon);
		}
	}
	
	private static void createWeapons() throws IOException
	{
		addNewItem("weapons.json");
	}
}