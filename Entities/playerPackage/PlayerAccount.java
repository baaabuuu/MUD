package playerPackage;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.time.Instant;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import items.WeaponList;
import items.WeaponType;
import metaData.MetaData;

public class PlayerAccount
{
	String 		accountName;
	String		password;
	String		email;
	String		creationDate;
	String		lastLogin;
	//can you guys say donate for 30 days :D
	//if -1 then infinite
	String		level;
	String		levelDuration;
	
	String		gmNotes;
	String		banned;
	
	String		playerID;
	String[]	characterID;
	
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
}