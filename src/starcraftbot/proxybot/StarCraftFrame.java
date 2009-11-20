package starcraftbot.proxybot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * GUI for showing the ProxyBot's view of the game state.
 */
public class StarCraftFrame extends JPanel {

	/** draw object IDs */
	private static boolean drawIDs = true;

	/** draw the starting locations */
	private static boolean drawStartSpots = true;
	
	/** draw mineral and gas locations */
	private static boolean drawResources = true;

	/** reference to the proxy bot */
	private ProxyBot proxyBot;
	
	/** pixels per map tile, StarCraft is 32 */
	private int tileSize = 6;
	
	/** height of the resource panel */
	private int panelHeight = 30;

	/** font size for unit ids */
	private int textSize = 8;

	/**
	 * Constructs a JFrame and draws the ProxyBot's state.
	 */
	public StarCraftFrame() {
		proxyBot = ProxyBot.getProxy();
		
		int width = proxyBot.getMap().getMapWidth();
		int height = proxyBot.getMap().getMapHeight();
		setPreferredSize(new Dimension(tileSize*width, tileSize*height + panelHeight));
		
		JFrame frame = new JFrame("Proxy Bot");
		frame.add(this);
		frame.pack();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocation(640, 0);
		frame.setVisible(true);
	}
	
	public void start() {
		while (true) {
			try {
				Thread.sleep(25);
				repaint();
			}
			catch (Exception e) {}
		}
	}
	
	public void paint(Graphics g) {
		if (proxyBot.getUnits() == null) {
			return;
		}
				
		// tile set
		Map map = proxyBot.getMap();
		for (int y=0; y<map.getMapHeight(); y++) {
			for (int x=0; x<map.getMapWidth(); x++) {
				int walkable = map.isWalkable(x, y) ? 1 : 0;
				int buildable = map.isBuildable(x, y) ? 1 : 0;
				int height = map.getHeight(x, y);
				
				int c = (70*walkable + 60*buildable + 50*height);
				g.setColor(new Color(c,c,(int)(c*3/4)));
				g.fillRect(x*tileSize, panelHeight  + y*tileSize, tileSize, tileSize);
			}
		}

		// Starting locations
		if (drawStartSpots) {
			g.setColor(Color.ORANGE);
			for (StartingLocation location : proxyBot.getStartingLocations()) {
				g.fillRect(location.getX()*tileSize, panelHeight  + location.getY()*tileSize, 4*tileSize, 3*tileSize);				
			}
		}
		
		// minerals
		if (drawResources) {
			g.setColor(new Color(0,255,255));
			for (Unit unit : proxyBot.getUnits()) {
				if (unit.getType().getId() == Constants.Resource_Mineral_Field) {
					g.fillRect(unit.getX()*tileSize, panelHeight  + unit.getY()*tileSize, tileSize, tileSize);				
				}
			}
		}
		
		// gas
		if (drawResources) {
			g.setColor(new Color(0,128,0));
			for (Unit unit : proxyBot.getUnits()) {
				if (unit.getType().getId() == Constants.Resource_Vespene_Geyser) {
					g.fillRect(unit.getX()*tileSize, panelHeight  + unit.getY()*tileSize, 
							unit.getType().getTileWidth()*tileSize, unit.getType().getTileHeight()*tileSize);				
				}
			}
		}
		
		// enemy units
		g.setColor(new Color(255,0,0));
		for (Unit unit : proxyBot.getUnits()) {
			if (unit.getPlayerID() == proxyBot.getEnemyID()) {
				g.fillRect(unit.getX()*tileSize, panelHeight  + unit.getY()*tileSize, 
						unit.getType().getTileWidth()*tileSize, unit.getType().getTileHeight()*tileSize);				
			}
		}		
		
		// player units
		g.setColor(new Color(0,255,0));
		for (Unit unit : proxyBot.getUnits()) {
			if (unit.getPlayerID() == proxyBot.getPlayerID()) {
				g.fillRect(unit.getX()*tileSize, panelHeight  + unit.getY()*tileSize, 
						unit.getType().getTileWidth()*tileSize, unit.getType().getTileHeight()*tileSize);				
			}
		}
		
		// status panel
		g.setColor(new Color(255,255,255));
		g.fillRect(0, 0, getWidth(), panelHeight); 
		
		// minerals
		g.setColor(new Color(0,0,255));
		g.fillRect(5, 10, 10, 10);
		g.setColor(new Color(0,0,0));
		g.drawRect(5, 10, 10, 10);
		g.setColor(new Color(0,0,0));
		g.drawString("" + proxyBot.getPlayer().getMinerals(), 25, 20);

		// gas
		g.setColor(new Color(0,255,0));
		g.fillRect(105, 10, 10, 10);
		g.setColor(new Color(0,0,0));
		g.drawRect(105, 10, 10, 10);
		g.setColor(new Color(0,0,0));
		g.drawString("" + proxyBot.getPlayer().getGas(), 125, 20);
		
		// supply
		g.setColor(new Color(0,0,0));
		g.drawString((proxyBot.getPlayer().getSupplyUsed()/2) + "/" 
  				   + (proxyBot.getPlayer().getSupplyTotal()/2), 200, 20);
		
		// unit IDs
		if (drawIDs) {
			g.setColor(new Color(255,255,255));
			g.setFont(new Font("ariel", 0, textSize));
			for (Unit unit : proxyBot.getUnits()) {
				g.drawString("" + unit.getID(), unit.getX()*tileSize, panelHeight  + unit.getY()*tileSize + textSize - 2);
			}		
		}
	}
}
