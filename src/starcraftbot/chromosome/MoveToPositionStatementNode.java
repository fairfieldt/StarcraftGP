package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;

public class MoveToPositionStatementNode extends StatementNode
{

	public MoveToPositionStatementNode(Node child1, Node child2, Unit owner)
	{
                super(owner);
		addChild(child1);
		addChild(child2);
	}
	void execute()
	{
		int xPos = ((ValueNode)children.get(0)).execute();
		int yPos = ((ValueNode)children.get(0)).execute();

                proxyBot.rightClick(owner.getID(), owner.getX() +xPos, owner.getY() + yPos);

                try{Thread.sleep(1000 * owner.getOrderTimer());}
                    catch (Exception e) {System.out.println(e);}

                  if (next != null)
                {
                    next.execute();
                }

	}
}