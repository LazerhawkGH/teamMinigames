package maarsehumphries.minigames;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApp extends Application {
    
    public static int points = 0;

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/FXMLChronosAeon.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
        scene.getRoot().requestFocus();
    }

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
