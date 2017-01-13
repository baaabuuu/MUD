package dungeons;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

import items.Armor;
import items.Item;
import items.Weapon;
import npc.Creep;
import npc.Entity;
import playerPackage.Character;

public class Battle 
{
	Entity				playerCharacter;
	Entity[]			enemies;
	ArrayList<Entity>	iniative	=	new ArrayList<Entity>();
	Random rand = new Random(); 

	public int fight(Entity[] entities) 
	{
		//TODO CHANGE THIS VALUE - once we have a player value like in shittytestclass
		playerCharacter	=	World.playerChar;
		//TODO CHANGE THIS VALUE TODO
		enemies=entities;
		System.out.println(enemyAppears());
		boolean	battleDone	=	false;
		Entity	currChar;
		Entity  currTarget;
		Character currHero;
		Creep	currEnemy;
		Weapon wep;
		String output;
		int counter;
		String msg;
		while(!battleDone)
		{
			currChar	=	iniative.get(0);
			if (currChar.equals(playerCharacter))
			{
				Scanner input = new Scanner(System.in);
				output="Select the target you want to hit: \nYou select them by writing [targetID]]\n";
				counter=1;
				for (Entity target: entities)
				{
					output+=" - ["+counter+"] - " + target.getName() + " - HP: " + (100*target.getCurrHp()/target.GetHPmax())+".\n";
					//actual value counter-1
					counter+=1;
				}
				System.out.println(output);
				while(true)
				{
					msg	=	input.nextLine().toLowerCase();
					//checks if the middle characters are integers - otherwise it just ignores it.
					//it also checks if those characters are legal - by checking if it is greather than the size
					if (msg.startsWith("[") && msg.endsWith("]") &&
							msg.substring(1, msg.length()-1).matches("^(?!0+$)\\d+$") &&
							enemies.length>Integer.parseInt(msg.substring(1, msg.length()-1)) &&
							Integer.parseInt(msg.substring(1, msg.length()-1))>0)
					{
						currHero	=	(Character) currChar;
						currTarget	=	enemies[Integer.parseInt(msg.substring(1, msg.length()-1))-1];
						wep			=	currHero.getWeapon();
						//if no weapon create a fist weapon
						if (wep==null)
							wep	=	new Weapon();
						
						if (calcHit(wep,currChar.getDex(),currTarget.getDex()))
						{
							//deal damage to the target
							dealDamage(currChar,wep,currTarget);
							//adds our enemy to the back of the iniative queue
							iniative.remove(currChar);
							iniative.add(currChar);
							//effects that occur afterwards (stuns and slows)
							afterHitEffects(currChar,wep,currTarget);
							//hit occurred
						}
						else
						{
							System.out.println("The <" + currChar.getName() + "> missed <" + 	
									playerCharacter.getName() + "> with its <" +
									wep.getName() + ">.");
						}
						break;
					}
				}
				input.close();
			}
			else // enemy is character
			{
				currEnemy	=	(Creep) currChar;
				wep			=	currEnemy.getWeapon();
				
				if(calcHit(wep,currChar.getDex(),playerCharacter.getDex()))
				{
					//deal damage to the target
					dealDamage(currChar,wep,playerCharacter);
					//adds our enemy to the back of the iniative queue
					iniative.remove(currChar);
					iniative.add(currChar);
					//effects that occur afterwards (stuns and slows)
					afterHitEffects(currChar,wep,playerCharacter);
					//hit occurred
				}
				else
				{
					System.out.println("The <" + currChar.getName() + "> missed <" + 	
										playerCharacter.getName() + "> with its <" +
										wep.getName() + ">.");
				}
			}
		}
		rollForIniative();
		//return the result
		return 0;
	}

	
	
	private void afterHitEffects(Entity entity, Weapon wep, Entity target) {
		//MORE WEAPON MODIFIERS
		String	modifier	= wep.getModifier().toLowerCase();
		//STUNNING 15% chance to stun the enemy - adding it to the back of the queue
		if (modifier.equals("stunning") && rand.nextInt(100)+1<=15)	
		{
			iniative.remove(target);
			iniative.add(target);
			System.out.println("<"+entity.getName()+"> stunned <" + target.getName()+">!" );
		}
		//Chilly 20% chance to move the enemy 1 spot backwards
		if (modifier.equals("chilly") && rand.nextInt(100)+1<=20)	
		{
			for (int i = 0;i<iniative.size();i++)
			{
				if (iniative.get(i).equals(target))
				{
					//pushes it 1 step backwards by swapping positions
					Collections.swap(iniative, i, i+1);
					System.out.println("<"+entity.getName()+"> froze <" + target.getName()+">!" );
					break;
				}
			}
		}
		
		//MORE WEAPON MODIFIERS
		
	}


	private void dealDamage(Entity player, Weapon wep, Entity target) {
		int damage 	=	calcWeaponDamage(wep);
		//crit chance = weapon crit chance + dexterity / 2
		if (wep.getCrit() + player.getDex()/2 >= rand.nextInt(100)+1)
			damage*=2;
		for (Item equip: target.getEquipment())
		{
			if (equip instanceof Armor)
			{
				damage	-=	((Armor) equip).getReduction();
			}
		}
		//in case damage is less than 1 we it to be 1.
		if (damage<1)
			damage=1;
		
		System.out.println("The <" + player.getName() + "> "+ wep.getAttackDesc() +" <" + 	
				target.getName() + "> with its <" +	wep.getName() + "> and dealt " + damage + " damage!");
		//MORE WEAPON MODIFIERS
		String	modifier	= wep.getModifier().toLowerCase();
		if (modifier.equals("vampiric"))
		{
			player.heal(damage/5);
			System.out.println("The  <" +	wep.getName() + "> healed < " + player.getName()+ "> " + damage/5 + " health!");
		}
		//MORE WEAPON MODIFIERS
		target.reduceHP(damage);
	}
	
	public boolean calcHit(Weapon wep, int dex1, int dex2)
	{
		int hit = rand.nextInt(80) + dex1 - dex2 + wep.getAccuracy();
		//weapon modifiers
		String	modifier	= wep.getModifier().toLowerCase();
		if (modifier.equals("accurate"))
			hit+=5;
		//weapon modifiers
		
		
		return rand.nextInt(100)+1	<= hit;
	}
	
	public int calcWeaponDamage(Weapon wep)
	{
		int dmgLow	=	wep.getDamage()[0];
		int dmgHigh	=	wep.getDamage()[1];
		int dmg	=	dmgLow	+	rand.nextInt(dmgHigh-dmgLow);
		if (wep.getCrit()>= rand.nextInt(100)+1)
			dmg*=2;
		//weapon modifiers
		String	modifier	= wep.getModifier().toLowerCase();
		if (modifier.equals("fiery"))
			dmg	+=	3;
		if (modifier.equals("chilly"))
			dmg +=	1;
		//weapon modifiers
		
		return dmg;
	}	
	
	public void rollForIniative()
	{
		ArrayList<Entity>	chars		=	new ArrayList<Entity>();
		ArrayList<Integer>	ini			=	new ArrayList<Integer>();
		chars.add(playerCharacter);
		int	id	=	0;
		int	currIni	=	0;
		for (Entity enemy : enemies)
		{
			chars.add(enemy);
			ini.add(rand.nextInt(20)+enemy.getDex());
		}
		while(ini.size()>0)
		{
			
			for (int i = 0;i<ini.size();i++)
			{
				if (ini.get(i)>currIni)
				{
					currIni	=	ini.get(i);
					id	=	i;
				}
				
			}
			iniative.add(chars.get(id));
			ini.remove(id);
			chars.remove(id);
			currIni	=	0;
		}	
	}
	
	private String enemyAppears()
	{
		if (enemies.length==1)
		{
			return "You are attacked by a <" + enemies[0].getName() + ">.";
		}
		String enemyString = "You are attacked by a ";
		for (int i	=	0;i<enemies.length-2;i++)
		{
			enemyString+= " <" + enemies[i].getName() + ">, ";
		}
		return enemyString.substring(0, enemyString.length()-2) +
				" and a <" + enemies[enemies.length-1].getName() + ">.";
	}
}
