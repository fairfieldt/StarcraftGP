package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import java.util.*;
public class UnitHealthValueNode extends ValueNode
{
	public UnitHealthValueNode(ValueNode unitId, Unit owner)
	{
                super(owner);
		addChild(unitId);
	}
	public int execute()
	{
                int unitID = ((ValueNode)children.get(0)).execute();
                ArrayList<Unit> units = proxyBot.getUnits();
		Unit target = null;
                for (Unit unit : units)
                {
                    if (unit.getID() == unitID)
                    {
                        target = unit;
                        break;
                    }
                }
		return owner.getHitPoints();
	}
}
