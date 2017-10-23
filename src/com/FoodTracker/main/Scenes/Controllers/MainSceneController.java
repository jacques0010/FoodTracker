package com.FoodTracker.main.Scenes.Controllers;


import com.FoodTracker.main.DateUtils.Day;
import com.FoodTracker.main.FileUtils.ResourceManager;
import com.FoodTracker.main.FileUtils.SceneController;
import com.FoodTracker.main.Math.CalorieCounter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Scene controller for the main scene
 */
public class MainSceneController {
    private ResourceManager RM = ResourceManager.getInstance();
    private Day today = RM.getDays().get(RM.getToday());
    @FXML
    private
    Text proteinAmount;
    @FXML
    private
    Text carbAmount;
    @FXML
    private Text fatAmount;
    @FXML
    private
    Text proteinCalPercent;
    @FXML
    private
    Text carbCalPercent;
    @FXML
    private Text fatCalPercent;
    @FXML
    private
    Text totalCalories;
    @FXML
    private
    Text calFromProtein;
    @FXML
    private
    Text calFromCarbs;
    @FXML
    private Text calFromFat;
    @FXML
    private Button addFood, goToFoodsEaten;
    @FXML
    private TableView calender;

    @FXML
    TableColumn monday, tuesday, wednesday, thursday, friday, saturday, sunday;

    private float protein = today.getTotalProtein();
    private float carbs = today.getTotalCarbs();
    private float fats = today.getTotalFats();


    private float calories = CalorieCounter.calories(protein, carbs, fats);
    private float pCalories = CalorieCounter.pCalorlies(protein);
    private float cCalories = CalorieCounter.cCalorlies(carbs);
    private float fCalories = CalorieCounter.fCalorlies(fats);


    public void initialize() {


        addFood.getStyleClass().clear();
        addFood.getStyleClass().add("styled-button");

        goToFoodsEaten.getStyleClass().clear();
        goToFoodsEaten.getStyleClass().add("styled-button");

        proteinAmount.setText("Protein Consumed: " + protein + "g");
        carbAmount.setText("Carbs Consumed: " + carbs + "g");
        fatAmount.setText("Fats Consumed: " + fats + "g");

        totalCalories.setText("Total calories eaten today: " + (int) calories + "Kcal");
        calFromProtein.setText("Calories from protein: " + (int) pCalories);
        calFromCarbs.setText("Calories from carbs: " + (int) cCalories);
        calFromFat.setText("Calories from fats: " + (int) fCalories);

        if (calories != 0) {
            proteinCalPercent.setText("From protein: " +
                    String.format("%.2f", ((pCalories / calories) * 1E2)) + "%");
            carbCalPercent.setText("From carbs: " + String.format("%.2f", ((cCalories / calories) * 1E2)) + " %");
            fatCalPercent.setText("From fats: " + String.format("%.2f", ((fCalories / calories) * 1E2)) + " %");
        } else {
            proteinCalPercent.setText("Nothing eaten yet");
            carbCalPercent.setText("Nothing eaten yet");
            fatCalPercent.setText("Nothing eaten yet");
        }
    }


    public void mousePressed(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.getStyleClass().clear();
        button.getStyleClass().add("styled-button-pressed");
    }

    public void mouseReleased(MouseEvent mouseEvent) {
        Button button = (Button) mouseEvent.getSource();
        button.getStyleClass().clear();
        button.getStyleClass().add("styled-button");
    }

    public void gotoScene(ActionEvent actionEvent) {
        Button button = (Button) actionEvent.getSource();
        Stage stage = (Stage) button.getScene().getWindow();
        switch (button.getId()) {
            case "addFood":
                stage.setScene(SceneController.getScene("FoodLogger"));
                break;
            case "goToFoodsEaten":
                stage.setScene(SceneController.getScene("TodaysFoodList"));
                break;
        }
    }
}
