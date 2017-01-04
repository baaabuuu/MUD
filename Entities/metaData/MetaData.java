package metaData;

import playerPackage.PlayerAccount;

public class MetaData
{
	private String 			gameName;
	private String			gameVersion;
	private String			totalPlayerCount;
	private String			totalCharacterCount;
	private String			totalKills;
	private String			totalCharDeath;
	//used to checks tuff
	private PlayerAccount[]	players;
	private	PlayerAccount[]	activePlayers;
	
	
	public void updatePlayerCount(){
		//checks an array with players if size is greater do X.
		//if the number is greater than playerCount, increase it
	}
	
	public void updateCharacterCount(){
		//updates an array with the player count
	}
	
	public void updateActivePlayerCount(){
		//updates an array containing activePlayers
	}
	
	
	
	/**
	 * Returns the game name
	 * @return gamename
	 */
	public String getgameName()
	{
		return gameName;
	}
	
	/**
	 * Returns the game version
	 * @return gameVersion as a string
	 */
	public String gameVersion()
	{
		return gameVersion;
	}
	
	/**
	 * Returns the totalPlayerCount
	 * @return totalPlayerCount as an integer
	 */
	public int getPlayerCount()
	{
		return Integer.parseInt(totalPlayerCount);
	}
	
	
	/**
	 * Returns the totalCharacterCount
	 * @return totalCharacterCount as integer
	 */
	public int getCharacterCount()
	{
		return Integer.parseInt(totalCharacterCount);
	}
	/**
	 * Returns the amount of creeps players have killed
	 * @return totalKills as integer
	 */
	public int getTotalKills()
	{
		return Integer.parseInt(totalKills);
	}
	/**
	 * 
	 */
	public int getTotalDeath()
	{
		return Integer.parseInt(totalCharDeath);
	}
	
	
}