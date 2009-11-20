/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package starcraftbot.proxybot;

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
        this.routine = routine;
    }

    public void run()
    {
        while (true)
        {
            routine.run();
        }
    }
}
