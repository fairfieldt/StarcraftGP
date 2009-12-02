package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;



public class FriendCountValueNode extends ValueNode
{
	public FriendCountValueNode(PlayerUnitWME owner)
	{
		super(owner);
	}
	public int execute()
	{
		return game.getPlayerUnits().size();
	}
}
