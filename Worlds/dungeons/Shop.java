package dungeons;

import java.util.ArrayList;
import java.util.Scanner;

import items.Item;
import npc.Entity;


public class Shop 
{
	
	
	public static void	newShop(ArrayList<Item> items, ArrayList<String> text)
	{
		//TODO CHANGE THIS VALUE - once we have a player value like in shittytestclass
		Entity playerCharacter = World.playerChar;
		//TODO CHANGE THIS VALUE TODO
		System.out.println(text.get(0));
		Scanner input = new Scanner(System.in);
		String in;
		int wepID;
		while (true)
		{
			System.out.println(updateItemList(items));
			// remove [] and <> to do some stuff later quicker and more easily
			in	=	input.nextLine().toLowerCase();
			if (in.startsWith("<") || in.startsWith("["))
				in.substring(1, in.length());
			
			if (in.endsWith(">") || in.endsWith("]"))
				in.substring(0, in.length()-1);
			//if the player wants to leave the shop
			if (in.equals("exit"))
			{
				System.out.println(text.get(6));
				break;
			}
			//check if legal item
			wepID	=	Integer.parseInt(in);
			if		(in.substring(0, in.length()).matches("^(?!0+$)\\d+$") &&
					items.size()>wepID && wepID>0)
			{
				//legal item
				//very long string - writing it to a string for that reason
				in	=	text.get(1) + items.get(wepID).getName() + " - " + items.get(wepID).getBuyPrice() 
						+ text.get(2)+"\nConfirm your purchase by writing <Y> or cancel by writing <N>.";
				System.out.println(in);
				in	=	input.nextLine().toLowerCase();
				//removing unneeded string modifiers in order to sanitize a bit
				if (in.startsWith("<"))
					in.substring(1, in.length());
				if (in.endsWith(">"))
					in.substring(0, in.length()-1);
				if (in.equals("y"))
				{
					playerCharacter.getInventory().add(items.get(wepID));
					items.remove(wepID);
				}
				//this is a legal item
			}
		}
		input.close();
	}
	
	public static String updateItemList(ArrayList<Item> items)
	{
		String s ="Write [number] to select which item you'd like to buy\nNote that all costs have been set to 0.\n";		
		for (int i = 0; i<items.size();i++)
		{
			s+=" -- [" + i + "] -- " + items.get(i).getName() + " - cost: " + items.get(i).getBuyPrice()
					 +" gold.\n";
		}
		return s + " -- <exit>";
	}
}
