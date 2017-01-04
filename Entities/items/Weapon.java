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
