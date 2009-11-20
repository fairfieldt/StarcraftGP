package starcraftbot.proxybot;

import starcraftbot.proxybot.Constants.Order;
/**
 * Throw in your bot code here.
 */

import java.util.*;
import starcraftbot.chromosome.*;

public class StarCraftAgent
{
    static ArrayList<Unit> units;

    public static Unit getUnit(int unitID)
    {
        Unit u = null;
        for (Unit unit : units)
        {
            if (unit.getID() == unitID)
            {
                u = unit;
                break;
            }
        }
        return u;
    }
	public void start()
        {
		
		ProxyBot proxyBot = ProxyBot.getProxy();
                int playerID = proxyBot.getPlayerID();


                while (true)
                {
                    units = proxyBot.getUnits();
                    ArrayList<Unit> myUnits = new ArrayList<Unit>();
                    for (Unit unit : units)
                    {
                        if (unit.getPlayerID() == playerID)
                        {
                            myUnits.add(unit);
                        }
                    }
                    Chromosome c = new Chromosome(9, myUnits);

                    ArrayList<Thread> threads = new ArrayList<Thread>();
                    for(int i = 0; i < 9; i++)
                    {
                        ZealotActor actor = new ZealotActor(c.get(i));
                        Thread t = new Thread(actor);
                        t.start();
                        threads.add(t);
                    }
                    try {Thread.sleep(28000);}
                    catch (Exception e){System.out.println(e);}

                    for (Thread t : threads)
                    {
                        t.stop();
                    }
                    
                    System.out.println("Units left: " + proxyBot.getUnits().size());
                    try {Thread.sleep(2000);}
                    catch (Exception e){System.out.println(e);}
                }
	}
}
