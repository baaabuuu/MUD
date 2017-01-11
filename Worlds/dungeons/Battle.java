package dungeons;


import java.util.ArrayList;
import java.util.Random;

import npc.Entity;

public class Battle 
{
	Entity				playerCharacter;
	Entity[]			enemies;
	ArrayList<Entity>	iniative	=	new ArrayList<Entity>();
	Random rand = new Random(); 

	public int fight(Entity[] entities) 
	{
		//CHANGE THIS
		playerCharacter	=	World.playerChar;
		//CHANGE THIS
		enemies=entities;
		System.out.println(enemyAppears());
		boolean	battleDone	=	false;
		Entity	currChar;
		while(!battleDone)
		{
			currChar	=	iniative.get(0);
			if (currChar.equals(playerCharacter))
			{
				
			}
			else // enemy is character
			{
				
			}
		}
		
		rollForIniative();
		//return the result
		return 0;
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
