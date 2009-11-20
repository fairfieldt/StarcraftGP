package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;

public class IfStatementNode extends StatementNode
{

	public IfStatementNode(Node child, Unit owner)
	{
                super(owner);
		addChild(child);
	}
	void execute()
	{
		if (((BooleanNode)children.get(0)).execute())
		{
			StatementNode n = (StatementNode)this.next;
                        while (n != null)
                        {
                            n.execute();
                            n = (StatementNode)n.next;
                        }
		}
	}


}
