package npc;

public class CreepType
{
	String 		type;
	String[]	challenge;
	String[] 	modifiers;
	String[] 	weapons;
	String[]	gold;
	String[] 	hp;
	String[]	loot;
	String[]	dmgBonus;
	String		modChance;
	String		speed;
	String		dexterity;
	String[]	armor;
	String[]	reduction;
	String[]	spellReduction;
	int			id;
	public CreepType(){}
	public void getInfo() {
		System.out.println("CreepType: "+ type + " modChance: " + modChance);
		
	};
	public void setID(int val){
		id=val;
	}
}