package starcraftbot.chromosome;

import starcraftbot.proxybot.*;
import java.util.*;
import starcraftbot.proxybot.wmes.unit.*;


public abstract class BooleanNode extends Node
{
        public BooleanNode(PlayerUnitWME owner)
        {
            super(owner);
        }
	abstract boolean execute();

}