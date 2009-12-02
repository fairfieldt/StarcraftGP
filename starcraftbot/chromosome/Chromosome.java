/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.wmes.unit.*;

/**
 *
 * @author csuser
 */
public class Chromosome
{
    private final int ROUTINESIZE = 10;
    private int numUnits;
    private PlayerUnitWME owner;
    private ArrayList<StatementNode> routines = new ArrayList<StatementNode>();
    private int ifDepth = 0;
    Random r = new Random();
    public Chromosome(int numUnits, ArrayList<PlayerUnitWME> units)
    {
        this.numUnits = numUnits;
        for (PlayerUnitWME unit : units)
        {
            owner = unit;
			StatementNode routine = getRoutine();
							
            routines.add(routine);
        }
    }

    public StatementNode get(int pos)
    {
        StatementNode n = routines.get(pos);
        while (n.next != null)
        {
            n = (StatementNode)n.next;
        }
        return routines.get(pos);
    }

    private StatementNode getRoutine()
    {
        int statements = 0;
        StatementNode head = null;
        while (statements < ROUTINESIZE)
        {
            StatementNode node = getStatementNode();
            node.next = head;
            head = node;
            statements++;			
        }

        return head;
    }


	private  StatementNode getStatementNode()
	{
		StatementNode n = null;
		int type = r.nextInt(8);

		switch(type)
		{
			case 0:
				n = new MoveToPositionStatementNode(getValueNode(), getValueNode(), owner);
				break;
                         
			case 1:
				n = new MoveToUnitStatementNode(getUnitValueNode(), owner);
				break;
			case 2:
				n = new MoveToPositionStatementNode(getValueNode(), getValueNode(), owner);
				break;
			case 3:
				n = new RunFromUnitStatementNode(getUnitValueNode(), owner);
				break;
			case 4:
				n = new StopStatementNode(owner);
				break;
			case 5:
				n = new WaitStatementNode(new NumberValueNode(owner), owner);
				break;
			default:
				ifDepth++;
				if (ifDepth > 0)
				{
					n = new StopStatementNode(owner);
					ifDepth = 0;
				}
				else
				{
					n = getIfStatementNode();
				}
		}
		return n;

	}

	public  ValueNode getValueNode()
	{
		ValueNode n = null;
		int type = r.nextInt(9);

		switch(type)
		{
			case 0:
				n = new MyHealthValueNode(owner);
				break;
			case 1:
				n = new UnitHealthValueNode(getUnitValueNode(), owner);
				break;
			case 2:
				n = new MyXValueNode(owner);
				break;
			case 3:
				n = new MyYValueNode(owner);
				break;
			case 4:
				n = new NumberValueNode(owner);
				break;
			case 5:
				n = new NumberValueNode(owner);
				break;
			case 6:
				n = new EnemyCountValueNode(owner);
				break;
			case 7:
				n = new FriendCountValueNode(owner);
				break;
			case 8:
				n = new UnitDistanceValueNode(getUnitValueNode(), owner);
				break;
		}
		return n;
	}

	public ValueNode getUnitValueNode()
	{
		ValueNode n = null;
		int type = r.nextInt(2);
		if (type == 0)
		{
			n = new GetEnemyValueNode(owner);
		}
		else
		{
			n = new GetFriendValueNode(owner);
		}
		return n;
	}

	public IfStatementNode getIfStatementNode()
	{
		IfStatementNode n = new IfStatementNode(getBooleanNode(), owner);

		int statementCount = r.nextInt(10);
		for (int i = 0; i < statementCount; i++)
		{
			n.addChild(getStatementNode());
		}
		return n;
	}

	public BooleanNode getBooleanNode()
	{
		BooleanNode n = null;
		int type = r.nextInt(3);

		switch(type)
		{
			case 0:
				n = new FriendNearBooleanNode(owner);
				break;
			case 1:
				n = new EnemyNearBooleanNode(owner);
				break;
			case 2:
				n = new EnemyInViewBooleanNode(owner);
				break;
		}
		return n;

	}
}
