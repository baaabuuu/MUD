package entities;

public class HostileCreep extends Entity {
	String[] loot;
	public HostileCreep(String name, int hp, String weapon, String[] loot) {
		super(name, hp, weapon);
		//array containing loot
		this.loot = loot;
		// TODO Auto-generated constructor stub
	}

}
