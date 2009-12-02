package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

import java.util.*;
public class EnemyNearBooleanNode extends BooleanNode
{
        public EnemyNearBooleanNode(PlayerUnitWME owner)
        {
            super(owner);
        }
	boolean execute()
	{
		ArrayList<EnemyUnitWME> enemyUnits = game.getEnemyUnits();
		
		double dist = 1000;
		for (EnemyUnitWME unit : enemyUnits)
		{
			double newDist = owner.distance(unit);
			dist = newDist < dist?  newDist : dist;
		}
			if (dist <= 5)
			{
                return true;
			}
			return false;
	}
}