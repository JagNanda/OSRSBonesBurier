package com.juggy3.bots.bones_burier_bot;

import com.juggy3.bots.bones_burier_bot.branches.*;
import com.juggy3.bots.bones_burier_bot.leaves.BuryBones;
import com.juggy3.bots.bones_burier_bot.leaves.EmptyLeaf;
import com.juggy3.bots.bones_burier_bot.leaves.OpenBank;
import com.juggy3.bots.bones_burier_bot.leaves.WithdrawBones;
import com.juggy3.bots.bones_burier_bot.ui.BoneBurierGui;
import com.juggy3.bots.bones_burier_bot.ui.BonesInfo;
import com.juggy3.bots.bones_burier_bot.ui.BonesInfoUi;
import com.runemate.game.api.client.embeddable.EmbeddableUI;
import com.runemate.game.api.hybrid.input.Mouse;
import com.runemate.game.api.hybrid.util.StopWatch;
import com.runemate.game.api.hybrid.util.calculations.CommonMath;
import com.runemate.game.api.script.framework.listeners.InventoryListener;
import com.runemate.game.api.script.framework.listeners.SkillListener;
import com.runemate.game.api.script.framework.listeners.events.ItemEvent;
import com.runemate.game.api.script.framework.listeners.events.SkillEvent;
import com.runemate.game.api.script.framework.tree.TreeBot;
import com.runemate.game.api.script.framework.tree.TreeTask;
import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.scene.Node;

import java.util.concurrent.TimeUnit;

public class BoneBurier extends TreeBot implements EmbeddableUI, SkillListener, InventoryListener
{
    public BonesInfo bonesInfo;
    public int bonesBuriedCount;
    public String currentTaskString = "Checking if near banker";
    public int expGained;
    public int levelsGained;
    public String bonesSelected;
    public boolean guiWait;
    public StopWatch stopWatch;

    private ObjectProperty<Node> botInterfaceProperty;
    private BoneBurierGui boneBurierGui;
    private BonesInfoUi bonesInfoUi;

    //Branch and leaf variables
    public int withdrawQuantity = 28;

    //Initialize Branches
    public InBankArea inBankArea = new InBankArea(this);
    public IsBankOpen isBankOpen = new IsBankOpen(this);
    public HaveBonesInInv haveBonesInInv = new HaveBonesInInv(this);
    public HaveBonesInBank haveBonesInBank = new HaveBonesInBank(this);

    //Initialize Leafs
    public OpenBank openBank = new OpenBank(this);
    public WithdrawBones withdrawBones = new WithdrawBones(this);
    public BuryBones buryBones = new BuryBones(this);
    public EmptyLeaf emptyLeaf = new EmptyLeaf(this);

    public BoneBurier ()
    {
        guiWait = true;
        setEmbeddableUI(this);
    }

    @Override
    public TreeTask createRootTask() {
        return new Root(this);
    }

    @Override
    public void onStart(String... arguments)
    {
        setLoopDelay(800, 1200);
        stopWatch = new StopWatch();
        stopWatch.start();
        Mouse.setSpeedMultiplier(0.55);
        getEventDispatcher().addListener(this);
    }


    @Override
    public ObjectProperty<? extends Node> botInterfaceProperty()
    {
        if (botInterfaceProperty == null)
        {
            botInterfaceProperty = new SimpleObjectProperty<>(boneBurierGui = new BoneBurierGui(this));
            bonesInfoUi = new BonesInfoUi(this);
        }
        return botInterfaceProperty;
    }

    @Override
    public void onItemRemoved(ItemEvent event)
    {
        if(event.getItem().toString().contains("Bones") && event.getQuantityChange() == 1)
        {
            bonesBuriedCount++;
        }
    }

    @Override
    public void onExperienceGained(SkillEvent event)
    {
        if(event.getSkill().toString().equals("Prayer"))
        {
            expGained += event.getChange();
        }
    }

    @Override
    public void onLevelUp(SkillEvent event)
    {
        if(event.getSkill().toString().equals("Prayer"))
        {
            levelsGained++;
        }
    }

    public void setToBonesInfoProperty()
    {
        botInterfaceProperty.set(bonesInfoUi);
    }

    public void updateInfo()
    {
        try
        {
            bonesInfo = new BonesInfo(
                    (int)CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), bonesBuriedCount),
                    (int)CommonMath.rate(TimeUnit.HOURS, stopWatch.getRuntime(), expGained),
                    levelsGained,
                    stopWatch.getRuntimeAsString(),
                    currentTaskString
            );
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        Platform.runLater(() -> bonesInfoUi.update());
    }
}
