package playerPackage;

import java.util.ArrayList;

import items.Item;
import npc.Entity;



public class Character extends Entity{
	protected 	int playerID;
	protected	ArrayList<Integer> stats;
	protected	int exp;
	protected	String	charClass;
	protected	ArrayList<Item> inventory;
	protected 	ArrayList<Item> equipment;
	protected	String	characterID;
	
	
	//TODO add spells, feats
	//private ArrayList<Abilities> spells;
	//private ArrayList<Feats> feats;
	
	public Character(String name, int hp, int level, int playerID,String charClass)
	{
		super(name, hp, level);
		this.playerID = playerID;
		stats = new ArrayList<Integer>();
		//sets all base stats to 5.
		inventory 	=	new ArrayList<Item>();
		equipment	=	new ArrayList<Item>();
		this.charClass	=	charClass;
		stats.add(5);
		stats.add(5);
		stats.add(5);
		stats.add(5);
		exp=0;
	}
	
	public void setLevel(int val)
	{
		super.setLevel(val);
	}
	
	public void setID(int val)
	{
		characterID		=	String.valueOf(val);
	}
	
	public String getCharacterID()
	{
		return characterID;
	}
	
	
	public String getCharClass()
	{
		return charClass;
	}
	
	public void equipItem(Item item)
	{
		equipment.add(item);
	}
	
	public void toInventory(Item item)
	{
		inventory.add(item);
	}
	
	public void unequipItem(int slotID)
	{
		equipment.remove(slotID);
	}
	
	public void removeFromInventory(int itemID)
	{
		inventory.remove(itemID);
	}
	
	public ArrayList<Item>getInventory()
	{
		return inventory;
	}
	public ArrayList<Item>getEquipment()
	{
		return equipment;
	}
	
	
	public void addEXP(int val)
	{
		exp+=val;
	}
	public int getExp()
	{
		return exp;
	}
	public void addSpell()
	{
		//TODO add spell functions
	}
	
	public void removeSpell()
	{
		//TODO remove spell function
	}
	
	public void addFeat()
	{
		//TODO add feats
	}
	
	public void removeFeat()
	{
		
	}
	
	public int getPlayerID()
	{
		return playerID;
	}
	
	public int getStat(int statID)
	{
		return stats.get(statID);
	}
	
	public ArrayList<Integer> getStats()
	{
		return stats;
	}
	
	/**
	 * sets the stat of that character
	 * Mighty, Dexterity, Wisdom, Consitution	=	0,	1,	2, 	3
	 * @param statID
	 * @param val
	 */
	
	public void addStats(int statID, int val)
	{
		if (stats.get(statID).equals(null))
		{
			stats.add(statID, val);
		}
		else
		{
			stats.set(statID, stats.get(statID)+val);
		}
		
	}

	public void levelUP() {
		// TODO level up function
		
	}

}
