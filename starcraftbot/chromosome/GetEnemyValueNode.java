package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

public class GetEnemyValueNode extends ValueNode
{
        public GetEnemyValueNode(PlayerUnitWME owner)
        {
            super(owner);
        }
	public int execute()
	{
                ArrayList<EnemyUnitWME> enemyUnits = game.getEnemyUnits();
				if (enemyUnits.size() == 0)
				{
					return owner.getID();
				}
                Random r = new Random();

                int enemyIndex = r.nextInt(enemyUnits.size());
				
                return enemyUnits.get(enemyIndex).getID();
	}
}
