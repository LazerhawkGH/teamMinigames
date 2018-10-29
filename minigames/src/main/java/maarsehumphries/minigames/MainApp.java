/*
 * Name: Zachary Maarse & Shayne Humphries
 * Date: Oct 15, 2018
 * Purpose: Opens the starting scene, handles the global variables
 */

package maarsehumphries.minigames;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    
    public static int points = 0;
    public static Boolean music1 = false;

    @Override
    public void start(Stage stage) throws Exception {
        
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml")); 
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
        stage.setOnCloseRequest(e -> System.exit(0));
    }

    // Handles the global variables
    public static int getPoints() {
        return points;
    }
    public static void setPoints(int points) {
        MainApp.points = points;
    }
    
    
    public static void main(String[] args) {
        launch(args);
    }

}
