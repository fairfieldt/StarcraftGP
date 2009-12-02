package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

public class MyYValueNode extends ValueNode
{
        public MyYValueNode(PlayerUnitWME owner)
        {
            super(owner);
        }
	public int execute()
	{
		return owner.getY();
	}
}
