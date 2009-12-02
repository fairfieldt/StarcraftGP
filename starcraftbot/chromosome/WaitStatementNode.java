package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;


public class WaitStatementNode extends StatementNode
{
	public WaitStatementNode(Node child, PlayerUnitWME owner)
	{
        super(owner);
		addChild(child);
	}

	public void execute()
	{
		int time = ((ValueNode)children.get(0)).execute();

		try{Thread.sleep(time * 1000);}
		catch(Exception e) {System.out.println(e);}

		 if (next != null)
		{
			next.execute();
		}
	}
}