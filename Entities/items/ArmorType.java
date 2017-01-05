package items;

public class ArmorType
{
	String 		type;
	String		slot;
	String		armorClass;
	String[]	modifiers;
	String		rarity;
	String		level;
	String		modChance;
	String		goldCost;
	String[]	evasionMod;
	String[]	reduction;
	String		description;
	String		durablityMax;
	String[]	unusedStats;
	int			id;
	
	public ArmorType(){}
	
	public void getInfo() {
		System.out.println("ArmorType: "+ type + " Description: " + description);	
	};
	
	public void setID(int val){
		id=val;
	}
}