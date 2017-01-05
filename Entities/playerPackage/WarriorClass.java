package playerPackage;

public class WarriorClass extends Character {

	
	int might	=	0; int dexterity	=	1;	int wisdom	=	2;	int constitution	=	3;
	public WarriorClass(String name, int hp, int level, int playerID) {
		super(name, hp, level, playerID);
		//warriors have +5 might and +2 const
		addStats(might,5);
		addStats(constitution,5);
		// TODO Auto-generated constructor stub
		// Add base thingys
	}
	
	public void addStat(int stat, int val)
	{
		super.addStats(stat, val);
	}
	
	public int getStat(int stat)
	{
		return super.getStats(stat);
	}
	
	
	/**
	 * Levelup function, warriors gain the following every level:
	 * 2 might
	 * 1 const
	 */
	public void levelUp(){
		addStat(might,2);
		addStat(constitution,1);
	}
	
	public String getCharacterClass()
	{
		return "Warrior";
	}

}
