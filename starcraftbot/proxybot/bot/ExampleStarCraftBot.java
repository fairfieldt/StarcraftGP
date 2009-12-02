package starcraftbot.proxybot.bot;

import starcraftbot.proxybot.Game;
import starcraftbot.proxybot.Constants.Order;
import starcraftbot.proxybot.Constants.Race;
import starcraftbot.proxybot.wmes.UnitTypeWME;
import starcraftbot.proxybot.wmes.UnitTypeWME.UnitType;
import starcraftbot.proxybot.wmes.unit.UnitWME;
/**
 * Example implementation of the StarCraftBot.
 * 
 * This build will tell workers to mine, build additional workers,
 * and build additional supply units.
 */
public class ExampleStarCraftBot implements StarCraftBot {

	/** specifies that the agent is running */
	boolean running = true;
	private Game game;
	/**
	 * Starts the bot.
	 * 
	 * The bot is now the owner of the current thread.
	 */
	public void start(Game game) 
	{
		this.game = game;
		// run until told to exit
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
		running = false;
	}
}
