package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;

public class GetEnemyValueNode extends ValueNode
{
        public GetEnemyValueNode(Unit owner)
        {
            super(owner);
        }
	public int execute()
	{
		int enemyID = proxyBot.getEnemyID();
                int enemyCount = getUnitCount(enemyID);
                if (enemyCount <= 0)
                {
                    return owner.getID();
                }
                Random r = new Random();

                int enemyIndex = r.nextInt(enemyCount);

                ArrayList<Unit> units = proxyBot.getUnits();

                return units.get(enemyIndex).getID();

	}
}
