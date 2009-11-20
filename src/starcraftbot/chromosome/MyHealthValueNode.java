package starcraftbot.chromosome;

import starcraftbot.proxybot.*;

public class MyHealthValueNode extends ValueNode
{
        public MyHealthValueNode(Unit owner)
        {
            super(owner);
        }
	public int execute()
	{
		return owner.getHitPoints();
	}
}