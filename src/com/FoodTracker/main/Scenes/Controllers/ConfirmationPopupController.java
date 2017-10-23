package com.FoodTracker.main.Scenes.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class ConfirmationPopupController {
    @FXML
    private
    Text text;

    private boolean isConfirmed;

    public Text getText(){
        return text;
    }

    public boolean isConfirmed(){
        return isConfirmed;
    }

    public void confirm(ActionEvent actionEvent) {
        Button button = (Button)  actionEvent.getSource();
        if (button.getId().equals("trueButton")) {
            isConfirmed = true;
        }else isConfirmed = false;
        Stage stage = (Stage) button.getScene().getWindow();
        stage.close();
    }
}
