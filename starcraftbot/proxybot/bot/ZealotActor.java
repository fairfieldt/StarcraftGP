/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
 
package starcraftbot.proxybot.bot;
import java.io.*;
 
import starcraftbot.chromosome.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;

import starcraftbot.proxybot.bot.actions.*;

import starcraftbot.proxybot.command.Command.StarCraftCommand;

import java.util.*;
public class ZealotActor implements Serializable, Runnable 
{
	transient private Game game;
	private int unitNumber;
	private int lastHealth = 0;
	
	private int runDist = 2;
	transient private EnemyUnitWME target = null;
	
	private Random r;
	
	private Action noTargetAction;
	private Action targetAction;
	private Action healthOKAction;
	private Action healthLowAction;
	private Action friendNearAction;
	private Action friendNotNearAction;
	
	public int fitness;
	
    public ZealotActor(Game game, int unitNumber)
    {
		this.game = game;
		this.unitNumber = unitNumber;
		r = new Random(unitNumber * System.currentTimeMillis());
		
    }
	
	public ZealotActor(ZealotActor z)
	{
		this.unitNumber = z.getUnitNumber();
		try
		{
			this.noTargetAction = (Action) z.getNoTargetAction().clone();
			this.targetAction = (Action) z.getTargetAction().clone();
			this.healthLowAction = (Action) z.getHealthLowAction().clone();
			this.friendNotNearAction = (Action) z.getFriendNotNearAction().clone();
			
		}
		catch (Exception e) {System.out.println("Copying an actor");System.out.println(e);}
		r = new Random(unitNumber * System.currentTimeMillis());

		
		
	}
 
    public void run()
    {
		if (ProxyBot.firstRun)
		{
			firstRunSetup();
		}

        while (true)
        {
			ArrayList<PlayerUnitWME> playerUnits = game.getPlayerUnits();
			
			PlayerUnitWME unit = getPlayerUnit(unitNumber);
			if (unit == null)
			{
				System.out.println("Unit " + unitNumber + " is dead");
				return;
			}
			updateActions(unit);

			/*Depending on the precedence of the Events, an action will be chosen*/
			Action action = getActionToRun(unit);
			if (action != null)
			{
				action.run();
			}
			
			try{ Thread.sleep((ProxyBot.GAMESPEED+1) * 10);}catch (Exception e){}

        }
    }
	

	private void firstRunSetup()
	{
		Action a1 = new MoveToPositionAction(getPlayerUnit(unitNumber), new NumCoordAction(2), new NumCoordAction(1));
		Action a2 = new AttackAction(getPlayerUnit(unitNumber));

		noTargetAction = a1;//getAction(getPlayerUnit(unitNumber));
		targetAction = a2;//getAction(getPlayerUnit(unitNumber));
		healthOKAction = getAction(getPlayerUnit(unitNumber));
		healthLowAction = getAction(getPlayerUnit(unitNumber));
		friendNearAction = getAction(getPlayerUnit(unitNumber));
		friendNotNearAction = getAction(getPlayerUnit(unitNumber));
		
	}
	private Action getActionToRun(PlayerUnitWME unit)
	{
		/*I'm about to die*/
		if (!healthOK(unit))
		{
			//System.out.println(unitNumber + "'s health is low!");
			return healthLowAction;
		}
		/*there's an enemy near*/
		if (enemyInView())
		{
			//System.out.println(unitNumber + " sees an enemy");
			return targetAction;
		}
		/*there's no friend near*/
		if (!friendNear(unit))
		{
			//System.out.println(unitNumber + " is alone");
			return friendNotNearAction;
		}
		/*there's no enemy near*/
		if (!enemyInView())
		{
			//System.out.println(unitNumber + " needs to find a target");
			return noTargetAction;
		}
		return null;
	}
	
	public void mutateAction()
	{
		int choice = r.nextInt(4);
		
		switch (choice)
		{
			case 0:
				noTargetAction = getAction(null);
				break;
			case 1:
				healthLowAction = getAction(null);
				break;
			case 2:
				targetAction = getAction(null);
				break;
			case 3:
				friendNotNearAction = getAction(null);
				break;
		}
	}
	
	public Action getHealthLowAction()
	{
		return healthLowAction;
	}
	
	public Action getNoTargetAction()
	{
		return noTargetAction;
	}
	
	public Action getTargetAction()
	{
		return targetAction;
	}
	
	public Action getFriendNotNearAction()
	{
		return friendNotNearAction;
	}
	
	public void setHealthLowAction(Action action)
	{
		healthLowAction = action;
	}
	
	public void setNoTargetAction(Action action)
	{
		noTargetAction = action;
	}
	
	public void setTargetAction(Action action)
	{
		targetAction = action;
	}
	
	public void setFriendNotNearAction(Action action)
	{
		friendNotNearAction = action;
	}

	private PlayerUnitWME getPlayerUnit(int unitID)
	{
			PlayerUnitWME unit = null;
			for (PlayerUnitWME p : game.getPlayerUnits())
			{
				if (p.getID() == unitID)
				{
					unit = p;
					break;
				}
			}
			return unit;
	}
	
	private void updateActions(PlayerUnitWME unit)
	{
		noTargetAction.update(unit);
		targetAction.update(unit);
		healthLowAction.update(unit);
		friendNotNearAction.update(unit);
	}
	
	private Action getAction(PlayerUnitWME unit)
	{		
		int choice = r.nextInt(2);
		Action a = null;
		switch (choice)
		{
			case 0:
				CoordAction coord[] = getCoords(unit);
				a = new MoveToPositionAction(unit, coord[0], coord[1]);
				break;
			case 1:
				a = new AttackAction(unit);
				break;
		}
		return a;
	}
	
	private CoordAction[] getCoords(PlayerUnitWME unit)
	{
		return new CoordAction[]{getCoord(unit), getCoord(unit)};
	}
	
	private CoordAction getCoord(PlayerUnitWME unit)
	{
		int choice = r.nextInt(10);
		
		switch (choice)
		{
			case 0:
				return new NumCoordAction(2);
			case 1:
				return new NumCoordAction(-2);
			case 2:
				return new NumCoordAction(1);
			case 3:
				return new NumCoordAction(-1);
			case 4:
				return new FriendXAction(unit);
			case 5:
				return new FriendYAction(unit);
			case 6:
				return new EnemyXAction(unit);
			case 7:
				return new EnemyYAction(unit);
			case 8:
				return new NumCoordAction(r.nextInt(5));
			default:
				return new NumCoordAction(0-r.nextInt(5));
		}			
	}
	
	

	public void update(Game game, int unitNumber)
	{
		this.game = game;
		this.unitNumber = unitNumber;
	}
	
	private boolean enemyInView()
	{
            return (game.getEnemyUnits().size() > 0);
	}
	
	private boolean healthOK(PlayerUnitWME unit)
	{
		int health = unit.getHitPoints() + unit.getShields();
		boolean lowHealth = true;
		if (health < lastHealth)
		{
			lowHealth = health > 60;
		}
		lastHealth = health;
		return lowHealth && true;
	}
	
	private boolean friendNear(PlayerUnitWME unit)
	{
		for (PlayerUnitWME p : game.getPlayerUnits())
		{
			if (unit.distance(p) < 2 && unit.distance(p) != 0)
			{
				return true;
			}
		}
		return false;
	}
	
	public int getUnitNumber()
	{
		return unitNumber;
	}
}

 