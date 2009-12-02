package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

public class MyXValueNode extends ValueNode
{
        public MyXValueNode(PlayerUnitWME owner)
        {
            super(owner);
        }
	public int execute()
	{
		return owner.getX();
	}
}
