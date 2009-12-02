package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;


public class FollowUnitStatementNode extends StatementNode
{

	public FollowUnitStatementNode(Node child, PlayerUnitWME owner)
	{
        super(owner);
		addChild(child);
	}
	void execute()
	{
		int unitID = ((ValueNode)children.get(0)).execute();
        game.getCommandQueue().rightClick(owner.getID(), unitID);
		if (next != null)
		{
			next.execute();
		}
	}
}
