package starcraftbot.proxybot;
/**
 * Stores information about the player (Bot)
 * 
 * Note: the supply used and supply total variables are double what you would expect, because
 *       small units are represented as 1 supply in StarCraft.
 */
public class Player {

	/** the player identifier */
	private int playerID;

	/** the player's race, see Constants.java for definitions */
	private int race;
	
	/** current mineral supply */
	private int minerals;
	
	/** current gas supply */
	private int gas;
	
	/** amount of supply used by the player */
	private int supplyUsed;

	/** amount of supply provided by the player */
	private int supplyTotal;
	
	/** array of the unit types the bot can afford that it has the tech for */
	private boolean[] unitProduction = new boolean[Constants.NumUnitTypes];
	
	/** array of the tech types the bot can afford that it has the tech for */
	private boolean[] techProduction = new boolean[Constants.NumTechTypes];
	
	/** array of the upgrade types the bot can afford that it has the tech for */
	private boolean[] upgradeProduction = new boolean[Constants.NumUpgradeTypes];

	/**
	 * Updates the players attributes given the command data.
	 * 
	 * Expects a message of the form "status;minerals;gas;supplyUsed;SupplyTotal:..."
	 */
	public void update(String playerData) {		
		String[] attributes = playerData.split(":")[0].split(";");
	
		minerals = Integer.parseInt(attributes[1]);
		gas = Integer.parseInt(attributes[2]);
		supplyUsed = Integer.parseInt(attributes[3]);
		supplyTotal = Integer.parseInt(attributes[4]);

		for (int i=0; i<unitProduction.length; i++) {
			unitProduction[i] = attributes[5].charAt(i) == '1';
		}
		
		for (int i=0; i<techProduction.length; i++) {
			techProduction[i] = attributes[6].charAt(i) == '1';
		}

		for (int i=0; i<upgradeProduction.length; i++) {
			upgradeProduction[i] = attributes[7].charAt(i) == '1';
		}
	}

	public int getMinerals() {
		return minerals;
	}

	public int getGas() {
		return gas;
	}

	public int getSupplyUsed() {
		return supplyUsed;
	}

	public int getSupplyTotal() {
		return supplyTotal;
	}

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}

	public int getRace() {
		return race;
	}

	public void setRace(int race) {
		this.race = race;
	}
	
	public boolean[] getUnitProduction() {
		return unitProduction;
	}

	public boolean[] getTechProduction() {
		return techProduction;
	}

	public boolean[] getUpgradeProduction() {
		return upgradeProduction;
	}

	public String toString() {
		return 
			"mins:" + minerals +
			" gas:" + gas +
			" supplyUsed:" + supplyUsed +
			" supplyTotal:" + supplyTotal;
	}
}
