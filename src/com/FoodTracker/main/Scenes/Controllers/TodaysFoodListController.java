package com.FoodTracker.main.Scenes.Controllers;

import com.FoodTracker.main.DateUtils.Day;
import com.FoodTracker.main.FileUtils.ResourceManager;
import com.FoodTracker.main.FileUtils.SceneController;
import com.FoodTracker.main.FoodUtils.Food;
import com.FoodTracker.main.Math.CalorieCounter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

public class TodaysFoodListController {

    @FXML
    private ListView foodList;
    @FXML
    private Button goToMain;
    @FXML
    private Text name, protein, carbs, fats, calories;

    private String foodSelected;

    private ResourceManager RM = ResourceManager.getInstance();
    private Day today = RM.getDays().get(RM.getToday());
    private HashMap<String, ArrayList<Object>> foodIndex = today.getFoodIndex();

    public void initialize() {
        ContextMenu menu = new ContextMenu();
        MenuItem delete = new MenuItem("Delete");
        delete.setOnAction(AE -> {
            today.setTimesEaten(foodSelected, today.getTimesEaten(foodSelected) - 1);
            foodList.getItems().remove(foodSelected);
            foodList.getSelectionModel().clearSelection();
            name.setText("");
            protein.setText("");
            carbs.setText("");
            fats.setText("");
            calories.setText("");
        });
        menu.getItems().add(delete);
        foodList.setContextMenu(menu);
        foodList.setOnContextMenuRequested(event -> {
                    if (foodSelected == null || foodSelected.equals("")) menu.hide();
                }
        );
        for (String foodName : foodIndex.keySet()) {
            for (int i = 0; i < today.getTimesEaten(foodName); i++) {
                foodList.getItems().add(today.getFood(foodName).getNAME());
            }
            foodList.setOnMouseClicked(ME -> {
                foodSelected = (String) foodList.getSelectionModel().getSelectedItem();
                name.setText(foodSelected);
                if (foodSelected != null) {
                    Food foodValue = today.getFood(foodSelected);
                    if (foodValue != null) {
                        protein.setText(String.valueOf(foodValue.getPROTIEN()));
                        carbs.setText(String.valueOf(foodValue.getCARBS()));
                        fats.setText(String.valueOf(foodValue.getFATS()));
                        calories.setText(String.valueOf(CalorieCounter.calories(foodValue.getPROTIEN(), foodValue.getCARBS(), foodValue.getFATS())));
                    }
                }
            });
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
