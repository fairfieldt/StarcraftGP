package starcraftbot.chromosome;

import java.util.*;
import starcraftbot.proxybot.*;
import starcraftbot.proxybot.wmes.unit.*;



public abstract class StatementNode extends Node
{

	public StatementNode(PlayerUnitWME owner)
	{
		super(owner);
	}
	abstract void execute();

	public void run()
	{
		execute();
	}
	
	public void print()
	{
		StatementNode s = this;
		do
		{
			System.out.println(s);
			s = (StatementNode)s.next;
		}
		while (s != null);
	}
}
