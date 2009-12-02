package starcraftbot.proxybot.bot;

import starcraftbot.proxybot.Game;
import starcraftbot.proxybot.Constants.Order;
import starcraftbot.proxybot.Constants.Race;
import starcraftbot.proxybot.wmes.UnitTypeWME;
import starcraftbot.proxybot.wmes.UnitTypeWME.UnitType;
import starcraftbot.proxybot.wmes.unit.UnitWME;
import starcraftbot.proxybot.wmes.unit.*;


import starcraftbot.chromosome.*;


import java.util.*;
/**
 * Example implementation of the StarCraftBot.
 * 
 * This build will tell workers to mine, build additional workers,
 * and build additional supply units.
 */
public class GPStarCraftBot implements StarCraftBot {

	/** specifies that the agent is running */
	boolean running = true;
	private Game game;
	private ArrayList<Thread> unitThreads;
	/**
	 * Starts the bot.
	 * 
	 * The bot is now the owner of the current thread.
	 */
	public void start(Game game) 
	{
		this.game = game;
		Node.game = game;
		// run until told to exit
		ArrayList<PlayerUnitWME> units =  game.getPlayerUnits();
		System.out.println("Got " + units.size() + " units.");
		unitThreads = new ArrayList<Thread>();
		Chromosome c = new Chromosome(units.size(), units);
		System.out.println("Got a chromosome");
		for (int i = 0; i < units.size(); i++)
		{
			ZealotActor actor = new ZealotActor(c.get(i));
			Thread t = new Thread(actor);
			t.start();
			System.out.println("Started thread " + i);
			unitThreads.add(t);
		}
		while (running) 
		{
			try {
				Thread.sleep(1000);
			}
			catch (Exception e) {}	
			
		}
	}

	/**
	 * Tell the main thread to quit.
	 */
	public void stop() 
	{
		for (Thread t : unitThreads)
		{
			t.stop();
		}
		running = false;
	}
}
