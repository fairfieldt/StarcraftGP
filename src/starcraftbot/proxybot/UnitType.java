package starcraftbot.proxybot;

import java.util.HashMap;
/**
 * Represents a unit type in StarCraft.
 * 
 * TODO: hit points are wrong for buildings. Not really a problem since this is type data, 
 *       instances should have accurate data.
 *       
 * Note: Minerals and Gas and considered unit types.
 * 
 * See Constants.java for a listing of the upgrade types.
 * 
 * TODO: get the whatBuilds, requiredUnits, requireTech attributes from StarCraft.
 */
public class UnitType {
	
	/** unit type identifier */
	private int id;
	
	/** the race of the unit type */
	private String race;
	
	/** the name of the type */
	private String name;
	
	/** mineral cost to produce */
	private int mineralsCost;
	
	/** gas cost to produce */
	private int gasCost;
	
	/** max hit points */
	private int maxHitPoints;
	
	/** max shields */
	private int maxShields;
	
	/** max energy, this should not be static */
	private int maxEnergy;
	
	/** time to produce the unit, specified in game frames */
	private int buildTime;
	
	/** does the type have an attack */
	private boolean canAttack;
	
	/** is the type mobile */
	private boolean canMove;

	/** width of the type in map tiles, note that units use a different size system than buildings */
	private int tileWidth;
	
	/** height of the type in map tiles, note that units use a different size system than buildings */
	private int tileHeight;
	
	/** supply require to produce, double the value you'd expect */
	private int supplyRequired;
	
	/** supply provided by the type, double the value you'd expect */
	private int supplyProvided;

	/** vision range of the type */
	private int sightRange;
	
	/** minimum ground attack range, i think this pertains only to sieged up tanks */
	private int groundMinRange;
	
	/** maximum ground attack range */
	private int groundMaxRange;
	
	/** base damage dealt to ground units */
	private int groundDamage;

	/** maximum air attack range */
	private int airRange;
	
	/** base damage dealt to air units */
	private int airDamage;

	/** is this type a building */
	private boolean building;
	
	/** is this a flying type */
	private boolean flyer;
	
	/** is this type a spell caster */
	private boolean spellCaster;
	
	/** is this type a worker unit */
	private boolean worker;
	
	/** id of the unit type that produces this */
	private int whatBuilds;
	
	/**
	 * Parses the unit types.
	 */
	public static HashMap<Integer, UnitType> getUnitTypes(String unitTypeData) {
		HashMap<Integer, UnitType> types = new HashMap<Integer, UnitType>();
		
		String[] unitTypes = unitTypeData.split(":");
		boolean first = true;
		
		for (String unitType : unitTypes) {
			if (first) {
				first = false;
				continue;
			}
			
			String[] attributes = unitType.split(";");
			UnitType type = new UnitType();

			type.id = Integer.parseInt(attributes[0]);
			type.race = attributes[1];
			type.name = attributes[2];
			type.mineralsCost = Integer.parseInt(attributes[3]);
			type.gasCost = Integer.parseInt(attributes[4]);
			type.maxHitPoints = Integer.parseInt(attributes[5]);
			type.maxShields = Integer.parseInt(attributes[6]);
			type.maxEnergy = Integer.parseInt(attributes[7]);
			type.buildTime = Integer.parseInt(attributes[8]);
			type.canAttack = Integer.parseInt(attributes[9]) == 1;
			type.canMove = Integer.parseInt(attributes[10]) == 1;
			type.tileWidth = Integer.parseInt(attributes[11]);
			type.tileHeight = Integer.parseInt(attributes[12]);
			type.supplyRequired = Integer.parseInt(attributes[13]);
			type.supplyProvided = Integer.parseInt(attributes[14]);
			type.sightRange = Integer.parseInt(attributes[15]);
			type.groundMaxRange = Integer.parseInt(attributes[16]);
			type.groundMinRange = Integer.parseInt(attributes[17]);
			type.groundDamage = Integer.parseInt(attributes[18]);
			type.airRange = Integer.parseInt(attributes[19]);
			type.airDamage = Integer.parseInt(attributes[20]);
			type.building = Integer.parseInt(attributes[21]) == 1;
			type.flyer = Integer.parseInt(attributes[22]) == 1;
			type.spellCaster = Integer.parseInt(attributes[23]) == 1;
			type.worker = Integer.parseInt(attributes[24]) == 1;
			type.whatBuilds = Integer.parseInt(attributes[25]);

			System.out.println(type.getId() + " " + type.whatBuilds);
			
			types.put(type.getId(), type);
		}
		
		return types;
	}
	
	public int getId() {
		return id;
	}
	
	public String getRace() {
		return race;
	}
	
	public String getName() {
		return name;
	}
	
	public int getMineralsCost() {
		return mineralsCost;
	}
	
	public int getGasCost() {
		return gasCost;
	}
	
	public int getMaxHitPoints() {
		return maxHitPoints;
	}
	
	public int getMaxShields() {
		return maxShields;
	}
	
	public int getMaxEnergy() {
		return maxEnergy;
	}
	
	public int getBuildTime() {
		return buildTime;
	}
	
	public boolean isCanAttack() {
		return canAttack;
	}
	
	public boolean isCanMove() {
		return canMove;
	}
	
	public int getTileWidth() {
		return tileWidth;
	}
	
	public int getTileHeight() {
		return tileHeight;
	}
	
	public int getSupplyRequired() {
		return supplyRequired;
	}
	
	public int getSupplyProvided() {
		return supplyProvided;
	}
	
	public int getSightRange() {
		return sightRange;
	}
	
	public int getGroundMinRange() {
		return groundMinRange;
	}
	
	public int getGroundMaxRange() {
		return groundMaxRange;
	}
	
	public int getGroundDamage() {
		return groundDamage;
	}
	
	public int getAirRange() {
		return airRange;
	}
	
	public int getAirDamage() {
		return airDamage;
	}
	
	public boolean isBuilding() {
		return building;
	}
	
	public boolean isFlyer() {
		return flyer;
	}
	
	public boolean isSpellCaster() {
		return spellCaster;
	}
	
	public boolean isWorker() {
		return worker;
	}

	public int getWhatBuilds() {
		return whatBuilds;
	}
	
	public boolean isCenter() {
		return id == Constants.Terran_Command_Center ||
			   id == Constants.Protoss_Nexus ||
			   id == Constants.Zerg_Hatchery;
	}
	
	public String toString() {
		return 
			"id:" + id +
			" race:" + race +
			" name:" + name +
			" minCost:" + mineralsCost +
			" gasCost:" + gasCost +
			" hitPoints:" + maxHitPoints +
			" shields:" + maxShields +
			" energy:" + maxEnergy +
			" buildTime:" + buildTime +
			" canMove:" + canMove +
			" canAttack:" + canAttack +
			" width:" + tileWidth +
			" height:" + tileHeight +
			" supplyRequired:" + supplyRequired +
			" supplyProvided:" + supplyProvided +
			" sight:" + sightRange  +
			" groundMaxRange:" + groundMaxRange +
			" groundMinRange:" + groundMinRange +
			" groundDamage:" + groundDamage +
			" airRange:" + airRange +
			" airDamage:" + airDamage +
			" building:" + building +
			" flyer:" + flyer +
			" spellcaster:" + spellCaster +
			" worker:" + worker + 	
			" whatBuilds:" + whatBuilds;			
	}
}
