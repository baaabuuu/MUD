package npc;

public class Entity {
	String name;
	int maxHP;
	String armor;
	int[] dmg = new int[2];
	int hp;
	int level;
	public Entity(String name, int hp, int level){
		this.name	=	name;
		maxHP		= 	hp;
		this.hp		=	hp;
		this.level	= 	level;
	}
	protected void recalcHP(){
		if (hp>this.maxHP) hp	=	maxHP;
	}
}
