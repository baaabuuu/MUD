package dungeons;

import java.util.ArrayList;

public class World {
	public static ArrayList<Dungeon> dungeonList = new ArrayList<Dungeon>();
	
	
	public static void Main()
	{
		Dungeon dung = new Dungeon("Test Town","A bussy little town.");
		createTestTown(dung);
	}
	
	
	/**
	 * Creates Test Town
	 */
	public static void createTestTown(Dungeon dung)
	{
		dung.newRoom();
		Room room	=	dung.newRoom;
		room.description.add("As you enter the town center, you notice the following:");
		room.description.add("There's nothing actually here besides unused blocks of data..");
		room.description.add("If you squint your eyes you can kinda notice something up ahead");
	}
	
}
