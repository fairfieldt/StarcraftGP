package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;

public class MoveToUnitStatementNode extends StatementNode
{

	public MoveToUnitStatementNode(ValueNode child, Unit owner)
	{
                super(owner);
		addChild(child);
	}
	void execute()
	{
		int unitId = ((ValueNode)children.get(0)).execute();
                proxyBot.rightClick(owner.getID(), unitId);
                    try{Thread.sleep(1000 + owner.getOrderTimer());}
                    catch (Exception e) {System.out.println(e);}

                  if (next != null)
                {
                    next.execute();
                }

	}
}
