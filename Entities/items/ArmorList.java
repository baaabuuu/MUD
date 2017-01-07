package items;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.google.gson.*;

//used to get the json files from package EnemyTypes
public class ArmorList
{
	//this array contains all the creep types.
	public static ArrayList<ArmorType> armorList = new ArrayList<ArmorType>();
	
	public ArrayList<ArmorType> getArmorList()
	{
		return armorList;
	}
	
	/**
	 * Used to generate weapon types and then adds them to an array
	 * @param filename, the file name from enemyTypes
	 * @throws IOException 
	 */
	public ArmorList() throws IOException
	{
		createArmors();
	}
	/**
	 * Adds the items to our itemList
	 * @param filename, name of file
	 * @throws IOException an IO exception in case of missing file - SHOULD NOT OCCUR EVER
	 */
	private static void addNewItem(String filename) throws IOException
	{
		Gson gson = new GsonBuilder().create();
		InputStreamReader reader = new InputStreamReader(ArmorList.class.getResourceAsStream("/items/"+filename), "UTF-8");
		ArmorType[] armorType = gson.fromJson(reader, ArmorType[].class);
		for(ArmorType armor: armorType)
		{
			//appends the ID to the item
			armor.setID(armorList.size());
			//adds it to the list.
			armorList.add(armor);
		}
	}
	
	public static void createArmors() throws IOException
	{
		addNewItem("armor.json");
	}
}