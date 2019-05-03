package com.juggy3.bots.bones_burier_bot.branches;

import com.juggy3.bots.bones_burier_bot.BoneBurier;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HaveBonesInBank extends BranchTask
{
    BoneBurier bot;

    public HaveBonesInBank(BoneBurier bot)
    {
        this.bot = bot;
    }

    @Override
    public boolean validate()
    {
        return Bank.contains(bot.bonesSelected);
    }

    @Override
    public TreeTask failureTask()
    {
        bot.currentTaskString = "No " + bot.bonesSelected + " in bank.";
        bot.updateInfo();
        bot.stop("No " + bot.bonesSelected + " in bank.");
        return bot.emptyLeaf;
    }

    @Override
    public TreeTask successTask()
    {
        return bot.withdrawBones;
    }
}
