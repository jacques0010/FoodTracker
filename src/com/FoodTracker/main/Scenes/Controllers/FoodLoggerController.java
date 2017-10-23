package com.FoodTracker.main.Scenes.Controllers;

import com.FoodTracker.main.DateUtils.Day;
import com.FoodTracker.main.FileUtils.ResourceManager;
import com.FoodTracker.main.FileUtils.SceneController;
import com.FoodTracker.main.FoodUtils.Food;
import com.FoodTracker.main.Scenes.CustomDialogs.ConfirmationPopupCreator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.HashMap;

/**
 * Controller for the UI that adds a new food for the day.
 * It also checks to see if the food is in it's list of foods and if it isn't creates asks the user if they want to add it to there list.
 * This class needs to be rethought with better event handling and maybe an event bus if more controllers are added in future.
 */

public class FoodLoggerController extends TreeCell<String> {

    //todo create a form of feedback for the submit button
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

    @FXML private Button logFood;
    @FXML private Button goToMain;

    private String name;

    private float protein;
    private float carbs;
    private float fats;

    private Food foodSelected;

    /**
     * Creates the a treeview based off the list of saved foods and adds a change listener to the treeview.
     * Any styled components are set here as well.
     */
    public void initialize() {
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
        logFood.getStyleClass().clear();
        logFood.getStyleClass().add("styled-button");
        goToMain.getStyleClass().clear();
        goToMain.getStyleClass().add("styled-button");
    }

    /**
     * Updates the treeview to the latest list of foods.
     */

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

    /**
     * Gets a action from a javafx button and checks if there is a food selected from the tree or if there is a custom <p>
     * food that has been created and logs it to the list of foods eaten that day. If the food is not on the saved list <p>
     * then the food will be added to the food list and the treeview updated.
     * @param actionEvent
     */
    public void logFood(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        if (!foodName.getText().equals("")
                && verifyFloat(proteinAmount.getText())
                && verifyFloat(carbAmount.getText())
                && verifyFloat(fatAmount.getText())) {
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
                RM.getDays().put(RM.getToday(), today);
            }
        } else if(foodSelected != null) {
            today.addFood(foodSelected);
            RM.getDays().put(RM.getToday(), today);
        }
    }

    /**
     * Verifies that the string can be made into a float.
     * @param input
     * @return True if the value can be parsed to a float
     */
    private boolean verifyFloat(String input) {
        try {
            Float.parseFloat(input);
        } catch (NumberFormatException NFE) {
            System.err.println("Not a number");
            return false;
        }
        return true;
    }
    //todo rename
    public void updateScene(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        if (button.getId().equals("update")) {
            stage.setScene(SceneController.getScene("FoodLogger"));
        } else stage.setScene(SceneController.getScene("Main"));
    }

    /**
     * Sets style for a button if the mouse is over the button.
     * @param mouseEvent
     */
    public void mouseOver(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.getStyleClass().clear();
        button.getStyleClass().add("styled-button-pressed");
    }

    /**
     * Sets style for a button if the mouse is not over the button.
     * @param mouseEvent
     */
    public void mouseExit(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.getStyleClass().clear();
        button.getStyleClass().add("styled-button");
    }
}
