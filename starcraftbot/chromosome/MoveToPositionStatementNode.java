package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;


public class MoveToPositionStatementNode extends StatementNode
{

	private int xPos;
	private int yPos;

	public MoveToPositionStatementNode(Node child1, Node child2, PlayerUnitWME owner)
	{
        super(owner);
		addChild(child1);
		addChild(child2);
	}
	
	public MoveToPositionStatementNode(int x, int y, PlayerUnitWME owner)
	{
        super(owner);
		xPos = x;
		yPos = y;
	}
	void execute()
	{
		//int xPos = ((ValueNode)children.get(0)).execute();
		//int yPos = ((ValueNode)children.get(0)).execute();

                game.getCommandQueue().rightClick(owner.getID(), xPos, yPos);

                try{Thread.sleep(1000 * owner.getOrderTimer());}
                    catch (Exception e) {System.out.println(e);}

                if (next != null)
                {
                    next.execute();
                }

	}
}