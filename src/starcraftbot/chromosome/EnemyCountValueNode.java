package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.Unit;


public class EnemyCountValueNode extends ValueNode
{
        public EnemyCountValueNode(Unit owner)
        {
            super(owner);
        }
	public int execute()
	{
            int enemyID = proxyBot.getEnemyID();

            return getUnitCount(enemyID);
        }
}
