package starcraftbot.proxybot;

import java.util.ArrayList;
/**
 * Reference data about the tech types in StarCraft.
 * 
 * See Constants.java for a listing of the tech types.
 */
public class TechType {

	/** the tech unique ID */
	private int id;

	/** the tech type name */
	private String name;
	
	/** the unit type that research the tech */
	private int whatResearchesID;
	
	/** mineral cost of the tech */
	private int mineralsCost;
	
	/** gas cost of the tech */
	private int gasCost;

	/**
	 * Parses the tech types.
	 */
	public static ArrayList<TechType> getTechTypes(String techData) {
		ArrayList<TechType> types = new ArrayList<TechType>();
		
		String[] typs = techData.split(":");
		boolean first = true;
		
		for (String typ : typs) {
			if (first) {
				first = false;
				continue;
			}
			
			String[] attribtes = typ.split(";");

			TechType type = new TechType();
			type.id = Integer.parseInt(attribtes[0]);
			type.name = attribtes[1];
			type.whatResearchesID = Integer.parseInt(attribtes[2]);
			type.mineralsCost = Integer.parseInt(attribtes[3]);
			type.gasCost = Integer.parseInt(attribtes[4]);

			types.add(type);
		}
		
		return types;
	}
		
	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
	
	public int getWhatResearchesID() {
		return whatResearchesID;
	}
	
	public int getMineralsCost() {
		return mineralsCost;
	}
	
	public int getGasCost() {
		return gasCost;
	}

	public String toString() {
		return 
			"id:" + id + 
			" name:" + name + 
			" whatResearches:" + whatResearchesID + 
			" mins:" + mineralsCost + 
			" gas:" + gasCost;
	}
}
