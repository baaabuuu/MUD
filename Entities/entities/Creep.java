package entities;

public class Creep extends Entity
{
	String[] loot;
	int dmgBonus;
	String[] modifier;
	
	public Creep(String name, int hp, String weapon, String[] loot, int dmgBonus,String[] modifier, int level)
	{
		super(name, hp, weapon,level);
		//array containing loot
		this.loot = loot;
		this.dmgBonus=dmgBonus;
		this.modifier=modifier;
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
		System.out.println(name+" "+level+" "+ hp+ " " + weapon);
		
	}
}
