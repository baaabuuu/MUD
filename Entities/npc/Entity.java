package npc;

import java.util.ArrayList;

import items.Item;

public class Entity {
	protected String name;
	protected int maxHP;
	protected String armor;
	int[] dmg = new int[2];
	protected int hp;
	protected int level;
	protected int dexterity;
	
	
	public Entity(String name, int hp, int level){
		this.name	=	name;
		maxHP		= 	hp;
		this.hp		=	hp;
		this.level	= 	level;
		//dexterity	= dex;
	}
	protected void recalcHP(){
		if (hp>this.maxHP) hp	=	maxHP;
	}
	
	
	public void reduceHP(int amm)
	{
		hp-=amm;
	}
	
	public void heal(int amm)
	{
		hp	=	(hp+amm<=maxHP) ? hp+amm : maxHP;
	}
	
	public int getDex()
	{
		return dexterity;
	}
	
	public void addDex(int val)
	{
		dexterity+=val;
	}
	
	public void setDex(int val)
	{
		dexterity=val;
	}
	
	public void setLevel(int val)
	{
		this.level=val;
	}
	public String getName()
	{
		return name;
	}
	
	public int GetHPmax()
	{
		return maxHP;
	}
	
	public String getArmor()
	{
		return armor;
	}
	public int getCurrHp()
	{
		return hp;
	}
	public int getLevel()
	{
		return level;
	}
	public ArrayList<Item> getEquipment() {
		// TODO Auto-generated method stub
		return null;
	}
	public ArrayList<Item> getInventory() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
