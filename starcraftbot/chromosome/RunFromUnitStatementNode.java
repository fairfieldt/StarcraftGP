package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

public class RunFromUnitStatementNode extends StatementNode
{

	public RunFromUnitStatementNode(Node child, PlayerUnitWME owner)
	{
                super(owner);
		addChild(child);
	}
	void execute()
	{
		int unitId = ((ValueNode)children.get(0)).execute();

                UnitWME unit = getUnit(unitId);
                if (unit != null)
                {
                    int xMove = 20;
                    int yMove = 20;
                    if (unit.getX() > owner.getX())
                    {
                        xMove = -1 * xMove;
                    }
                    if (unit.getY() > owner.getY())
                    {
                        yMove = -1 * yMove;
                    }

                    game.getCommandQueue().rightClick(owner.getID(), owner.getX() + xMove, owner.getY() +yMove);
                    try { Thread.sleep(1000 * owner.getOrderTimer());}
                    catch (Exception e) {System.out.println(e);}
                }

                  if (next != null)
                {
                    next.execute();
                }
	}
	
}
