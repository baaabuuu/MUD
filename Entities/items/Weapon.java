package items;

public class Weapon extends Item {
	protected int		accuracyMod;
	protected int		critMod;
	protected int		damage[];
	protected String	attackDescription;
	protected String 	weaponClass;
	protected int[]		unusedStats;

	public Weapon(String name, String slot, int rarity, int level, int goldCost, String desc, String modifier, int accuracyMod, int critmod,
			int[] damage, String attackDescription, String weaponClass,int durability, int[] stats) 
	{
		super(name, slot, rarity, level, goldCost, desc, modifier, durability);
		this.accuracyMod 		=	accuracyMod;
		this.critMod			=	critmod;
		this.damage 			=	damage;
		this.attackDescription	=	attackDescription;
		this.weaponClass		=	weaponClass;
		//currently unused stats
		this.unusedStats		=	stats;
	}
	
	public Weapon(boolean load, String name, String slot, int rarity, int level, int goldCost, String desc, String modifier, int accuracyMod, int critmod,
			int[] damage, String attackDescription, String weaponClass,int durability,int currDurability, int[] stats)
	{
		super(name, slot, rarity, level, goldCost, desc, modifier, durability);
		this.accuracyMod 		=	accuracyMod;
		this.critMod			=	critmod;
		this.damage 			=	damage;
		this.attackDescription	=	attackDescription;
		this.weaponClass		=	weaponClass;
		this.currDurrability	=	currDurability;
		//currently unused stats
		this.unusedStats		=	stats;
	}
	
	
	public String toSaveString()
	{
		String returnString = "weapon@"+name+"@"+slot+"@"+rarity+"@"+goldCost+"@"+description+"@"+modifier+"@"+durabilityMax+"@"+currDurrability+"@"+accuracyMod+"@"+critMod+"@"+damage[0]+"@"+damage[1]+"@"+attackDescription+
				"@"+weaponClass;
		for (int stat: unusedStats)
		{
			returnString = returnString+"@"+String.valueOf(stat);
		}
		return returnString;
	}
	
	
	public void getInfo(){
		System.out.println("name: " + name + " damage: " + damage[0]+"-"+damage[1]);
	}
	
	public int getAccuracy()
	{
		return accuracyMod;
	}
	
	public int getCrit()
	{
		return critMod;
	}
	
	public int[] getDamage()
	{
		return damage;
	}
	
	public String getAttackDesc()
	{
		return attackDescription;
	}
	
	public String getWepClass()
	{
		return weaponClass;
	}
}
