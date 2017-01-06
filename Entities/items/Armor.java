package items;

public class Armor extends Item
{
	protected int		evasionMod;
	protected int		reduction;
	protected String 	armorClass;
	protected int[]		unusedStats;
	protected int		spellReduction;

	public Armor(String name, String slot, int rarity, int level, int goldCost, String desc, int durability, String modifier, int evasionMod, int reduction,
		String armorClass, int[] stats,int spellReduction) 
	{
		super(name, slot, rarity, level, goldCost, desc, modifier, durability);
		this.reduction 			=	reduction;
		this.evasionMod			=	evasionMod;
		this.armorClass			=	armorClass;
		//currently unused stats
		this.unusedStats		=	stats;
		this.spellReduction		=	spellReduction;
	}
	
	public Armor(String name, String slot, int rarity, int level, int goldCost, String desc, int curDurability, int durability, String modifier, int evasionMod, int reduction,
			String armorClass, int[] stats,int spellReduction) 
		{
			super(name, slot, rarity, level, goldCost, desc, modifier, durability);
			this.reduction 			=	reduction;
			this.evasionMod			=	evasionMod;
			this.armorClass			=	armorClass;
			this.currDurrability	=	curDurability;
			//currently unused stats
			this.unusedStats		=	stats;
			this.spellReduction		=	spellReduction;
		}
	
	
	
	public String toSaveString()
	{
		String returnString = "armor@"+name+"@"+slot+"@"+rarity+"@"+level+"@"+goldCost+"@"+description+"@"+modifier+"@"+durabilityMax+"@"+currDurrability+"@"+evasionMod+"@"+reduction+"@"+spellReduction+"@"+
				"@"+armorClass;
		for (int stat: unusedStats)
		{
			returnString = returnString+"@"+String.valueOf(stat);
		}
		return returnString;
	}
	
	
	
	public void getInfo()
	{
		System.out.println("name: " + name + " slot: " + slot + " level: " + " rarity: " + rarity + " cost: "+goldCost);
		System.out.println("Description: " + description);
		System.out.println("ArmorClass: " + armorClass + " evasionModifier: " + evasionMod +  " Damage Reduction: " + reduction + " Spell Reduction:" + spellReduction);
	}
	
	
	public int getEvasionMod()
	{
		return evasionMod;
	}
	public int getSpellReduction()
	{
		return spellReduction;
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
