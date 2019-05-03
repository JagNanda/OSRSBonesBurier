package com.juggy3.bots.bones_burier_bot.branches;

import com.juggy3.bots.bones_burier_bot.BoneBurier;
import com.runemate.game.api.hybrid.entities.Npc;
import com.runemate.game.api.hybrid.entities.Player;
import com.runemate.game.api.hybrid.local.Camera;
import com.runemate.game.api.hybrid.region.Npcs;
import com.runemate.game.api.hybrid.region.Players;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if in bank.
 * Root Branch
 */
public class InBankArea extends BranchTask
{
    private BoneBurier bot;
    private Npc banker;
    private Player player;


    public InBankArea(BoneBurier bot)
    {
        this.bot = bot;
    }

    @Override
    public boolean validate()
    {
        bot.currentTaskString = "Checking if near banker";
        if (((player = Players.getLocal()) != null))
        {
            banker = Npcs.newQuery().names("Banker").results().nearest();
            if (banker == null) //looking for nearest banker
            {
                bot.stop("Cant find banker");
            }
            if (!banker.isVisible())
            {
                Camera.concurrentlyTurnTo(banker);
            }

            return true;
        }
        else
        {
            return false;
        }
    }

    @Override
    public TreeTask failureTask()
    {
        return bot.emptyLeaf;
    }

    @Override
    public TreeTask successTask()
    {
        return bot.isBankOpen;
    }
}
