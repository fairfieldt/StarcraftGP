package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;

public class StopStatementNode extends StatementNode
{

        public StopStatementNode(Unit owner)
        {
            super(owner);
        }
	void execute()
	{
                proxyBot.stop(owner.getID());
                try {Thread.sleep(250);}
                catch (Exception e) {System.out.println(e);}

                  if (next != null)
                {
                    next.execute();
                }
	}


}
