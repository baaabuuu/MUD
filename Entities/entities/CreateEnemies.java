package entities;


import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import com.google.gson.*;
import enemyTypes.fileClass;

public class CreateEnemies {
	public static ArrayList<CreepType> creepList = new ArrayList<CreepType>();
	
	/**
	 * Used to generate creep types and then adds them to an array
	 * @param filename, the file name from enemyTypes
	 * @throws IOException 
	 */
	
	public static void CreateCreep(String filename) throws IOException
	{
		Gson gson = new GsonBuilder().create();
		InputStreamReader reader = new InputStreamReader(fileClass.class.getResourceAsStream("/enemyTypes/"+filename), "UTF-8");
		CreepType enemyType = gson.fromJson(reader, CreepType.class);
		creepList.add(enemyType);
	}
	
	public static void main(String[] args) throws IOException
	{
		CreateCreep("LesserGoblinChild");
		CreateCreep("LesserGoblinAdult");
	}


	
	
}
