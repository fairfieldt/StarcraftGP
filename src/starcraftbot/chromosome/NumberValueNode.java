package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
public class NumberValueNode extends ValueNode
{
	int value;

	public NumberValueNode(Unit owner)
	{
                super(owner);
		Random r = new Random();

		value = r.nextInt(25);
	}
	int execute()
	{
		return value;
	}
}