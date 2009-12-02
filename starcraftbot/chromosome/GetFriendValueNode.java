package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

public class GetFriendValueNode extends ValueNode
{
        public GetFriendValueNode(PlayerUnitWME owner)
        {
            super(owner);
        }
	public int execute()
	{
		ArrayList<PlayerUnitWME> myUnits = game.getPlayerUnits();
		if (myUnits.size() == 0)
		{
			return owner.getID();
		}
		Random r = new Random();

		int myIndex = r.nextInt(myUnits.size());
		
		return myUnits.get(myIndex).getID();
	}
}
