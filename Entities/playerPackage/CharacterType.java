package playerPackage;

import java.util.ArrayList;

public class CharacterType {
	String		name;
	String		charClass;
	String		playerID;
	String[]	stats;
	String		exp;
	String		level;
	String[]	inventory;
	String[]	equipment;
	String[]	spells;
	String[]	feats;
	
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
