package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

public class IfStatementNode extends StatementNode
{

	public IfStatementNode(Node child, PlayerUnitWME owner)
	{
        super(owner);
		addChild(child);
	}
	void execute()
	{
		System.out.print("If " + children.get(0) + " then: ");
		if (((BooleanNode)children.get(0)).execute())
		{
			System.out.println(" TRUE");
			
			for (int i = 1; i < children.size(); i++)
			{
				((StatementNode)children.get(i)).execute();
			}
		}
		else
		{
			System.out.println(" FALSE");
		}
		
		if (next != null)
		{
			next.execute();
		}
	}


}
