package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;

public abstract class StatementNode extends Node
{

        public StatementNode(Unit owner)
        {
            super(owner);
        }
	abstract void execute();

        public void run()
        {
            execute();
        }
}
