package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;

public abstract class Node
{
        
	ArrayList<Node> children = new ArrayList<Node>();
        protected ProxyBot proxyBot = ProxyBot.getProxy();
        protected Unit owner;

        public StatementNode next;
        public Node(Unit owner)
        {
            this.owner = owner;
        }

	void addChild(Node child)
	{
		children.add(child);
	}

        protected int getUnitCount(int playerID)
        {
            ArrayList<Unit> allUnits = proxyBot.getUnits();
            int count = 0;
            for (Unit unit : allUnits)
            {
                if (unit.getPlayerID() == playerID)
                {
                    count++;
                }
            }
            return count;
        }

}