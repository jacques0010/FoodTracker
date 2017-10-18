package com.FoodTracker.main.FileUtils;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;

public class SceneController {
    /**
     * This class manages scenes by saving a scene fxml file location in a hashmap and referencing them with a user made key.
     **/
    private static HashMap<String, String> scenes = new HashMap<>();
    private static final String path = "/com/FoodTracker/main/Scenes/SceneRes/";

    private SceneController() {
    }

    public static void addScene(String key, String fileName) {
     scenes.put(key, path + fileName);
    }

    /**
     * loads a file using the file location in the hashmap.
     * For now, if the file cannot be loaded it will just close the program.
     */
     public static Scene getScene(String key) {
     Scene scene;
     try {
     scene = new Scene(FXMLLoader.load(SceneController.class.getResource(scenes.get(key))));
     } catch (NullPointerException NPE) {
     System.err.println("Could not load scene! Closing Program!");
     NPE.printStackTrace();
     scene = new Scene(new Group());
     Platform.exit();
     } catch (IOException IOE) {
     System.err.println("Could not load scene! Closing Program!");
     IOE.printStackTrace();
     scene = new Scene(new Group());
     Platform.exit();
     }
     return scene;
     }
    /*public static void addScene(String key, String fileName) {
        FXMLLoader fxmlLoader = new FXMLLoader();
        try {
            fxmlLoader.setLocation(SceneController.class.getResource(path + fileName));
            fxmlLoader.load();
            scenes.put(key, fxmlLoader);
        } catch (NullPointerException NPE) {
            System.err.println("Could not load scene! Closing Program!");
            NPE.printStackTrace();
            Platform.exit();
        } catch (IOException IOE) {
            System.err.println("Could not load scene! Closing Program!");
            IOE.printStackTrace();
            Platform.exit();
        }

    }

    public static FXMLLoader getScene(String key){
       return scenes.get(key);
    }
*/
}
