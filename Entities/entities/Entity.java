package entities;

public class Entity {
	String name;
	int maxHP;
	String weapon;
	int[] dmg = new int[2];
	public Entity(String name, int hp,String weapon){
		this.name	=	name;
		maxHP		= 	hp;
		this.weapon	=	weapon;
	}
}
