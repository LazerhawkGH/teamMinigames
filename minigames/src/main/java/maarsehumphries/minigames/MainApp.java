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
    
    
    // Each global variable created
    public static int points = 0;
    public static boolean boughtBullet = false;
    public static boolean boughtScore = false;
    public static boolean boughtObstacle = false;
    public static boolean boughtObjective = false;

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
    
    public static Boolean getBulletUpgrade() {
        return boughtBullet;
    }
    public static void setBulletUpgrade(boolean boughtBullet) {
        MainApp.boughtBullet = boughtBullet;
    }
    
    public static Boolean getScoreUpgrade() {
        return boughtScore;
    }
    public static void setScoreUpgrade(boolean boughtScore) {
        MainApp.boughtScore = boughtScore;
    }
    
    public static Boolean getObstacleUpgrade() {
        return boughtObstacle;
    }
    public static void setObstacleUpgrade(boolean boughtObstacle) {
        MainApp.boughtObstacle = boughtObstacle;
    }
    
    public static Boolean getObjectiveUpgrade() {
        return boughtObjective;
    }
    public static void setObjectiveUpgrade(boolean boughtObjective) {
        MainApp.boughtObjective = boughtObjective;
    }
    
    public static void main(String[] args) {
        launch(args);
    }

}
