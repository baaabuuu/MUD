package items;

public class WeaponType
{
	String 		type;
	String		slot;
	String		weaponClass;
	String[]	modifiers;
	String		rarity;
	String		level;
	String		modChance;
	String		goldCost;
	String 		critMod;
	String		accuracyMod;
	String[]	damageHigh;
	String[]	damageLow;
	String		description;
	String		attackDescription;
	String		durablityMax;
	String[]	unusedStats;
	int			id;
	
	public WeaponType(){}
	
	public void getInfo() {
		System.out.println("WeaponType: "+ type + " modChance: " + modChance);	
	};
	
	public void setID(int val){
		id=val;
	}
}