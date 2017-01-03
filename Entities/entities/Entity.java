package entities;

public class Entity {
	String name;
	int maxHP;
	String weapon;
	String armor;
	int[] dmg = new int[2];
	int hp;
	int level;
	public Entity(String name, int hp,String weapon, int level){
		this.name	=	name;
		maxHP		= 	hp;
		this.hp		=	hp;
		this.weapon	=	weapon;
		this.level	= 	level;
	}
	protected void recalcHP(){
		if (hp>this.maxHP) hp	=	maxHP;
	}
}
