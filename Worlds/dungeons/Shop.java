package dungeons;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.ArrayBlockingQueue;

import gameServer.Sender;
import items.Item;
import npc.Entity;
import playerPackage.Character;


public class Shop 
{
	
	
	public static void	newShop(ArrayList<Item> items, ArrayList<String> text, Sender transmitter, Character playerCharacter) throws InterruptedException
	{
		ArrayBlockingQueue<String> inbound = null;
		while(inbound  == null)
		{
			try{
				inbound		=	transmitter.inboundQueue;
			}
			catch (NullPointerException e)
			{
			}
		
		}
		transmitter.sendACT(text.get(0));
		Scanner input = new Scanner(System.in);
		String in;
		int wepID;
		String data;
		
		while (true)
		{
			updateItemList(items,transmitter);
			// remove [] and <> to do some stuff later quicker and more easily
			
			data = inbound.take();
			in	 =	data.substring(5,data.length()).toLowerCase();
			if (in.startsWith("<") || in.startsWith("["))
				in.substring(1, in.length());
			
			if (in.endsWith(">") || in.endsWith("]"))
				in.substring(0, in.length()-1);
			//if the player wants to leave the shop
			if (in.equals("exit") && data.substring(0,5).equals(":ACT:"))
			{
				transmitter.sendACT(text.get(6));
				break;
			}
			//check if legal item
			wepID	=	Integer.parseInt(in);
			if		(in.substring(0, in.length()).matches("^(?!0+$)\\d+$") &&
					items.size()>wepID && wepID>0)
			{
				//legal item
				//very long string - writing it to a string for that reason
				
				transmitter.sendACT(text.get(1) + items.get(wepID).getName() + " - " + items.get(wepID).getBuyPrice() + text.get(2));
				transmitter.sendACT("Confirm your purchase by writing <Y> or cancel by writing <N>.");
				
				data = inbound.take();
				in	 =	data.substring(5,data.length()).toLowerCase();
				//removing unneeded string modifiers in order to sanitize a bit
				if (in.startsWith("<"))
					in.substring(1, in.length());
				if (in.endsWith(">"))
					in.substring(0, in.length()-1);
				if (in.equals("y") && data.substring(0,5).equals(":ACT:"))
				{
					playerCharacter.getInventory().add(items.get(wepID));
					items.remove(wepID);
					playerCharacter.updateItems(transmitter);
				}
				//this is a legal item
			}
		}
		input.close();
	}
	
	public static void updateItemList(ArrayList<Item> items, Sender transmitter)
	{
		transmitter.sendACT("Write [number] to select which item you'd like to buy\nNote that all costs have been set to 0.");		
		for (int i = 0; i<items.size();i++)
		{
			transmitter.sendACT(" -- [" + i + "] -- " + items.get(i).getName() + " - cost: " + items.get(i).getBuyPrice()
					 +" gold.");
		}
		transmitter.sendACT(" -- <exit>");
	}
}
