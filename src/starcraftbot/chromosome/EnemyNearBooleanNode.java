package starcraftbot.chromosome;

import starcraftbot.proxybot.*;

public class EnemyNearBooleanNode extends BooleanNode
{
        public EnemyNearBooleanNode(Unit owner)
        {
            super(owner);
        }
	boolean execute()
	{
		//FIXME: Loop through the enemy units and see if they are within some
                // distance of me.

                return true;
	}
}