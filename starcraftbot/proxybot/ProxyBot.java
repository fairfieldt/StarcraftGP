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
		return ((Chromosome)o1).getFitness() - ((Chromosome)o2).getFitness();
	}
	
	public boolean equals(Object o1, Object o2)
	{
		return ((Chromosome)o1).getFitness() == ((Chromosome)o2).getFitness();
	}
}
public class ProxyBot {


	public static int POPULATION_SIZE = 50;
	/** speed for the game to run 0 (fastest) to 100 (slowest) */
	public static int GAMESPEED = 0;
	
	public static int TIMETORUN = 8000;
	
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
	
	public static void main(String[] args) 
	{
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
		    
		    while (true) {
			    System.out.println("Waiting for client connection");
			    Socket clientSocket = serverSocket.accept();			
			    
			    System.out.println("Client connected");		    
			    runGame(clientSocket);
				if (++runCount >= POPULATION_SIZE)
				{
					runCount = 0;
					firstRun = false;
					System.out.println("Finished a population run");
					try
					{
						evolve();
					}
					catch (Exception e)
					{
						System.out.println(e);
					}
					FileWriter fw = new FileWriter("log.txt", true);
					BufferedWriter bw = new BufferedWriter(fw);
					int bestFitness = 0;
					int avgFitness = 0;
					for (Chromosome c : chromosomes)
					{
						avgFitness += c.getFitness();
						if (c.getFitness() > bestFitness)
						{
							bestFitness = c.getFitness();
						}
					}
					avgFitness /= chromosomes.size();
					bw.write("Generation " + generationCount + " finished.  The best fitness was " + bestFitness + " and the average fitness was " + avgFitness +"\n");
					bw.close();
					
					saveChromosomes();
					generationCount++;
				}	
				System.out.println("Runcount: " + runCount);
		    }
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}	
	private void saveChromosomes()
	{
		// Write the top 25% of chromosomes to disk
		for (int i = 0; i < POPULATION_SIZE/4; i++)
		{
			String fileName = "chromosome" + generationCount + "-" + i;
			FileOutputStream fos = null;
			ObjectOutputStream out = null;
			try
			{
				fos = new FileOutputStream(fileName);
				out = new ObjectOutputStream(fos);
				out.writeObject(chromosomes.get(i));
				out.close();
			}
			catch(IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	private void evolve()
	{
		System.out.println("We have " + chromosomes.size() + " chromosomes");
		
		// Now get the top and bottom 25%
		
		//First sort
		Collections.sort(chromosomes, new FitnessComparator());
		ArrayList<Chromosome> top = new ArrayList<Chromosome>();
		ArrayList<Chromosome> bottom = new ArrayList<Chromosome>();
		for (int i = 0; i < chromosomes.size() / 4; i++)
		{
			bottom.add(chromosomes.get(i));
			top.add(chromosomes.get(chromosomes.size()-i-1));
			System.out.println("Top: " + top.get(i).getFitness() + " Bottom: " + bottom.get(i).getFitness());
		}
		
		// Keep the top 25%
		for (int i = 0; i < top.size(); i++)
		{
			chromosomes.set(i, top.get(i));
		}
		// Mutate the top 25% and put them in the new pop.
		for (int i = 0; i < top.size(); i++)
		{
			for (Chromosome c : top)
			{
				c.mutate();
				chromosomes.set((POPULATION_SIZE / 4) + i, c);
			}
		}
		// Crossover the top and bottom 25% randomly and put the result in the population.
		ArrayList<Chromosome> combo = new ArrayList<Chromosome>();
		combo.addAll(top);
		combo.addAll(bottom);
		Collections.shuffle(combo);
		
		for (int i = 0; i < POPULATION_SIZE /4; i++)
		{
			chromosomes.set((POPULATION_SIZE/2) + i, crossover(combo.get(i), combo.get(combo.size() -1 -i)));
		}
		
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
	    	System.out.println("Sending bot options");
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

	    	System.out.println("Game starting");
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
			System.out.println("Start time: " + startTime);
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
					
					if (elapsedTime >= TIMETORUN || game.getPlayerUnits().size() == 0)
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
