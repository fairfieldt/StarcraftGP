package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;


public class EnemyInViewBooleanNode extends BooleanNode
{
        public EnemyInViewBooleanNode(PlayerUnitWME owner)
        {
            super(owner);
        }
	boolean execute()
	{
            return (game.getEnemyUnits().size() > 0);
	}
}