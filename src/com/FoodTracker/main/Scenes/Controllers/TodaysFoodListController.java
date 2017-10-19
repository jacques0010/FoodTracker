package com.FoodTracker.main.Scenes.Controllers;

import com.FoodTracker.main.DateUtils.Day;
import com.FoodTracker.main.FileUtils.ResourceManager;
import com.FoodTracker.main.FileUtils.SceneController;
import com.FoodTracker.main.FoodUtils.Food;
import javafx.collections.ListChangeListener;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import javax.swing.event.ChangeListener;
import java.util.ArrayList;

public class TodaysFoodListController {

    @FXML
    private ListView foodList;
    @FXML
    private Button goToMain;

    private Food foodSelected;

    private ResourceManager RM = ResourceManager.getInstance();
    private Day today = RM.getDays().get(RM.getToday());
    private ArrayList<Food> foods = today.getFoods();
    public void initialize(){
        for (Food food: foods) {
            foodList.getItems().add(food.getNAME());
            }
        goToMain.getStyleClass().clear();
        goToMain.getStyleClass().add("styled-button");
        }

    public void mouseEntered(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.getStyleClass().clear();
        button.getStyleClass().add("styled-button-pressed");
    }

    public void mouseExited(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.getStyleClass().clear();
        button.getStyleClass().add("styled-button");
    }

    public void goToScene(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(SceneController.getScene("Main"));
    }
}
