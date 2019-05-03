package com.juggy3.bots.bones_burier_bot.branches;

import com.juggy3.bots.bones_burier_bot.BoneBurier;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class Root extends BranchTask
{

    private BoneBurier bot;

    public Root(BoneBurier bot)
    {
        this.bot = bot;
    }

    @Override
    public boolean validate()
    {
        return bot.guiWait;
    }

    @Override
    public TreeTask failureTask()
    {
        bot.updateInfo();
        return bot.inBankArea;
    }

    @Override
    public TreeTask successTask()
    {
        if(bot.stopWatch.getRuntime() > 120000)
        {
            bot.stop("User did not start bot within 2 minutes.");
        }
        return bot.emptyLeaf;
    }
}
