package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import java.util.*;
import starcraftbot.proxybot.wmes.unit.*;

public class UnitDistanceValueNode extends ValueNode
{
	public UnitDistanceValueNode(Node unitId, PlayerUnitWME owner)
	{
        super(owner);
		addChild(unitId);
	}
	public int execute()
	{
		int unitID = ((ValueNode)children.get(0)).execute();
		UnitWME target = getUnit(unitID);
				
		return (int)owner.distance(target);
	}
}