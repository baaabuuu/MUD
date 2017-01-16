package items;

import java.io.IOException;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Random;

public class WeaponGeneration {
	private static WeaponList weapons;
	static Random rand = new Random(); 
	private static Hashtable<String, Integer> weaponHash = new Hashtable<String, Integer>();
	
	//dummy method for testing, creates random weapons
	public static void main(String[] args) throws IOException
	{
		weapons = new WeaponList();
		Weapon newWeapon = createWeapon();
		newWeapon.getInfo();
	}
	/**
	 * generates the weaponHash which is used for string lookups instead of preprovided IDs
	 * @throws IOException 
	 */
	private static void generateHash() throws IOException
	{
		weaponHash = new Hashtable<String,Integer>();
		weapons = new WeaponList();
		//hashtables :D
		for (int i=0; i<weapons.getWeaponList().size();i++){
			weaponHash.put(weapons.getWeaponList().get(i).type, weapons.getWeaponList().get(i).id);
		}
	}
	public WeaponGeneration() throws IOException{
		weapons = new WeaponList();
		generateHash();
		
	}
	
	public static Weapon createWeapon(String id) throws IOException{
		if (weapons	== null)
			weapons = new WeaponList();
		if (weaponHash.isEmpty())
		{
			generateHash();
		}
		int index = weaponHash.get(id);
		//weaps is short form of weapons
		ArrayList<WeaponType> weaps = weapons.getWeaponList();
		WeaponType weaponType		= weaps.get(index);
		
		int level 		= Integer.parseInt(weaponType.level);
		int durability	= Integer.parseInt(weaponType.durablityMax);
		int accuracy	= Integer.parseInt(weaponType.accuracyMod);
		int crit		= Integer.parseInt(weaponType.critMod);
		int rarity 		= Integer.parseInt(weaponType.rarity);
		int goldCost	= Integer.parseInt(weaponType.goldCost);
		
		//adds the unused stats to an array
		int[] stats = new int[weaponType.unusedStats.length];
		for (int i=0; i<stats.length;i++)
		{
			stats[i]=Integer.parseInt(weaponType.unusedStats[i]);
		}		
		int[] damage	= {randVal(weaponType.damageLow),randVal(weaponType.damageHigh)};

		String mod 		= addModifiers(weaponType);
		String name		= mod + weaponType.type;
		String slot		= weaponType.slot;
		String desc		= weaponType.description;
		String atkdesc	= weaponType.attackDescription;
		String wepClass	= weaponType.weaponClass;
		
		return new Weapon(name, slot, rarity, level, goldCost, desc, mod,accuracy, crit, damage,atkdesc, wepClass, durability,stats);
	}
	
	public static Weapon createWeapon(int index){
		if (weapons	== null)
		{
			try {
				weapons = new WeaponList();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//weaps is short form of weapons
		ArrayList<WeaponType> weaps = weapons.getWeaponList();
		WeaponType armorType		= weaps.get(index);
		
		int level 		= Integer.parseInt(armorType.level);
		int durability	= Integer.parseInt(armorType.durablityMax);
		int accuracy	= Integer.parseInt(armorType.accuracyMod);
		int crit		= Integer.parseInt(armorType.critMod);
		int rarity 		= Integer.parseInt(armorType.rarity);
		int goldCost	= Integer.parseInt(armorType.goldCost);
		
		//adds the unused stats to an array
		int[] stats = new int[armorType.unusedStats.length];
		for (int i=0; i<stats.length;i++)
		{
			stats[i]=Integer.parseInt(armorType.unusedStats[i]);
		}		
		int[] damage	= {randVal(armorType.damageLow),randVal(armorType.damageHigh)};

		String mod 		= addModifiers(armorType);
		String name		= mod + armorType.type;
		String slot		= armorType.slot;
		String desc		= armorType.description;
		String atkdesc	= armorType.attackDescription;
		String wepClass	= armorType.weaponClass;
		
		return new Weapon(name, slot, rarity, level, goldCost, desc, mod,accuracy, crit, damage,atkdesc, wepClass, durability,stats);
	}
	
	
	//generates a random weapon
	private static Weapon createWeapon(){
		//no modifiers in terms of searching
		//needs search modifiers
		

		//weaps is short form of weapons
		ArrayList<WeaponType> weaps = weapons.getWeaponList();
		int index					= rand.nextInt(weaps.size());
		WeaponType armorType		= weaps.get(index);
		
		int level 		= Integer.parseInt(armorType.level);
		int durability	= Integer.parseInt(armorType.durablityMax);
		int accuracy	= Integer.parseInt(armorType.accuracyMod);
		int crit		= Integer.parseInt(armorType.critMod);
		int rarity 		= Integer.parseInt(armorType.rarity);
		int goldCost	= Integer.parseInt(armorType.goldCost);
		//adds the unused stats to an array
		int[] stats = new int[armorType.unusedStats.length];
		for (int i=0; i<stats.length;i++)
		{
			stats[i]=Integer.parseInt(armorType.unusedStats[i]);
		}	
		int[] damage	= {randVal(armorType.damageLow),randVal(armorType.damageHigh)};

		String mod 		= addModifiers(armorType);
		String name		= mod + armorType.type;
		String slot		= armorType.slot;
		String desc		= armorType.description;
		String atkdesc	= armorType.attackDescription;
		String wepClass	= armorType.weaponClass;
		
		return new Weapon(name, slot, rarity, level, goldCost, desc, mod,accuracy, crit, damage,atkdesc, wepClass, durability,stats);
	}
	

	public static int randVal(String[] val)
	{
		return Integer.parseInt(val[0])+rand.nextInt((Integer.parseInt(val[1])-Integer.parseInt(val[0])+1));
	}
	
	/**
	 * adds modifiers to the creature type using 2 lists.. woo?
	 * @param monsterType, the monster type
	 * @param level, level of monster used to determin how many mods they can get = level/2+1 (note, 0 is considered a 
	 * @return a string array consisting of modifiers
	 */
	public static String addModifiers(WeaponType armorType)
	{
		if (armorType.modifiers.length>0)
		{
			ArrayList<String> modifierList = new ArrayList<String>();
			
			for (String mod : armorType.modifiers)
			{
				modifierList.add(mod);
			}
			int randNum = rand.nextInt(101);
			int chance	= Integer.parseInt(armorType.modChance);
			if (randNum<=chance)
			{
				String modifier = modifierList.get(rand.nextInt(modifierList.size()));
				return modifier + " ";
			}
					
		}
		return "";
	}
}
