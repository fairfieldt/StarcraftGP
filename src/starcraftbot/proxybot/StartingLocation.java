package starcraftbot.proxybot;

import java.util.ArrayList;
/**
 * Represents a starting location in StarCraft.
 * 
 * Note: x and y are in tile coordinates
 */
public class StartingLocation {

	private int x;
	
	private int y;
		
	/**
	 * Parses the starting locations.
	 */
	public static ArrayList<StartingLocation> getLocations(String locationData) {
		ArrayList<StartingLocation> locations = new ArrayList<StartingLocation>();
	
		String[] locs = locationData.split(":");
		boolean first = true;
		
		for (String location : locs) {
			if (first) {
				first = false;
				continue;
			}
			
			String[] coords = location.split(";");

			StartingLocation loc = new StartingLocation();
			loc.x = Integer.parseInt(coords[0]) + 2;
			loc.y = Integer.parseInt(coords[1]) + 1;
			locations.add(loc);
		}
		
		return locations;		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public String toString() {
		return x + "," + y;
	}
}
