package entities;



public class CreepType
{
	String 		type;
	String[]		challenge;
	String[] 	modifiers;
	String[] 	weapons;
	String[]		gold;
	String[] 		hp;
	String[]	loot;
	String[]		dmgBonus;
	String		modChance;
	public CreepType(){}
	public void getInfo() {
		System.out.println("CreepType: "+ type + " modChance: " + modChance);
		
	};
}
