package npc;

import items.Armor;
import items.Weapon;

public class Creep extends Entity
{
	String[] loot;
	int dmgBonus;
	Weapon weapon;
	Armor	chest;
	String[] modifier;
	
	public Creep(String name, int hp, Weapon weapon, String[] loot, int dmgBonus,String[] modifier, int level, Armor chest)
	{
		super(name, hp,level);
		//array containing loot
		this.loot 		=	loot;
		this.dmgBonus	=	dmgBonus;
		this.modifier	=	modifier;
		this.weapon		=	weapon;
		this.chest		=	chest;
		applyModifierEffect();
	}
	
	private void applyModifierEffect()
	{
		for (String mod : modifier)
		{
			switch (mod)
			{
				case "Enraged": 
					dmgBonus	+=	5;
					maxHP		=	maxHP/100*75;
					break;
				case "Weakend": 
					dmgBonus	-=	2;
					maxHP		=	maxHP / 100 * 65;
					break;
				default: break;
			}
		}
	recalcHP();
	}

	public void printInfo()
	{
		System.out.println(name+" "+level+" "+ hp+ " " + weapon.getName());
		
	}
}
