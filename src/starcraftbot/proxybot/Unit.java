package starcraftbot.proxybot;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * Represents a unit in StarCraft.
 * 
 * TODO: use a location more accuracy than map tile.
 */
public class Unit {
	
	/** a unique identifier for referencing the unit */
	private int ID;
	
	/** the player the unit belongs too */
	private int playerID;

	/** the unit type */
	private UnitType type;
	
	/** x tile position */
	private int x;
	
	/** y tile position */
	private int y;
	
	/** unit hit points */
	private int hitPoints;
	
	/** unit shields */
	private int shields;
	
	/** unit energy */
	private int energy;
	
	/** an internal timer used in StarCraft */
	private int orderTimer;

	/**
	 * Order type currently being executed by the unit.
	 * @See the Order enum in Constants.java
	 */
	private int order;

	/** resources remaining, mineral count for patches, and gas for geysers */ 
	private int resources;

	/**
	 * Parses the unit data.
	 */
	public static ArrayList<Unit> getUnits(String unitData, HashMap<Integer, UnitType> types) {
		ArrayList<Unit> units = new ArrayList<Unit>();
		
		String[] unitDatas = unitData.split(":");
		boolean first = true;
		
		for (String data : unitDatas) {
			if (first) {
				first = false;
				continue;
			}
			
			String[] attributes = data.split(";");
			Unit unit = new Unit();
			unit.ID = Integer.parseInt(attributes[0]);
			unit.playerID = Integer.parseInt(attributes[1]);
			unit.type = types.get(Integer.parseInt(attributes[2]));
			unit.x = Integer.parseInt(attributes[3]);
			unit.y = Integer.parseInt(attributes[4]);
			unit.hitPoints = Integer.parseInt(attributes[5]);
			unit.shields = Integer.parseInt(attributes[6]);
			unit.energy = Integer.parseInt(attributes[7]);
			unit.orderTimer = Integer.parseInt(attributes[8]);
			unit.order = Integer.parseInt(attributes[9]);
			unit.resources = Integer.parseInt(attributes[10]);
			
			units.add(unit);
		}		

		return units;
	}

	public double distance(Unit unit) {
		double dx = unit.x - x;
		double dy = unit.y - y;
		
		return Math.sqrt(dx*dx + dy*dy);
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}
	
	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(int playerID) {
		this.playerID = playerID;
	}
	
	public UnitType getType() {
		return type;
	}
	
	public void setType(UnitType type) {
		this.type = type;
	}
	
	public int getX() {
		return x;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}
	
	public int getShields() {
		return shields;
	}
	
	public void setShields(int shields) {
		this.shields = shields;
	}
	
	public int getEnergy() {
		return energy;
	}
	
	public void setEnergy(int energy) {
		this.energy = energy;
	}
	
	public int getOrderTimer() {
		return orderTimer;
	}

	public void setOrderTimer(int orderTimer) {
		this.orderTimer = orderTimer;
	}
	
	public int getOrder() {
		return order;
	}
	
	public void setOrder(int order) {
		this.order = order;
	}

	public int getResources() {
		return resources;
	}
	
	public String toString() {
		return 
			"ID:" + ID +
			" player:" + playerID +
			" type:" + type.getName() +
			" x:" + x +
			" y:" + y +
			" hitPoints:" + hitPoints +
			" shields:" + shields +
			" enemy:" + energy +
			" orderTimer:" + orderTimer +
			" order:" + order + 
			" resource:" + resources;
	}
}
