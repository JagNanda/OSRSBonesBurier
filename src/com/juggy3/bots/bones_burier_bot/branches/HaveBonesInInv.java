package com.juggy3.bots.bones_burier_bot.branches;

import com.juggy3.bots.bones_burier_bot.BoneBurier;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.framework.tree.BranchTask;
import com.runemate.game.api.script.framework.tree.TreeTask;

public class HaveBonesInInv extends BranchTask
{

    private BoneBurier bot;

    public HaveBonesInInv(BoneBurier bot)
    {
        this.bot = bot;
    }

    @Override
    public boolean validate()
    {
        if(Inventory.contains(bot.bonesSelected) && Inventory.containsOnly(bot.bonesSelected)) //containsOnly returns true if inv is empty
        {
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
        return bot.openBank;
    }

    @Override
    public TreeTask successTask()
    {
        return bot.buryBones;
    }
}
