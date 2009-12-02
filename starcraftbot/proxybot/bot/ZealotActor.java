/*
* To change this template, choose Tools | Templates
* and open the template in the editor.
*/
 
package starcraftbot.proxybot.bot;
 
import starcraftbot.chromosome.*;
/**
*
* @author csuser
*/
public class ZealotActor implements Runnable
{
    private StatementNode routine;
    public ZealotActor(StatementNode routine)
    {
		System.out.println("Made an actor");
        this.routine = routine;
    }
 
    public void run()
    {
		System.out.println("Thread starting");
		routine.print();
        while (true)
        {
			try{ Thread.sleep(5);}catch (Exception e){}
            routine.run();
			//System.out.println("Finished a routine run");
        }
    }
}
 