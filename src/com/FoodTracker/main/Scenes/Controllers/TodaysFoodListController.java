package com.FoodTracker.main.Scenes.Controllers;

import com.FoodTracker.main.DateUtils.Day;
import com.FoodTracker.main.FileUtils.ResourceManager;
import com.FoodTracker.main.FileUtils.SceneController;
import com.FoodTracker.main.FoodUtils.Food;
import com.FoodTracker.main.Math.CalorieCounter;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuItem;
import javafx.scene.input.ContextMenuEvent;
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
    private HashMap<String, Food> foodIndex = today.getFoodIndex();
    private ArrayList<Food> foods = today.getFoods();

    public void initialize() {
        for (Food food : foods) {
            ContextMenu menu = new ContextMenu();
            //fixme works but throws error
            menu.setOnShown(event -> {
                if (foodSelected == null || foodSelected.equals("")) menu.hide();
            });
            MenuItem delete = new MenuItem("Delete");
            delete.setOnAction(AE -> {
                int index = foods.indexOf(foodIndex.get(foodSelected));
                System.out.println(index);
                foods.remove(index);
                foodList.getItems().remove(foodSelected);
            });
            menu.getItems().add(delete);
            foodList.getItems().add(food.getNAME());
            foodList.setOnMouseClicked(ME -> {
                foodSelected = (String) foodList.getSelectionModel().getSelectedItem();
                name.setText(foodSelected);
                Food foodValue = foodIndex.get(foodSelected);
                if (foodValue != null) {
                    protein.setText(String.valueOf(foodValue.getPROTIEN()));
                    carbs.setText(String.valueOf(foodValue.getCARBS()));
                    fats.setText(String.valueOf(foodValue.getFATS()));
                    calories.setText(String.valueOf(CalorieCounter.calories(foodValue.getPROTIEN(), foodValue.getCARBS(), foodValue.getFATS())));
                }
            });
            foodList.setContextMenu(menu);
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
