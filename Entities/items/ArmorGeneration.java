package items;

import java.io.IOException;
import java.util.Hashtable;
import java.util.ArrayList;
import java.util.Random;

public class ArmorGeneration {
	private static ArmorList armors;
	static Random rand = new Random(); 
	private static Hashtable<String, Integer> armorHash = new Hashtable<String, Integer>();
	
	//dummy method for testing, creates random armors
	public static void main(String[] args) throws IOException
	{
		armors = new ArmorList();
		Armor newArmor = createArmor();
		newArmor.getInfo();
	}
	/**
	 * generates the weaponHash which is used for string lookups instead of preprovided IDs
	 * @throws IOException 
	 */
	private static void generateHash() throws IOException
	{
		armorHash = new Hashtable<String,Integer>();
		armors = new ArmorList();
		//hashtables :D
		for (int i=0; i<armors.getArmorList().size();i++){
			armorHash.put(armors.getArmorList().get(i).type, armors.getArmorList().get(i).id);
		}
	}
	public ArmorGeneration() throws IOException{
		armors = new ArmorList();
		generateHash();
	}
	
	public static Armor createArmor(String id) throws IOException{
		if (armors == null)
		{
			armors	=	new ArmorList();
		}
		if (armorHash.isEmpty())
		{
			generateHash();
		}
		int index = armorHash.get(id);
		//weaps is short form of armors
		ArrayList<ArmorType> armor = armors.getArmorList();
		ArmorType armorType		= armor.get(index);
		
		int level 		= Integer.parseInt(armorType.level);
		int durability	= Integer.parseInt(armorType.durablityMax);
		int evasionMod	= randVal(armorType.evasionMod);
		int reduction	= randVal(armorType.reduction);
		System.out.println(armorType.spellReduction[0] + " spellReduc: " + armorType.spellReduction[1]);
		int spellReduc	= randVal(armorType.spellReduction);
		int rarity 		= Integer.parseInt(armorType.rarity);
		int goldCost	= Integer.parseInt(armorType.goldCost);
		
		//adds the unused stats to an array
		int[] stats = new int[armorType.unusedStats.length];
		for (int i=0; i<stats.length;i++)
		{
			stats[i]=Integer.parseInt(armorType.unusedStats[i]);
		}
		String mod 		= addModifiers(armorType);
		String name		= mod + armorType.type;
		String slot		= armorType.slot;
		String desc		= armorType.description;
		String armClass	= armorType.armorClass;
		
		return new Armor(name, slot, rarity, level, goldCost, desc,durability,mod,evasionMod,reduction,armClass,stats,spellReduc);
	}
	
	public static Armor createArmor(int index){
		if (armors==null)
		{
			try {
				armors	=	new ArmorList();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//weaps is short form of armors
		ArrayList<ArmorType> armor = armors.getArmorList();
		ArmorType armorType		= armor.get(index);
		
		int level 		= Integer.parseInt(armorType.level);
		int durability	= Integer.parseInt(armorType.durablityMax);
		int evasionMod	= randVal(armorType.evasionMod);
		int reduction	= randVal(armorType.reduction);
		int rarity 		= Integer.parseInt(armorType.rarity);
		int goldCost	= Integer.parseInt(armorType.goldCost);
		
		System.out.println(armorType.spellReduction[0] + " spellReduc: " + armorType.spellReduction[1]);

		int spellReduc	= randVal(armorType.spellReduction);
		
		//adds the unused stats to an array
		int[] stats = new int[armorType.unusedStats.length];
		for (int i=0; i<stats.length;i++)
		{
			stats[i]=Integer.parseInt(armorType.unusedStats[i]);
		}
		String mod 		= addModifiers(armorType);
		String name		= mod + armorType.type;
		String slot		= armorType.slot;
		String desc		= armorType.description;
		String armClass	= armorType.armorClass;
		
		return new Armor(name, slot, rarity, level, goldCost, desc,durability,mod,evasionMod,reduction,armClass,stats,spellReduc);
	}
	
	
	//generates a random armor
	public static Armor createArmor(){
		ArrayList<ArmorType> arms = armors.getArmorList();
		int index					= rand.nextInt(arms.size());
	
		ArmorType armorType		= arms.get(index);
		
		int level 		= Integer.parseInt(armorType.level);
		int durability	= Integer.parseInt(armorType.durablityMax);
		int evasionMod	= randVal(armorType.evasionMod);
		int reduction	= randVal(armorType.reduction);
		int spellReduc	= randVal(armorType.spellReduction);
		int rarity 		= Integer.parseInt(armorType.rarity);
		int goldCost	= Integer.parseInt(armorType.goldCost);
		
		
		//adds the unused stats to an array
		int[] stats = new int[armorType.unusedStats.length];
		for (int i=0; i<stats.length;i++)
		{
			stats[i]=Integer.parseInt(armorType.unusedStats[i]);
		}
		String mod 		= addModifiers(armorType);
		String name		= mod + armorType.type;
		String slot		= armorType.slot;
		String desc		= armorType.description;
		String armClass	= armorType.armorClass;
		
		return new Armor(name, slot, rarity, level, goldCost, desc,durability,mod,evasionMod,reduction,armClass,stats,spellReduc);
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
	public static String addModifiers(ArmorType armorType)
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
