package starcraftbot.proxybot.bot;

import starcraftbot.proxybot.Game;
import starcraftbot.proxybot.Constants.Order;
import starcraftbot.proxybot.Constants.Race;
import starcraftbot.proxybot.wmes.UnitTypeWME;

import starcraftbot.proxybot.wmes.UnitTypeWME.UnitType;
import starcraftbot.proxybot.wmes.unit.UnitWME;
import starcraftbot.proxybot.wmes.unit.*;

import starcraftbot.proxybot.bot.actions.*;

import starcraftbot.chromosome.*;

import starcraftbot.proxybot.*;


import java.util.*;
import java.io.*;
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
	private  ArrayList<Thread> actorThreads;
	
	private static ArrayList<PlayerUnitWME> myUnits;
	private static ArrayList<EnemyUnitWME> enemyUnits;
	
	
	/* score stuff*/
	int enemiesLeft = 0;
	int unitsLeft = 0;
	/**
	 * Starts the bot.
	 * 
	 * The bot is now the owner of the current thread.
	 */
	public void start(Game game) 
	{
		this.game = game;
		Action.game = game;

		ArrayList<PlayerUnitWME> units =  game.getPlayerUnits();

		//This means it's the first run and the chromosomes need to be seeded
		if (ProxyBot.firstRun)
		{
			Chromosome c = new Chromosome();
			for (int i = 0; i < units.size(); i++)
			{
				
				ZealotActor actor = new ZealotActor(game, i+1);
				c.addActor(actor);
			}
			ProxyBot.chromosomes.add(c);
		}
		//Otherwise just update the existing ones with the new game state and unit
		else
		{
			for (int i = 0; i < units.size(); i++)
			{
				ProxyBot.chromosomes.get(ProxyBot.runCount).getActor(i).update(game, (i % 9) + 1);
			}
		}
		
		actorThreads = new ArrayList<Thread>();
		for (int i = 0; i <  + units.size(); i++)
		{
			Thread t = new Thread(ProxyBot.chromosomes.get(ProxyBot.runCount).getActor(i));
			actorThreads.add(t);
		}
		for(Thread t : actorThreads)
		{
			t.start();
		}
		while (running) 
		{
			try {
				Thread.sleep(100);
			}
			catch (Exception e) {}	
			
		}
	}

	/**
	 * Tell the main thread to quit.
	 */
	public void stop() 
	{
		for (Thread t : actorThreads)
		{
			t.stop();
		}
		calculateScore();
		updateFitness();
		writeLog();
		System.out.println(unitsLeft + " friendly units left");
		System.out.println(enemiesLeft + " enemy units left");
		running = false;
	}
	private void updateFitness()
	{
		int fitness = (9 - enemiesLeft) * 10;
		if (enemiesLeft == 0)
		{
			fitness += unitsLeft * 10;
		}
			ProxyBot.chromosomes.get(ProxyBot.runCount).setFitness(fitness);
	}
	private void calculateScore()
	{
		unitsLeft = game.getPlayer().getMinerals();
		enemiesLeft = 9 - game.getPlayer().getGas();
		
	}
	
	private void writeLog()
	{
		try
		{
			FileWriter writer = new FileWriter("log.txt", true);
			BufferedWriter bw = new BufferedWriter(writer);
			bw.write("Generation " + ProxyBot.generationCount + ", run " + ProxyBot.runCount + " had a fitness of " + ProxyBot.chromosomes.get(ProxyBot.runCount).getFitness() + "\n");
			bw.write("It had " + unitsLeft + " units left." + "\n");
			bw.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}
	}
}
