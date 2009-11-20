package starcraftbot.chromosome;

import starcraftbot.proxybot.*;


public class FriendCountValueNode extends ValueNode
{
        public FriendCountValueNode(Unit owner)
        {
            super(owner);
        }
	public int execute()
	{
		int friendID = proxyBot.getPlayerID();

                return getUnitCount(friendID);
	}
}
