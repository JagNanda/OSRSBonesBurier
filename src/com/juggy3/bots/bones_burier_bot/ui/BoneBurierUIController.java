package com.juggy3.bots.bones_burier_bot.ui;

import com.juggy3.bots.bones_burier_bot.BoneBurier;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

import java.net.URL;
import java.util.ResourceBundle;

public class BoneBurierUIController implements Initializable
{

    private BoneBurier bot;

    @FXML
    Button btnStart;

    @FXML
    ComboBox selectBones;

    public BoneBurierUIController(BoneBurier bot)
    {
        this.bot = bot;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources)
    {
        selectBones.getItems().addAll("Babydragon bones", "Big bones", "Bones", "Dagannoth bones", "Dragon bones", "Drake bones", "Hydra bones", "Jogre bones", "Lava dragon bones", "Ourg bones", "Shaikahan bones", "Superior dragon bones", "Wyrm bones");
        selectBones.getSelectionModel().select("Bones");
        btnStart.setOnAction(btnStartAction());
    }

    public EventHandler<ActionEvent> btnStartAction()
    {
        return e -> {
            bot.bonesSelected = selectBones.getSelectionModel().getSelectedItem().toString();
            bot.guiWait = false;
            Platform.runLater(() -> bot.setToBonesInfoProperty());
        };
    }
}
