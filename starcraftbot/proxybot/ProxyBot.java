package starcraftbot.proxybot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import starcraftbot.proxybot.bot.GPStarCraftBot;
import starcraftbot.proxybot.bot.StarCraftBot;

import java.util.*;
import starcraftbot.proxybot.bot.*;

import java.io.*;
/**
 * ProxyBot.
 * 
 * Manages socket connections with StarCraft and handles the
 * agent <-> StarCraft communication.
 */
 
class FitnessComparator implements Comparator
{
	public int compare(Object o1, Object o2)
	{
		return ((Chromosome)o2).getFitness() - ((Chromosome)o1).getFitness();
	}
	
	public boolean equals(Object o1, Object o2)
	{
		return ((Chromosome)o1).getFitness() == ((Chromosome)o2).getFitness();
	}
}
public class ProxyBot {


	public static int POPULATION_SIZE = 8;
	/** speed for the game to run 0 (fastest) to 100 (slowest) */
	public static int GAMESPEED = 0;
	
	public static int TIMETORUN = 4000;
	public static int TIMETORUN2 = 8000;
	
	/** port to start the server socket on */
	public static int port = 12345;
	
	/** allow the user to control units */
	public static boolean allowUserControl = true;
	
	/** turn on complete information */
	public static boolean completeInformation = false;

	/** display agent commands in SC? */
	public static boolean logCommands = false;

	/** display agent commands in SC? */
	public static boolean terrainAnalysis = false;

	/** display the GUI? */
	public static boolean showGUI = false;

	public static boolean showSpeedPanel = false;
	
	public static boolean firstRun = true;

	public static ArrayList<Chromosome> chromosomes = new ArrayList<Chromosome>();
	public static ArrayList<ZealotActor> children = new ArrayList<ZealotActor>();
	
	public static int runCount = 0;
	public static int generationCount = 0;
	
	private long starting;
	public static String best = "-1";
	
	public static void main(String[] args) 
	{
		if (args.length > 0)
		{
			best = args[0];
		}
		new ProxyBot().start();
	}

	/**
	 * Starts the ProxyBot.
	 * 
	 * A server socket is opened and waits for client connections.
	 */
	public void start() {
		try {			
		    ServerSocket serverSocket = new ServerSocket(port);
		    starting = System.currentTimeMillis();
		    while (true) {
			    Socket clientSocket = serverSocket.accept();			
			  	if (best.equals("-1"))
				{
					//nothing
				}
				else
				{
					runCount = 0;
					GAMESPEED = 60;
					TIMETORUN = 120000;
					TIMETORUN2 = 120000;
				}  
			    runGame(clientSocket);
				
				if (++runCount >= POPULATION_SIZE)
				{
					runCount = 0;
					firstRun = false;
					float time = (System.currentTimeMillis() - starting)/1000F;
					starting = System.currentTimeMillis();

					System.out.println("Finished a population run in : " + time);
					//First sort
					Collections.sort(chromosomes, new FitnessComparator());
					saveChromosome();
					FileWriter fw = new FileWriter("log.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					int bestFitness = 0;
					float avgFitness = 0;
					for (Chromosome c : chromosomes)
					{
						avgFitness += c.getFitness();
						if (c.getFitness() > bestFitness)
						{
							bestFitness = c.getFitness();
						}
					}
					avgFitness /= chromosomes.size();
					bw.write("Generation " + generationCount + " finished in " + time + " seconds.  The best fitness was " + bestFitness + " and the average fitness was " + avgFitness +"\n");
					
					try
					{
						evolve();
					}
					catch (Exception e)
					{
						e.printStackTrace();
					}
					generationCount++;
					bw.close();

				}	

				System.out.println("Runcount: " + runCount);
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}	
	private void saveChromosome()
	{
		System.out.println("Saving a chromosome with fitness " + chromosomes.get(0).getFitness());
		String fileName = "chromosome-" + generationCount;
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try
		{
			fos = new FileOutputStream(fileName);
			out = new ObjectOutputStream(fos);
			out.writeObject(chromosomes.get(0));
			out.close();
		}
		catch(IOException ex)
		{
				ex.printStackTrace();
		}
	}
	private void evolve()
	{		

		ArrayList<Chromosome> newPopulation = new ArrayList<Chromosome>();
		//get the top and bottom halves
		ArrayList<Chromosome> top = new ArrayList<Chromosome>();
		ArrayList<Chromosome> bottom = new ArrayList<Chromosome>();
		for (int i = 0; i < chromosomes.size() / 2; i++)
		{
			top.add(chromosomes.get(i));
			bottom.add(chromosomes.get(chromosomes.size()-i-1));
		}

		//Keep the top 50%
		for (Chromosome c : top)
		{
			newPopulation.add(new Chromosome(c));
		}
		
		//mutate the top 25% and put them in
		for (int i = 0; i < chromosomes.size() / 4; i++)
		{

			Chromosome c =  new Chromosome(chromosomes.get(i));
			c.mutate();
			newPopulation.add(c);
		}
		
		
		//Now do some crossover on a random 25%
		ArrayList<Integer> rnd = new ArrayList<Integer>();
		for (int i = 0; i < chromosomes.size() / 2; i++)
		{
			rnd.add(i);
		}
		Collections.shuffle(rnd);
		for (int i = 0; i < rnd.size()-1; i++)
		{
			int c1 = rnd.get(i++);
			int c2 = rnd.get(i);
			
			Chromosome c = crossover(chromosomes.get(c1), chromosomes.get(c2));
			newPopulation.add(c);
		}
		
		chromosomes = newPopulation;

	}
	
	
	public Chromosome crossover(Chromosome c1, Chromosome c2)
	{
		return c1.crossover(c2);
	}
	

	/**
	 * Manages communication with StarCraft.
	 */
	private void runGame(Socket socket) {		
		
		StarCraftFrame frame = null;
    	SpeedPanel speedPanel = null;
		final StarCraftBot bot = new GPStarCraftBot();
		
		try {
			// 1. get the initial game information
		    BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
	    	String playerData = reader.readLine();
	
	    	// 2. respond with bot options
	    	String botOptions = (allowUserControl ? "1" : "0") 
	    					  + (completeInformation ? "1" : "0")
	    					  + (logCommands ? "1" : "0")
      					      + (terrainAnalysis ? "1" : "0");
	    	socket.getOutputStream().write(botOptions.getBytes());
			
	    	// 3. get the starting locations and map information
	    	String locationData = reader.readLine();
	    	String mapData = reader.readLine();
	    	
	    	// TA
	    	String chokesData = "Chokes:";
	    	String basesData = "Bases:";
	    	if (terrainAnalysis) {
	    		chokesData = reader.readLine();
	    		basesData = reader.readLine();
	    	}

	    	final Game game = new Game(playerData, locationData, mapData, chokesData, basesData);
	    	boolean firstFrame = true;
	    	
	    	// show the game speed panel
	    	if (showSpeedPanel) {
	    		speedPanel = new SpeedPanel(game);
	    	}
	    	else {
	    		game.getCommandQueue().setGameSpeed(GAMESPEED);
	    	}
	    	
	    	// 4. game updates
			long startTime = System.currentTimeMillis();
			boolean running = true;
			Thread botThread = null;
	    	while (running) 
			{
	    		String update = reader.readLine();
				long elapsedTime = System.currentTimeMillis() - startTime;				
	    		if (update == null) {
	    			break;
	    		}
	    		else {	    				    			
	    			// update the game
	    			game.update(update);	    			

	    			if (firstFrame) {	    				
	    				firstFrame = false;
	    				
	    				// start the agent
	    				botThread = new Thread() {
	    					public void run() {
	    	    				bot.start(game);
	    					}
	    				};
						botThread.start();

	    				// initialize the GUI
	    				if (showGUI) {
		    				frame = new StarCraftFrame(game);
		    			}
	    			}
					
					if (elapsedTime >= TIMETORUN2 || game.getPlayerUnits().size() == 0 || ( elapsedTime >= TIMETORUN && game.getEnemyUnits().size() == 0))
					{
						System.out.println("Restarting");
						game.getCommandQueue().restart();
						botThread.stop();
						running = false;
					}
					
	    				    			
	    			// 5. send commands
	    	    	socket.getOutputStream().write(game.getCommandQueue().getCommands().getBytes());
	    	    	
	    			if (frame != null) {
	    				frame.repaint();
	    			}
	    		}
	    	}
	    	
	    	// wait for game to terminate
	    	System.out.println("Game Ended");
		}
		catch (SocketException e) {
			System.out.println("StarCraft has disconnected");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
			// stop the bot
			if (bot != null) {
				bot.stop();
			}
			
			// close the frame
			if (frame != null) {
				frame.stop();
			}
			
			// close the speed panel
			if (speedPanel != null) {
				speedPanel.stop();
			}
		}
	}
}
