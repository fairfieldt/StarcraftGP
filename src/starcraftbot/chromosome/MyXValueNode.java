package starcraftbot.chromosome;

import starcraftbot.proxybot.*;

public class MyXValueNode extends ValueNode
{
        public MyXValueNode(Unit owner)
        {
            super(owner);
        }
	public int execute()
	{
		return owner.getX();
	}
}
