package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import java.util.*;
import starcraftbot.proxybot.wmes.unit.*;

public class UnitHealthValueNode extends ValueNode
{
	public UnitHealthValueNode(ValueNode unitId, PlayerUnitWME owner)
	{
        super(owner);
		addChild(unitId);
	}
	public int execute()
	{
		return owner.getHitPoints();
	}
}
