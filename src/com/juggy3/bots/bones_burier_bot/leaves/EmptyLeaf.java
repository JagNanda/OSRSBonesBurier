package com.juggy3.bots.bones_burier_bot.leaves;

import com.juggy3.bots.bones_burier_bot.BoneBurier;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class EmptyLeaf extends LeafTask
{

    private BoneBurier bot;

    public EmptyLeaf(BoneBurier bot)
    {
        this.bot = bot;
    }

    @Override
    public void execute()
    {
        System.out.println("Returning empty leaf");
    }
}
