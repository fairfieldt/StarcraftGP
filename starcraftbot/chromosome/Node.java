package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;


public abstract class Node
{
    protected ArrayList<Node> children = new ArrayList<Node>();
   
    //This needs to be set once at the beginning.
    public static Game game;
	protected StatementNode next;
	protected PlayerUnitWME owner;
	
	public Node(PlayerUnitWME owner)
	{
		this.game = game;
		this.owner = owner;
	}

	void addChild(Node child)
	{
		children.add(child);
	}
	
	protected UnitWME getUnit(int unitID)
	{
		for (UnitWME unit : game.getUnits())
		{
			if (unit.getID() == unitID)
			{
				return unit;
			}
		}
		return null;
	}

}