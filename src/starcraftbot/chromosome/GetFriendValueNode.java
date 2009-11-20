package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;

public class GetFriendValueNode extends ValueNode
{
        public GetFriendValueNode(Unit owner)
        {
            super(owner);
        }
	public int execute()
	{
		int myID = proxyBot.getPlayerID();
                int unitCount = getUnitCount(myID);

                Random r = new Random();

                int unitIndex = r.nextInt(unitCount);

                ArrayList<Unit> units = proxyBot.getUnits();

                return units.get(unitIndex).getID();
	}
}
