package items;

public class Weapon extends Item {
	protected int		accuracyMod;
	protected int		critMod;
	protected int		damage[];
	protected String	attackDescription;
	protected String 	weaponClass;
	protected int[]		unusedStats;
	protected String	mod;
	

	public Weapon(String name, String slot, int rarity, int level, int goldCost, String desc, String modifier, int accuracyMod, int critmod,
			int[] damage, String attackDescription, String weaponClass,int durability, int[] stats) 
	{
		super(name, slot, rarity, level, goldCost, desc, modifier, durability);
		this.accuracyMod 		=	accuracyMod;
		this.critMod			=	critmod;
		this.damage 			=	damage;
		this.attackDescription	=	attackDescription;
		this.weaponClass		=	weaponClass;
		this.mod				=	modifier;
		//currently unused stats
		this.unusedStats		=	stats;
	}
	
	public Weapon() 
	{
		super("Fists", "Weapon", 0, 1, 0, "a fist","", 1000);
		this.accuracyMod 		=	0;
		this.critMod			=	0;
		this.damage 			=	new int[2];
		this.damage[0]			=	2;
		this.damage[1]			=	5;
		this.attackDescription	=	"punches";
		this.weaponClass		=	"martial";
		this.mod				=	"";
		//currently unused stats
	}
	
	public Weapon(String name, String slot, int rarity, int level, int goldCost, String desc,
			String modifier, int accuracyMod, int critmod,int[] damage, 
			String attackDescription, String weaponClass,int durability,int currDurability,
			int[] stats)
	{
		super(name, slot, rarity, level, goldCost, desc, modifier, durability);
		this.accuracyMod 		=	accuracyMod;
		this.critMod			=	critmod;
		this.damage 			=	damage;
		this.attackDescription	=	attackDescription;
		this.weaponClass		=	weaponClass;
		this.currDurrability	=	currDurability;
		this.mod				=	modifier;
		//currently unused stats
		this.unusedStats		=	stats;
	}
	
	public String getModifier() {
		return mod;
	}
	
	public String toSaveString()
	{
		String returnString = "weapon@"+name+"@"+slot+"@"+rarity+"@"+level+"@"+goldCost+"@"+description+"@"+modifier+"@"+durabilityMax+"@"+currDurrability+"@"+accuracyMod+"@"+critMod+"@"+damage[0]+"@"+damage[1]+"@"+attackDescription+
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
