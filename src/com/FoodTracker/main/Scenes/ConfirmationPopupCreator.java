package com.FoodTracker.main.Scenes;

import com.FoodTracker.main.Scenes.Controllers.ConfirmationPopupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by Justin on 10/18/2017.
 */
public class ConfirmationPopupCreator {

    private FXMLLoader loader;
    private ConfirmationPopupController controller;

    private boolean isConfirmed;

    public ConfirmationPopupCreator(String text) {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Confirm");
        loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/FoodTracker/main/Scenes/SceneRes/ConfirmationPopup.fxml"));
        try {
            stage.setScene(new Scene(loader.load()));
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Could not load popup!");
        }
        controller = loader.getController();
        controller.getText().setText(text);
        stage.showAndWait();
        isConfirmed = controller.isConfirmed();
    }


    public boolean isConfirmed() {
        return isConfirmed;
    }
}