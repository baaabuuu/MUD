package npc;

public class Entity {
	protected String name;
	protected int maxHP;
	protected String armor;
	int[] dmg = new int[2];
	protected int hp;
	protected int level;
	public Entity(String name, int hp, int level){
		this.name	=	name;
		maxHP		= 	hp;
		this.hp		=	hp;
		this.level	= 	level;
	}
	protected void recalcHP(){
		if (hp>this.maxHP) hp	=	maxHP;
	}
	public void setLevel(int val)
	{
		this.level=val;
	}
	public String getName()
	{
		return name;
	}
	
	public int GetHPmax()
	{
		return maxHP;
	}
	
	public String getArmor()
	{
		return armor;
	}
	public int getCurrHp()
	{
		return hp;
	}
	public int getLevel()
	{
		return level;
	}
	
	
}
