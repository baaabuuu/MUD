package playerPackage;

import java.util.ArrayList;

import items.Armor;
import items.Item;
import items.Weapon;
import npc.Entity;

public class Character extends Entity{
	 	int playerID;
		ArrayList<Integer> stats;
		int exp;
		String	charClass;
		Weapon	wep;
		ArrayList<Item> inventory;
	 	ArrayList<Item> equipment;
		String	characterID;
	
	
	//TODO add spells, feats
	//private ArrayList<Abilities> spells;
	//private ArrayList<Feats> feats;
	int might	=	0; int dexterity	=	1;	int wisdom	=	2;	int constitution	=	3;
		
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
		recalcHP();
	}
	
	//TODO - maybe implement more values to send?
	//sends - name, character class, might, dexterity, wisdom, const, current hp, maximum hp, weapon crit chance, weapon hit chance.
	public String toDataStream()
	{
		int dmgLow 	= (wep == null)? 2 : wep.getDamage()[0];
		int dmgHigh = (wep == null)? 5 : wep.getDamage()[1];
		return name+"@"+charClass+"@"+stats.get(0)+"@"+stats.get(1)+"@"+stats.get(2)+"@"+stats.get(3)+"@"+hp+"@"+maxHP+"@"+(wep.getCrit() + stats.get(1)/2)+"@"+(wep.getAccuracy()+stats.get(1))
				+"@"+ dmgLow+"@"+dmgHigh;
	}
	
	//equips an item and de-equips an item in case the slot is already in use
	public boolean equipItem(Item item)
	{
		//checks if weapon
		if (item instanceof Weapon)
		{
			if (wep != null)
				inventory.add(wep);
			wep	=	(Weapon) item;
			equipment.add(item);			
			return true;
		}
		//checks if armor
		else if (item instanceof Armor)
		{
			//check if we have anything already equipped of this kind of item slot
			for (Item equipped : equipment)
			{
				if (equipped.getSlot().equals(item.getSlot()))
				{
					equipment.remove(equipped);
					break;
				}
			}
			equipment.add(item);
			return true;
		}
		return false;
	}
	
	public void recalcHP()
	{
		int hpadd = stats.get(3)*3 + 10;
		if (maxHP<hpadd)
		{
			hp += (hpadd-maxHP);
			maxHP	=	hp;
		}
	}
		
	public int getDex()
	{
		return stats.get(1);
	}
	
	public void getInfo()
	{
		System.out.println("Name: " + name + " Class: " + charClass + " level " + level);
	}
	
	public void setExp(int val)
	{
		exp+=val;
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
	
	public ArrayList<Item> getInventory()
	{
		return inventory;
	}
	
	public ArrayList<Item> getEquipment()
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

	public Weapon getWeapon() {
		// TODO Auto-generated method stub
		return wep;
	}

}
