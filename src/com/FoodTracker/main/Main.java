package com.FoodTracker.main;

import com.FoodTracker.main.DateUtils.Day;
import com.FoodTracker.main.FileUtils.ResourceManager;
import com.FoodTracker.main.FileUtils.SceneController;
import com.FoodTracker.main.FileUtils.LoadFile;
import javafx.application.Application;
import javafx.stage.Stage;


public class Main extends Application {
    private LoadFile LF = LoadFile.getInstance();
    private ResourceManager RM = ResourceManager.getInstance();

    @Override
    public void init() {
        if (!RM.getDays().containsKey(RM.getToday())) {
            RM.getDays().put(RM.getToday(), new Day());
        }
        //get a reference to scenes
        //todo save scene references to file
        SceneController.addScene("Main", "MainScene.fxml");
        SceneController.addScene("FoodLogger", "FoodLogger.fxml");
        SceneController.addScene("ConfirmationPopup", "ConfirmationPopup.fxml");
        SceneController.addScene("TodaysFoodList" , "TodaysFoodList.fxml");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Set Primary stage and load main screen
        primaryStage.setTitle("Calorie Counter");
        primaryStage.setScene(SceneController.getScene("Main"));
        primaryStage.show();
    }

    @Override
    public void stop() {
        //Save data gathered during runtime
        LF.save(RM.getDays(), "Dates.ser");
        LF.save(RM.getFoods(), "Foods.ser");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
