package starcraftbot.chromosome;

import starcraftbot.proxybot.*;

public class FriendNearBooleanNode extends BooleanNode
{
         public FriendNearBooleanNode(Unit owner)
        {
            super(owner);
        }
	boolean execute()
	{
                //FIXME: Needs to be done the same as enemy near.
		return true;
	}
}