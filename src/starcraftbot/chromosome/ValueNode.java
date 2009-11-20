package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;


public abstract class ValueNode extends Node
{
    public ValueNode(Unit owner)
    {
        super(owner);
    }

    abstract int execute();
}