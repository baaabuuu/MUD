package playerPackage;

import java.util.ArrayList;

public class CharacterType {
	String		name;
	String		charClass;
	String[]	stats;
	String		exp;
	String		level;
	String[]	inventory;
	String[]	equipment;
	String[]	spells;
	String[]	feats;
	String 		characterID;
	String		playerID;

	
	public ArrayList<String> convertToArrayList(String[] array)
	{
		ArrayList<String> list= new ArrayList<String>();
		for (String item: array)
		{
			list.add(item);
		}
		return list;
		
	}
}
