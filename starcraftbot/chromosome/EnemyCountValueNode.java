package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.wmes.unit.*;



public class EnemyCountValueNode extends ValueNode
{
        public EnemyCountValueNode(PlayerUnitWME owner)
        {
            super(owner);
        }
	public int execute()
	{
        return game.getEnemyUnits().size();
    }
}
