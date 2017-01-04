package items;

public class Item {
	protected String name;
	protected String slot;
	protected int rarity;
	protected int level;
	protected int goldCost;
	protected String description;
	protected String modifier;
	protected int durabilityMax;
	protected int currDurrability;
	
	public Item(String name, String slot,int rarity, int level, int goldCost, String desc, String modifier,int durability){
		this.name		=	name;
		this.level		= 	level;
		this.slot		=	slot;
		this.rarity		=	rarity;
		description		=	desc;
		this.modifier	=	modifier;
		durabilityMax	=	durability;
		currDurrability	=	durability;
	}
	public int getCurrDurrability()
	{
		return currDurrability;
	}
	//setter method for changing the value
	public void changeCurDurrability(int diff)
	{
		currDurrability+=diff;
	}
	
	public int getMaxDurability()
	{
		return durabilityMax;
	}
	public String getName()
	{
		return name;
	}
	public String getSlot()
	{
		return slot;
	}
	public int getSellPrice()
	{
		return goldCost/2;
	}
	public int getBuyPrice()
	{
		return goldCost;
	}
	public String getDescription()
	{
		return description;
	}
	public int getLevel()
	{
		return level;
	}
	public int getRarity()
	{
		return level;
	}
}