package com.FoodTracker.main.Scenes.Controllers;

import com.FoodTracker.main.DateUtils.Day;
import com.FoodTracker.main.FileUtils.ResourceManager;
import com.FoodTracker.main.FileUtils.SceneController;
import com.FoodTracker.main.FoodUtils.Food;
import com.FoodTracker.main.Scenes.ConfirmationPopupCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

/**
 * Controller for the UI that adds a new food for the day.
 * It also checks to see if the food is in it's list of foods and if it isn't creates asks the user if they want to add it to there list.
 * This class needs to be rethought with better event handling and maybe an event bus if more controllers are added in future.
 */

public class FoodLoggerController extends TreeCell<String> implements Initializable {

    //todo rethink popup control
    private ResourceManager RM = ResourceManager.getInstance();
    private Day today = RM.getDays().get(RM.getToday());
    private HashMap<String, Food> foods = RM.getFoods();

    @FXML
    private
    TextField foodName;
    @FXML
    private
    TextField proteinAmount;
    @FXML
    private
    TextField carbAmount;
    @FXML
    private
    TextField fatAmount;

    @FXML
    private
    TreeView<String> foodList;

    private String name;

    private float protein;
    private float carbs;
    private float fats;

    private Food foodSelected;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        updateTreeView();
        if (foodList != null) {
            MultipleSelectionModel<TreeItem<String>> model = foodList.getSelectionModel();
            model.selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                        foodSelected = RM.getFoods().get(newValue.getValue());
                        if (foodSelected != null) {
                            System.out.println(foodSelected.getNAME());
                        }
                    }
            );
        }
    }


    private void updateTreeView() {
        TreeItem<String> root = new TreeItem<>("Foods");
        root.setExpanded(true);
        for (String name : foods.keySet()) {
            root.getChildren().add(new TreeItem<>(name));
        }
        if (foodList != null) {
            foodList.setRoot(root);
        }
    }

    public void logFood(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (!foodName.getText().equals("")
                && varifyFloat(proteinAmount.getText())
                && varifyFloat(carbAmount.getText())
                && varifyFloat(fatAmount.getText())) {
            name = foodName.getText();
            System.out.println(name);
            protein = Float.parseFloat(proteinAmount.getText());
            carbs = Float.parseFloat(carbAmount.getText());
            fats = Float.parseFloat(fatAmount.getText());

            if (!foods.containsKey(name)) {
                boolean result;
                ConfirmationPopupCreator popup = new ConfirmationPopupCreator("This food is not on the list of foods! \n" +
                        "Would you like to add it?");
                result = popup.isConfirmed();
                if (result) {
                    RM.getFoods().put(name, new Food(name, protein, carbs, fats));
                    updateTreeView();
                    today.addFood(RM.getFoods().get(name));
                } else today.addFood(name, protein, carbs, fats);
            }
            RM.getDays().put(RM.getToday(), today);
        } else {
            today.addFood(foodSelected);
            RM.getDays().put(RM.getToday(), today);
        }
    }

    private boolean varifyFloat(String input) {
        try {
            Float.parseFloat(input);
        } catch (NumberFormatException NFE) {
            System.err.println("Not a number");
            return false;
        }
        return true;
    }

    public void updateScene(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        if (button.getId().equals("update")) {
            stage.setScene(SceneController.getScene("FoodLogger"));
        } else stage.setScene(SceneController.getScene("Main"));
    }
}
