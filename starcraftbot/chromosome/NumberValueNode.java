package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

public class NumberValueNode extends ValueNode
{
	int value;

	public NumberValueNode(PlayerUnitWME owner)
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