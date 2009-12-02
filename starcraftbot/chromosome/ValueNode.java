package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;


public abstract class ValueNode extends Node
{
    public ValueNode(PlayerUnitWME owner)
    {
        super(owner);
    }

    abstract int execute();
}