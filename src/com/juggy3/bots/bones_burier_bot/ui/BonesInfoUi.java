package com.juggy3.bots.bones_burier_bot.ui;

import com.juggy3.bots.bones_burier_bot.BoneBurier;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BonesInfoUi extends Pane implements Initializable
{
    private BoneBurier bot;

    public BonesInfoUi(BoneBurier bot)
    {
        this.bot = bot;
        FXMLLoader loader = new FXMLLoader();
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/juggy3/bots/bones_burier_bot/ui/BonesInfo.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        try
        {
            loader.load(stream.get());
        }
        catch (IOException | InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setVisible(true);
    }

    @FXML
    Label lblBonesBuried, lblBonesPerHour, lblXpPerHour, lblRunTime, lblCurrentTask, lblXpGained, lblPrayerLevelsGained;

    public void update()
    {
        try
        {
            BonesInfo info = bot.bonesInfo;
            lblBonesBuried.setText("" + bot.bonesBuriedCount);
            lblXpGained.setText("" + bot.expGained);
            lblBonesPerHour.setText("" + info.bonesPerHour);
            lblXpPerHour.setText("" + info.xpPerHour);
            lblRunTime.setText("" + info.timeRunning);
            lblCurrentTask.setText("" + info.currentTask);
            lblPrayerLevelsGained.setText("" + bot.levelsGained);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }


}
