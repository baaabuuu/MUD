package items;

public class Armor extends Item
{
	protected int		evasionMod;
	protected int		reduction;
	protected String 	armorClass;
	protected int[]		unusedStats;

	public Armor(String name, String slot, int rarity, int level, int goldCost, String desc, int durability, String modifier, int evasionMod, int reduction,
		String armorClass, int[] stats) 
	{
		super(name, slot, rarity, level, goldCost, desc, modifier, durability);
		this.reduction 			=	reduction;
		this.evasionMod			=	evasionMod;
		this.armorClass			=	armorClass;
		//currently unused stats
		this.unusedStats		=	stats;
	}
	
	public void getInfo()
	{
		System.out.println("name: " + name + " slot: " + slot + " level: " + " rarity: " + rarity + " cost: "+goldCost);
		System.out.println("Description: " + description);
		System.out.println("ArmorClass: " + armorClass + " evasionModifier: " + evasionMod +  " Damage Reduction: " + reduction);
	}
	
	public int getEvasionMod()
	{
		return evasionMod;
	}
	
	public int getReduction()
	{
		return reduction;
	}
	public int getArmorClass()
	{
		return evasionMod;
	}
	public int getUnusesStats(int val)
	{
		return unusedStats[val];
	}
}
