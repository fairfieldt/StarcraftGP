package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

public class MoveToPositionStatementNode extends StatementNode
{

	public MoveToPositionStatementNode(Node child1, Node child2, PlayerUnitWME owner)
	{
        super(owner);
		addChild(child1);
		addChild(child2);
	}
	void execute()
	{
		int xPos = ((ValueNode)children.get(0)).execute();
		int yPos = ((ValueNode)children.get(0)).execute();

                game.getCommandQueue().rightClick(owner.getID(), owner.getX() +xPos, owner.getY() + yPos);

                try{Thread.sleep(1000 * owner.getOrderTimer());}
                    catch (Exception e) {System.out.println(e);}

                if (next != null)
                {
                    next.execute();
                }

	}
}