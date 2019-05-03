package com.juggy3.bots.bones_burier_bot.leaves;

import com.juggy3.bots.bones_burier_bot.BoneBurier;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Opens the bank.
 */
public class OpenBank extends LeafTask
{

    private BoneBurier bot;

    public OpenBank(BoneBurier bot)
    {
        this.bot = bot;
    }

    @Override
    public void execute()
    {
        bot.currentTaskString = "Opening Bank";
        if(Bank.open())
        {
            Execution.delayUntil(()->Bank.isOpen());
        }
        if (!Inventory.containsOnly(bot.bonesSelected)) //if bank contains anything other than bones, deposit everything.
        {
            Bank.depositInventory();
        }
    }
}
