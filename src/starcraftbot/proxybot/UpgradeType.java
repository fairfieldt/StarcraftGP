package starcraftbot.proxybot;

import java.util.ArrayList;
/**
 * Reference data about the upgrades in StarCraft.
 * 
 * See Constants.java for a listing of the upgrade types.
 */
public class UpgradeType {

	/** upgrade type identifier */
	private int id;

	/** name of the upgrade */
	private String name;
	
	/** id of the unit type that researches the upgrade */
	private int whatResearchesID;

	/** number of times the upgrade can be researched. usually 1, 3 for weapon upgrades */
	private int repeats;
	
	/** mineral cost of the upgrade */
	private int mineralsBase;
	
	/** increase in mineral cost per upgrade level */
	private int mineralsFactor;

	/** gas cost of the upgrade */
	private int gasBase;
	
	/** increase in gas cost per upgrade level */
	private int gasFactor;
		
	/**
	 * Parses the upgrade types.
	 */
	public static ArrayList<UpgradeType> getUpgradeTypes(String techData) {
		ArrayList<UpgradeType> types = new ArrayList<UpgradeType>();
		
		String[] typs = techData.split(":");
		boolean first = true;
		
		for (String typ : typs) {
			if (first) {
				first = false;
				continue;
			}
			
			String[] attribtes = typ.split(";");
			
			UpgradeType type = new UpgradeType();
			type.id = Integer.parseInt(attribtes[0]);
			type.name = attribtes[1];
			type.whatResearchesID = Integer.parseInt(attribtes[2]);
			type.repeats = Integer.parseInt(attribtes[3]);
			type.mineralsBase = Integer.parseInt(attribtes[4]);
			type.mineralsFactor = Integer.parseInt(attribtes[5]);
			type.gasBase = Integer.parseInt(attribtes[6]);
			type.gasFactor = Integer.parseInt(attribtes[7]);

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

	public int getRepeats() {
		return repeats;
	}

	public int getMineralsBase() {
		return mineralsBase;
	}

	public int getMineralsFactor() {
		return mineralsFactor;
	}

	public int getGasBase() {
		return gasBase;
	}

	public int getGasFactor() {
		return gasFactor;
	}

	public String toString() {
		return 
			"id:" + id + 
			" name:" + name + 
			" whatResearches:" + whatResearchesID + 
			" repeats:" + repeats + 
			" minsBase:" + mineralsBase + 
			" minsFactor:" + mineralsFactor + 
			" gasBase:" + gasBase + 
			" gasFactor:" + gasFactor;
	}
}
