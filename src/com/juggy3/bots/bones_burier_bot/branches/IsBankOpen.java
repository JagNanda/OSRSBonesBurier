package com.juggy3.bots.bones_burier_bot.branches;

import com.juggy3.bots.bones_burier_bot.BoneBurier;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

/**
 * NOTES:
 * Checks if bank is open
 */
public class IsBankOpen extends BranchTask
{

    private BoneBurier bot;

    public IsBankOpen(BoneBurier bot)
    {
        this.bot = bot;
    }

    @Override
    public boolean validate()
    {
        return Bank.isOpen();
    }

    @Override
    public TreeTask failureTask()
    {
        return bot.haveBonesInInv;
    }

    @Override
    public TreeTask successTask()
    {
        return bot.haveBonesInBank;
    }
}
