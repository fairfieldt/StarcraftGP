package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;

public class FollowUnitStatementNode extends StatementNode
{

	public FollowUnitStatementNode(Node child, Unit owner)
	{
                super(owner);
		addChild(child);
	}
	void execute()
	{
		int unitID = ((ValueNode)children.get(0)).execute();
                proxyBot.follow(owner.getID(), unitID);

                if (next != null)
                {
                    next.execute();
                }
	}
}
