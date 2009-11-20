package starcraftbot.chromosome;

import starcraftbot.proxybot.*;

public class WaitStatementNode extends StatementNode
{
	public WaitStatementNode(Node child, Unit owner)
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