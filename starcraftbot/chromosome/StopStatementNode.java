package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

public class StopStatementNode extends StatementNode
{

        public StopStatementNode(PlayerUnitWME owner)
        {
            super(owner);
        }
	void execute()
	{
				System.out.println("Stopping");
                game.getCommandQueue().stop(owner.getID());
                try {Thread.sleep(250);}
                catch (Exception e) {System.out.println(e);}

                if (next != null)
                {
                    next.execute();
                }
	}


}
