package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

import java.util.*;

public class FriendNearBooleanNode extends BooleanNode
{
         public FriendNearBooleanNode(PlayerUnitWME owner)
        {
            super(owner);
        }
	boolean execute()
	{
        ArrayList<PlayerUnitWME> myUnits = game.getPlayerUnits();
		
		double dist = 1000;
		for (PlayerUnitWME unit : myUnits)
		{
			double newDist = owner.distance(unit);
			dist = newDist < dist?  newDist : dist;
		}
			if (dist <= 5)
			{
                return true;
			}
			return false;
	}
}