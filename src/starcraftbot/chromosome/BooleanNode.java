package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import java.util.*;

public abstract class BooleanNode extends Node
{
        public BooleanNode(Unit owner)
        {
            super(owner);
        }
	abstract boolean execute();

}