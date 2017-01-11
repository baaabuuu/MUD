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
	int		spellReduction;
	int		reduction;
	int		dexterity;
	
	public Creep(String name, int hp, Weapon weapon, String[] loot, int dmgBonus,String[] modifier, int level, Armor chest, int spellRes, int armorRess, int dex)
	{
		super(name, hp,level);
		//array containing loot
		this.loot 		=	loot;
		this.dmgBonus	=	dmgBonus;
		this.modifier	=	modifier;
		this.weapon		=	weapon;
		this.chest		=	chest;
		dexterity		=	dex;
		spellReduction	=	spellRes;
		reduction		=	armorRess;
		applyModifierEffect();
	}
	
	public Weapon getWeapon()
	{
		return weapon;
	}
	
	public int getDex()
	{
		return dexterity;
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
