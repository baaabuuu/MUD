package metaData;

public class MetaData
{
	public static String 	gameName	=	"Baaabuuu Rules, everyone else Drools the game";
	public static String	gameVersion	=	"0.1";
	public static String	totalPlayerCount;
	public static String	totalCharacterCount;
	public static String	totalKills;
	public static String	totalCharDeath;	
	
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