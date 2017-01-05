package playerPackage;

import java.util.ArrayList;

import npc.Entity;



public class Character extends Entity{
	private int playerID;
	private ArrayList<Integer> stats;
	private int exp;
	
	//TODO add spells, feats and classes
	//private ArrayList<Abilities> spells;
	//private ArrayList<Feats> feats;
	//private CharacterCla charClass; 
	
	public Character(String name, int hp, int level, int playerID)
	{
		super(name, hp, level);
		this.playerID = playerID;
		stats = new ArrayList<Integer>();
		//sets all base stats to 5.
		addStats(0,5);
		addStats(1,5);
		addStats(2,5);
		addStats(3,5);
		exp=0;
		// TODO CHARACTER CLASSES
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
	
	public int getStats(int statID)
	{
		return stats.get(statID);
	}
	
	/**
	 * sets the stat of that character
	 * Mighty, Dexterity, Wisdom, Consitution	=	0,	1,	2, 	3
	 * @param statID
	 * @param val
	 */
	
	public void addStats(int statID, int val)
	{
		stats.set(statID, stats.get(statID)+val);
	}

	public void levelUP() {
		// TODO level up function
		
	}	
}
