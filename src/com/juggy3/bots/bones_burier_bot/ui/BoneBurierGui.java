package com.juggy3.bots.bones_burier_bot.ui;

import com.juggy3.bots.bones_burier_bot.BoneBurier;
import com.runemate.game.api.hybrid.util.Resources;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class BoneBurierGui extends Pane implements Initializable
{


    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        setVisible(true);
    }

    public BoneBurierGui(BoneBurier bot)
    {
        FXMLLoader loader = new FXMLLoader();
        Future<InputStream> stream = bot.getPlatform().invokeLater(() -> Resources.getAsStream("com/juggy3/bots/bones_burier_bot/ui/BoneBurier.fxml"));
        loader.setController(new BoneBurierUIController(bot));
        loader.setRoot(this);

        try
        {
            loader.load(stream.get());
        }
        catch (IOException | InterruptedException | ExecutionException e)
        {
            System.err.println("Error loading GUI");
            e.printStackTrace();
        }
    }
}
