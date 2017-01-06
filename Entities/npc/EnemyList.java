package npc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.google.gson.*;
//used to get the jason files from package EnemyTypes

public class EnemyList
{
	//this array contains all the creep types.
	public static ArrayList<CreepType> creepList;
	
	public ArrayList<CreepType> getCreepList()
	{
		return creepList;
	}
	
	/**
	 * Used to generate creep types and then adds them to an array
	 * @param filename, the file name from enemyTypes
	 * @throws IOException 
	 */
	public EnemyList() throws IOException
	{
		creepList = new ArrayList<CreepType>();
		createMobs();
	}
	/**
	 * Adds the enemies to our creepList
	 * @param filename, name of file
	 * @throws IOException an IO exception in case of missing file - SHOULD NOT OCCUR EVER
	 */
	
	
	public static void addNewItem(String filename) throws IOException
	{
		Gson gson = new GsonBuilder().create();
		InputStreamReader reader = new InputStreamReader(CreepType.class.getResourceAsStream("/npc/"+filename), "UTF-8");
		CreepType[] creepType = gson.fromJson(reader, CreepType[].class);
		for(CreepType creep: creepType)
		{
			//appends the ID to the item
			creep.setID(creepList.size());
			//adds it to the list.
			creepList.add(creep);
		}
	}
	
	public void createMobs() throws IOException
	{
		addNewItem("LesserGoblin.json");
	}
}