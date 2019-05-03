package com.juggy3.bots.bones_burier_bot.leaves;

import com.juggy3.bots.bones_burier_bot.BoneBurier;
import com.runemate.game.api.hybrid.local.hud.interfaces.Bank;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

/**
 * NOTES:
 * Stops the bot.
 */
public class WithdrawBones extends LeafTask {

    private BoneBurier bot;

    public WithdrawBones(BoneBurier bot)
    {
        this.bot = bot;
    }

    @Override
    public void execute() {

        bot.currentTaskString = "Withdrawing Bones";
        int numOfBonesBank = Bank.getQuantity(bot.bonesSelected);
        if(numOfBonesBank < 28)
        {
            bot.withdrawQuantity = numOfBonesBank;
        }
        if(Bank.withdraw(bot.bonesSelected, bot.withdrawQuantity))
        {
            Execution.delayUntil(() -> Inventory.contains(bot.bonesSelected), 500, 1000);
        }
        Bank.close();
    }
}
