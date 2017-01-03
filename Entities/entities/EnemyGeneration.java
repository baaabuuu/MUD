package entities;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class EnemyGeneration {
	private static EnemyList mobs;
	static Random rand = new Random(); 
	
	public static void main(String[] args) throws IOException
	{
		mobs = new EnemyList();		
		Creep newMob = createCreep();
		newMob.printInfo();
	}
	
	public EnemyGeneration() throws IOException{
		mobs = new EnemyList();
		Creep newMob = createCreep();
		newMob.printInfo();
	}
	
	private static Creep createCreep(){
		//no modifiers in terms of searching
		//if (search.isEmpty())
		//{
		//	enemyList = mobs.getCreepList();
		//}
		System.out.println("test: "+ EnemyList.creepList.size());
		int index			= rand.nextInt(EnemyList.creepList.size());
		CreepType monsterType	= EnemyList.creepList.get(index);
		
		//handles the simple stuff
		//null pointer exception
		
		
		int level = randVal(monsterType.challenge);
		int hp = randVal(monsterType.hp);
		int dmgBonus = randVal(monsterType.dmgBonus);
		
		String weapon = monsterType.weapons[rand.nextInt(monsterType.weapons.length)];

		String[] modifiers = addModifiers(monsterType,level);
		String name = addNameMods(monsterType.type,modifiers);
		
		//TODO ADD LOOT, LOOT TABLES ARE IMPLEMENTED ALREADY.
		
		String[] loot = {null};		
		return new Creep(name,hp,weapon,loot, dmgBonus, modifiers, level);
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
	public static String[] addModifiers(CreepType monsterType, int level)
	{
		if (monsterType.modifiers.length>0)
		{
			ArrayList<String> modifierList = new ArrayList<String>();
			ArrayList<String> mods = new ArrayList<String>();
			
			for (String mod : monsterType.modifiers)
			{
				modifierList.add(mod);
			}
			
			int maxMods = level/2;
			int chance = Integer.parseInt(monsterType.modChance);
			int id;
			
			for (int i = 0;i<=maxMods;i++)
			{
				if (rand.nextInt(101)<=chance)
				{
					id = rand.nextInt(modifierList.size());
					mods.add(modifierList.get(id));
					modifierList.remove(id);
				}
			}
			String[] array = new String[mods.size()];
			
			for (int i=0; i<mods.size();i++){
				array[i]=mods.get(i);
			}
			return array;
			
			}
		return new String[0];
	}
	
	public static String addNameMods(String name, String[] mods)
	{
		if (mods.length>0)
		{
			for (String mod : mods)
			{
				name= mod + " " + name;
			}
		}
		return name;
	}
}
