package items;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class WeaponGeneration {
	private static WeaponList weapons;
	static Random rand = new Random(); 
	
	public static void main(String[] args) throws IOException
	{
		weapons = new WeaponList();
		Weapon newWeapon = createWeapon();
		newWeapon.getInfo();
	}
	
	public WeaponGeneration() throws IOException{
		weapons = new WeaponList();		
		Weapon newWeapon = createWeapon();
		newWeapon.getInfo();
	}
	
	
	private static Weapon createWeapon(int index){

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
	
	
	//generates a random weapon
	private static Weapon createWeapon(){
		//no modifiers in terms of searching
		//needs search modifiers
		

		//weaps is short form of weapons
		ArrayList<WeaponType> weaps = weapons.getWeaponList();
		int index					= rand.nextInt(weaps.size());
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
	

	public static int randVal(String[] val)
	{
		return Integer.parseInt(val[0])+rand.nextInt((Integer.parseInt(val[1])-Integer.parseInt(val[0])+1));
	}
	
	/**
	 * adds modifiers to the creature tpye using 2 lists.. woo?
	 * @param monsterType, the monster type
	 * @param level, level of monster used to determin how many mods they can get = level/2+1 (note, 0 is considered a 
	 * @return a string array consisting of modifiers
	 */
	public static String addModifiers(WeaponType weaponType)
	{
		if (weaponType.modifiers.length>0)
		{
			ArrayList<String> modifierList = new ArrayList<String>();
			
			for (String mod : weaponType.modifiers)
			{
				modifierList.add(mod);
			}
			int randNum = rand.nextInt(101);
			int chance	= Integer.parseInt(weaponType.modChance);
			System.out.println("rand Num: " + randNum + " chance: " + chance);
			if (randNum<=chance)
			{
				String modifier = modifierList.get(rand.nextInt(modifierList.size()));
				System.out.println(modifier);
				return modifier + " ";
			}
					
		}
		return "";
	}
}
