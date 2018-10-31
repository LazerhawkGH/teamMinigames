/*
 * Name: Zachary Maarse
 * Date Started: Oct 15, 2018
 * Date Finished: Oct 31, 2018
 * Purpose: Allows the user to user to use their earned points to buy various things
 */
package maarsehumphries.minigames;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import static maarsehumphries.minigames.MainApp.*;

/**
 *
 * @author zacha
 */
public class FXMLShopController implements Initializable {

    @FXML private Label lblPoints;
    @FXML private Button btnBullet;
    @FXML private Button btnScore;
    @FXML private Button btnObstacle;
    @FXML private Button btnObjective;
    
    MediaPlayer player;

    @FXML
    private void back(ActionEvent event) throws IOException {
        Parent home_page_parent = FXMLLoader.load(getClass().getResource("/fxml/Scene.fxml"));
        Scene home_page_scene = new Scene(home_page_parent);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
        stage.setScene(home_page_scene);
        stage.setTitle("Main Menu");
        stage.show();
        home_page_scene.getRoot().requestFocus();
        stage.setOnCloseRequest(e -> System.exit(0));
        player.stop();
    }
    
    @FXML
    private void purchase(ActionEvent e) {
        if (btnBullet.isArmed()) {
            if (getPoints() >= 1000) {
                setPoints(getPoints() - 1000);
                lblPoints.setText("" + getPoints());
                boughtBullet = true;
                btnBullet.setDisable(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Not enough points");
                alert.showAndWait();
            }

        }
        if (btnScore.isArmed()) {
            if (getPoints() >= 5000) {
                setPoints(getPoints() - 5000);
                lblPoints.setText("" + getPoints());
                boughtScore = true;
                btnScore.setDisable(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Not enough points");
                alert.showAndWait();
            }
        }
        if (btnObstacle.isArmed()) {
            if (getPoints() >= 1000) {
                setPoints(getPoints() - 1000);
                lblPoints.setText("" + getPoints());
                boughtObstacle = true;
                btnObstacle.setDisable(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Not enough points");
                alert.showAndWait();
            }
        }
        if (btnObjective.isArmed()) {
            if (getPoints() >= 1000) {
                setPoints(getPoints() - 1000);
                lblPoints.setText("" + getPoints());
                boughtObjective = true;
                btnObjective.setDisable(true);
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Not enough points");
                alert.showAndWait();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        player = new MediaPlayer((new Media(getClass().getResource("/BadfingerComeAndGetIt.mp3").toString())));
        player.play();
        setPoints(getPoints());   // Obtains the amount of points the user has, sets a label box to that amount
        lblPoints.setText("" + getPoints());
        
        setBulletUpgrade(getBulletUpgrade());
        setScoreUpgrade(getScoreUpgrade());
        setObstacleUpgrade(getObstacleUpgrade());
        setObjectiveUpgrade(getObjectiveUpgrade());
        
        if (boughtBullet){
            btnBullet.setDisable(true);
        }
        if (boughtScore){
            btnScore.setDisable(true);
        }
        if (boughtObstacle){
            btnObstacle.setDisable(true);
        }
        if (boughtObjective){
            btnObjective.setDisable(true);
        }
        
    }
}
