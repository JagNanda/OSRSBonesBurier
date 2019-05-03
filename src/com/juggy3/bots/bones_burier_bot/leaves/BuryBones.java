package com.juggy3.bots.bones_burier_bot.leaves;

import com.juggy3.bots.bones_burier_bot.BoneBurier;
import com.runemate.game.api.hybrid.local.hud.interfaces.Inventory;
import com.runemate.game.api.hybrid.local.hud.interfaces.SpriteItem;
import com.runemate.game.api.script.Execution;
import com.runemate.game.api.script.framework.tree.LeafTask;

public class BuryBones extends LeafTask
{
    private BoneBurier bot;

    public BuryBones(BoneBurier bot)
    {
        this.bot = bot;
    }

    @Override
    public void execute()
    {
        bot.currentTaskString = "Burying Bones";
        SpriteItem firstBones = Inventory.getItems(bot.bonesSelected).first();
        if (firstBones.click())
        {
            Execution.delayUntil((() -> !firstBones.isVisible()), 630, 980);
        }
    }
}
