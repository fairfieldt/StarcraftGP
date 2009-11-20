package starcraftbot.chromosome;

import starcraftbot.proxybot.*;

public class EnemyInViewBooleanNode extends BooleanNode
{
        public EnemyInViewBooleanNode(Unit owner)
        {
            super(owner);
        }
	boolean execute()
	{
            return (getUnitCount(proxyBot.getEnemyID()) > 0);
	}
}