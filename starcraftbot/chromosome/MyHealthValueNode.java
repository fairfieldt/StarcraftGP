package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

public class MyHealthValueNode extends ValueNode
{
        public MyHealthValueNode(PlayerUnitWME owner)
        {
            super(owner);
        }
	public int execute()
	{
		return owner.getHitPoints();
	}
}