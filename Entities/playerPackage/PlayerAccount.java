package playerPackage;

import java.time.Instant;


public class PlayerAccount
{
	private String 		accountName;
	private String		password;
	private String		email;
	private String		creationDate;
	private String		lastLogin;
	//can you guys say donate for 30 days :D
	//if -1 then infinite
	private String		level;
	private String		levelDuration;
		
	private String		gmNotes;
	private String		banned;
	
	private String		playerID;
	private String[]	characterID;
	
	public PlayerAccount(String name, String pass, String email)
	{
		accountName 		=	name;
		password			=	pass;
		this.email			=	email;
		//gets account creation time.
		creationDate		=	Instant.now().toString();
		lastLogin			=	creationDate;
		level				=	"0";
		levelDuration		=	"-1";
		gmNotes				=	"";
		banned				=	"false";
		//player can have up to 6 characters per account.
		characterID			=	new String[6];
		
		//needs to get a check on how many playerIDs exist
		//characterID is used to hold how many characters that player owns.
	}

	public void setID(int val)
	{
		this.playerID	=	String.valueOf(val);
	}
	
	public String getID()
	{
		return playerID;
	}
	
	public String[] getCharacterIDS()
	{
		return characterID;
	}
	
	public void addCharacter(int i,int id)
	{
		characterID[i]=String.valueOf(id);
	}
	
	//get methods for non-encrypted data?
	public String getName()
	{
		return accountName;
	}
	public String getEmail()
	{
		return email;
	}
	public String creationData()
	{
		return creationDate;
	}
	public String getLastLogin()
	{
		return lastLogin;
	}
	public String getLevel()
	{
		return level;
	}
	public String getLevelDuration()
	{
		return levelDuration;
	}
	public String getGMnotes()
	{
		return gmNotes;
	}
	public boolean isBanned()
	{
		return banned.equals("true");
	}
	
}